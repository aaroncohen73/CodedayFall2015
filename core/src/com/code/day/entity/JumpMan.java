package com.code.day.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;
import com.code.day.level.Level;

import java.util.ArrayList;

/**
 * Created by merfoo on 11/8/15.
 */
public class JumpMan {
    public static final float JUMP_HEIGHT = Barrel.HEIGHT + 2.0f;
    public static final float JUMP_VELOCITY = 2.5f;
    public static final float EPSILON = 2.0f;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 jumpPosition;

    private boolean onLadder = false;
    private boolean jumping = false;
    private boolean jumpRunning = false;

    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;

    private Girder currentGirder = null;

    public void update(float delta){
        updateInput();

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
                currentGirder = getNearestGirder(Level.girders);

                if (Math.abs(position.y - ((position.x * currentGirder.getSlope()) + currentGirder.getYIntercept())) < EPSILON) {
                    jumping = false;
                    jumpRunning = false;
                    velocity.y = 0;
                }
            }

            position.y += velocity.y;
        }
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
