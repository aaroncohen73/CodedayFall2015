package com.code.day.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.code.day.gfx.AnimLoader;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;
import com.code.day.level.Level;

import java.util.ArrayList;

/**
 * Created by merfoo on 11/8/15.
 */
public class JumpMan {

    private static final Animation JUMPMAN_IDLE_LEFT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 2, 3, 0.1f);
    private static final Animation JUMPMAN_IDLE_RIGHT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 3, 4, 0.1f);
    private static final Animation JUMPMAN_WALK_LEFT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 0, 3, 0.2f);
    private static final Animation JUMPMAN_WALK_RIGHT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 3, 6, 0.2f);
    private static final Animation JUMPMAN_JUMP_LEFT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 0, 1, 0.1f);
    private static final Animation JUMPMAN_JUMP_RIGHT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 5, 6, 0.1f);
    private static final Animation JUMPMAN_FACE_LADDER = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 6, 7, 0.2f);
    private static final Animation JUMPMAN_CLIMB_LADDER = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 7, 9, 0.4f);
    private static final Animation JUMPMAN_CLIMB_UP = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 9, 11, 0.2f);

    public static final float JUMP_HEIGHT = Barrel.HEIGHT + 2.0f;
    public static final float JUMP_VELOCITY = 2.5f;
    public static final float EPSILON = 2.0f;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 jumpPosition;

    private boolean facingRight = true;
    private float animTime = 0.0f;

    private boolean onLadder = false;
    private boolean jumping = false;
    private boolean jumpRunning = false;

    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;

    private Girder currentGirder = null;

    public JumpMan(int startX, int startY) {
        position = new Vector2(startX, startY);
        velocity = new Vector2(40, 0);
    }

    public void update(float delta){
        updateInput();
        currentGirder = getNearestGirder(Level.girders);

        if (keyLeft) facingRight = false;
        if (keyRight) facingRight = true;

        if((keyLeft || keyRight) || jumpRunning) {
            position.x += (keyLeft ? -1 : 1) * velocity.x * delta;

            if(!jumpRunning)
                position.y = (position.x * currentGirder.getSlope()) + currentGirder.getYIntercept();
        }

        if(jumping){
            // Check if you've jumped high enough, if so, set velocity to downwards
            if(velocity.y > 0 && position.y - jumpPosition.y >= JUMP_HEIGHT)
                velocity.y = -JUMP_VELOCITY;

            // If falling, check if hitting girder, stop falling
            if(velocity.y < 0) {
                if (Math.abs(position.y - ((position.x * currentGirder.getSlope()) + currentGirder.getYIntercept())) < EPSILON) {
                    jumping = false;
                    jumpRunning = false;
                    velocity.y = 0;
                }
            }

            position.y += velocity.y;
        }
    }

    public void draw(SpriteBatch batch) {
        Animation currentAnim = JUMPMAN_IDLE_RIGHT;
        if (onLadder) currentAnim = JUMPMAN_CLIMB_LADDER;
        else if (facingRight && keyRight) currentAnim = JUMPMAN_WALK_RIGHT;
        else if (!facingRight && keyLeft) currentAnim = JUMPMAN_WALK_LEFT;
        else if (facingRight && jumping) currentAnim = JUMPMAN_JUMP_RIGHT;
        else if (!facingRight && jumping) currentAnim = JUMPMAN_JUMP_LEFT;
        else if (facingRight) currentAnim = JUMPMAN_IDLE_RIGHT;
        else if (!facingRight) currentAnim = JUMPMAN_IDLE_LEFT;

        batch.draw(currentAnim.getKeyFrame(animTime), position.x, position.y);
    }

    private void updateInput(){
        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            keyUp = true;

        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            keyDown = true;

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            keyLeft = true;

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            keyRight = true;

        // Jump if not jumping, not on ladder
        else if(Gdx.input.isKeyPressed(Input.Keys.C) && !jumping && !onLadder){
            jumpPosition = position;
            jumping = true;
            velocity.y = JUMP_VELOCITY;

            // If currently running, set jumpRunning to true
            if(keyLeft || keyRight)
                jumpRunning = true;
        }
    }

    private Girder getNearestGirder(ArrayList<Girder> girders) {
        Girder nearestGirder = null;
//        float smallestDist = 0.0f;
//
//        for (Girder girder : girders) {
//            Vector2 beg = girder.getBeginning();
//            Vector2 end = girder.getEnd();
//            Vector2 cen = new Vector2((beg.x + end.x) / 2.0f, (beg.y + end.y) / 2.0f);
//            float small = Vector2.dst(position.x, position.y, cen.x, cen.y);
//
//            if (small < smallestDist) {
//                nearestGirder = girder;
//                smallestDist = small;
//            }
//        }

        for(Girder girder : girders)
            if(position.x >= girder.getBeginning().x && position.x <= girder.getEnd().x)
                if (position.y > (girder.getBeginning().y + girder.getEnd().y) / 2.0f)
                    return girder;

        return nearestGirder;
    }
}
