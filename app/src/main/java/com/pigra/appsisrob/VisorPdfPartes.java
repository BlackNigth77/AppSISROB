package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

public class VisorPdfPartes extends AppCompatActivity {


    ProgressBar progressBar;
    PDFView pdfView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf_partes);

        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);

        String urlPdf = "https://www.hatz-diesel.com/fileadmin/user_upload/hatz-diesel.com/download/Dokumentationen/1609_Taschenkarte_70030727_alteM.pdf";
        new RecibirPdfStrem(pdfView,progressBar).execute(urlPdf);

    }
}