package com.code.day.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by aaron on 11/7/15.
 */
public class AnimLoader {

    public static Animation loadAnim(String animPath, int frameWidth, int frameHeight, int startFrame, int endFrame, float frameDiff) {
        Texture frames = new Texture(Gdx.files.internal(animPath));
        int WIDTH_TILES = frames.getWidth() / frameWidth;
        int HEIGHT_TILES = frames.getHeight() / frameHeight;

        TextureRegion[][] tmp = TextureRegion.split(frames, frameWidth, frameHeight);
        TextureRegion[] finalFrames = new TextureRegion[endFrame - startFrame];
        for(int y = 0; y < HEIGHT_TILES; y++) {
            for(int x = 0; x < WIDTH_TILES; x++) {
                int frameNum =  y * WIDTH_TILES + x;
                if(frameNum >= startFrame && frameNum < endFrame) {
                    finalFrames[frameNum - startFrame] = tmp[y][x];
                }
            }
        }

        return new Animation(frameDiff, finalFrames);
    }

}
