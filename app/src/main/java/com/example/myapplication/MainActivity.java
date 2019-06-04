package com.example.myapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    ImageView ivPlayPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.go_durachek);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F67B01'>Music Player</font>"));

        seekBar = findViewById(R.id.seekBar);

        ivPlayPause = findViewById(R.id.ivPlayPause);
        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1000);



    }


    public void onClickPlayPause(View view) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            ivPlayPause.setImageResource(R.drawable.ic_play_arrow_24dp);
        }else {
            mediaPlayer.start();
            ivPlayPause.setImageResource(R.drawable.ic_pause_24dp);
        }
    }

    public void onClickSkipPrevious(View view) {
        mediaPlayer.seekTo(0);
        seekBar.setProgress(0);
        mediaPlayer.pause();
        ivPlayPause.setImageResource(R.drawable.ic_play_arrow_24dp);
    }

    public void onClickSkipNext(View view) {
        int duration = mediaPlayer.getDuration();
        seekBar.setProgress(duration);
        mediaPlayer.seekTo(duration);
        mediaPlayer.pause();
        ivPlayPause.setImageResource(R.drawable.ic_play_arrow_24dp);
    }
}
