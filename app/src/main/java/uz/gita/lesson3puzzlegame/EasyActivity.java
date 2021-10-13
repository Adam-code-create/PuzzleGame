package uz.gita.lesson3puzzlegame;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class EasyActivity extends AppCompatActivity {
    private AppCompatButton[][] buttons;
    private TextView textScore;
    private Chronometer chronometer;
    private Cordinate emptySpace;
    private ArrayList<Integer> numbers;
    private int score;
    private MediaPlayer mediaPlayer;
    private boolean checkMusic;
    private SharedPreferences preferences;
    private ImageView musicBtn;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        ImageView medal = findViewById(R.id.medalEasy);
        ImageView menu = findViewById(R.id.easy_menu);
        menu.setOnClickListener(view -> {
            finish();
        });
        medal.setOnClickListener(view -> {
            Intent intent = new Intent(this, EasyTopResultActivity.class);
            startActivity(intent);
        });
        loadView();
        loadData();
        loadDataToViews();

        preferences = this.getSharedPreferences("music", Context.MODE_PRIVATE);
        checkMusic = preferences.getBoolean("check_music", false);
        mediaPlayer = MediaPlayer.create(this, R.raw.puzzlemusic);
        musicBtn = findViewById(R.id.musicImageEasy);
        musicBtn.setImageResource(R.drawable.ic_music_off);
        musicBtn.setOnClickListener(view -> {
            playMusic();
        });
    }

    private void playMusic() {
        if (checkMusic) {
            checkMusic = false;
            preferences.edit().putBoolean("check_music", false).apply();
            musicBtn.setImageResource(R.drawable.ic_music_off);
            mediaPlayer.pause();
        } else {
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
        textScore = findViewById(R.id.scoreEasy);
        chronometer = findViewById(R.id.timer);
        ViewGroup group = findViewById(R.id.group);
        int count = group.getChildCount();
        buttons = new AppCompatButton[3][3];
        for (int i = 0; i < count; i++) {
            int x = i / 3;
            int y = i % 3;

            AppCompatButton button = (AppCompatButton) group.getChildAt(i);
            buttons[x][y] = button;
            button.setOnClickListener(this::click);
            button.setTag(new Cordinate(x, y));

        }
    }

    private void loadData() {
        numbers = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            numbers.add(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadDataToViews() {
        Collections.shuffle(numbers);
        while (!isSolvable(numbers)) {
            Collections.shuffle(numbers);
        }

        for (int i = 0; i < 8; i++) {
            int x = i / 3;
            int y = i % 3;
            int number = numbers.get(i);
            buttons[x][y].setText(String.valueOf(number));
        }

        if (emptySpace != null) {
            buttons[emptySpace.getX()][emptySpace.getY()].setBackgroundResource(R.drawable.btn_easy);
        }
        emptySpace = new Cordinate(2, 2);
        score = 0;
        textScore.setText("0");
        buttons[emptySpace.getX()][emptySpace.getY()].setText("");
        buttons[emptySpace.getX()][emptySpace.getY()].setBackgroundResource(R.drawable.btn_empty);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }

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
                Intent intent = new Intent(EasyActivity.this, EasyWinnerActivity.class);
                intent.putExtra("winScoreEasy", score);
                startActivity(intent);
                finish();
            }

        }
    }

    private boolean isWinner() {
        for (int i = 0; i < 8; i++) {
            int x = i / 3;
            int y = i % 3;
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

    public boolean isSolvable(ArrayList<Integer> puzzle) {

        int parity = 0;
        for (int i = 0; i < puzzle.size() - 1; i++) {
            for (int j = i + 1; j < puzzle.size(); j++) {
                if (puzzle.get(i) > puzzle.get(j)) {
                    parity++;
                }
            }
        }
        return parity % 2 == 0;

    }
}