package com.code.day.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by aaron on 11/7/15.
 */
public class InputHandler implements InputProcessor {

    public static boolean SPACE_TRIGGERED = false;
    public static boolean NUM0_TRIGGERED = false;
    public static boolean NUM1_TRIGGERED = false;
    public static boolean NUM2_TRIGGERED = false;
    public static boolean NUM3_TRIGGERED = false;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                SPACE_TRIGGERED = true;
                break;
            case Input.Keys.NUM_0:
                NUM0_TRIGGERED = true;
                break;
            case Input.Keys.NUM_1:
                NUM1_TRIGGERED = true;
                break;
            case Input.Keys.NUM_2:
                NUM2_TRIGGERED = true;
                break;
            case Input.Keys.NUM_3:
                NUM3_TRIGGERED = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
