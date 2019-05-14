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

public class Credits extends  State {

    private Stage stage;
    private SpriteBatch sprite;
    private Texture back, menutetxure;
    private TextureRegion menuRegion;
    private TextureRegionDrawable menuDraw;
    private ImageButton menu;
    private float n = 12f, counter = 0, aux = 0;
    private float WIDTH, HEIGHT, POSXMENU, POSYMENU;
    private boolean scaleMenu = false;
    private Sound botones;
    private  Preferencess prefs;
    public Music musicaFondo;
    public static float musica;

    public Credits(GameStateManager gsm){
        super(gsm);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        sprite = new SpriteBatch();
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("audio/musica.mp3"));
        prefs = new Preferencess();
        botones =  Gdx.audio.newSound(Gdx.files.internal("audio/botones.mp3"));
        back = new Texture("fondocreditos.png");

        menutetxure = new Texture("menu.png");
        menuRegion = new TextureRegion(menutetxure);
        menuDraw = new TextureRegionDrawable(menuRegion);
        menu = new ImageButton(menuDraw);
        menu.setWidth(Gdx.graphics.getWidth()/3.7f);
        menu.setHeight(Gdx.graphics.getHeight()/11.85f);
        menu.setPosition(15, (Gdx.graphics.getHeight()/1.25f));

        WIDTH = menu.getWidth()- n;
        HEIGHT = menu.getHeight() -n;
        POSXMENU = menu.getX() + n/2;
        POSYMENU = menu.getY() + n/2;

        menu.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menu.setWidth(WIDTH);
                menu.setHeight(HEIGHT);
                menu.setPosition(POSXMENU, POSYMENU);
                if(prefs.getExtraSound())
                    botones.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menu.setWidth(WIDTH + n);
                menu.setHeight(HEIGHT + n);
                menu.setPosition(15, (Gdx.graphics.getHeight()/1.25f));
                scaleMenu = true;
            }

            @Override
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
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        counter = dt;
        if(scaleMenu) {
            aux += counter*2;

            if( aux >= .25f){
                Menu.entroCredits = true;
                musicaFondo.pause();
                musica = musicaFondo.getPosition();
                Menu.musica = musica;
                gsm.set(new Menu(gsm));
                scaleMenu = false;
            }
        }
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
        botones.dispose();
        menutetxure.dispose();
        musicaFondo.dispose();
    }
}
