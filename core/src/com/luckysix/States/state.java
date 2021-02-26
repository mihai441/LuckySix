package com.luckysix.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by User on 7/3/2017.
 */

public abstract class state {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected state(GameStateManager gsm){

        this.gsm=gsm;
        mouse = new Vector3();
        cam = new OrthographicCamera();
    }

    protected abstract void handleinput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
