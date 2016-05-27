package com.lostboy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Eric Chow on 27/05/2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private TextureRegion region;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(Texture texture, int frameWidthCount, int frameHeightCount, float cycleTime) {
        region = new TextureRegion(texture);
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameWidthCount;
        int frameHeight = region.getRegionHeight() / frameHeightCount;
        for(int j = 0; j < frameHeightCount; j++){
            for(int i = 0; i < frameWidthCount; i++){
                frames.add(new TextureRegion(region, i*frameWidth, j*frameHeight, frameWidth, frameHeight));
            }
        }
        this.frameCount = frameWidthCount;
        maxFrameTime = cycleTime / frameWidthCount;
        frame = 0;
    }

    public void update(float dt, int direction, int changeDirection){
        //boy direction: 0 = up, 1 = left, 2 = right, 3 = down;
        currentFrameTime += dt;
        if(changeDirection == 1)
            frame = direction*frame;

        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount*(direction+1))
            frame = direction * frameCount;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
