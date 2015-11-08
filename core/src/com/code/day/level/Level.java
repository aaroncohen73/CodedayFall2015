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

        girders.add(Girder.createGirder(0, 200, 200, 100, true));
        girders.add(Girder.createGirder(0, 50, 250, 100, true));
        girders.get(0).setNextGirder(girders.get(1));
        girders.get(0).addLadder(Ladder.createLadder(100, girders.get(0), girders.get(1), false));

        barrels.add(Barrel.createBarrel(0, 200, 1, girders.get(0)));
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
