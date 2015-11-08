package com.code.day.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.code.day.gfx.AnimLoader;
import com.code.day.input.InputHandler;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;
import com.code.day.level.Ladder;
import com.code.day.level.Level;

import java.util.ArrayList;

/**
 * Created by merfoo on 11/8/15.
 */
public class JumpMan {

    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final Animation JUMPMAN_IDLE_LEFT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 2, 3, 0.1f);
    private static final Animation JUMPMAN_IDLE_RIGHT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 3, 4, 0.1f);
    private static final Animation JUMPMAN_WALK_LEFT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 0, 3, 0.2f);
    private static final Animation JUMPMAN_WALK_RIGHT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 3, 6, 0.2f);
    private static final Animation JUMPMAN_JUMP_LEFT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 0, 1, 0.1f);
    private static final Animation JUMPMAN_JUMP_RIGHT = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 5, 6, 0.1f);
    private static final Animation JUMPMAN_FACE_LADDER = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 6, 7, 0.2f);
    private static final Animation JUMPMAN_CLIMB_LADDER = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 7, 9, 0.4f);
    private static final Animation JUMPMAN_CLIMB_UP = AnimLoader.loadAnim("jumpManSheet.png", 16, 16, 9, 11, 0.2f);

    public static final float JUMP_HEIGHT = Barrel.HEIGHT + 5.0f;
    public static final float JUMP_VELOCITY = 0.75f;
    public static final float RUN_VELOCITY = 25.0f;
    public static final float EPSILON = 2.0f;

    private float animTimer = 0.0f;

    private Vector2 position;
    private Vector2 velocity = new Vector2(0.0f, 0.0f);
    private Vector2 jumpPosition = new Vector2(0.0f, 0.0f);

    private boolean facingRight = true;
    private float animTime = 0.0f;

    private boolean onLadder = false;
    private boolean jumping = false;
    private boolean jumpRunning = false;
    private int jumpDirection = 1;

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

    public JumpMan(int startX, int startY) {
        position = new Vector2(startX, startY);
        velocity = new Vector2(40, 0);
    }

    public void update(float delta){
        animTimer += delta;
        updateInput();
        currentGirder = getNearestGirder();

        if (InputHandler.LEFT_PRESSED) facingRight = false;
        else if (InputHandler.RIGHT_PRESSED) facingRight = true;

        if (InputHandler.ALT_TRIGGERED && !jumping && !onLadder) {
            jumpPosition = position;
            jumping = true;
            velocity.y = JUMP_VELOCITY;

            if (InputHandler.LEFT_PRESSED || InputHandler.RIGHT_PRESSED) jumpRunning = true;

            InputHandler.ALT_TRIGGERED = false;
        }

        // Change x position if left/right is pressed OR they're jumping AND running
        if((keyLeft || keyRight) || jumpRunning) {
            float posXInc = 0.0f;

            if(jumpRunning)
                posXInc = jumpDirection * RUN_VELOCITY * delta;

            // Only set y position to girder y if left/right is pressed AND they're NOT jumping AND running
            if(!jumpRunning) {
                posXInc = (keyLeft ? -1 : 1) * RUN_VELOCITY * delta;
                position.y = (position.x * currentGirder.getSlope()) + currentGirder.getYIntercept() + (Girder.TILE_HEIGHT / 2.0f);
            }

            position.x += posXInc;
        }

        // Change y position if they're jumping
        if(jumping){
            // Check if you've jumped high enough, if so, set velocity to downwards
            if(velocity.y > 0 && Math.abs(position.y - jumpPosition.y) >= JUMP_HEIGHT)
                velocity.y = -JUMP_VELOCITY;

            // If falling, check if hitting girder, stop falling
            if(velocity.y < 0) {
                currentGirder = getNearestGirder();

                if (Math.abs(position.y - ((position.x * currentGirder.getSlope()) + currentGirder.getYIntercept() + (Girder.TILE_HEIGHT / 2.0f))) < EPSILON) {
                    jumping = false;
                    jumpRunning = false;
                    velocity.y = 0;
                }
            }

            position.y += velocity.y;
        }

        // Change y position if they're climbing a ladder
        if(onLadder){
            if(keyUp){
                currentGirder = currentLadder.getGirder();
                position.y += JUMP_VELOCITY;
            }

            else if(keyDown){
                currentGirder = currentLadder.getNextGirder();
                position.y -= JUMP_VELOCITY;
            }

            float midX = currentLadder.getX() + (Ladder.TILE_WIDTH / 2.0f);
            float ladTop = ((midX * currentLadder.getGirder().getSlope()) + currentLadder.getGirder().getYIntercept());// - (Girder.TILE_HEIGHT / 2.0f));

            if ((ladTop - position.y) <= 0) {
                onLadder = false;
                System.out.println("1 WHY DOES THIS HAVE TO HAPPEN LIKE THIS");
            }

            float ladBot = ((midX * currentLadder.getNextGirder().getSlope()) + currentLadder.getNextGirder().getYIntercept() + Girder.TILE_HEIGHT + HEIGHT + 500);
            System.out.println("POsition - ladBot : " + (position.y) + " :: " + (ladBot));
            if ((position.y - ladBot) <= 0) {
                onLadder = false;
                System.out.println("2ND FOR LOOP THING IF STATEMENT MANN IDK TBH");
            }
        }
    }

    public void draw(SpriteBatch batch) {
        Animation currentAnim = JUMPMAN_IDLE_RIGHT;
        if (onLadder) currentAnim = JUMPMAN_CLIMB_LADDER;
        else if (facingRight && InputHandler.RIGHT_PRESSED) currentAnim = JUMPMAN_WALK_RIGHT;
        else if (!facingRight && InputHandler.LEFT_PRESSED) currentAnim = JUMPMAN_WALK_LEFT;
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

        keyUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        keyDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        // TODO: Finish ladder climbing logic
        if(!jumping && !onLadder && (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
            Ladder nearestLadder = getNearestLadder();//currentGirder);

            if (nearestLadder != null) {
                currentLadder = nearestLadder;
                onLadder = true;
                System.out.println("FOUND NEAR LADDER YOOO");
            }
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !jumping && !onLadder)
            keyLeft = true;

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !jumping && !onLadder)
            keyRight = true;

        // Jump if NOT jumping AND NOT on ladder
        if(Gdx.input.isKeyPressed(Input.Keys.C) && !jumping && !onLadder){
            jumpPosition.x = position.x;
            jumpPosition.y = position.y;
            jumping = true;
            velocity.y = JUMP_VELOCITY;

            // If currently running, set jumpRunning to true
            if(keyLeft || keyRight) {
                jumpRunning = true;
                jumpDirection = keyLeft ? -1 : 1;
            }
        }
    }

    private Girder getNearestGirder() {
        for(Girder girder : Level.girders)
            if(position.x >= girder.getBeginning().x && position.x <= girder.getEnd().x)
                if (position.y >= (girder.getBeginning().y + girder.getEnd().y) / 2.0f)
                    return girder;

        return null;
    }

    public Ladder getNearestLadder(){
        for(Girder girder : Level.girders) {
            for (Ladder ladder : girder.getLadders()) {
                // Within X
                if (position.x + (WIDTH / 2.0f ) >= ladder.getX() && position.x + (WIDTH / 2.0f ) <= ladder.getX() + ladder.TILE_WIDTH) {
                    float midX = ladder.getX() + (Ladder.TILE_WIDTH / 2.0f);
                    float topY = (girder.getSlope() * midX) + girder.getYIntercept() + (Girder.TILE_HEIGHT / 2.0f) + EPSILON;
                    float botY = (midX * ladder.getNextGirder().getSlope()) + ladder.getNextGirder().getYIntercept() + (Girder.TILE_HEIGHT / 2.0f) - EPSILON;

                    // Within Y
                    if (position.y - EPSILON <= topY && position.y >= botY && !ladder.isBroken())
                    {
                        System.out.println("FOUND A LADDER FOR YOU MAN FUK");
                        return ladder;
                    }
                }
            }
        }

        return null;
    }
}
