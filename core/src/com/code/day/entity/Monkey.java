package com.code.day.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.code.day.gfx.AnimLoader;
import com.code.day.level.Barrel;
import com.code.day.level.Girder;

/**
 * Created by aaron on 11/7/15.
 */
public class Monkey {

    public static final int MONKEY_XPOS = 30;
    public static final int MONKEY_YPOS = 168;
    public static final Animation MONKEY_IDLE = AnimLoader.loadAnim("donkIdle.png", 40, 32, 0, 1, 1.0f);
    public static final Animation MONKEY_ATTACK = AnimLoader.loadAnim("donkAttackSheet.png", 43, 32, 0, 3, 0.5f);
    public static final Animation MONKEY_WIN = AnimLoader.loadAnim("donkChamp.png", 45, 32, 0, 2, 1.0f);

    static {
        MONKEY_IDLE.setPlayMode(Animation.PlayMode.LOOP);
        MONKEY_ATTACK.setPlayMode(Animation.PlayMode.NORMAL);
        MONKEY_WIN.setPlayMode(Animation.PlayMode.LOOP);
    }

    private float animTimer = 0.0f;
    private Animation currentAnim = MONKEY_IDLE;

    private Girder monkeyGirder;
    private float barrelCooldown = 0.0f;
    public Barrel toThrow = null;

    public void setMonkeyGirder(Girder girder) {
        monkeyGirder = girder;
    }

    public Girder getMonkeyGirder() {
        return monkeyGirder;
    }

    public void throwBarrel() {
        if (barrelCooldown > 0.0f) return;

        animTimer = 0.0f;
        currentAnim = MONKEY_ATTACK;
        barrelCooldown = 5.0f;
    }

    public void update(float delta) {
        animTimer += delta;
        if (currentAnim == MONKEY_ATTACK && currentAnim.isAnimationFinished(animTimer)) {
            currentAnim = MONKEY_IDLE;
            toThrow = Barrel.createBarrel(MONKEY_XPOS + 45, MONKEY_YPOS, 1, monkeyGirder);
        }

        if (barrelCooldown > 0) {
            barrelCooldown -= delta;
            if (barrelCooldown < 0) barrelCooldown = 0.0f;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentAnim.getKeyFrame(animTimer), MONKEY_XPOS, MONKEY_YPOS);
    }

}
