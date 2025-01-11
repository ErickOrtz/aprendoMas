package com.example.app2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class InfoActivity extends AppCompatActivity {
    private VideoView videoView;
    private Button buttonPlay,pause,stop,botonTest,moreinfo,closeButton;
    private SeekBar seekBar;
    private TextView tvTitle,contenido;

    private FrameLayout frameLayout;
    MaterialCardView floatingCard;
    private  int posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        frameLayout = findViewById(R.id.floatingWindow);
        contenido = findViewById(R.id.cardContent);
        videoView = findViewById(R.id.videoView);
        buttonPlay = findViewById(R.id.buttonPlay);
        seekBar = findViewById(R.id.seekBar);
        tvTitle = findViewById(R.id.tvTitle);
        pause = findViewById(R.id.buttonPause);
        stop = findViewById(R.id.buttonStop);
        botonTest = findViewById(R.id.botonQuiz);
        moreinfo = findViewById(R.id.moreInformation);
        floatingCard = findViewById(R.id.floatingCard);
        closeButton = findViewById(R.id.closeButton);



        botonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoActivity.this, PlanetsClass.class));
                finish();
            }
        });


        moreinfo.setOnClickListener(v -> {
            contenido.setText(
                    "El sistema solar es un grupo de planetas, lunas, asteroides, cometas y otros objetos que giran alrededor del Sol. ¡El Sol es el centro del sistema solar y también es una estrella! Proporciona luz y calor, lo que hace posible la vida en nuestro planeta.\n\n" +
                    "Los planetas del sistema solar son ocho:\n\n" +
                    "1. Mercurio: Es el planeta más cercano al Sol y el más pequeño. No tiene lunas y su superficie es muy caliente.\n\n" +
                    "2. Venus: Conocido como el \"hermano\" de la Tierra porque son de tamaño similar. Es muy caliente debido a su atmósfera densa.\n\n" +
                    "3. Tierra: Nuestro hogar, el único planeta donde sabemos que hay vida. Tiene una luna que llamamos \"Luna\".\n\n" +
                    "4. Marte: El \"planeta rojo\" por su color. Tiene montañas y valles, y es el más explorado después de la Tierra.\n\n" +
                    "5. Júpiter: Es el planeta más grande y tiene una gran tormenta llamada la \"Gran Mancha Roja\". Tiene muchas lunas, ¡más de 70!\n\n" +
                    "6. Saturno: Es famoso por sus hermosos anillos hechos de hielo y polvo.\n\n" +
                    "7. Urano: Tiene un color azul verdoso por los gases en su atmósfera y gira de lado.\n\n" +
                    "8. Neptuno: Es el planeta más lejano y muy frío. También tiene un color azul por los gases.");
            frameLayout.setVisibility(View.VISIBLE);
        });

        closeButton.setOnClickListener(v -> {
            frameLayout.setVisibility(View.INVISIBLE);
        });
        String videoUri = "android.resource://" + getPackageName() + "/" + R.raw.video;

        videoView.setVideoURI(Uri.parse(videoUri));

        buttonPlay.setOnClickListener(v -> videoView.start());

        pause.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                posicion = videoView.getCurrentPosition();
            } else {
                videoView.seekTo(posicion);
                videoView.start();
            }
        });


        stop.setOnClickListener(v -> {
            videoView.stopPlayback();
            posicion = 0;
            videoView.setVideoURI(Uri.parse(videoUri));
        });


        videoView.setOnPreparedListener(mediaPlayer -> {
            int videoDuration = videoView.getDuration();
            seekBar.setMax(videoDuration);
        });

        videoView.setOnInfoListener((mp, what, extra) -> {
            if (what == android.media.MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                new Thread(() -> {
                    int currentPosition;
                     while (videoView.isPlaying()) {
                        currentPosition = videoView.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        try {
                            Thread.sleep(1000); // Actualiza cada segundo
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            return true;
        });

        // Manejar cambios en el SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress); // Cambiar la posición del video según el SeekBar
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(InfoActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Deseas regresar al menu principal?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(InfoActivity.this, MainActivity.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }


}