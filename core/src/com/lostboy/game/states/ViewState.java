package com.lostboy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.lostboy.game.Lost_Boy;
import com.lostboy.game.sprites.Tree;

/**
 * Created by Eric Chow on 26/05/2016.
 */
public class ViewState extends State {
    private Texture background;
    private Array<Tree> trees;
    private Tree tree;

    protected ViewState(GameStateManager gsm, Array<Tree> Trees){
        super(gsm);
        background = new Texture("grass.png");
        trees = Trees;
        cam.setToOrtho(false, Lost_Boy.WIDTH / 2, Lost_Boy.HEIGHT / 2);

    }


    @Override
    protected void handleInput() {

        //Game Over
        if(Gdx.input.justTouched()) {
            //display end game, then reset to MenuState
            gsm.set(new MenuState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {

        handleInput();
        //boy.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        for(int i = 0; i < trees.size; i++){
            sb.draw(trees.get(i).getTree(), trees.get(i).getPosition().x, trees.get(i).getPosition().y,40,40);
        }
        //draw the boy
        //sb.draw(boy.getBoy(), boy.getPosition().x, boy.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        for(int i = 0; i < trees.size; i++){
            trees.get(i).getTree().dispose();
        }
    }
}
