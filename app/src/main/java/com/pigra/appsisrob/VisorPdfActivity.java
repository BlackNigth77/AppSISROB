package com.pigra.appsisrob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

public class VisorPdfActivity extends AppCompatActivity {

    ProgressBar progressBar;
    PDFView pdfView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf);
        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);

        String urlPdf = "http://hts-mining.com/wp-content/uploads/2015/04/Alpha-20-TDS-EN-100063006-1.pdf";
        new RecibirPdfStrem(pdfView,progressBar).execute(urlPdf);

    }
}