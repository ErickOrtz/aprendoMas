package com.example.app2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class MatClass extends AppCompatActivity {

    MaterialCardView cardMensaje;
    TextView quizText, aAns, bAns, cAns, dAns, TextMensajeSuperior, TextMensajeInferiori;
    List<QuestionsItem> questionsItems;
    int currentQuestions = 0;
    int correct = 0, wrong = 0;
    Button botonNext;
    LinearLayout LayoutSup, LayoutInf, LayoutBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        CargarEstilo();

        TextMensajeSuperior = findViewById(R.id.TextMensajeSuperior);
        TextMensajeInferiori = findViewById(R.id.TextMensajeInferior);

        botonNext = findViewById(R.id.BotonSiguiente);
        LayoutSup = findViewById(R.id.LinearMensajeSuperior);
        LayoutInf = findViewById(R.id.LinearMensajeInferior);
        //LayoutBoton = findViewById(R.id.LayoutBoton);
        cardMensaje = findViewById(R.id.cardMensaje);
        quizText = findViewById(R.id.quizText);
        aAns = findViewById(R.id.aAnswer);
        bAns = findViewById(R.id.bAnswer);
        cAns = findViewById(R.id.cAnswer);
        dAns = findViewById(R.id.dAnswer);

        loadAllQuestions();
        Collections.shuffle(questionsItems);
        setQuestionScreen(currentQuestions);

        View.OnClickListener answerClickListener = v -> {
            String selectedAnswer = ((TextView) v).getText().toString();
            boolean isCorrect = selectedAnswer.equals(questionsItems.get(currentQuestions).getCorrect());

            // Mostrar tarjeta con mensaje
            showResultCard(isCorrect);
            if (isCorrect){
                aAns.setEnabled(false);
                bAns.setEnabled(false);
                cAns.setEnabled(false);
                dAns.setEnabled(false);
                correct++;
                ((TextView) v).setTextColor(getColor(R.color.white));
                v.setBackgroundColor(getColor(R.color.green_primary));
            }
            else {
                aAns.setEnabled(false);
                bAns.setEnabled(false);
                cAns.setEnabled(false);
                dAns.setEnabled(false);
                v.setBackgroundColor(getColor(R.color.red_intense));
                ((TextView) v).setTextColor(getColor(R.color.white));
                wrong++;
            }
        };

        aAns.setOnClickListener(answerClickListener);
        bAns.setOnClickListener(answerClickListener);
        cAns.setOnClickListener(answerClickListener);
        dAns.setOnClickListener(answerClickListener);

        botonNext.setOnClickListener(v -> {
            cardMensaje.setVisibility(View.GONE);
            if (currentQuestions < questionsItems.size() - 1) {
                currentQuestions++;
                setQuestionScreen(currentQuestions);
            } else {
                Intent intent1 = new Intent(MatClass.this, ResultActivity.class);
                intent1.putExtra("correct", correct);
                intent1.putExtra("wrong", wrong);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void setQuestionScreen(int currentQuestions) {
        aAns.setEnabled(true);
        bAns.setEnabled(true);
        cAns.setEnabled(true);
        dAns.setEnabled(true);
        quizText.setText(questionsItems.get(currentQuestions).getQuestions());
        aAns.setText(questionsItems.get(currentQuestions).getAnswer1());
        bAns.setText(questionsItems.get(currentQuestions).getAnswer2());
        cAns.setText(questionsItems.get(currentQuestions).getAnswer3());
        dAns.setText(questionsItems.get(currentQuestions).getAnswer4());
        resetAnswerColors();
    }

    private void resetAnswerColors() {
        int defaultColor = getResources().getColor(R.color.blanco);
        int textColor = getResources().getColor(R.color.gris_oscuro);

        aAns.setBackgroundResource(R.color.blanco);
        bAns.setBackgroundResource(R.color.blanco);
        cAns.setBackgroundResource(R.color.blanco);
        dAns.setBackgroundResource(R.color.blanco);

        aAns.setTextColor(textColor);
        bAns.setTextColor(textColor);
        cAns.setTextColor(textColor);
        dAns.setTextColor(textColor);
    }

 //   private void showResultCard(boolean isCorrect) {
 //       cardMensaje.setVisibility(View.VISIBLE);
 //       TextMensajeSuperior.setText(isCorrect ? "¡Correcto!" : "Incorrecto");
 //       TextMensajeInferiori.setText(isCorrect
 //               ? "Has seleccionado la respuesta correcta."
 //               : "La respuesta correcta es: " + questionsItems.get(currentQuestions).getCorrect());
 //   }
    private void showResultCard(boolean isCorrect) {
        // Hacer visible la tarjeta de mensaje
        cardMensaje.setVisibility(View.VISIBLE);

        // Cambiar colores según el resultado
        int backgroundColorSuperior = isCorrect ? getColor(R.color.light_green) : getColor(R.color.light_red);
        int backgroundColorInferior = isCorrect ? getColor(R.color.green_primary) : getColor(R.color.red_intense);
        //int textColorSuperior = isCorrect ? getColor(R.color.light_green) : getColor(R.color.light_red);
        int textColorSuperior = getColor(R.color.white);

        // Configurar el mensaje superior (encabezado)
        TextMensajeSuperior.setText(isCorrect ? "¡Correcto!" : "Respuesta Incorrecta");
        LayoutSup.setBackgroundColor(backgroundColorSuperior);
        TextMensajeSuperior.setTextColor(textColorSuperior);

        // Configurar el mensaje inferior (contenido)
        if (isCorrect) {
            TextMensajeInferiori.setText("Has seleccionado la respuesta correcta.");
            botonNext.setTextColor(getColor(R.color.black));
            botonNext.setBackgroundColor(getColor(R.color.white));
        } else {
            String respuestaCorrecta = questionsItems.get(currentQuestions).getCorrect();
            TextMensajeInferiori.setText("La respuesta correcta es: " + respuestaCorrecta);
            botonNext.setTextColor(getColor(R.color.black));
            botonNext.setBackgroundColor(getColor(R.color.white));
        }
        LayoutInf.setBackgroundColor(backgroundColorInferior);
        TextMensajeInferiori.setTextColor(Color.WHITE);
    }


    private void loadAllQuestions() {
        questionsItems = new ArrayList<>();
        String jsonquiz = loadJsonFromAsset("matematicsQuestions.json");
        try {
            JSONObject jsonObject = new JSONObject(jsonquiz);
            JSONArray questions = jsonObject.getJSONArray("matQuestions");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);
                String questionsString = question.getString("questions");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");
                questionsItems.add(new QuestionsItem(questionsString, answer1String, answer2String, answer3String, answer4String, correctString));
            }
        } catch (JSONException e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MatClass.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Deseas regresar al menu principal?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(MatClass.this, MainActivity.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }

    private void CargarEstilo() {
        TextView textView = findViewById(R.id.textViewTitulo);
        try {
            InputStream inputStream = getAssets().open("styleMateries.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray mathematics = jsonObject.getJSONArray("mathematics");
            JSONObject math = mathematics.getJSONObject(0);
            String title = math.getString("Title");
            String background = math.getString("background");
            textView.setText(title);
            textView.setBackgroundColor(getColorFromString(background));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}
