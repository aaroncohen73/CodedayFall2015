package com.code.day.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.code.day.gfx.AnimLoader;

/**
 * Created by aaron on 11/7/15.
 */
public class Ladder {

    public static final int TILE_WIDTH = 8;
    private static final int TILE_HEIGHT = 5;

    private static final Texture LADDER_TILABLE = new Texture("ladder.png");
    private static final Texture LADDER_LABELS = new Texture("ladderLabels.png");

    public static final TextureRegion LABEL_0 = new TextureRegion(LADDER_LABELS, 0, 0, 10, 10);
    public static final TextureRegion LABEL_1 = new TextureRegion(LADDER_LABELS, 10, 0, 10, 10);
    public static final TextureRegion LABEL_2 = new TextureRegion(LADDER_LABELS, 20, 0, 10, 10);
    public static final TextureRegion LABEL_3 = new TextureRegion(LADDER_LABELS, 30, 0, 10, 10);

    private static int currentUID = 0;

    private int x;
    private int height;
    private Vector2[] tilePositions;

    private int uid;
    private int label = -1; // -1 == no label
    private Girder girder, nextGirder;
    private boolean broken;

    private Ladder() {} //Use factory function instead

    public int getX() {
        return x;
    }

    public int getHeight() {
        return height;
    }

    public Vector2[] getTilePositions() {
        return tilePositions;
    }

    public int getUID() {
        return uid;
    }

    public void setLabel(int val) {
        label = val;
    }

    public int getLabel() {
        return label;
    }

    public Girder getGirder() {
        return girder;
    }

    public Girder getNextGirder() {
        return nextGirder;
    }

    public boolean isBroken() {
        return broken;
    }

    public void draw(SpriteBatch batch) {
        for (Vector2 tilePosition : tilePositions) {
            batch.draw(LADDER_TILABLE, tilePosition.x, tilePosition.y);
        }

        switch (label) {
            case 0:
                batch.draw(LABEL_0, tilePositions[0].x - 1, tilePositions[0].y - 5);
                break;
            case 1:
                batch.draw(LABEL_1, tilePositions[0].x - 1, tilePositions[0].y - 5);
                break;
            case 2:
                batch.draw(LABEL_2, tilePositions[0].x - 1, tilePositions[0].y - 5);
                break;
            case 3:
                batch.draw(LABEL_3, tilePositions[0].x - 1, tilePositions[0].y - 5);
                break;
            default:
                break;
        }
    }

    public static Ladder createLadder(int x, Girder beginning, Girder end, boolean broken) {
        Ladder ladder = new Ladder();

        ladder.x = x;

        int beginY = beginning.getYPosAt(x);
        int endY = end.getYPosAt(x);

        if (broken) {
            ladder.tilePositions = new Vector2[] {
                    new Vector2(x, beginY - TILE_HEIGHT),
                    new Vector2(x, endY + (TILE_HEIGHT * 1.5f))
            };
        } else {
            int numTiles = (int) Math.ceil((beginY - endY) / TILE_HEIGHT);
            ladder.tilePositions = new Vector2[numTiles];
            for (int i = 1; i < numTiles + 1; i++) {
                ladder.tilePositions[i - 1] = new Vector2(x, beginY - (TILE_HEIGHT * i));
            }
        }

        ladder.uid = currentUID++;
        ladder.girder = beginning;
        ladder.nextGirder = end;
        ladder.broken = broken;
        ladder.height = beginY - endY;

        beginning.addLadder(ladder);

        return ladder;
    }

}
