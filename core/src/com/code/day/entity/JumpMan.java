package com.code.day.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;
import com.code.day.level.Ladder;
import com.code.day.level.Level;

import java.util.ArrayList;

/**
 * Created by merfoo on 11/8/15.
 */
public class JumpMan {
    public static final float JUMP_HEIGHT = Barrel.HEIGHT + 2.0f;
    public static final float JUMP_VELOCITY = 5.5f;
    public static final float RUN_VELOCITY = 4.0f;
    public static final float EPSILON = 2.0f;

    private float animTimer = 0.0f;

    private Vector2 position;
    private Vector2 velocity = new Vector2(0.0f, 0.0f);
    private Vector2 jumpPosition;

    private boolean onLadder = false;
    private boolean jumping = false;
    private boolean jumpRunning = false;

    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;

    private Girder currentGirder = null;
    private Ladder currentLadder = null;

    public JumpMan(float posX, float posY, Girder startGirder){
        position.set(posX, posY);
        currentGirder = startGirder;
    }

    public void update(float delta){
        animTimer += delta;
        updateInput();

        // Change x position if left/right is pressed OR they're jumping AND running
        if((keyLeft || keyRight) || jumpRunning) {
            position.x += (keyLeft ? -1 : 1) * RUN_VELOCITY * delta;

            // Only set y position to girder y if left/right is pressed AND they're NOT jumping AND running
            if(!jumpRunning)
                position.y = (position.x * currentGirder.getSlope()) + currentGirder.getYIntercept();
        }

        // Change y position if they're jumping
        if(jumping){
            // Check if you've jumped high enough, if so, set velocity to downwards
            if(velocity.y > 0 && position.y - jumpPosition.y >= JUMP_HEIGHT)
                velocity.y = -JUMP_VELOCITY;

            // If falling, check if hitting girder, stop falling
            if(velocity.y < 0) {
                currentGirder = getNearestGirder();

                if (Math.abs(position.y - ((position.x * currentGirder.getSlope()) + currentGirder.getYIntercept())) < EPSILON) {
                    jumping = false;
                    jumpRunning = false;
                    velocity.y = 0;
                }
            }

            position.y += velocity.y;
        }

        // TODO: Finish ladder climbing logic
        else{

        }
    }

    private void updateInput(){
        keyUp = false;
        keyDown = false;
        keyLeft = false;
        keyRight = false;

        // TODO: Finish ladder climbing logic
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            Ladder nearestLadder = getNearestLadder();//currentGirder);

            if(nearestLadder != null) {
                currentLadder = nearestLadder;
                onLadder = true;
            }

            keyUp = Gdx.input.isKeyPressed(Input.Keys.UP);
            keyDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        }

        // TODO: Finish ladder climbing logic
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            keyDown = true;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            keyLeft = true;

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            keyRight = true;

        // Jump if NOT jumping AND NOT on ladder
        if(Gdx.input.isKeyPressed(Input.Keys.C) && !jumping && !onLadder){
            jumpPosition = position;
            jumping = true;
            velocity.y = JUMP_VELOCITY;

            // If currently running, set jumpRunning to true
            if(keyLeft || keyRight)
                jumpRunning = true;
        }
    }

    private Girder getNearestGirder() {
//        Girder nearestGirder = null;
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

        for(Girder girder : Level.girders)
            if(position.x >= girder.getBeginning().x && position.x <= girder.getEnd().x)
                if (position.y > (girder.getBeginning().y + girder.getEnd().y) / 2.0f)
                    return girder;

        return null;
    }

    public Ladder getNearestLadder(){
        for(Girder girder : Level.girders) {
            for (Ladder ladder : girder.getLadders()) {
                if (position.x >= ladder.getX() && position.x <= ladder.getX() + ladder.TILE_WIDTH) {
                    float midX = (girder.getBeginning().x + girder.getEnd().x) / 2.0f;
                    float topY = (girder.getSlope() * midX) + girder.getYIntercept();

                    if (position.y <= topY && position.y >= topY - ladder.getHeight())
                        return ladder;
                }
            }
        }

        return null;
    }

    // TODO: Finish drawing sprite function
    public void draw(SpriteBatch batch){
    }
}
