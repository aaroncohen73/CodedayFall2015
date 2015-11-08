package com.code.day.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aaron on 11/7/15.
 */
public class Level {

    private ArrayList<Girder> girders;
    private ArrayList<Barrel> barrels;

    public void load() {
        girders = new ArrayList<Girder>();
        barrels = new ArrayList<Barrel>();

        girders.add(Girder.createGirder (15, 160, 126, 160, true));//girder1 flat 0
        girders.add(Girder.createGirder (135, 159, 200, 154, true));//girder1 1
        girders.add(Girder.createGirder(31, 126, 223, 135, true));//girder2 2
        girders.add(Girder.createGirder(15, 104, 200, 96, true));//girder3 3
        girders.add(Girder.createGirder(31, 66, 223, 75, true));//girder4 4
        girders.add(Girder.createGirder(15, 44, 200, 36, true));//girder5 5
        girders.add(Girder.createGirder(7, 8, 130, 8, true));//girder6 flat 6
        girders.add(Girder.createGirder(150, 10, 223, 14, true));//girder6 7
        girders.add(Girder.createGirder(79, 177, 100, 177, true));//The_unnamed_woman lower 8
        girders.add(Girder.createGirder(103, 185, 150, 185, true));//The_unnamed_woman higher 9
        Ladder.createLadder(104, girders.get(0), girders.get(2), true);


    }

    public void update(float delta) {

        // Loop through all the barrels
        for(int barrelIndex = 0; barrelIndex < barrels.size(); barrelIndex++){
            Barrel barrel = barrels.get(barrelIndex);
            barrel.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        for (Girder girder : girders) {
            girder.draw(batch);
        }

        for (Barrel barrel : barrels) {
            barrel.draw(batch);
        }
    }

}
