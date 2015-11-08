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

        girders.add(Girder.createGirder(15, 0, 126, 0, true));
        girders.add(Girder.createGirder(127, 0, 224, 0, true));
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
