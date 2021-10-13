package uz.gita.lesson3puzzlegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button about = findViewById(R.id.menu_about);
        Button easy = findViewById(R.id.menu_easy);
        Button medium = findViewById(R.id.menu_medium);
        Button top_score = findViewById(R.id.menu_top_score);
        Button exit = findViewById(R.id.menu_exit);

        about.setOnClickListener(view -> {
            Intent intentAbout = new Intent(this, AboutActivity.class);
            startActivity(intentAbout);
        });

        easy.setOnClickListener(view -> {
            Intent intentEasy = new Intent(this, EasyActivity.class);
            startActivity(intentEasy);
    });

        medium.setOnClickListener(view -> {
            Intent intentMedium = new Intent(this, MediumActivity.class);
            startActivity(intentMedium);
        });


        top_score.setOnClickListener(view -> {
            Intent intentTop = new Intent(this,TopScoreActivity.class);
            startActivity(intentTop);
        });

        exit.setOnClickListener(view -> {
            finish();

//            AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Exit")
//                    .setMessage("Do you want to exit?")
//                    .setPositiveButton("ok", (dialogInterface, i) -> finish())
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(MainActivity.this,
//                                    "Great! Now you can continue your game",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }).create();
//            dialog.show();
        });




    }
}