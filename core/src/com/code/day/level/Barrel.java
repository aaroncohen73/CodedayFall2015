package com.code.day.level;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.code.day.gfx.AnimLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by aaron on 11/7/15.
 */
public class Barrel{

    public static final int BARREL_XVEL = 30;
    public static final int BARREL_YVEL = 20; // Just some random value
    public static final int EPSILON = 10;

    private static final Animation BARREL_SIDE_ANIM = AnimLoader.loadAnim("barrelSheet.png", 12, 10, 0, 3, 0.1f);
    private static final Animation BARREL_FRONT_ANIM = AnimLoader.loadAnim("barrelLadderSheet.png", 15, 10, 0, 1, 0.2f);

    private float animTimer;

    private boolean fallMode;
    private boolean beRemoved;

    private Vector2 position;
    private Vector2 velocity;

    private Girder currentGirder;
    private ArrayList<Integer> ladderPath;

    private Barrel() {}

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public boolean getBeRemoved(){
        return beRemoved;
    }

    public boolean pastCurrentGirder(){
        if(velocity.x > 0 && position.x > currentGirder.getEnd().x)
            return true;

        if(velocity.x < 0 && position.x < currentGirder.getBeginning().x)
            return true;

        return false;
    }

    public boolean getFallMode(){
        return fallMode;
    }

    public void setCurrentGirder(Girder girder) {
        currentGirder = girder;
    }

    public void addLadder(int uid) {
        ladderPath.add(uid);
    }

    public ArrayList<Integer> getLadderPath() {
        return ladderPath;
    }

    public void update(float delta)
    {
        if (beRemoved) return;

        //animTimer += delta;

        // Check if the barrel is past the current girder
        if(pastCurrentGirder()) {

            // If the current girder is the last in the current layer, set barrel to fall mode
            if (currentGirder.isLast()) {
                fallMode = true;  // Says to only update the y position
                velocity.x *= -1;
            }

            Girder nextGirder = currentGirder.getNextGirder();

            if(nextGirder == null)
                beRemoved = true;

            currentGirder = nextGirder;
        }

        if(currentGirder != null) {

            // Loop through all the the current girders ladders
            for (Ladder ladder : currentGirder.getLadders()) {

                // TODO: Uncomment the "ladderPath.contains(ladder.getUID()))"
                // If distance between one of the ladders and the barrel position is less than some delta, set to fall mode
                if (/*ladderPath.contains(ladder.getUID())) && */Math.abs(position.x - ladder.getX()) < EPSILON) {
                    fallMode = true;
                    velocity.x *= -1;
                    currentGirder = ladder.getNextGirder();
                    break;
                }
            }

            if(fallMode)
            {
                position.y -= BARREL_YVEL * delta;

                // If the y position is on the current girder line
                if(Math.abs(position.y - ((position.x * currentGirder.getSlope()) + currentGirder.getYIntercept())) < EPSILON)
                    fallMode = false;
            }

            else{
                position.x += velocity.x * delta;
                position.y = (position.x * currentGirder.getSlope()) + currentGirder.getYIntercept();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        if (beRemoved) return;

        Animation currentAnim = fallMode ? BARREL_FRONT_ANIM : BARREL_SIDE_ANIM;

        batch.draw(currentAnim.getKeyFrame(animTimer), position.x, position.y);
    }

    public static Barrel createBarrel(int startX, int startY, int startDir, Girder startGirder) {
        Barrel barrel = new Barrel();

        barrel.animTimer = 0.0f;
        barrel.fallMode = true;
        barrel.beRemoved = false;

        barrel.position = new Vector2(startX, startY);
        barrel.velocity = new Vector2(BARREL_XVEL * startDir, 0);

        barrel.currentGirder = startGirder;
        barrel.ladderPath = new ArrayList<Integer>();

        return barrel;
    }

}
