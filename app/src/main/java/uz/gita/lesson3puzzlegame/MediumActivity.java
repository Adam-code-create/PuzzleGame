package uz.gita.lesson3puzzlegame;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MediumActivity extends AppCompatActivity {
    private AppCompatButton[][] buttons;
    private TextView textScore;
    private Chronometer chronometer;
    private Cordinate emptySpace;
    private ArrayList<Integer> numbers;
    private int score;
    private MediaPlayer mediaPlayer;
    private LocalStorage storage;
    private int gold, silver, bronze;
    private SharedPreferences preferences;
    private ImageView musicBtn;
    private boolean checkMusic;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        storage = new LocalStorage(this);
        ImageView medal = findViewById(R.id.medal);
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(view -> {
            finish();

        });

        medal.setOnClickListener(view -> {
            Intent intent = new Intent(this, MediumTopResultActivity.class);
            startActivity(intent);
        });
        loadView();
        loadData();
        loadDataToViews();
        preferences = this.getSharedPreferences("music", Context.MODE_PRIVATE);
        checkMusic = preferences.getBoolean("check_music", false);

        mediaPlayer = MediaPlayer.create(this, R.raw.puzzlemusic);
        musicBtn = findViewById(R.id.musicImage);
        musicBtn.setImageResource(R.drawable.ic_music_off);
        musicBtn.setOnClickListener(view -> {
            playMusic();
        });


    }

    private void playMusic (){
        if (checkMusic){
            checkMusic = false;
            preferences.edit().putBoolean("check_music", false).apply();
            musicBtn.setImageResource(R.drawable.ic_music_off);
            mediaPlayer.pause();
        }else {
            checkMusic = true;
            preferences.edit().putBoolean("check_music", true).apply();
            musicBtn.setImageResource(R.drawable.ic_baseline_music_note_24);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkMusic) {
            mediaPlayer.start();
            musicBtn.setImageResource(R.drawable.ic_baseline_music_note_24);
        }

        mediaPlayer.setLooping(checkMusic);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadView() {
        findViewById(R.id.restart).setOnClickListener(view -> restart());

        textScore = findViewById(R.id.score);
        chronometer = findViewById(R.id.timer);
        ViewGroup group = findViewById(R.id.group);
        int count = group.getChildCount();
        buttons = new AppCompatButton[4][4];
        for (int i = 0; i < count; i++) {
            int x = i / 4;
            int y = i % 4;

            AppCompatButton button = (AppCompatButton) group.getChildAt(i);
            buttons[x][y] = button;
            button.setOnClickListener(this::click);
            button.setTag(new Cordinate(x, y));

        }
    }

    private void loadData() {
        numbers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numbers.add(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadDataToViews() {
        Collections.shuffle(numbers);
        while (!isSolvable(numbers)){
            Collections.shuffle(numbers);
        }

        for (int i = 0; i < 15; i++) {
            int x = i / 4;
            int y = i % 4;
            int number = numbers.get(i);
            buttons[x][y].setText(String.valueOf(number));
        }

        if (emptySpace != null) {
            buttons[emptySpace.getX()][emptySpace.getY()].setBackgroundResource(R.drawable.btn_easy);
        }
        emptySpace = new Cordinate(3, 3);
        score = 0;
        textScore.setText("0");
        buttons[emptySpace.getX()][emptySpace.getY()].setText("");
        buttons[emptySpace.getX()][emptySpace.getY()].setBackgroundResource(R.drawable.btn_empty);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void click(View view) {
        AppCompatButton button = (AppCompatButton) view;
        Cordinate c = (Cordinate) button.getTag();
        int btnX = Math.abs(c.getX() - emptySpace.getX());
        int btnY = Math.abs(c.getY() - emptySpace.getY());

        if (btnX + btnY == 1) {
            score++;
            textScore.setText(String.valueOf(score));
            String s = button.getText().toString();
            AppCompatButton emptyButton = buttons[emptySpace.getX()][emptySpace.getY()];
            emptyButton.setText(s);
            button.setText("");
            emptyButton.setBackgroundResource(R.drawable.btn_easy);
            button.setBackgroundResource(R.drawable.btn_empty);
            emptySpace = c;
            if (isWinner()) {
                gold = storage.getGoldScore();
                silver = storage.getSilverScore();
                bronze = storage.getBronzeScore();
                if (gold == 0) {
                    storage.setGoldScore(score);
                } else if (score >= gold && silver == 0 && bronze == 0) {
                    storage.setSilverScore(score);
                } else if (score > gold && silver < score && bronze == 0) {
                    storage.setBronzeScore(score);
                }else if (score >= gold && silver >= score) {
                    storage.setBronzeScore(silver);
                    storage.setSilverScore(score);
                } else if (score > silver && score > gold && score < bronze) {
                    storage.setBronzeScore(score);
                } else if (score < gold) {
                    storage.setBronzeScore(silver);
                    storage.setSilverScore(gold);
                    storage.setGoldScore(score);
                }
                Intent intent = new Intent(this, WinnerActivity.class);
                intent.putExtra("winScore", score);
                startActivity(intent);
                finish();
            }

        }
    }

    public boolean isSolvable(ArrayList<Integer> puzzle) {

        int parity = 0;
        for (int i = 0; i < puzzle.size()-1; i++) {
            for (int j = i+1; j < puzzle.size(); j++) {
                if (puzzle.get(i) > puzzle.get(j)){
                    parity++;
                }
            }
        }
        return parity %2 == 0;

    }
    private boolean isWinner() {
        for (int i = 0; i < 15; i++) {
            int x = i / 4;
            int y = i % 4;
            String text = buttons[x][y].getText().toString();
            if (text.isEmpty()) return false;
            int n = Integer.parseInt(text);
            if (n != i + 1) return false;
        }
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void restart() {
        loadDataToViews();


    }
}