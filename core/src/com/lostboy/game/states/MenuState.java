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
        background = new Texture("grass.png");
        playBtn = new Texture("play_button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, Lost_Boy.WIDTH, Lost_Boy.HEIGHT);
        sb.draw(playBtn, (Lost_Boy.WIDTH / 2) - (playBtn.getWidth() / 2), Lost_Boy.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose(){
        background.dispose();
        //playBtn.dispose();
    }
}