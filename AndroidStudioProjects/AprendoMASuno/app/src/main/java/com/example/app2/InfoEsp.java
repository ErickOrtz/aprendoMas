package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class InfoEsp extends AppCompatActivity {

    ImageButton botonVideo;
    Button botonCerrar, play, pause, stop;
    FrameLayout frameLayout;

    VideoView videoView;

    private  int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_esp);

        frameLayout = findViewById(R.id.floatingWindowMatVid);
        botonVideo = findViewById(R.id.botonVideo);
        botonCerrar = findViewById(R.id.closeButton);
        videoView = findViewById(R.id.videoViewMat);

        play = findViewById(R.id.playButton);
        pause = findViewById(R.id.pauseButton);
        stop = findViewById(R.id.stopButton);

        botonVideo.setOnClickListener(v -> {
            frameLayout.setVisibility(View.VISIBLE);
        });
        botonCerrar.setOnClickListener(v -> {
            frameLayout.setVisibility(View.INVISIBLE);
            videoView.stopPlayback();
        });

        String videoUri = "android.resource://" + getPackageName() + "/" + R.raw.esp;

        videoView.setVideoURI(Uri.parse(videoUri));

        play.setOnClickListener(v -> videoView.start());

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
    }


    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(InfoEsp.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("¿Deseas regresar al menu principal?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            startActivity(new Intent(InfoEsp.this, MainActivity.class));
            finish();
        });
        materialAlertDialogBuilder.show();
    }
}