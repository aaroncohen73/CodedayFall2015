package com.code.day.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.code.day.gfx.AnimLoader;
import com.code.day.input.InputHandler;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;

/**
 * Created by aaron on 11/7/15.
 */
public class Monkey {

    public static final int MONKEY_XPOS = 30;
    public static final int MONKEY_YPOS = 168;
    public static final Animation MONKEY_IDLE = AnimLoader.loadAnim("donkIdle.png", 40, 32, 0, 1, 1.0f);
    public static final Animation MONKEY_GRAB = AnimLoader.loadAnim("donkAttackSheet.png", 43, 32, 0, 2, 0.4f);
    public static final Animation MONKEY_THROW = AnimLoader.loadAnim("donkAttackSheet.png", 43, 32, 2, 3, 0.5f);
    public static final Animation MONKEY_WIN = AnimLoader.loadAnim("donkChamp.png", 45, 32, 0, 2, 1.0f);

    static {
        MONKEY_IDLE.setPlayMode(Animation.PlayMode.LOOP);
        MONKEY_GRAB.setPlayMode(Animation.PlayMode.NORMAL);
        MONKEY_THROW.setPlayMode(Animation.PlayMode.NORMAL);
        MONKEY_WIN.setPlayMode(Animation.PlayMode.LOOP);
    }

    private float animTimer = 0.0f;
    public Animation currentAnim = MONKEY_IDLE;
    public boolean throwMode;

    private Girder monkeyGirder;

    public void setMonkeyGirder(Girder girder) {
        monkeyGirder = girder;
    }

    public Girder getMonkeyGirder() {
        return monkeyGirder;
    }

    public void grabBarrel() {
        if (currentAnim != MONKEY_IDLE) return;

        currentAnim = MONKEY_GRAB;
        animTimer = 0.0f;
    }

    public void throwBarrel() {
        currentAnim = MONKEY_THROW;
        throwMode = false;
        animTimer = 0.0f;
    }

    public void update(float delta) {
        animTimer += delta;

        if (currentAnim == MONKEY_GRAB && currentAnim.isAnimationFinished(animTimer)) {
            throwMode = true;
        }

        if (currentAnim == MONKEY_THROW && currentAnim.isAnimationFinished(animTimer)) {
            currentAnim = MONKEY_IDLE;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentAnim.getKeyFrame(animTimer), MONKEY_XPOS, MONKEY_YPOS);
    }

}
