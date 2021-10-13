package uz.gita.lesson3puzzlegame;

import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

public class LocalStorage {
     SharedPreferences preferences;
    private static final String SCOREGOLD = "aksl2rtfds";
    private static final String SCORESILVER = "amkd2hy36";
    private static final String SCOREBRONZE = "a2325rws6";

    public LocalStorage (Context context){
        preferences = new SecurePreferences(context, "1234567qwerty", "LocalStorageSample");
    }
    public void setGoldScore (int goldScore){
        preferences.edit().putInt(SCOREGOLD, goldScore).apply();
    }
    public void setSilverScore (int silverSCore){
        preferences.edit().putInt(SCORESILVER, silverSCore).apply();
    }
    public void setBronzeScore (int bronzeScoreScore){
        preferences.edit().putInt(SCOREBRONZE,bronzeScoreScore).apply();
    }

    public int getGoldScore (){
        return preferences.getInt(SCOREGOLD,0);
    }
    public int getSilverScore (){
        return preferences.getInt(SCORESILVER,0);
    }
    public  int getBronzeScore (){
        return preferences.getInt(SCOREBRONZE,0);
    }


}
