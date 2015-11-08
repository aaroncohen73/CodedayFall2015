package com.code.day.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aaron on 11/7/15.
 */
public class Ladder {

    private static final int TILE_WIDTH = 8;
    private static final int TILE_HEIGHT = 5;

    private static final Texture LADDER_TILABLE = new Texture("ladder.png");

    private int x;
    private int height;
    private Vector2[] tilePositions;

    private Girder girder, nextGirder;
    private boolean broken;

    private Ladder() {} //Use factory function instead

    public static Ladder createLadder(int x, Girder beginning, Girder end, boolean broken) {
        Ladder ladder = new Ladder();

        ladder.x = x;

        int beginY = beginning.getYPosAt(x);
        int endY = beginning.getYPosAt(x);

        if (broken) {
            ladder.tilePositions = new Vector2[] {
                    new Vector2(x, beginY - TILE_HEIGHT),
                    new Vector2(x, endY)
            };
        } else {
            int numTiles = (int) Math.ceil((beginY - endY) / TILE_HEIGHT);
            ladder.tilePositions = new Vector2[numTiles];
            for (int i = 1; i < numTiles; i++) {
                ladder.tilePositions[i - 1] = new Vector2(x, beginY - (TILE_HEIGHT * i));
            }
        }

        ladder.girder = beginning;
        ladder.nextGirder = end;
        ladder.broken = broken;

        return ladder;
    }

}
