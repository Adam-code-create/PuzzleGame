package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean isPlay = true;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        ImageView menu = findViewById(R.id.menuWinner);
        menu.setOnClickListener(view -> {
            finish();
        });

        TextView winScore = findViewById(R.id.winnerScore);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            score = bundle.getInt("winScore");
        }

        winScore.setText(String.valueOf(score));
        mediaPlayer = MediaPlayer.create(this, R.raw.apploud);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isPlay)
            mediaPlayer.start();
        mediaPlayer.setLooping(isPlay);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
    }


}