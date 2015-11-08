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
    Barrel barrel;

    public void load() {
        girders = new ArrayList<Girder>();
        barrels = new ArrayList<Barrel>();

        girders.add(Girder.createGirder(0, 200, 200, 100, true));
        girders.add(Girder.createGirder(0, 50, 250, 100, true));
        girders.get(0).setNextGirder(girders.get(1));
        girders.get(0).addLadder(Ladder.createLadder(100, girders.get(0), girders.get(1), false));
        barrel = barrel.createBarrel(0, 200, 1, girders.get(0));
        //barrel.setPosition(new Vector2(0, 200));
        //barrel.setCurrentGirder(girders.get(0));
        
//        girders.add(Girder.createGirder (15, 160, 126, 160, true));
//        girders.add(Girder.createGirder (100, 159, 224, 157, true));
        //girders.add(Girder.createGirder(50, 160, 200, 175, true));
        //girders.add(Girder.createGirder(50, 130, 200, 125, true));
        //girders.add(Girder.createGirder(50, 100, 200, 95, true));
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
