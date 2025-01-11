package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    MaterialCardView matematics,spanish,history,planets;

    ImageButton bookMat,bookSpan,bookHist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matematics=findViewById(R.id.matematicsCard);
        spanish=findViewById(R.id.spanishCard);
        history=findViewById(R.id.historyCard);
        planets=findViewById(R.id.planetsCard);

        bookMat = findViewById(R.id.bookButtonMat);
        bookSpan = findViewById(R.id.bookButtonSapanish);
        bookHist = findViewById(R.id.bookButtonHist);

        bookMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoMat.class));
                finish();
            }
        });

        bookSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoEsp.class));
                finish();
            }
        });

        bookHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoHist.class));
                finish();
            }
        });

    }


    @Override
    public void onBackPressed(){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Seguro que deseas salir de la app?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        materialAlertDialogBuilder.show();
    }

    public void iniciarMat(View view){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Seguro que quieres iniciar el quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(MainActivity.this, MatClass.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }

    public void iniciarEsp(View view){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Seguro que quieres iniciar el quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(MainActivity.this, EspClass.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }

    public void iniciarHist(View view){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Seguro que quieres iniciar el quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(MainActivity.this, HistClass.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }

    public void iniciarPlanets(View view){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Seguro que quieres iniciar el quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }

}