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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import mx.unam.Elements.BadShip;
import mx.unam.Elements.BadShip2;

public class GameOver extends State {

    private Texture fondo, gameoverl;
    private SpriteBatch sprite;
    private Skin skins;
    public static  Label titulo, mejorP;
    private Stage stage;
    public static Preferences prefs;
    public int puntos;
    static float tamaño = 0.1f;
    private Texture menuTexture;
    private TextureRegion menuRegion;
    private TextureRegionDrawable menuDraw;
    private ImageButton menu;
    private float WIDTH, HEIGHT, POSXMENU, POSYMENU;
    private boolean scalePlay = false;
    private float n = 12f, counter = 0, aux = 0;
    private Sound botones;
    private Music gameover;
    Preferencess prefss;

    public GameOver(GameStateManager gsm){
         super(gsm);
        n = 12f;
        counter = 0;
        aux = 0;
        scalePlay = false;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        prefss = new Preferencess();
        botones =  Gdx.audio.newSound(Gdx.files.internal("audio/botones.mp3"));
        gameover = Gdx.audio.newMusic(Gdx.files.internal("audio/videogamefin.mp3"));
        menuTexture = new Texture("menu.png");
        menuRegion = new TextureRegion(menuTexture);
        menuDraw = new TextureRegionDrawable(menuRegion);
        menu = new ImageButton(menuDraw);
        menu.setWidth(Gdx.graphics.getWidth()/2.4f);
        menu.setHeight(Gdx.graphics.getHeight()/7.9f);
        menu.setPosition((Gdx.graphics.getWidth()/2) - (menu.getWidth()/2), (Gdx.graphics.getHeight()/4));

        WIDTH = menu.getWidth()- n;
        HEIGHT = menu.getHeight() -n;
        POSXMENU = menu.getX() + n/2;
        POSYMENU = menu.getY() + n/2;

        fondo = new Texture("gameover.jpg");
        gameoverl = new Texture("gameoverl.png");
        sprite = new SpriteBatch();
        prefs = Gdx.app.getPreferences("Puntuacion");
        puntos =prefs.getInteger("puntos", 0);
        skins = new Skin(Gdx.files.internal("skin/arcade-ui.json"));
        mejorP = new Label("Best Score: " + puntos, skins);
        mejorP.setPosition(0,(Gdx.graphics.getHeight())-350);
        mejorP.setFontScale(3f);
        titulo = new Label("Score: " + PlayState.puntos  , skins);
        titulo.setPosition(0,(Gdx.graphics.getHeight())-450);
        titulo.setFontScale(3.5f);
        stage.addActor(mejorP);
        stage.addActor(titulo);
        if(PlayState.puntos> prefs.getInteger("puntos")){
            prefs.putInteger("puntos", PlayState.puntos);
        }
        prefs.flush();

        menu.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                menu.setWidth(WIDTH);
                menu.setHeight(HEIGHT);
                menu.setPosition(POSXMENU, POSYMENU);
                if(prefss.getExtraSound())
                botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                menu.setWidth(WIDTH + n);
                menu.setHeight(HEIGHT + n);
                menu.setPosition((Gdx.graphics.getWidth()/2) - (menu.getWidth()/2), (Gdx.graphics.getHeight()/4));
                scalePlay = true;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });

        stage.addActor(menu);
        if(prefss.getExtraSound())
        gameover.play();
    }


    @Override
    protected void handleInput() {
       if(Gdx.input.justTouched()){
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
           gsm.set(new PlayState(gsm));
       }
    }

    @Override
    public void update(float dt) {
         counter = dt;
        if(scalePlay) {
            aux += counter*2;

            if( aux >= .25f){
                gsm.set(new Menu(gsm));
                scalePlay = false;
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.input.setInputProcessor(stage);
        sb.setProjectionMatrix(cam.combined);
           sprite.begin();
           sprite.draw(fondo, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
           sprite.draw(gameoverl, (Gdx.graphics.getWidth()/2)- (gameoverl.getWidth()/2), (Gdx.graphics.getHeight()/2)-(gameoverl.getHeight()/2));
           sprite.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        menuTexture.dispose();
         fondo.dispose();
         stage.dispose();
         prefs.flush();
         botones.dispose();
         gameover.dispose();
         gameoverl.dispose();
    }
}
