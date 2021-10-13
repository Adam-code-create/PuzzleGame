package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyWinnerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean isPlay = true;
    private int score = 0;
    private int gold = 0, silver = 0, bronze = 0;
    EasyLocalStorage storageEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_winner);
        storageEasy = new EasyLocalStorage(this);
        ImageView menu = findViewById(R.id.menuEasyWinner);
        menu.setOnClickListener(view -> {
            finish();
        });

        TextView scoreEasy = findViewById(R.id.winnerScoreEasy);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            score = bundle.getInt("winScoreEasy");
            scoreEasy.setText(String.valueOf(score));
            gold = storageEasy.getGoldScoreEasy();
            silver = storageEasy.getSilverScoreEasy();
            bronze = storageEasy.getBronzeScoreEasy();
            if (gold == 0) {
                storageEasy.setGoldScoreEasy(score);
            } else if (score >= gold && silver == 0 && bronze == 0) {
                storageEasy.setSilverScoreEasy(score);
            } else if (score > gold && silver < score && bronze == 0) {
                storageEasy.setBronzeScoreEasy(score);
            } else if (score >= gold && silver >= score) {
                storageEasy.setBronzeScoreEasy(silver);
                storageEasy.setSilverScoreEasy(score);
            } else if (score > silver && score > gold && score < bronze) {
                storageEasy.setBronzeScoreEasy(score);
            } else if (score < gold) {
                storageEasy.setBronzeScoreEasy(silver);
                storageEasy.setSilverScoreEasy(gold);
                storageEasy.setGoldScoreEasy(score);
            }

        }
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