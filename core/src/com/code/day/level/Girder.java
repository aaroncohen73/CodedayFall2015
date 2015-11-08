package com.code.day.level;

import com.badlogic.gdx.graphics.Texture;
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

    /**
     * Get the y position of the top of the girder at a given x value
     * @param x The X position
     * @return The y position of the top of the girder if the girder exists there, -1 otherwise
     */
    public int getYPosAt(int x) {
        return -1;
    }

    public void addLadder(Ladder ladder) {
        ladders.add(ladder);
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
