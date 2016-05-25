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
    private int[][]astarpath = new int[PATHWIDTH][PATHHEIGHT];

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
        if(Gdx.input.justTouched() && trees.size < MAXTREES){
            int x = snapInPosition(Gdx.input.getX()/2);
            int y = snapInPosition((Lost_Boy.HEIGHT - Gdx.input.getY())/2);
            if(!isOverlap(x,y) && x/BLOCKSIZE<PATHWIDTH-1 && y/BLOCKSIZE<PATHHEIGHT-1){
                tree = new Tree(x,y);
                trees.add(tree);
                astarpath[x/BLOCKSIZE][y/BLOCKSIZE]=BLOCKED;
                astarpath[(x+BLOCKSIZE)/BLOCKSIZE][y/BLOCKSIZE]=BLOCKED;
                astarpath[x/BLOCKSIZE][(y+BLOCKSIZE)/BLOCKSIZE]=BLOCKED;
                astarpath[(x+BLOCKSIZE)/BLOCKSIZE][(y+BLOCKSIZE)/BLOCKSIZE]=BLOCKED;
                //printOutMatrix(astarpath);
            }

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        //boy.update(dt);
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
        sb.draw(isOverlap(x,y)?hoverTree.getTreeNot():hoverTree.getTree(), x, y, 40, 40);
        if(trees.size == MAXTREES) {
            //draw the boy
            //sb.draw(boy.getBoy(), boy.getPosition().x, boy.getPosition().y);
        }
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

    public void printOutMatrix(int[][] multi){
        Gdx.app.log("matrix",""+multi[0][19]+","+multi[1][19]+","+multi[2][19]+","+multi[3][19]+","+multi[4][19]+","+multi[5][19]+","+multi[6][19]+","+multi[7][19]+","+multi[8][19]+","+multi[9][19]+","+multi[10][19]+","+multi[11][19]);
        Gdx.app.log("matrix",""+multi[0][18]+","+multi[1][18]+","+multi[2][18]+","+multi[3][18]+","+multi[4][18]+","+multi[5][18]+","+multi[6][18]+","+multi[7][18]+","+multi[8][18]+","+multi[9][18]+","+multi[10][18]+","+multi[11][18]);
        Gdx.app.log("matrix",""+multi[0][17]+","+multi[1][17]+","+multi[2][17]+","+multi[3][17]+","+multi[4][17]+","+multi[5][17]+","+multi[6][17]+","+multi[7][17]+","+multi[8][17]+","+multi[9][17]+","+multi[10][17]+","+multi[11][17]);
        Gdx.app.log("matrix",""+multi[0][16]+","+multi[1][16]+","+multi[2][16]+","+multi[3][16]+","+multi[4][16]+","+multi[5][16]+","+multi[6][16]+","+multi[7][16]+","+multi[8][16]+","+multi[9][16]+","+multi[10][16]+","+multi[11][16]);
        Gdx.app.log("matrix",""+multi[0][15]+","+multi[1][15]+","+multi[2][15]+","+multi[3][15]+","+multi[4][15]+","+multi[5][15]+","+multi[6][15]+","+multi[7][15]+","+multi[8][15]+","+multi[9][15]+","+multi[10][15]+","+multi[11][15]);
        Gdx.app.log("matrix",""+multi[0][14]+","+multi[1][14]+","+multi[2][14]+","+multi[3][14]+","+multi[4][14]+","+multi[5][14]+","+multi[6][14]+","+multi[7][14]+","+multi[8][14]+","+multi[9][14]+","+multi[10][14]+","+multi[11][14]);
        Gdx.app.log("matrix",""+multi[0][13]+","+multi[1][13]+","+multi[2][13]+","+multi[3][13]+","+multi[4][13]+","+multi[5][13]+","+multi[6][13]+","+multi[7][13]+","+multi[8][13]+","+multi[9][13]+","+multi[10][13]+","+multi[11][13]);
        Gdx.app.log("matrix",""+multi[0][12]+","+multi[1][12]+","+multi[2][12]+","+multi[3][12]+","+multi[4][12]+","+multi[5][12]+","+multi[6][12]+","+multi[7][12]+","+multi[8][12]+","+multi[9][12]+","+multi[10][12]+","+multi[11][12]);
        Gdx.app.log("matrix",""+multi[0][11]+","+multi[1][11]+","+multi[2][11]+","+multi[3][11]+","+multi[4][11]+","+multi[5][11]+","+multi[6][11]+","+multi[7][11]+","+multi[8][11]+","+multi[9][11]+","+multi[10][11]+","+multi[11][11]);
        Gdx.app.log("matrix",""+multi[0][10]+","+multi[1][10]+","+multi[2][10]+","+multi[3][10]+","+multi[4][10]+","+multi[5][10]+","+multi[6][10]+","+multi[7][10]+","+multi[8][10]+","+multi[9][10]+","+multi[10][10]+","+multi[11][10]);
        Gdx.app.log("matrix",""+multi[0][9]+","+multi[1][9]+","+multi[2][9]+","+multi[3][9]+","+multi[4][9]+","+multi[5][9]+","+multi[6][9]+","+multi[7][9]+","+multi[8][9]+","+multi[9][9]+","+multi[10][9]+","+multi[11][9]);
        Gdx.app.log("matrix",""+multi[0][8]+","+multi[1][8]+","+multi[2][8]+","+multi[3][8]+","+multi[4][8]+","+multi[5][8]+","+multi[6][8]+","+multi[7][8]+","+multi[8][8]+","+multi[9][8]+","+multi[10][8]+","+multi[11][8]);
        Gdx.app.log("matrix",""+multi[0][7]+","+multi[1][7]+","+multi[2][7]+","+multi[3][7]+","+multi[4][7]+","+multi[5][7]+","+multi[6][7]+","+multi[7][7]+","+multi[8][7]+","+multi[9][7]+","+multi[10][7]+","+multi[11][7]);
        Gdx.app.log("matrix",""+multi[0][6]+","+multi[1][6]+","+multi[2][6]+","+multi[3][6]+","+multi[4][6]+","+multi[5][6]+","+multi[6][6]+","+multi[7][6]+","+multi[8][6]+","+multi[9][6]+","+multi[10][6]+","+multi[11][6]);
        Gdx.app.log("matrix",""+multi[0][5]+","+multi[1][5]+","+multi[2][5]+","+multi[3][5]+","+multi[4][5]+","+multi[5][5]+","+multi[6][5]+","+multi[7][5]+","+multi[8][5]+","+multi[9][5]+","+multi[10][5]+","+multi[11][5]);
        Gdx.app.log("matrix",""+multi[0][4]+","+multi[1][4]+","+multi[2][4]+","+multi[3][4]+","+multi[4][4]+","+multi[5][4]+","+multi[6][4]+","+multi[7][4]+","+multi[8][4]+","+multi[9][4]+","+multi[10][4]+","+multi[11][4]);
        Gdx.app.log("matrix",""+multi[0][3]+","+multi[1][3]+","+multi[2][3]+","+multi[3][3]+","+multi[4][3]+","+multi[5][3]+","+multi[6][3]+","+multi[7][3]+","+multi[8][3]+","+multi[9][3]+","+multi[10][3]+","+multi[11][3]);
        Gdx.app.log("matrix",""+multi[0][2]+","+multi[1][2]+","+multi[2][2]+","+multi[3][2]+","+multi[4][2]+","+multi[5][2]+","+multi[6][2]+","+multi[7][2]+","+multi[8][2]+","+multi[9][2]+","+multi[10][2]+","+multi[11][2]);
        Gdx.app.log("matrix",""+multi[0][1]+","+multi[1][1]+","+multi[2][1]+","+multi[3][1]+","+multi[4][1]+","+multi[5][1]+","+multi[6][1]+","+multi[7][1]+","+multi[8][1]+","+multi[9][1]+","+multi[10][1]+","+multi[11][1]);
        Gdx.app.log("matrix",""+multi[0][0]+","+multi[1][0]+","+multi[2][0]+","+multi[3][0]+","+multi[4][0]+","+multi[5][0]+","+multi[6][0]+","+multi[7][0]+","+multi[8][0]+","+multi[9][0]+","+multi[10][0]+","+multi[11][0]);
    }

    @Override
    public void dispose() {

    }
}
