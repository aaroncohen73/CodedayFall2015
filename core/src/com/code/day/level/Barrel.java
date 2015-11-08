package com.code.day.level;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.code.day.gfx.AnimLoader;

/**
 * Created by aaron on 11/7/15.
 */
public class Barrel{

    public static final int BARREL_XVEL = 30;

    private static final Animation BARREL_SIDE_ANIM = AnimLoader.loadAnim("barrelSheet.png", 16, 16, 0, 7, 0.1f);
    private static final Animation BARREL_FRONT_ANIM = AnimLoader.loadAnim("barrelLadderSheet.png", 16, 16, 0, 1, 0.2f);

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2(30, 0);

    private Girder currentGirder;

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

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Girder getCurrentGirder() {
        return currentGirder;
    }

    public void setCurrentGirder(Girder currentGirder) {
        this.currentGirder = currentGirder;
    }

}
