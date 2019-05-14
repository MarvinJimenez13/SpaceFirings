package mx.unam.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Config extends State{

    private Stage stage;
    private Texture back, flag;
    private SpriteBatch sprite;
    private Texture musicMenu, musicGame, soundAste, soundShips, extraSounds, menuTexture, languageTetxure;
    private TextureRegion musicRegion, musicGameRegion, soundAsteRegion, soundShipsRegion, extraRegion, menuRegion, languageRegion;
    private TextureRegionDrawable musicDraw, musicGameDraw, soundAsteDraw, soundShipsDraw, estraDraw, menuDraw, languageDraw;
    private ImageButton music, musicGames, soundAstes, soundShip, extraSound, menu, language;
    private float WIDTH, HEIGHT, WIDTHMENU, HEIGHTMENU, POSXSOUNDMENU, POSYSOUNDMENU, POSXGAME, POSYGAME, POSXASTE, POSYASTE, POSXMENU, POSYMENU, POSXLANGUAGE, POSYLANGUAGE,
            POSXSHIPS, POSYSHIPS, POSXEXTRA, POSYEXTRA;
    private boolean scaleMenu= false;
    private float n = 12f, counter = 0, aux = 0;
    private Sound botones;
    public boolean musicMenuBoo, musicGameBoo, soundShipsBoo, soundAstesBoo, extrasoundBoo, languageBoo;
    public boolean ver = false;
    public static Preferencess prefs;
    public Music musicaFondo;
    public static float musica;


    public Config(GameStateManager gsm){
        super(gsm);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        sprite = new SpriteBatch();
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("audio/musica.mp3"));
        back = new Texture("fondoconfig.jpg");
        prefs = new Preferencess();
        botones =  Gdx.audio.newSound(Gdx.files.internal("audio/botones.mp3"));

        musicMenuBoo = prefs.getMenuBoo();
        musicGameBoo = prefs.getGameBoo();
        soundShipsBoo = prefs.getSoudShipsBoo();
        soundAstesBoo = prefs.getSoundAsteBoo();
        extrasoundBoo = prefs.getExtraSound();
        languageBoo = prefs.getIngles();

        menuTexture = new Texture("menu.png");
        menuRegion = new TextureRegion(menuTexture);
        menuDraw = new TextureRegionDrawable(menuRegion);
        menu = new ImageButton(menuDraw);
        menu.setWidth(Gdx.graphics.getWidth()/3.7f);
        menu.setHeight(Gdx.graphics.getHeight()/11.85f);
        menu.setPosition((Gdx.graphics.getWidth()/2) - (menu.getWidth()), (Gdx.graphics.getHeight()/1.35f) - (menu.getHeight()/2));

        WIDTHMENU = menu.getWidth()- n;
        HEIGHTMENU = menu.getHeight() -n;
        POSXMENU = menu.getX() + n/2;
        POSYMENU = menu.getY() + n/2;

        musicMenu = new Texture("musicmenu.png");
        musicRegion = new TextureRegion(musicMenu);
        musicDraw = new TextureRegionDrawable(musicRegion);
        music = new ImageButton(musicDraw);
        music.setWidth(Gdx.graphics.getWidth()/1.8f);
        music.setHeight(Gdx.graphics.getHeight()/11.85f);
        music.setPosition((Gdx.graphics.getWidth()/2) - (menu.getWidth()), (menu.getY() - menu.getHeight())-15);

        WIDTH = music.getWidth()- n;
        HEIGHT = music.getHeight() -n;
        POSXSOUNDMENU = music.getX() + n/2;
        POSYSOUNDMENU = music.getY() + n/2;

        musicGame = new Texture("musicgame.png");
        musicGameRegion= new TextureRegion(musicGame);
        musicGameDraw = new TextureRegionDrawable(musicGameRegion);
        musicGames = new ImageButton(musicGameDraw);
        musicGames.setWidth(Gdx.graphics.getWidth()/1.8f);
        musicGames.setHeight(Gdx.graphics.getHeight()/11.85f);
        musicGames.setPosition((Gdx.graphics.getWidth()/2) - (music.getWidth()/2), (music.getY() - music.getHeight())-5);

        POSXGAME = musicGames.getX() + n/2;
        POSYGAME = musicGames.getY() + n/2;

        soundAste = new Texture("soundaste.png");
        soundAsteRegion = new TextureRegion(soundAste);
        soundAsteDraw= new TextureRegionDrawable(soundAsteRegion);
        soundAstes = new ImageButton(soundAsteDraw);
        soundAstes.setWidth(Gdx.graphics.getWidth()/1.8f);
        soundAstes.setHeight(Gdx.graphics.getHeight()/11.85f);
        soundAstes.setPosition((Gdx.graphics.getWidth()/2) - (musicGames.getWidth()/2), (musicGames.getY() - musicGames.getHeight())-5);

        POSXASTE = soundAstes.getX() + n/2;
        POSYASTE = soundAstes.getY() + n/2;

        soundShips = new Texture("soundships.png");
        soundShipsRegion = new TextureRegion(soundShips);
        soundShipsDraw = new TextureRegionDrawable(soundShipsRegion);
        soundShip = new ImageButton(soundShipsDraw);
        soundShip.setWidth(Gdx.graphics.getWidth()/1.8f);
        soundShip.setHeight(Gdx.graphics.getHeight()/11.85f);
        soundShip.setPosition((Gdx.graphics.getWidth()/2) - (soundAstes.getWidth()/2), (soundAstes.getY() - soundAstes.getHeight())-5);

        POSXSHIPS = soundShip.getX() + n/2;
        POSYSHIPS = soundShip.getY() + n/2;

        extraSounds = new Texture("extrasounds.png");
        extraRegion = new TextureRegion(extraSounds);
        estraDraw = new TextureRegionDrawable(extraRegion);
        extraSound = new ImageButton(estraDraw);
        extraSound.setWidth(Gdx.graphics.getWidth()/1.8f);
        extraSound.setHeight(Gdx.graphics.getHeight()/11.85f);
        extraSound.setPosition((Gdx.graphics.getWidth()/2) - (soundShip.getWidth()/2), (soundShip.getY() - soundShip.getHeight())-5);

        POSXEXTRA = extraSound.getX() + n/2;
        POSYEXTRA = extraSound.getY() + n/2;

        if(languageBoo){
            System.out.println("Gia euaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        languageTetxure = new Texture("languageguideeua.png");}
        else{
            languageTetxure = new Texture("languageguidemx.png");
            System.out.println("Gia mxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        languageRegion = new TextureRegion(languageTetxure);
        languageDraw = new TextureRegionDrawable(languageRegion);
        language = new ImageButton(languageDraw);
        language.setWidth(Gdx.graphics.getWidth()/1.8f);
        language.setHeight(Gdx.graphics.getHeight()/11.85f);
        language.setPosition((Gdx.graphics.getWidth()/2) - (extraSound.getWidth()/2), (extraSound.getY() - extraSound.getHeight())-5);

        POSXLANGUAGE = language.getX() + n/2;
        POSYLANGUAGE = language.getY() + n/2;


        review();

        menu.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menu.setWidth(WIDTHMENU);
                menu.setHeight(HEIGHTMENU);
                menu.setPosition(POSXMENU, POSYMENU);
                if(prefs.getExtraSound())
                botones.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menu.setWidth(WIDTHMENU + n);
                menu.setHeight(HEIGHTMENU + n);
                menu.setPosition((Gdx.graphics.getWidth()/2) - (menu.getWidth()), (Gdx.graphics.getHeight()/1.35f) - (menu.getHeight()/2));
                scaleMenu = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        music.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                music.setWidth(WIDTH);
                music.setHeight(HEIGHT);
                music.setPosition(POSXSOUNDMENU, POSYSOUNDMENU);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                music.setWidth(WIDTH + n);
                ver = true;
                music.setHeight(HEIGHT + n);
                music.setPosition((Gdx.graphics.getWidth()/2) - (menu.getWidth() ), (menu.getY() - menu.getHeight())-15);
                if(musicMenuBoo)
                    musicMenuBoo = false;
                else musicMenuBoo = true;

                if(!musicMenuBoo){
                    musicaFondo.pause();
                }else musicaFondo.play();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        musicGames.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                musicGames.setWidth(WIDTH);
                musicGames.setHeight(HEIGHT);
                musicGames.setPosition(POSXGAME, POSYGAME);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                musicGames.setWidth(WIDTH + n);
                ver = true;
                musicGames.setHeight(HEIGHT + n);
                musicGames.setPosition((Gdx.graphics.getWidth()/2) - (music.getWidth()/2), (music.getY() - music.getHeight())-5);
                if(musicGameBoo)
                    musicGameBoo = false;
                else musicGameBoo = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        soundAstes.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
               soundAstes.setWidth(WIDTH);
               soundAstes.setHeight(HEIGHT);
               soundAstes.setPosition(POSXASTE, POSYASTE);
               if(prefs.getExtraSound())
               botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                soundAstes.setWidth(WIDTH + n);
                soundAstes.setHeight(HEIGHT + n);
                ver = true;
                soundAstes.setPosition((Gdx.graphics.getWidth()/2) - (musicGames.getWidth()/2), (musicGames.getY() - musicGames.getHeight())-5);
                if(soundAstesBoo)
                    soundAstesBoo = false;
                else soundAstesBoo = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });


        soundShip.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                soundShip.setWidth(WIDTH);
                soundShip.setHeight(HEIGHT);
                soundShip.setPosition(POSXSHIPS, POSYSHIPS);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                 soundShip.setWidth(WIDTH + n);
                soundShip.setHeight(HEIGHT + n);
                ver = true;
                soundShip.setPosition((Gdx.graphics.getWidth()/2) - (soundAstes.getWidth()/2), (soundAstes.getY() - soundAstes.getHeight())-5);
                if(soundShipsBoo)
                    soundShipsBoo = false;
                else soundShipsBoo = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });


        extraSound.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                extraSound.setWidth(WIDTH);
                extraSound.setHeight(HEIGHT);
                extraSound.setPosition(POSXEXTRA, POSYEXTRA);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                extraSound.setWidth(WIDTH + n);
                extraSound.setHeight(HEIGHT + n);
                ver = true;
                extraSound.setPosition((Gdx.graphics.getWidth()/2) - (soundShip.getWidth()/2), (soundShip.getY() - soundShip.getHeight())-5);
                if(extrasoundBoo)
                    extrasoundBoo = false;
                else extrasoundBoo = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });


        language.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                language.setWidth(WIDTH);
                language.setHeight(HEIGHT);
                language.setPosition(POSXLANGUAGE, POSYLANGUAGE);
                if(prefs.getExtraSound())
                    botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                language.setWidth(WIDTH + n);
                language.setHeight(HEIGHT + n);
                ver = true;
                language.setPosition((Gdx.graphics.getWidth()/2) - (extraSound.getWidth()/2), (extraSound.getY() - extraSound.getHeight())-5);
                if(languageBoo)
                    languageBoo = false;
                else languageBoo = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        if(prefs.getMenuBoo()) {
            if(!musicaFondo.isPlaying()){
                musicaFondo.setLooping(true);
                musicaFondo.setPosition(musica);
                musicaFondo.play();
            }}
        stage.addActor(menu);
        stage.addActor(music);
        stage.addActor(musicGames);
        stage.addActor(soundAstes);
        stage.addActor(soundShip);
        stage.addActor(extraSound);
        stage.addActor(language);

    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {


        handleInput();
        counter = dt;
        if(scaleMenu) {
            aux += counter*2;

            if( aux >= .2f){
                musicaFondo.pause();
                Menu.entroConfig = true;
                musica = musicaFondo.getPosition();
                Menu.musica = musica;
                gsm.set(new Menu(gsm));
                scaleMenu= false;
            }
        }
         if(ver){
             aux += counter*2;
             if(aux > .2f && aux < .4f)
                 ver = true;
             if( aux >= .2f){
                 ver = false;
                 review();
                 aux = 0;
             }
         }

         prefs.flushChafa();
    }

    public void review(){
        if(musicMenuBoo){
            musicMenu = new Texture("musicmenu.png");
            musicRegion.setRegion(musicMenu);
            musicDraw.setRegion(musicRegion);
            music.setBackground(musicDraw);}
        else{
            musicMenu = new Texture("musicmenux.png");
            musicRegion.setRegion(musicMenu);
            musicDraw.setRegion(musicRegion);
            music.setBackground(musicDraw);}

        if(musicGameBoo){
            musicGame = new Texture("musicgame.png");
            musicGameRegion.setRegion(musicGame);
            musicGameDraw.setRegion(musicGameRegion);
            musicGames.setBackground(musicGameDraw);}
        else{
            musicGame = new Texture("musicgamex.png");
            musicGameRegion.setRegion(musicGame);
            musicGameDraw.setRegion(musicGameRegion);
            musicGames.setBackground(musicGameDraw);}

        if(soundAstesBoo){
            soundAste = new Texture("soundaste.png");
            soundAsteRegion.setRegion(soundAste);
            soundAsteDraw.setRegion(soundAsteRegion);
            soundAstes.setBackground(soundAsteDraw);}
        else{
            soundAste = new Texture("soundastex.png");
            soundAsteRegion.setRegion(soundAste);
            soundAsteDraw.setRegion(soundAsteRegion);
            soundAstes.setBackground(soundAsteDraw);}

        if(soundShipsBoo){
            soundShips = new Texture("soundships.png");
            soundShipsRegion.setRegion(soundShips);
            soundShipsDraw.setRegion(soundShipsRegion);
            soundShip.setBackground(soundShipsDraw);}
        else{
            soundShips = new Texture("soundshipsx.png");
            soundShipsRegion.setRegion(soundShips);
            soundShipsDraw.setRegion(soundShipsRegion);
            soundShip.setBackground(soundShipsDraw);}

        if(extrasoundBoo){
            extraSounds = new Texture("extrasounds.png");
            extraRegion.setRegion(extraSounds);
            estraDraw.setRegion(extraRegion);
            extraSound.setBackground(estraDraw);}
        else{
            extraSounds = new Texture("extrasoundsx.png");
            extraRegion.setRegion(extraSounds);
            estraDraw.setRegion(extraRegion);
            extraSound.setBackground(estraDraw);}

        if(languageBoo){
            languageTetxure = new Texture("languageguideeua.png");
            languageRegion.setRegion(languageTetxure);
            languageDraw.setRegion(languageRegion);
            language.setBackground(languageDraw);}
        else{
            languageTetxure = new Texture("languageguidemx.png");
            languageRegion.setRegion(languageTetxure);
            languageDraw.setRegion(languageRegion);
            language.setBackground(languageDraw);}

            prefs.musicMenuBoo = musicMenuBoo;
            prefs.musicGameBoo = musicGameBoo;
            prefs.soundAstesBoo = soundAstesBoo;
            prefs.soundShipsBoo = soundShipsBoo;
            prefs.extrasoundBoo = extrasoundBoo;
            prefs.ingles = languageBoo;

        prefs.flushChafa();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.input.setInputProcessor(stage);
        sb.setProjectionMatrix(cam.combined);
        sprite.begin();
        sprite.draw(back, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        sprite.dispose();
        back.dispose();
        musicGame.dispose();
        musicMenu.dispose();
        soundAste.dispose();
        soundShips.dispose();
        extraSounds.dispose();
        menuTexture.dispose();
        languageTetxure.dispose();
        botones.dispose();
        prefs.flushChafa();
        musicaFondo.dispose();
    }
}
