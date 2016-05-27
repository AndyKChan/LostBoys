package com.lostboy.game.AStar;

/**
 * Created by Eric Chow on 26/05/2016.
 */
public class Node {
    private int col;
    private int row;
    private int g;
    private int h;
    private int f;
    private boolean blocked;
    private static final int PATHWIDTH = 12;
    private static final int PATHHEIGHT = 20;

    public Node(int col, int row, int g, int h) {
        this.col = col;
        this.row = row;
        this.g = g;
        this.h = Math.abs(col - PATHWIDTH/2) + Math.abs(row - PATHHEIGHT);
        this.f = g + h;
        blocked = false;
    }

    public int getCol() { return col; }
    public int getRow() { return row; }
    public boolean isBottomRow() {
        if(row == 1) {
            return true;
        }
        return false;
    }
    public boolean isTopRow() {
        if(row == PATHHEIGHT) {
            return true;
        }
        return false;
    }
    public boolean isLeftColumn() {
        if(col == 1) {
            return true;
        }
        return false;
    }
    public boolean isRightColumn() {
        if(col == PATHWIDTH) {
            return true;
        }
        return false;
    }
    public boolean isBlocked(){ return blocked; }

}
