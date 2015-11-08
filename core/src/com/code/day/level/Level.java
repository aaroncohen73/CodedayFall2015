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
    Barrel barrel = new Barrel();

    public void load() {
        girders = new ArrayList<Girder>();
        barrels = new ArrayList<Barrel>();

        girders.add(Girder.createGirder(0, 200, 200, 100, true));
        barrel.setPosition(new Vector2(0, 200));
        barrel.setCurrentGirder(girders.get(0));
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

        barrel.update(1/60.0f);
        barrel.draw(batch);
//        for (Barrel barrel : barrels) {
//            barrel.draw(batch);
//        }
    }

}
