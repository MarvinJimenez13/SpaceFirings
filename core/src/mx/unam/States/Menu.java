package mx.unam.States;

import com.badlogic.gdx.Gdx;
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

import java.util.Random;

import mx.unam.Elements.BadShip;
import mx.unam.Elements.BadShip2;

public class Menu extends State{

    private Stage stage;
    private SpriteBatch sprite;
    private Texture fondo, loading;
    private Random random;
    private int num;
    private Texture playTexture, guideTexture, configTexture, creditsTexture;
    private TextureRegion playRegion, guideRegion, configRegion, creditsRegion;
    private TextureRegionDrawable playDraw, guideDraw, configDraw, creditsDraw;
    private ImageButton play, guide, config, credits;
    public static boolean scalePlay = false, auxScale = false, scaleConfig = false, scaleCredits = false, scaleGuide = false;
    private float n = 12f, counter = 0, aux = 0;
    private float WIDTH, HEIGHT, POSXPLAY, POSYPLAY, POSXGUIDE, POSYGUIDE, POSXCONFIG, POSYCONFIG, POSXCREDITS, POSYCREDITS;
    public Music musicaFondo;
    private Sound botones;
    Preferencess prefs;
    public static float musica;
    public static boolean entroConfig = false, entroGuide = false, entroCredits = false;

    public Menu(final GameStateManager gsm){
        super(gsm);
        n = 12f;
        counter = 0;
        aux = 0;
        scalePlay = false;
        scaleConfig = false;
        auxScale = false;
        scaleGuide = false;
        scaleCredits = false;
        prefs = new Preferencess();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        botones =  Gdx.audio.newSound(Gdx.files.internal("audio/botones.mp3"));
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("audio/musica.mp3"));
        sprite = new SpriteBatch();
        loading = new Texture("loading.png");
        playTexture = new Texture("play.png");
        playRegion = new TextureRegion(playTexture);
        playDraw = new TextureRegionDrawable(playRegion);
        play = new ImageButton(playDraw);
        //play.setSize(Gdx.graphics.getWidth()/2.4f, Gdx.graphics.getHeight()/7.9f);
        play.setWidth(Gdx.graphics.getWidth()/2.4f);
        play.setHeight(Gdx.graphics.getHeight()/7.9f);
        play.setPosition((Gdx.graphics.getWidth()/2) - (play.getWidth()/2), (Gdx.graphics.getHeight()/2) - (play.getHeight()/2));

        WIDTH = play.getWidth()- n;
        HEIGHT = play.getHeight() -n;
        POSXPLAY = play.getX() + n/2;
        POSYPLAY = play.getY() + n/2;

        guideTexture = new Texture("guide.png");
        guideRegion = new TextureRegion(guideTexture);
        guideDraw = new TextureRegionDrawable(guideRegion);
        guide = new ImageButton(guideDraw);
        guide.setWidth(Gdx.graphics.getWidth()/2.4f);
        guide.setHeight(Gdx.graphics.getHeight()/7.9f);
        guide.setPosition((Gdx.graphics.getWidth()/2) - (play.getWidth()/2), (play.getY() - play.getHeight())-5);


        POSXGUIDE = guide.getX() + n/2;
        POSYGUIDE = guide.getY() + n/2;

        configTexture = new Texture("config.png");
        configRegion = new TextureRegion(configTexture);
        configDraw = new TextureRegionDrawable(configRegion);
        config = new ImageButton(configDraw);
        config.setWidth(Gdx.graphics.getWidth()/2.4f);
        config.setHeight(Gdx.graphics.getHeight()/7.9f);
        config.setPosition((Gdx.graphics.getWidth()/2) - (guide.getWidth()/2), (guide.getY() - guide.getHeight())-5);


        POSXCONFIG = config.getX() + n/2;
        POSYCONFIG = config.getY() + n/2;

        creditsTexture = new Texture("credits.png");
        creditsRegion = new TextureRegion(creditsTexture);
        creditsDraw = new TextureRegionDrawable(creditsRegion);
        credits = new ImageButton(creditsDraw);
        credits.setWidth(Gdx.graphics.getWidth()/2.4f);
        credits.setHeight(Gdx.graphics.getHeight()/7.9f);
        credits.setPosition((Gdx.graphics.getWidth()/2) - (config.getWidth()/2), (config.getY() - config.getHeight())-5);


        POSXCREDITS = credits.getX() + n/2;
        POSYCREDITS = credits.getY() + n/2;

