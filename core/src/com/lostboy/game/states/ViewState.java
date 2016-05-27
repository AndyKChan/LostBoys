package com.lostboy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.lostboy.game.Lost_Boy;
import com.lostboy.game.sprites.Tree;
import com.lostboy.game.sprites.boy;

/**
 * Created by Eric Chow on 26/05/2016.
 */
public class ViewState extends State {
    private Texture background;
    private Array<Tree> trees;
    private boy boy;

    protected ViewState(GameStateManager gsm, Array<Tree> Trees){
        super(gsm);
        background = new Texture("bg1.png");
        trees = Trees;
        cam.setToOrtho(false, Lost_Boy.WIDTH / 2, Lost_Boy.HEIGHT / 2);
        boy = new boy(Lost_Boy.WIDTH/4,0);
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            int x = Gdx.input.getX()/2;
            int y = (Lost_Boy.HEIGHT - Gdx.input.getY()) / 2;
            boy.move(x, y);
        }

        if(boy.getPosition().x > 100 && boy.getPosition().x < 120 && boy.getPosition().y > 350)
            gsm.set(new MenuState(gsm));
        /*
        //Game Over
        if(Gdx.input.justTouched()) {
            //display end game, then reset to MenuState
            gsm.set(new MenuState(gsm));
        }
        */
    }

    @Override
    public void update(float dt) {

        handleInput();
        boy.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0,Lost_Boy.WIDTH/2, Lost_Boy.HEIGHT/2);
        for(int i = 0; i < trees.size; i++){
            sb.draw(trees.get(i).getTree(), trees.get(i).getPosition().x, trees.get(i).getPosition().y,40,40);
        }
        //draw the boy
        sb.draw(boy.getBoy(), boy.getPosition().x, boy.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        for(int i = 0; i < trees.size; i++){
            trees.get(i).getTree().dispose();
        }
        boy.dispose();
        System.out.println("ViewState Disposed!");
    }
}
