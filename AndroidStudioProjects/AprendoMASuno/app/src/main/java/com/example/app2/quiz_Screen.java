package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class quiz_Screen extends AppCompatActivity {

    MaterialCardView cardMensaje;
    TextView quizText,aAns,bAns,cAns,dAns,TextMensajeSuperior,TextMensajeInferiori;
    List<QuestionsItem> questionsItems;
    int currentQuestions = 0;
    int correct = 0, wrong = 0;
    Intent intent;
    Button botonNext;
    LinearLayout LayoutSup,LayoutInf,LayoutBoton;
    private int indice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        TextMensajeSuperior = findViewById(R.id.TextMensajeSuperior);
        TextMensajeInferiori = findViewById(R.id.TextMensajeInferior);

        botonNext = findViewById(R.id.BotonSiguiente);

        LayoutSup = findViewById(R.id.LinearMensajeSuperior);
        LayoutInf = findViewById(R.id.LinearMensajeInferior);
        //LayoutBoton = findViewById(R.id.LayoutBoton);


        quizText = findViewById(R.id.quizText);
        aAns = findViewById(R.id.aAnswer);
        bAns = findViewById(R.id.bAnswer);
        cAns = findViewById(R.id.cAnswer);
        dAns = findViewById(R.id.dAnswer);

        cardMensaje = findViewById(R.id.cardMensaje);

        CargarEstilo();

        loadAllQuestions();
        Collections.shuffle(questionsItems);
        setQuestionScreen(currentQuestions);

        aAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionsItems.get(currentQuestions).getAnswer1().equals(questionsItems.get(currentQuestions).getCorrect())){
                    correct++;
                    aAns.setBackgroundResource(R.color.colorGreen);
                    aAns.setTextColor(getResources().getColor(R.color.blanco));
                }else{
                    wrong++;
                    aAns.setBackgroundResource(R.color.colorRed);
                    aAns.setTextColor(getResources().getColor(R.color.blanco));
                }

                if(currentQuestions<questionsItems.size()-1){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            aAns.setBackgroundResource(R.color.blanco);
                            aAns.setTextColor(getResources().getColor(R.color.gris_oscuro));
                        }
                    }, 500);
                }else {
                    Intent intent1 = new Intent(quiz_Screen.this, ResultActivity.class);
                    intent1.putExtra("correct",correct);
                    intent1.putExtra("wrong",wrong);
                    startActivity(intent1);
                    finish();
                }
            }
        });

        bAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionsItems.get(currentQuestions).getAnswer2().equals(questionsItems.get(currentQuestions).getCorrect())){
                    correct++;
                    bAns.setBackgroundResource(R.color.colorGreen);
                    bAns.setTextColor(getResources().getColor(R.color.blanco));
                }else{
                    wrong++;
                    bAns.setBackgroundResource(R.color.colorRed);
                    bAns.setTextColor(getResources().getColor(R.color.blanco));
                }

                if(currentQuestions<questionsItems.size()-1){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            bAns.setBackgroundResource(R.color.blanco);
                            bAns.setTextColor(getResources().getColor(R.color.gris_oscuro));
                        }
                    }, 500);
                }else {
                    Intent intent1 = new Intent(quiz_Screen.this, ResultActivity.class);
                    intent1.putExtra("correct",correct);
                    intent1.putExtra("wrong",wrong);
                    startActivity(intent1);
                    finish();
                }
            }
        });

        cAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionsItems.get(currentQuestions).getAnswer3().equals(questionsItems.get(currentQuestions).getCorrect())){
                    correct++;
                    cAns.setBackgroundResource(R.color.colorGreen);
                    cAns.setTextColor(getResources().getColor(R.color.blanco));
                }else{
                    wrong++;
                    cAns.setBackgroundResource(R.color.colorRed);
                    cAns.setTextColor(getResources().getColor(R.color.blanco));
                }

                if(currentQuestions<questionsItems.size()-1){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            cAns.setBackgroundResource(R.color.blanco);
                            cAns.setTextColor(getResources().getColor(R.color.gris_oscuro));
                        }
                    }, 500);
                }else {
                    Intent intent1 = new Intent(quiz_Screen.this, ResultActivity.class);
                    intent1.putExtra("correct",correct);
                    intent1.putExtra("wrong",wrong);
                    startActivity(intent1);
                    finish();
                }
            }
        });

        dAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionsItems.get(currentQuestions).getAnswer4().equals(questionsItems.get(currentQuestions).getCorrect())){
                    correct++;
                    dAns.setBackgroundResource(R.color.colorGreen);
                    dAns.setTextColor(getResources().getColor(R.color.blanco));
                }else{
                    wrong++;
                    dAns.setBackgroundResource(R.color.colorRed);
                    dAns.setTextColor(getResources().getColor(R.color.blanco));
                }

                if(currentQuestions<questionsItems.size()-1){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            dAns.setBackgroundResource(R.color.blanco);
                            dAns.setTextColor(getResources().getColor(R.color.gris_oscuro));
                        }
                    }, 500);
                }else {
                    Intent intent1 = new Intent(quiz_Screen.this, ResultActivity.class);
                    intent1.putExtra("correct",correct);
                    intent1.putExtra("wrong",wrong);
                    startActivity(intent1);
                    finish();
                }
            }
        });


    }

    private void CargarEstilo() {

        TextView textView = findViewById(R.id.textViewTitulo);

        try {
            InputStream inputStream = getAssets().open("styleMateries.json.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // Convertir el contenido a una cadena
            String json = new String(buffer, "UTF-8");

            // Parsear el JSON
            JSONObject jsonObject = new JSONObject(json);

            // Extraer el primer valor de cada categoría
            JSONArray mathematics = jsonObject.getJSONArray("matematics");

            // Puedes elegir cuál usar. Aquí seleccionamos "mathematics" como ejemplo
            JSONObject math = mathematics.getJSONObject(indice);

            // Configurar el TextView con el Title y el color de fondo
            String title = math.getString("Title");
            String background = math.getString("background");

            // Establecer el texto
            textView.setText(title);

            // Establecer el fondo
            textView.setBackgroundColor(getColorFromString(background));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Método para convertir el nombre del color a su valor hex
    private int getColorFromString(String colorName) {
        switch (colorName) {
            case "@color/verde_lima":
                return getResources().getColor(R.color.verde_lima);
            case "@color/azul_celeste":
                return getResources().getColor(R.color.azul_celeste);
            case "@color/amarillo_brillante":
                return getResources().getColor(R.color.amarillo_brillante);
            case "@color/morado_claro":
                return getResources().getColor(R.color.morado_claro);
            default:
                return getResources().getColor(R.color.blanco);
        }
    }

    private void setQuestionScreen(int currentQuestions) {
        quizText.setText(questionsItems.get(currentQuestions).getQuestions());
        aAns.setText(questionsItems.get(currentQuestions).getAnswer1());
        bAns.setText(questionsItems.get(currentQuestions).getAnswer2());
        cAns.setText(questionsItems.get(currentQuestions).getAnswer3());
        dAns.setText(questionsItems.get(currentQuestions).getAnswer4());
    }

    private void loadAllQuestions() {
        questionsItems = new ArrayList<>();
        String jsonquiz = loadJsonFromAsset("matematicsQuestions.json");
        try {
            JSONObject jsonObject = new JSONObject(jsonquiz);
            JSONArray questions = jsonObject.getJSONArray("matQuestions");
            for (int i = 0; i < questions.length();i++){
                JSONObject question = questions.getJSONObject(i);

                String questionsString = question.getString("questions");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");

                questionsItems.add(new QuestionsItem(questionsString, answer1String, answer2String, answer3String, answer4String, correctString));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String loadJsonFromAsset(String s) {
        String json = "";
        try {
            InputStream inputStream = getAssets().open(s);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onBackPressed(){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(quiz_Screen.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("Are you sure want to exit the quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(quiz_Screen.this, MainActivity.class));
                finish();
            }
        });
        materialAlertDialogBuilder.show();
    }
}