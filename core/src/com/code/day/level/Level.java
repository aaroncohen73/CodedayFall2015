package com.code.day.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.code.day.entity.Monkey;
import com.code.day.input.InputHandler;

/**
 * Created by aaron on 11/7/15.
 */
public class Level {

    private static final Texture BARREL_STACK = new Texture("barrelStack.png");

    private Monkey DK;
    private int throwLevel = 0;
    private Barrel nextBarrel = null;

    public static ArrayList<Girder> girders;
    private ArrayList<Ladder> ladders;
    private ArrayList<Barrel> barrels;

    public void load() {
        DK = new Monkey();

        girders = new ArrayList<Girder>();
        ladders = new ArrayList<Ladder>();
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

        ladders.add(Ladder.createLadder(104, girders.get(0), girders.get(2), true));//broken ladder on girder0
        ladders.add(Ladder.createLadder(185, girders.get(1), girders.get(2), false));//ladder on on girder1
        ladders.add(Ladder.createLadder(50, girders.get(2), girders.get(3), false));//ladder from girder2 to 3
        ladders.add(Ladder.createLadder(90, girders.get(2), girders.get(3), false));//ladder from girder2 to 3
        ladders.add(Ladder.createLadder(160, girders.get(2), girders.get(3), true));//broken ladder from girder2 to 3
        ladders.add(Ladder.createLadder(80, girders.get(3), girders.get(4), true));//broken ladder from girder3 to 4
        ladders.add(Ladder.createLadder(120, girders.get(3), girders.get(4), false));//ladder from girder3 to 4
        ladders.add(Ladder.createLadder(175, girders.get(3), girders.get(4), false));//ladder from girder3 to 4
        ladders.add(Ladder.createLadder(50, girders.get(4), girders.get(5), false));//ladder from girder4 to 5
        ladders.add(Ladder.createLadder(105, girders.get(4), girders.get(5), false));//ladder from girder4 to 5
        ladders.add(Ladder.createLadder(90, girders.get(5), girders.get(7), true));//ladder from girder5 to 6
        ladders.add(Ladder.createLadder(185, girders.get(5), girders.get(6), false));//ladder from girder5 to 7
        Ladder.createLadder(79, girders.get(8), girders.get(0), false);//ladder to The_unnamed_woman lower
        Ladder.createLadder(95, girders.get(8), girders.get(0), false);//ladder to The_unnamed_woman lower
        Ladder.createLadder(143, girders.get(9), girders.get(1), false);//ladder to The_unnamed_woman lower;

        DK.setMonkeyGirder(girders.get(0));
    }

    int i = 0;
    int j = 0;

    public void update(float delta) {
        DK.update(delta);

        for(Ladder ladder : ladders) {
            ladder.setLabel(-1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            i++;

        if(Gdx.input.isKeyPressed(Input.Keys.B))
            j++;

        System.out.println(i + " :: " + j);

        if (DK.throwMode) {
            switch (throwLevel) {
                case 1:
                    ladders.get(0).setLabel(1);
                    ladders.get(1).setLabel(2);

                    if (InputHandler.NUM1_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(0).getUID());
                        throwLevel += 1;
                        InputHandler.NUM1_TRIGGERED = false;
                    } else if (InputHandler.NUM2_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(1).getUID());
                        throwLevel += 1;
                        InputHandler.NUM2_TRIGGERED = false;
                    } else if (InputHandler.NUM3_TRIGGERED) {
                        InputHandler.NUM3_TRIGGERED = false;
                    }
                    break;
                case 2:
                    ladders.get(2).setLabel(1);
                    ladders.get(3).setLabel(2);
                    ladders.get(4).setLabel(3);

                    if (InputHandler.NUM1_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(2).getUID());
                        throwLevel += 1;
                        InputHandler.NUM1_TRIGGERED = false;
                    } else if (InputHandler.NUM2_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(3).getUID());
                        throwLevel += 1;
                        InputHandler.NUM2_TRIGGERED = false;
                    } else if (InputHandler.NUM3_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(4).getUID());
                        throwLevel += 1;
                        InputHandler.NUM3_TRIGGERED = false;
                    }
                    break;
                case 3:
                    ladders.get(5).setLabel(1);
                    ladders.get(6).setLabel(2);
                    ladders.get(7).setLabel(3);

