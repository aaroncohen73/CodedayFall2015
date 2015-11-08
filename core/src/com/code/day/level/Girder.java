package com.code.day.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by aaron on 11/7/15.
 */
public class Girder {

    private static final int TILE_WIDTH = 24;
    private static final int TILE_HEIGHT = 8;

    private static final Texture GIRDER_TILABLE = new Texture("girder.png");

    private Vector2 beginning, end;

    private Vector2[] tilePositions;

    private ArrayList<Ladder> ladders;
    private boolean isLast;

    private Girder() {} //Use factory function instead

    public Vector2 getBeginning() {
        return beginning;
    }

    public Vector2 getEnd() {
        return end;
    }

    public Vector2[] getTilePositions() {
        return tilePositions;
    }

    public ArrayList<Ladder> getLadders() {
        return ladders;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }

    public int getYPosAt(int x) {
        for (Vector2 tilePosition : tilePositions) { //Probably a better way to do this, but meh
            if (x > tilePosition.x && x < tilePosition.x + TILE_WIDTH) {
                return (int) Math.floor(tilePosition.y);
            }
        }

        return -1;
    }

    public void addLadder(Ladder ladder) {
        ladders.add(ladder);
    }

    public void draw(SpriteBatch batch) {
        for (Vector2 tilePosition : tilePositions) {
            batch.draw(GIRDER_TILABLE, tilePosition.x, tilePosition.y);
        }

        for (Ladder ladder : ladders) {
            ladder.draw(batch);
        }
    }

    public static Girder createGirder(int startX, int startY, int endX, int endY, boolean isLast) {
        Girder girder = new Girder();

        girder.beginning = new Vector2(startX, startY);
        girder.end = new Vector2(endX, endY);

        int deltaX = TILE_WIDTH * (endX > startX ? 1 : -1);
        int deltaY = TILE_HEIGHT * (endY > startY ? 1 : -1);
        int numTiles = (int) Math.ceil(Math.abs(endX - startX) / TILE_WIDTH);

        girder.tilePositions = new Vector2[numTiles];
        for (int i = 0; i < numTiles; i++) {
            girder.tilePositions[i] = new Vector2(startX + (deltaX * i), startY + (deltaY * i)); //Creating individual tiles
        }

        girder.ladders = new ArrayList<Ladder>();
        girder.isLast = isLast;

        return girder;
    }

}
