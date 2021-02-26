package com.luckysix;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.luckysix.States.GameStateManager;
import com.luckysix.States.MenuState;
import com.luckysix.States.PickState;
import com.luckysix.States.PlayState;

public class luckysix extends ApplicationAdapter {
	public static final int WIDTH=800;
	public static final int HEIGHT=420;
	SpriteBatch batch;
	Texture img;
	GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));


	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
//		batch.dispose();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
