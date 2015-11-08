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
        
        girders.add(Girder.createGirder (15, 160, 126, 160, false));//girder1 flat
        girders.add(Girder.createGirder (135, 159, 200, 154, true));//girder1
        girders.add(Girder.createGirder(31, 126, 223, 135, true));//girder2
        girders.add(Girder.createGirder(15, 104, 200, 96, true));//girder3
        girders.add(Girder.createGirder(31, 66, 223, 75, true));//girder4
        girders.add(Girder.createGirder(15, 44, 200, 36, true));//girder5
        girders.add(Girder.createGirder(150, 10, 223, 14, false));//girder6
        girders.add(Girder.createGirder(7, 8, 130, 8, true));//girder6 flat
        girders.add(Girder.createGirder(79, 177, 100, 177, true));//The_unnamed_woman lower
        girders.add(Girder.createGirder(103, 185, 150, 185, true));//peach higher

        girders.get(0).setNextGirder(girders.get(1));
        girders.get(1).setNextGirder(girders.get(2));
        girders.get(2).setNextGirder(girders.get(3));
        girders.get(3).setNextGirder(girders.get(4));
        girders.get(4).setNextGirder(girders.get(5));
        girders.get(5).setNextGirder(girders.get(6));
        girders.get(6).setNextGirder(girders.get(7));


        Ladder.createLadder(150, girders.get(1), girders.get(2), false);

        barrels.add(Barrel.createBarrel(15, 160, 1, girders.get(0)));
        barrels.get(0).addLadder(1);
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
