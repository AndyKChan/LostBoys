package com.lostboy.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.lostboy.game.Lost_Boy;
import com.lostboy.game.sprites.LostBoy;
import com.lostboy.game.sprites.Tree;

import java.util.ArrayList;


/**
 * Created by Andy on 5/15/2016.
 */
public class PlayState extends State {
    private LostBoy bird;
    private Texture background;
    private Tree tree;
    private Array<Tree> trees;
    private Tree hoverTree;
    private static final int MAXTREES = 20;
    private static final int BLOCKSIZE = 20;
    private static final int BLOCKED = 1000;
    private static final int PATHWIDTH = 12;
    private static final int PATHHEIGHT = 20;
    /*
    private static final int[][] distanceH = new int[][]{
            { 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6},
            { 7, 6, 5, 4, 3, 2, 3, 4, 5, 6, 7},
            { 8, 7, 6, 5, 4, 3, 4, 5, 6, 7, 8},
            { 9, 8, 7, 6, 5, 4, 5, 6, 7, 8, 9},
            {10, 9, 8, 7, 6, 5, 6, 7, 8, 9,10},
            {11,10, 9, 8, 7, 6, 7, 8, 9,10,11},
            {12,11,10, 9, 8, 7, 8, 9,10,11,12},
            {13,12,11,10, 9, 8, 9,10,11,12,13},
            {14,13,12,11,10, 9,10,11,12,13,14},
            {15,14,13,12,11,10,11,12,13,14,15},
            {16,15,14,13,12,11,12,13,14,15,16},
            {17,16,15,14,13,12,13,14,15,16,17},
            {18,17,16,15,14,13,14,15,16,17,18},
            {19,18,17,16,15,14,15,16,17,18,19},
            {20,19,18,17,16,15,16,17,18,19,20},
            {21,20,19,18,17,16,17,18,19,20,21},
            {22,21,20,19,18,17,18,19,20,21,22},
            {23,22,21,20,19,18,19,20,21,22,23},
            {24,23,22,21,20,19,20,21,22,23,24}
    };
    */

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new LostBoy(50,300);
        cam.setToOrtho(false, Lost_Boy.WIDTH / 2, Lost_Boy.HEIGHT / 2);
        background = new Texture("grass.png");
        trees = new Array<Tree>();
        hoverTree = new Tree(0, 0);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {

            bird.jump();
//            tree = new Tree(Gdx.input.getX(), Gdx.input.getY());
        }
//        if(Gdx.input.justTouched()){
//            tree = new Tree(Gdx.input.getX(), Gdx.input.getY());
//            trees.add(tree);
//        }

        if(Gdx.input.justTouched() && trees.size < MAXTREES) {
            int x = snapInPosition(Gdx.input.getX() / 2);
            int y = snapInPosition((Lost_Boy.HEIGHT - Gdx.input.getY()) / 2);
            if (!isOverlap(x, y) && x / BLOCKSIZE < PATHWIDTH - 1 && y / BLOCKSIZE < PATHHEIGHT - 1) {
                tree = new Tree(x, y);
                trees.add(tree);
                if(trees.size == MAXTREES){
                    //change game state to viewing where all inputs are ignored
                    //will also call A Star pathfinding etc there
                    gsm.set(new ViewState(gsm, trees));
                    dispose();
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(int i = 0; i < trees.size; i++){
            sb.draw(trees.get(i).getTree(), trees.get(i).getPosition().x, trees.get(i).getPosition().y,40,40);
        }
        int x = snapInPosition(Gdx.input.getX()/2);
        int y = snapInPosition((Lost_Boy.HEIGHT - Gdx.input.getY())/2);
        sb.draw(hoverTree.getTreeImage(x,y,trees), x, y, 40, 40);
        sb.end();

    }

    public boolean isOverlap(int x, int y) {
        for(int i = 0; i < trees.size; i++){
            if(Math.abs(x-trees.get(i).getPosition().x) <= 39.9){
                if(Math.abs(y-trees.get(i).getPosition().y) <= 39.9) {
                    return true;
                }
            }
        }
        return false;
    }


    public int snapInPosition(int coordinate) {
        double value = 0;

        value = Math.ceil(coordinate/BLOCKSIZE)*BLOCKSIZE;

        return (int)(value);
    }

    @Override
    public void dispose() {
        background.dispose();
        hoverTree.dispose();
    }
}
