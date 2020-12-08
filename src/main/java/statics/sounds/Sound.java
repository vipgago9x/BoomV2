package main.java.statics.sounds;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;


public class Sound {
    public static Sound instane;
    public static final String MENU = "menu.wav";
    public static final String PLAYGAME = "renai.wav";
    public static final String BOMB = "newbomb.wav";
    public static final String PLAYER_DIE = "die.wav    ";
    public static final String MONSTER_DIE = "bomber_die.wav";
    public static final String BOMBANG = "bomb_bang.wav";
    public static final String ITEM = "item.wav";
    public static final String WIN = "win.wav";
    public static final String LOSE = "lose.mid";

    private HashMap<String, AudioClip> audioMap;

    public Sound() {
        audioMap = new HashMap<String, AudioClip>();
        loadAudio();
    }
    public static Sound getInstance() {
        if (instane == null) {
            instane = new Sound();
        }
        return instane;
    }
    public void loadAudio() {
        putAudio(MENU);
        putAudio(PLAYGAME);
        putAudio(BOMB);
        putAudio(PLAYER_DIE);
        putAudio(MONSTER_DIE);
        putAudio(BOMBANG);
        putAudio(ITEM);
        putAudio(WIN);
        putAudio(LOSE);
    }
    public void Stop() {
        getAudio(MENU).stop();
        getAudio(PLAYGAME).stop();
        getAudio(BOMB).stop();
        getAudio(PLAYER_DIE).stop();
        getAudio(MONSTER_DIE).stop();
        getAudio(BOMBANG).stop();
        getAudio(ITEM).stop();
        getAudio(WIN).stop();
        getAudio(LOSE).stop();
    }
    public void putAudio(String name) {
        AudioClip audioClip = Applet.newAudioClip(Sound.class.getResource(name));
        audioMap.put(name, audioClip);
    }
    public AudioClip getAudio(String name) {
        return audioMap.get(name);
    }
}
