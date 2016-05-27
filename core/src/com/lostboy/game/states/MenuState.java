package com.lostboy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lostboy.game.Lost_Boy;

/**
 * Created by Andy on 5/15/2016.
 */
public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("bg1.png");
        playBtn = new Texture("play_button.png");
        cam.setToOrtho(false, Lost_Boy.WIDTH, Lost_Boy.HEIGHT);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/ 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose(){
        background.dispose();
        playBtn.dispose();
        System.out.println("MenuState Disposed!");
    }
}