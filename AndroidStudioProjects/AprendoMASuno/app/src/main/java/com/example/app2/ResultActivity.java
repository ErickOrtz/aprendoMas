package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ResultActivity extends AppCompatActivity {

    MaterialCardView home;
    TextView correctt,wrongt,resultInfo,resultScore;
    ImageView resultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        home = findViewById(R.id.returnHome);
        correctt = findViewById(R.id.correctScore);
        wrongt = findViewById(R.id.wrongScore);
        resultInfo = findViewById(R.id.resultInfo);
        resultScore = findViewById(R.id.resultScore);
        resultImage = findViewById(R.id.resultimage);

        int correct = getIntent().getIntExtra("correct", 0);
        int wrong = getIntent().getIntExtra("wrong", 0);
        int score = correct*10;

        correctt.setText(""+correct);
        wrongt.setText(""+wrong);
        resultScore.setText("Score: "+score);

        if (score>=0 && score <=20){
            resultInfo.setText("Deberias intentar el test una vez mas!");
        } else if (score>=30 && score <=50) {
            resultInfo.setText("Vamos tu puedes!");
        } else if (score>=6 && score <=80) {
            resultInfo.setText("Muy bien!");
        }
        else resultInfo.setText("Perfectisimo!");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ResultActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Deseas regresar al menu principal?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(ResultActivity.this, MainActivity.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }
}