package com.lostboy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Andy on 5/17/2016.
 */
public class Tree {
    private Texture tree;
    private Texture treeNot;
    private Vector2 position;
    private Random rand;

    public Tree(float x, float y){
        tree = new Texture("tree.png");
        treeNot = new Texture("treeNot.png");

        position = new Vector2(x, y);
    }

    public Texture getTree() {
        return tree;
    }
    public Texture getTreeNot() {
        return treeNot;
    }

    public Vector2 getPosition() {
        return position;
    }
}
