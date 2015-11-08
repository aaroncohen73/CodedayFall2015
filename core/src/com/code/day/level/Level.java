package com.code.day.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.code.day.entity.Monkey;
import com.code.day.input.InputHandler;
import jdk.internal.util.xml.impl.Input;

/**
 * Created by aaron on 11/7/15.
 */
public class Level {

    private static final Texture BARREL_STACK = new Texture("barrelStack.png");

    private Monkey DK;

    private ArrayList<Girder> girders;
    private ArrayList<Barrel> barrels;

    private boolean throwMode;

    public void load() {
        DK = new Monkey();

        girders = new ArrayList<Girder>();
        barrels = new ArrayList<Barrel>();
        
        girders.add(Girder.createGirder(15, 160, 126, 160, false));//girder1 flat 0
        girders.add(Girder.createGirder(135, 159, 200, 154, true));//girder1 1
        girders.add(Girder.createGirder(31, 126, 223, 135, true));//girder2 2
        girders.add(Girder.createGirder(15, 104, 200, 96, true));//girder3 3
        girders.add(Girder.createGirder(31, 66, 223, 75, true));//girder4 4
        girders.add(Girder.createGirder(15, 44, 200, 36, true));//girder5 5
        girders.add(Girder.createGirder(150, 10, 223, 14, false));//girder6 6
        girders.add(Girder.createGirder(7, 8, 130, 8, true));//girder6 flat 7
        girders.add(Girder.createGirder(79, 177, 100, 177, true));//The_unnamed_woman lower 8
        girders.add(Girder.createGirder(103, 185, 150, 185, true));//The_unnamed_woman higher 9

        girders.get(0).setNextGirder(girders.get(1));
        girders.get(1).setNextGirder(girders.get(2));
        girders.get(2).setNextGirder(girders.get(3));
        girders.get(3).setNextGirder(girders.get(4));
        girders.get(4).setNextGirder(girders.get(5));
        girders.get(5).setNextGirder(girders.get(6));
        girders.get(6).setNextGirder(girders.get(7));

        Ladder.createLadder(104, girders.get(0), girders.get(2), true);//broken ladder on girder0
        Ladder.createLadder(185, girders.get(1), girders.get(2), false);//ladder on on girder1
        Ladder.createLadder(50, girders.get(2), girders.get(3), false);//ladder from girder2 to 3
        Ladder.createLadder(90, girders.get(2), girders.get(3), false);//ladder from girder2 to 3
        Ladder.createLadder(160, girders.get(2), girders.get(3), true);//broken ladder from girder2 to 3
        Ladder.createLadder(80, girders.get(3), girders.get(4), true);//broken ladder from girder3 to 4
        Ladder.createLadder(120, girders.get(3), girders.get(4), false);//ladder from girder3 to 4
        Ladder.createLadder(175, girders.get(3), girders.get(4), false);//ladder from girder3 to 4
        Ladder.createLadder(50, girders.get(4), girders.get(5), false);//ladder from girder4 to 5
        Ladder.createLadder(105, girders.get(4), girders.get(5), false);//ladder from girder4 to 5
        Ladder.createLadder(90, girders.get(5), girders.get(6), true);//ladder from girder5 to 6
        Ladder.createLadder(185, girders.get(5), girders.get(7), false);//ladder from girder5 to 7
        Ladder.createLadder(79, girders.get(8), girders.get(0), false);//ladder to The_unnamed_woman lower
        Ladder.createLadder(95, girders.get(8), girders.get(0), false);//ladder to The_unnamed_woman lower
        Ladder.createLadder(143, girders.get(9), girders.get(1), false);//ladder to The_unnamed_woman lower;

        DK.setMonkeyGirder(girders.get(0));
    }

    public void update(float delta) {
        DK.update(delta);

        if (DK.throwMode) {
            if (InputHandler.SPACE_TRIGGERED) {
                DK.throwBarrel();
                barrels.add(Barrel.createBarrel(Monkey.MONKEY_XPOS + 45, Monkey.MONKEY_YPOS, 1, DK.getMonkeyGirder()));
                InputHandler.SPACE_TRIGGERED = false;
            }
        } else {
            if (InputHandler.SPACE_TRIGGERED) {
                DK.grabBarrel();
                InputHandler.SPACE_TRIGGERED = false;
            }
        }

        // Loop through all the barrels
        for(int barrelIndex = 0; barrelIndex < barrels.size(); barrelIndex++){
            Barrel barrel = barrels.get(barrelIndex);
            barrel.update(delta);
            if (barrel.getBeRemoved()) barrels.remove(barrel);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(BARREL_STACK, 10, 168);
        DK.draw(batch);

        for (Girder girder : girders) {
            girder.draw(batch);
        }

        for (Barrel barrel : barrels) {
            barrel.draw(batch);
        }
    }

}
