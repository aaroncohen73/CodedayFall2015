package com.code.day;

import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Created by donavynhaley on 11/7/2015.
 */
public class Sfx {
    static HashMap<String, Sound> soundMap = new HashMap();

    public static void addSfx (String name, Sound sound) {
        soundMap.put(name, sound);
    }

    public static void removeSound (String name) {
        if (soundMap.containsKey(name)) {
            soundMap.remove(name);
        }
        else {
            throw new NullPointerException("Sound not found");
        }
    }

    public static void playSound (String name, float volume) {
        if (soundMap.containsKey(name)) {
            Sound s = soundMap.get(name);
            s.play(volume);
        }
        else {
            throw new NullPointerException("Sound not found");
        }
    }


}
