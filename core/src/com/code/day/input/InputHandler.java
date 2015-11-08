package com.code.day.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by aaron on 11/7/15.
 */
public class InputHandler implements InputProcessor {

    //DK Controls
    public static boolean SPACE_TRIGGERED = false;
    public static boolean NUM0_TRIGGERED = false;
    public static boolean NUM1_TRIGGERED = false;
    public static boolean NUM2_TRIGGERED = false;
    public static boolean NUM3_TRIGGERED = false;
    public static boolean BACKSPACE_TRIGGERED = false;
    public static boolean ESC_TRIGGERED = false;

    //Jumpman Controls
    public static boolean LEFT_PRESSED = false;
    public static boolean DOWN_PRESSED = false;
    public static boolean RIGHT_PRESSED = false;
    public static boolean UP_PRESSED = false;
    public static boolean ALT_TRIGGERED = false;

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
            case Input.Keys.BACKSPACE:
                BACKSPACE_TRIGGERED = true;
                break;
            case Input.Keys.ESCAPE:
                ESC_TRIGGERED = true;
                break;
            case Input.Keys.LEFT:
                LEFT_PRESSED = true;
                break;
            case Input.Keys.DOWN:
                DOWN_PRESSED = true;
                break;
            case Input.Keys.RIGHT:
                RIGHT_PRESSED = true;
                break;
            case Input.Keys.UP:
                UP_PRESSED = true;
                break;
            case Input.Keys.ALT_RIGHT:
                ALT_TRIGGERED = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                LEFT_PRESSED = false;
                break;
            case Input.Keys.DOWN:
                DOWN_PRESSED = false;
                break;
            case Input.Keys.RIGHT:
                RIGHT_PRESSED = false;
                break;
            case Input.Keys.UP:
                UP_PRESSED = false;
                break;
        }

        return true;
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