        random = new Random();
        num = random.nextInt(2);
        if(num == 0){
            fondo = new Texture("fondomenu.png");
        }else if(num ==1){
            fondo = new Texture("fondomenu2.png");
        }


        play.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                play.setWidth(WIDTH);
                play.setHeight(HEIGHT);
                play.setPosition(POSXPLAY, POSYPLAY);
                PlayState.life = 10;
                PlayState.puntos = 0;
                PlayState.render = true;
                PlayState.back5Bo = false;
                PlayState.back4Bo = false;
                PlayState.back3Bo = false;
                PlayState.back2Bo =false;
                PlayState.back = true;
                BadShip.aux = 200;
                BadShip2.aux = 300;
                PlayState.forceTrue = false;
                if(prefs.getExtraSound())
                botones.play();
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                play.setWidth(WIDTH + n);
                play.setHeight(HEIGHT + n);
                play.setPosition((Gdx.graphics.getWidth()/2) - (play.getWidth()/2), (Gdx.graphics.getHeight()/2) - (play.getHeight()/2));
                scalePlay = true;
                auxScale = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        guide.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                guide.setWidth(WIDTH);
                guide.setHeight(HEIGHT);
                guide.setPosition(POSXGUIDE, POSYGUIDE);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                guide.setWidth(WIDTH + n);
                guide.setHeight(HEIGHT + n);
                guide.setPosition((Gdx.graphics.getWidth()/2) - (play.getWidth()/2), (play.getY() - play.getHeight())-5);
                scaleGuide = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

       config.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                config.setWidth(WIDTH);
                config.setHeight(HEIGHT);
                config.setPosition(POSXCONFIG, POSYCONFIG);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                config.setWidth(WIDTH + n);
                config.setHeight(HEIGHT + n);
                config.setPosition((Gdx.graphics.getWidth()/2) - (guide.getWidth()/2), (guide.getY() - guide.getHeight())-5);
                scaleConfig = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        credits.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                credits.setWidth(WIDTH);
                credits.setHeight(HEIGHT);
                credits.setPosition(POSXCREDITS, POSYCREDITS);
                if(prefs.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                credits.setWidth(WIDTH + n);
                credits.setHeight(HEIGHT + n);
                credits.setPosition((Gdx.graphics.getWidth()/2) - (config.getWidth()/2), (config.getY() - config.getHeight())-5);
                scaleCredits = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });



        if(prefs.getMenuBoo()) {
            if(!musicaFondo.isPlaying()) {
                if (!entroGuide && !entroConfig && !entroCredits) {
                    musicaFondo.setLooping(true);
                    musicaFondo.play();

                } else {
                    musicaFondo.setPosition(musica);
                    musicaFondo.setLooping(true);
                    musicaFondo.play();

                }
            }
        }
        stage.addActor(play);
        stage.addActor(guide);
        stage.addActor(config);
        stage.addActor(credits);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
             handleInput();
             counter = dt;
        if(scalePlay) {
            aux += counter*2;
            if(aux > .2f && aux < .4f){
                play.setVisible(false);
                scalePlay = true;
            }
            if( aux >= .4f){
                gsm.set(new PlayState(gsm));
                scalePlay = false;
            }
        }
        if(scaleGuide) {
            aux += counter*2;

            if( aux >= .2f){
                musicaFondo.pause();
                musica = musicaFondo.getPosition();
                Guide.musica = musica;
                gsm.set(new Guide(gsm));
                scaleGuide = false;
            }
        }
        if(scaleConfig) {
            aux += counter*2;

            if( aux >= .2f){
                musicaFondo.pause();
                musica = musicaFondo.getPosition();
                Config.musica = musica;
                gsm.set(new Config(gsm));
                scaleConfig = false;
            }
        }
        if(scaleCredits) {
            aux += counter*2;

            if( aux >= .2f){
                musicaFondo.pause();
                musica = musicaFondo.getPosition();
                Credits.musica = musica;
                gsm.set(new Credits(gsm));
                scaleConfig = false;
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.input.setInputProcessor(stage);
        sb.setProjectionMatrix(cam.combined);
        sprite.begin();
        sprite.draw(fondo, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(auxScale)
            sprite.draw(loading, play.getX(), play.getY(), play.getWidth(), play.getHeight());
        sprite.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        fondo.dispose();
        stage.dispose();
        playTexture.dispose();
        guideTexture.dispose();
        configTexture.dispose();
        creditsTexture.dispose();
        sprite.dispose();
        loading.dispose();
        musicaFondo.dispose();
        botones.dispose();
    }

}
