package com.lostboy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Eric Chow on 27/05/2016.
 */
public class boy {
    private Vector3 position;
    private Vector3 velocity;
    private Animation boyAnimation;
    private Texture boy;
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 3;
    private int direction;
    private int targetX;
    private int targetY;

    public boy(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        boy = new Texture("boyAnimation.png");
        boyAnimation = new Animation(boy, 4, 4, 0.5f);
        direction = DOWN;
        targetX = 100;
        targetY = 0;
    }

    public void update(float dt){
        position.add(velocity.x, velocity.y, 0);
        this.move(targetX, targetY);

        if(position.y < 0)
            position.y = 0;
        else if(position.y > 360)
            position.y = 360;
        if(position.x < 0)
            position.x = 0;
        else if (position.x > 200)
            position.x = 200;

        int oldDirection = direction;
        int changeDirection = 0;
        if(velocity.x < 0)
            direction = LEFT;
        else if (velocity.x > 0)
            direction = RIGHT;
        else if (velocity.y < 0)
            direction = UP;
        else if (velocity.y > 0)
            direction = DOWN;
        else direction = DOWN;

        if(oldDirection != direction)
            changeDirection = 1;

        boyAnimation.update(dt, direction, changeDirection);
    }

    public Vector3 getPosition() {
        return position;
    }
    public Vector3 getVelocity() {
        return velocity;
    }

    public void move(int targetX, int targetY){
        if(targetX > 200) targetX = 200;
        if(targetY > 360) targetY = 360;
        this.targetX = targetX;
        this.targetY = targetY;
        int leftright = this.targetX - (int)this.getPosition().x;
        int updown = this.targetY - (int)this.getPosition().y;

        if(leftright > 5)
            velocity.x = 1;
        else if (leftright < -5)
            velocity.x = -1;
        else if(updown > 5)
            velocity.y = 1;
        else if(updown < -5)
            velocity.y = -1;
        if(Math.abs(leftright) < 5)
            velocity.x = 0;
        if(Math.abs(updown) < 5)
            velocity.y = 0;
    }

    public TextureRegion getBoy() {
        return boyAnimation.getFrame();
    }

    public void dispose() {
        boy.dispose();
    }
}
