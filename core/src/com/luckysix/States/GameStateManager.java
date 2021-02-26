package com.luckysix.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by User on 7/3/2017.
 */

public class GameStateManager {
    private Stack<state> States;

    public GameStateManager(){
        States = new Stack<state>();
    }

    public void push(state state){
        States.push(state);
    }

    public void pop(){
        States.pop();
    }

    public void set(state state){
        States.pop();
        States.push(state);
    }

    public void update(float dt){
        States.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        States.peek().render(sb);
    }

}
