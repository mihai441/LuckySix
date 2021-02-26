package com.luckysix.Textures;

import com.luckysix.luckysix;

/**
 * Created by AsX on 7/4/2017.
 */

public class sprites {

    public static final int defaultWIDTH=1920;
    public static final int defaultHEIGHT=1080;

    public static float getX(int val,float GAME_WIDTH){
        float x=0;

        x = (caseX(val) * GAME_WIDTH)/defaultWIDTH;

        return x;
    }

    public static float getY(int val,float GAME_HEIGHT){
        float y=0;
        y=((defaultHEIGHT-caseY(val)) * GAME_HEIGHT)/defaultHEIGHT;

        return y;
    }



    public static int caseY(int val) {

        switch (val) {

            case 0:
                return 123;
            case 1:
                return 221;
            case 2:
                return 330;
            case 3:
                return 430;
            case 4:
                return 503;
            case 5:
                return 133;
            case 6:
                return 249;
            case 7:
                return 367;
            case 8:
                return 480;
            case 9:
                return 588;
            case 10:
                return 699;
            case 11:
                return 819;
            case 12:
                return 939;
            case 13:
                return 1054;
            case 14:
                return 774;
            case 15:
                return 894;
            case 16:
                return 1014;
            case 17:
                return 774;
            case 18:
                return 894;
            case 19:
                return 1014;
            case 20:
                return 774;
            case 21:
                return 894;
            case 22:
                return 1014;
            case 23:
                return 774;
            case 24:
                return 894;
            case 25:
                return 1014;
            case 26:
                return 133;
            case 27:
                return 249;
            case 28:
                return 367;
            case 29:
                return 480;
            case 30:
                return 588;
            case 31:
                return 699;
            case 32:
                return 819;
            case 33:
                return 939;
            case 34:
                return 1054;
        }
        return -1;
    }

public static int caseX(int val){
        switch(val){
            case 0:
                return 1170;
            case 1:
                return 1215;
            case 2:
                return 1208;
            case 3:
                return 1169;
            case 4:
                return 1094;
            case 5:
                return 133;
            case 6:
                return 133;
            case 7:
                return 133;
            case 8:
                return 133;
            case 9:
                return 133;
            case 10:
                return 133;
            case 11:
                return 133;
            case 12:
                return 133;
            case 13:
                return 133;
            case 14:
                return 543;
            case 15:
                return 543;
            case 16:
                return 543;
            case 17:
                return 783;
            case 18:
                return 783;
            case 19:
                return 783;
            case 20:
                return 1021;
            case 21:
                return 1021;
            case 22:
                return 1021;
            case 23:
                return 1253;
            case 24:
                return 1253;
            case 25:
                return 1253;
            case 26:
                return 1689;
            case 27:
                return 1689;
            case 28:
                return 1689;
            case 29:
                return 1689;
            case 30:
                return 1689;
            case 31:
                return 1689;
            case 32:
                return 1689;
            case 33:
                return 1689;
            case 34:
                return 1689;
        }
        return -1;
    }

    public static float getManualX(int val, float GAME_WIDTH){
        float x=0;
        x = ((val) * GAME_WIDTH)/defaultWIDTH;
        return x;
    }

    public static float getManualY(int val, float GAME_HEIGHT){
        float x=0;
        x = ((val) * GAME_HEIGHT)/defaultHEIGHT;
        return x;
    }


}
