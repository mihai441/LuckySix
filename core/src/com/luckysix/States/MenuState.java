package com.luckysix.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.luckysix.luckysix;

/**
 * Created by AsX on 7/4/2017.
 */

public class MenuState extends state {

    Texture background;


    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("background.jpg");
        cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cam.translate(cam.viewportWidth/2,cam.viewportHeight/2);
    }
    @Override
    public void handleinput() {
       if (Gdx.input.isTouched()) {
           gsm.set(new PickState(gsm));
           dispose();
       }
       }

    @Override
    public void update(float dt) {
    handleinput();
    }

    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void dispose(){
        background.dispose();
    }
}
