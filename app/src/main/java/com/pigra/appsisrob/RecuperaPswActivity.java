package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import javax.annotation.Nullable;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperaPswActivity extends AppCompatActivity {

    Button btnRecuperar;
    TextView lblRecuperaPsw;
    EditText txtCorreoUsu, txtDni;
    String  correo;
    String clave;
    String mensaje, asunto,correoReceptor;
    Session session;
    Boolean validar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_psw);
        validar = true;
        asignarReferencias();
    }


    private void asignarReferencias(){

        btnRecuperar=findViewById(R.id.btnRecuperar);
        lblRecuperaPsw=findViewById(R.id.lblRecuperaT2);
        txtCorreoUsu = findViewById(R.id.txtCorreoUsu);
        txtDni = findViewById(R.id.txtDni);

        //Datos de correo a enviar

        correo = "managersisrob@gmail.com";
        clave = "1234K567";
        asunto = "Recupera tu contrase??a SISROB";
        mensaje = "El Administrador de Sistema,le enviara un correo electronico en un plazo no mayor de 24 horas, con su nueva " +
                 " contrase??a autogenerada.";

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validar())
                    actualizaPassword();

            }
        });

        lblRecuperaPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RecuperaPswActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void actualizaPassword() {

        String url = "http://pruebaupc.atwebpages.com/index.php/PASSWORD";
        Random rnd = new Random();
        int maximo = 25;
        int contrarnd = rnd.nextInt(maximo) ;

        StringRequest peticion = new StringRequest(Request.Method.POST, url,
                response -> {
            enviarMail(contrarnd + "");
            },
                error -> Log.d("==>",error.toString()))
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("dni", txtDni.getText().toString());
                param.put("pwd", contrarnd + "" );
                return param;
            }
        };

        RequestQueue cola = Volley.newRequestQueue(this );
        cola.add(peticion);
    }
    private boolean validar(){
        boolean retorno = true;
        if (txtCorreoUsu.getText().toString().equals("")) {
            txtCorreoUsu.setError("Correo Electronico Obligatorio");
            retorno = false;
        }
        else
        {
            correoReceptor = txtCorreoUsu.getText().toString();
            if(isValidEmaillId(correoReceptor.trim()))
            {
                retorno = true;
            }
            else
            {
                txtCorreoUsu.setError("Correo Electronico Invalido");
                //Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                retorno = false;
                txtCorreoUsu.setText("");
            }
        }

        if( txtDni.getText().toString().equals("") )
        {
            txtDni.setError("Ingrese DNI");
            retorno = false;
        }
        return retorno;
    }
    private void enviarMail(String nuevaContra) {

        mensaje = "Su nueva contrase??a es autogenerada es " + nuevaContra +  " Atte. Sistema Robocom. ";

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");

            session = javax.mail.Session.getDefaultInstance(propiedades, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo, clave);
                }
            });


            try {

                // Inicializar contenido de email
                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(correo));
                // contenido email
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(correoReceptor));

                message.setSubject(asunto);
                message.setText(mensaje);

                // enviar email
                new SendMail().execute(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }

    }

    private class SendMail extends AsyncTask<Message,String,String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RecuperaPswActivity.this
                    ,"Espere por Favor","Enviando Correo Electronioo", true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Success")){


                AlertDialog.Builder builder = new AlertDialog.Builder(RecuperaPswActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324> Success </font>'"));
                builder.setMessage("Proceso Culminado Exitosamente");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent( RecuperaPswActivity.this,ValRecupPswActivity.class);
                        startActivity(intent);

                    }
                });
                builder.show();

            }else{

                Toast.makeText(getApplicationContext(),
                        "Revisar Informacion Email no valido",Toast.LENGTH_SHORT).show();
                         txtCorreoUsu.setText("");
            }
        }
    }

    private boolean isValidEmaillId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


}