                    if (InputHandler.NUM1_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(5).getUID());
                        throwLevel += 1;
                        InputHandler.NUM1_TRIGGERED = false;
                    } else if (InputHandler.NUM2_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(6).getUID());
                        throwLevel += 1;
                        InputHandler.NUM2_TRIGGERED = false;
                    } else if (InputHandler.NUM3_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(7).getUID());
                        throwLevel += 1;
                        InputHandler.NUM3_TRIGGERED = false;
                    }
                    break;
                case 4:
                    ladders.get(8).setLabel(1);
                    ladders.get(9).setLabel(2);

                    if (InputHandler.NUM1_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(8).getUID());
                        throwLevel += 1;
                        InputHandler.NUM1_TRIGGERED = false;
                    } else if (InputHandler.NUM2_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(9).getUID());
                        throwLevel += 1;
                        InputHandler.NUM2_TRIGGERED = false;
                    } else if (InputHandler.NUM3_TRIGGERED) {
                        InputHandler.NUM3_TRIGGERED = false;
                    }
                    break;
                case 5:
                    ladders.get(10).setLabel(1);
                    ladders.get(11).setLabel(2);

                    if (InputHandler.NUM1_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(10).getUID());
                        throwLevel += 1;
                        InputHandler.NUM1_TRIGGERED = false;
                    } else if (InputHandler.NUM2_TRIGGERED) {
                        nextBarrel.addLadder(ladders.get(11).getUID());
                        throwLevel += 1;
                        InputHandler.NUM2_TRIGGERED = false;
                    } else if (InputHandler.NUM3_TRIGGERED) {
                        InputHandler.NUM3_TRIGGERED = false;
                    }
                    break;
            }

            if (InputHandler.SPACE_TRIGGERED || throwLevel > 5) {
                DK.throwBarrel();
                barrels.add(nextBarrel);
                throwLevel = 0;
                InputHandler.SPACE_TRIGGERED = false;
            } else if (InputHandler.NUM0_TRIGGERED) {
                nextBarrel.addLadder(-1);
                throwLevel += 1;
                if (throwLevel > 5) InputHandler.SPACE_TRIGGERED = true;
                InputHandler.NUM0_TRIGGERED = false;
            } else if (InputHandler.BACKSPACE_TRIGGERED) {
                throwLevel -= 1;
                if (throwLevel < 1) throwLevel = 1;
                else nextBarrel.getLadderPath().remove(nextBarrel.getLadderPath().size() - 1);
                InputHandler.BACKSPACE_TRIGGERED = false;
            } else if (InputHandler.ESC_TRIGGERED) {
                DK.dropBarrel();
                nextBarrel = null;
                InputHandler.ESC_TRIGGERED = false;
            }
        } else {
            if (InputHandler.SPACE_TRIGGERED && DK.barrelCooldown == 0) {
                DK.grabBarrel();
                nextBarrel = Barrel.createBarrel(Monkey.MONKEY_XPOS + 45, Monkey.MONKEY_YPOS, 1, DK.getMonkeyGirder());
                throwLevel = 1;
                InputHandler.SPACE_TRIGGERED = false;
                InputHandler.NUM0_TRIGGERED = false;
                InputHandler.NUM1_TRIGGERED = false;
                InputHandler.NUM2_TRIGGERED = false;
                InputHandler.NUM3_TRIGGERED = false;
                InputHandler.BACKSPACE_TRIGGERED = false;
                InputHandler.ESC_TRIGGERED = false;
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

        if (DK.throwMode) {
            batch.draw(Ladder.LABEL_0, 220, 170);
        }

        for (Girder girder : girders) {
            girder.draw(batch);
        }

        for (Barrel barrel : barrels) {
            barrel.draw(batch);
        }
    }

}
