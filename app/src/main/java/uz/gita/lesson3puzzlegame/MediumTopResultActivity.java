package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MediumTopResultActivity extends AppCompatActivity {
    private int gold, silver, bronze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_top_result);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());

        LocalStorage storage = new LocalStorage(this);
        TextView goldScore = findViewById(R.id.scoreGold);
        gold = storage.getGoldScore();
        goldScore.setText(String.valueOf(gold));
        TextView silverScore = findViewById(R.id.scoreSilver);
        silver = storage.getSilverScore();
        silverScore.setText(String.valueOf(silver));
        TextView bronzeScore = findViewById(R.id.scoreBronze);
        bronze = storage.getBronzeScore();
        bronzeScore.setText(String.valueOf(bronze));

    }
}