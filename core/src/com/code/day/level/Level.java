package com.code.day.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.Math;
import java.util.ArrayList;

/**
 * Created by aaron on 11/7/15.
 */
public class Level {

    private ArrayList<Girder> girders;
    private ArrayList<Barrel> barrels;

    public void load() {
        girders = new ArrayList<Girder>();
        barrels = new ArrayList<Barrel>();

        girders.add(Girder.createGirder(50, 200, 200, 185, true));
        girders.add(Girder.createGirder(50, 165, 200, 150, true));
        Ladder.createLadder(170, girders.get(0), girders.get(1), false);
    }

    public void update() {

        // Loop through all the barrels
        for(Barrel barrel : barrels){

            // Check if the barrel is past the current girder
            if(barrel.pastCurrentGirder()) {

                // If the current girder is the last in the current layer, set barrel to fall mode
                if (barrel.getCurrentGirder().isLast()) {
                    barrel.setFallMode(true);
                    barrel.setVelocity(barrel.getFallVelocity());
                }

                else{

                }

                Girder nextGirder = barrel.getCurrentGirder().getNextGirder();

                if(nextGirder == null) {
                    // TODO: Remove the current barrel
                    int temp = 0;
                }

                else
                    barrel.setCurrentGirder(nextGirder);
            }

            // Loop through all the the current girders ladders
            for(Ladder ladder : barrel.getCurrentGirder().getLadders()){

                // If distance between one of the ladders and the barrel position is less than some delta, set to fall mode
                if(Math.abs(barrel.getPosition().x - ladder.getX()) < 10) // TODO: Set this to some deltaD variable
                    barrel.setFallMode(true);
            }
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
