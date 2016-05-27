package com.lostboy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Andy on 5/17/2016.
 */
public class Tree {
    private Texture tree;
    private Texture treeNot, treeNotBot, treeNotBotLeft, treeNotBotRight, treeNotLeft, treeNotRight, treeNotTop, treeNotTopLeft, treeNotTopRight, treeNotAll;
    private Texture  treeNotTopLeftBotRight, treeNotTopRightBotLeft, treeNotTopLeftBotLeftRight, treeNotTopLeftRightBotLeft, treeNotTopLeftRightBotRight, treeNotTopRightBotLeftRight;
    private Vector2 position;
    private Random rand;

    public Tree(float x, float y){
        tree = new Texture("tree.png");
        treeNot = new Texture("treeNot.png");
        treeNotBot = new Texture("treeNotBot.png");
        treeNotBotLeft = new Texture("treeNotBotLeft.png");
        treeNotBotRight = new Texture("treeNotBotRight.png");
        treeNotLeft = new Texture("treeNotLeft.png");
        treeNotRight = new Texture("treeNotRight.png");
        treeNotTop = new Texture("treeNotTop.png");
        treeNotTopLeft = new Texture("treeNotTopLeft.png");
        treeNotTopRight = new Texture("treeNotTopRight.png");
        treeNotAll = new Texture("treeNotAll.png");
        treeNotTopLeftBotRight = new Texture("treeNotTopLeftBotRight.png");
        treeNotTopRightBotLeft = new Texture("treeNotTopRightBotLeft.png");
        treeNotTopLeftBotLeftRight = new Texture("treeNotTopLeftBotLeftRight.png");
        treeNotTopLeftRightBotLeft = new Texture("treeNotTopLeftRightBotLeft.png");
        treeNotTopLeftRightBotRight = new Texture("treeNotTopLeftRightBotRight.png");
        treeNotTopRightBotLeftRight = new Texture("treeNotTopRightBotLeftRight.png");

        position = new Vector2(x, y);
    }

    public Texture getTree() {
        return tree;
    }
    public Texture getTreeImage(int x, int y, Array<Tree> trees){
        if(x >10*20 || y > 18*20) return treeNotAll;
        float treeX, treeY;
        boolean botLeft = true, botRight = true, topLeft = true, topRight = true;
        for(int i = 0; i < trees.size; i++){
            if(Math.abs(x-trees.get(i).getPosition().x) <= 39.9){
                if(Math.abs(y-trees.get(i).getPosition().y) <= 39.9) {
                    treeX = trees.get(i).getPosition().x;
                    treeY = trees.get(i).getPosition().y;
                    if( x > treeX){
                        if( y > treeY ){
                            botLeft = false;
                        } else if ( y < treeY ){
                            topLeft = false;
                        } else {
                            botLeft = false;
                            topLeft = false;
                        }
                    } else if (x < treeX){
                        if( y > treeY ){
                            botRight = false;
                        } else if ( y < treeY ){
                            topRight = false;
                        } else {
                            botRight = false;
                            topRight = false;
                        }
                    } else {
                        if( y > treeY ){
                            botLeft = false;
                            botRight = false;
                        } else if ( y < treeY ){
                            topLeft = false;
                            topRight = false;
                        } else {
                            return treeNotAll;
                        }
                    }
                }

            }
        }

        //add visual cannot place if overlap on border
        if(x == 0) {
            botLeft = false;
            topLeft = false;
            if(y == 0){
                botRight = false;
            } else if(y > 17*20) {
                topRight = false;
            }
        } else if (x > 9*20) {
            botRight = false;
            topRight = false;
            if(y == 0){
                botLeft = false;
            } else if(y > 17*20) {
                topLeft = false;
            }
        } else if (y ==0) {
            botLeft = false;
            botRight = false;
        } else if (y > 17*20){
            topLeft = false;
            topRight = false;
        }

        if(botLeft && botRight && topLeft && !topRight) return treeNotTopRight;
        else if(botLeft && botRight && !topLeft && topRight) return treeNotTopLeft;
        else if(botLeft && botRight && !topLeft && !topRight) return treeNotTop;
        else if(botLeft && !botRight && topLeft && topRight) return treeNotBotRight;
        else if(botLeft && !botRight && topLeft && !topRight) return treeNotRight;
        else if(botLeft && !botRight && !topLeft && topRight) return treeNotTopLeftBotRight;
        else if(botLeft && !botRight && !topLeft && !topRight) return treeNotTopLeftRightBotRight;
        else if(!botLeft && botRight && topLeft && topRight) return treeNotBotLeft;
        else if(!botLeft && botRight && topLeft && !topRight) return treeNotTopRightBotLeft;
        else if(!botLeft && botRight && !topLeft && topRight) return treeNotLeft;
        else if(!botLeft && botRight && !topLeft && !topRight) return treeNotTopLeftRightBotLeft;
        else if(!botLeft && !botRight && topLeft && topRight) return treeNotBot;
        else if(!botLeft && !botRight && topLeft && !topRight) return treeNotTopRightBotLeftRight;
        else if(!botLeft && !botRight && !topLeft && topRight) return treeNotTopLeftBotLeftRight;
        else if(!botLeft && !botRight && !topLeft && !topRight) return treeNotAll;
        else return tree;

    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        tree.dispose();
        treeNot.dispose();
        treeNotBot.dispose();
        treeNotBotLeft.dispose();
        treeNotBotRight.dispose();
        treeNotLeft.dispose();
        treeNotRight.dispose();
        treeNotTop.dispose();
        treeNotTopLeft.dispose();
        treeNotTopRight.dispose();
        treeNotAll.dispose();
        treeNotTopLeftBotRight.dispose();
        treeNotTopRightBotLeft.dispose();
        treeNotTopLeftBotLeftRight.dispose();
        treeNotTopLeftRightBotLeft.dispose();
        treeNotTopLeftRightBotRight.dispose();
        treeNotTopRightBotLeftRight.dispose();
    }
}
