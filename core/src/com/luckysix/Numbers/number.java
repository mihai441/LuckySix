package com.luckysix.Numbers;

import com.badlogic.gdx.graphics.Texture;


/**
 * Created by AsX on 7/4/2017.
 */

public class number {

    Texture sprite;
    Integer numar;

    public number(int val) {
        numar = val;
        setSprite(val);
    }

    private void setSprite(int val) {
    String numeTextura = Integer.toString(val+1) + ".png";
        sprite = new Texture(numeTextura);
    }

    public Texture getSprite() {
        return sprite;
    }

    public Integer getNumar() {
        return numar;
    }
}
