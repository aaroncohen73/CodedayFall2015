package com.code.day.level;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.code.day.gfx.AnimLoader;

/**
 * Created by aaron on 11/7/15.
 */
public class Barrel{

    private static final Animation BARREL_SIDE_ANIM = AnimLoader.loadAnim("barrel.png", 16, 16, 0, 7, 0.1f);
    private static final Animation BARREL_FRONT_ANIM = AnimLoader.loadAnim("barrel2.png", 16, 16, 0, 1, 0.2f);

    private Vector2 position;
    private Vector2 velocity;

    private Girder current;

}
