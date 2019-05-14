package mx.unam.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Preferencess{

    public static Preferences prefs;
    public boolean musicMenuBoo = true, musicGameBoo, soundShipsBoo, soundAstesBoo, extrasoundBoo,
            fondoInglesBoo, fondoEspañolBoo, ingles;

    public Preferencess(){
        prefs = Gdx.app.getPreferences("Sonidos2");
        musicMenuBoo = prefs.getBoolean("musicMenu", true);
        musicGameBoo = prefs.getBoolean("musicGame", true);
        soundShipsBoo = prefs.getBoolean("soundShip", true);
        soundAstesBoo = prefs.getBoolean("soundAste", true);
        extrasoundBoo = prefs.getBoolean("extrasound", true);
        fondoInglesBoo = prefs.getBoolean("fondoIngles", true);
        fondoEspañolBoo = prefs.getBoolean("fondoEspañol", true);
        ingles = prefs.getBoolean("ingles", true);

        prefs.flush();
    }



    public boolean getFondoInglesBoo(){
        fondoInglesBoo = prefs.getBoolean("fondoIngles");
        return  fondoInglesBoo;
    }
    public boolean getFondoEspañolBoo(){
        fondoEspañolBoo = prefs.getBoolean("fondoEspañol");
        return fondoEspañolBoo;
    }

    public  boolean getIngles(){
        ingles = prefs.getBoolean("ingles");
        return  ingles;
    }

    public boolean getMenuBoo(){
        musicMenuBoo = prefs.getBoolean("musicMenu");
        return  musicMenuBoo;

    }

    public boolean getGameBoo(){
        musicGameBoo = prefs.getBoolean("musicGame");
        return musicGameBoo;
    }

    public boolean getSoundAsteBoo(){
        soundAstesBoo = prefs.getBoolean("soundAste");
        return  soundAstesBoo;
    }

    public boolean getSoudShipsBoo(){
        soundShipsBoo = prefs.getBoolean("soundShip");
        return soundShipsBoo;
    }

    public boolean getExtraSound(){
        extrasoundBoo = prefs.getBoolean("extrasound");
        return extrasoundBoo;
    }

    public void flushChafa(){
        prefs.putBoolean("musicMenu", musicMenuBoo);
        prefs.putBoolean("musicGame", musicGameBoo);
        prefs.putBoolean("soundShip", soundShipsBoo);
        prefs.putBoolean("soundAste", soundAstesBoo);
        prefs.putBoolean("extrasound", extrasoundBoo);
        prefs.putBoolean("fondoIngles", fondoInglesBoo);
        prefs.putBoolean("fondoEspañol", fondoEspañolBoo);
        prefs.putBoolean("ingles", ingles);
        prefs.flush();
    }


}
