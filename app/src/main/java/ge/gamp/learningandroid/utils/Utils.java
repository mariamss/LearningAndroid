package ge.gamp.learningandroid.utils;

import java.util.Random;

public class Utils {
    public static int parseStringToInt(String integerText) {
        if(integerText.isEmpty()){
            return 0;
        }
        return  Integer.parseInt(integerText);
    }

    public static int generateRandomInt(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
