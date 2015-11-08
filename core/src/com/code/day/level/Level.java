package com.code.day.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by aaron on 11/7/15.
 */
public class Level {

    private ArrayList<Girder> girders;
    private ArrayList<Barrel> barrels;

    public void load() {
        girders = new ArrayList<Girder>();
        barrels = new ArrayList<Barrel>();

        girders.add(Girder.createGirder (15, 160, 126, 160, true));//girder1 flat
        girders.add(Girder.createGirder (135, 159, 200, 154, true));//girder1
        girders.add(Girder.createGirder(31, 126, 223, 135, true));//girder2
        girders.add(Girder.createGirder(15, 104, 200, 96, true));//girder3
        girders.add(Girder.createGirder(31, 66, 223, 75, true));//girder4
        girders.add(Girder.createGirder(15, 44, 200, 36, true));//girder5
        girders.add(Girder.createGirder(7, 8, 130, 8, true));//girder6 flat
        girders.add(Girder.createGirder(150, 10, 223, 14, true));//girder6
        girders.add(Girder.createGirder(79, 177, 100, 177, true));//The_unnamed_woman lower
        girders.add(Girder.createGirder(103, 185, 150, 185, true));//peach higher
    }

    public void update() {

        // Loop through all the barrels
        for(int barrelIndex = 0; barrelIndex < barrels.size(); barrelIndex++){
            Barrel barrel = barrels.get(barrelIndex);


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
