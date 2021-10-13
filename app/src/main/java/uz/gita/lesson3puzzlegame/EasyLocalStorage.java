package uz.gita.lesson3puzzlegame;

import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

public class EasyLocalStorage {

    SharedPreferences preferencesEasy;
    private static final String SCOREGOLD = "akslrtfds";
    private static final String SCORESILVER = "amkdhy36";
    private static final String SCOREBRONZE = "a235rws6";

    public EasyLocalStorage (Context context){
        preferencesEasy = new SecurePreferences(context, "11234567qwerty", "LocalStorageEasySample");
    }
    public void setGoldScoreEasy (int goldScore){
        preferencesEasy.edit().putInt(SCOREGOLD, goldScore).apply();
    }
    public void setSilverScoreEasy (int silverSCore){
        preferencesEasy.edit().putInt(SCORESILVER, silverSCore).apply();
    }
    public void setBronzeScoreEasy (int bronzeScoreScore){
        preferencesEasy.edit().putInt(SCOREBRONZE,bronzeScoreScore).apply();
    }

    public int getGoldScoreEasy (){
        return preferencesEasy.getInt(SCOREGOLD,0);
    }
    public int getSilverScoreEasy (){
        return preferencesEasy.getInt(SCORESILVER,0);
    }
    public  int getBronzeScoreEasy (){
        return preferencesEasy.getInt(SCOREBRONZE,0);
    }

}
