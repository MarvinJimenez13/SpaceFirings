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

public class Guide extends  State {

    private Stage stage;
    private SpriteBatch sprite;
    private Texture fondoIngles,fondoIngles1, fondoEspañol, fondoEspañol1;
    private Texture flechaTexture,menuTexture;
    private TextureRegion flechaRegion, menuRegion;
    private TextureRegionDrawable flechaDraw, menuDraw;
    private ImageButton flecha,  menu;
    private float WIDTH, HEIGHT,HEIGHTMENU, WIDTHMENU,POSXMENU, POSYMENU, POSXFLECHA, POSYFLECHA;
    private boolean fondoInglesBoo, fondoEspañolBoo, ingles, scaleMenu = false;
    private Preferencess prefs;
    private Sound botones;
    private float n = 12f, counter = 0, aux = 0;
    public Music musicaFondo;
    public static float musica;


    public Guide(GameStateManager gsm){
        super(gsm);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        prefs = new Preferencess();
        fondoInglesBoo = prefs.getFondoInglesBoo();
        fondoEspañolBoo = prefs.getFondoEspañolBoo();
        ingles = prefs.getIngles();
        sprite = new SpriteBatch();
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("audio/musica.mp3"));
        botones =  Gdx.audio.newSound(Gdx.files.internal("audio/botones.mp3"));
        fondoIngles = new Texture("fondoguiaingles1.png");
        fondoIngles1 = new Texture("fondoguiaingles.png");
        fondoEspañol = new Texture("fondoguia1.png");
        fondoEspañol1 = new Texture("fondoguia.png");

        menuTexture = new Texture("menu.png");
        menuRegion = new TextureRegion(menuTexture);
        menuDraw = new TextureRegionDrawable(menuRegion);
        menu = new ImageButton(menuDraw);
        menu.setWidth(Gdx.graphics.getWidth()/3.7f);
        menu.setHeight(Gdx.graphics.getHeight()/11.85f);
        menu.setPosition(15, (Gdx.graphics.getHeight()/1.25f));

        WIDTHMENU = menu.getWidth()- n;
        HEIGHTMENU = menu.getHeight() -n;
        POSXMENU = menu.getX() + n/2;
        POSYMENU = menu.getY() + n/2;

        flechaTexture = new Texture("flecha.png");
        flechaRegion = new TextureRegion(flechaTexture);
        flechaDraw = new TextureRegionDrawable(flechaRegion);
        flecha = new ImageButton(flechaDraw);
        flecha.setWidth(Gdx.graphics.getWidth()/8.22f);
        flecha.setHeight(Gdx.graphics.getHeight()/11.85f);
        flecha.setPosition((Gdx.graphics.getWidth()/2) - (flecha.getWidth()/2), 0);

        WIDTH = flecha.getWidth()- n;
        HEIGHT =flecha.getHeight() -n;
        POSXFLECHA = flecha.getX() + n/2;
        POSYFLECHA = flecha.getY() + n/2;


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
                menu.setPosition(15, (Gdx.graphics.getHeight()/1.25f));
                scaleMenu = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });


        flecha.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                flecha.setWidth(WIDTH);
                flecha.setHeight(HEIGHT);
                flecha.setPosition(POSXFLECHA, POSYFLECHA);
                if(prefs.getExtraSound())
                    botones.play();
                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int buttons) {
                flecha.setWidth(WIDTH + n);
                flecha.setHeight(HEIGHT + n);
                flecha.setPosition((Gdx.graphics.getWidth()/2) - (flecha.getWidth()/2), 0);
                if(ingles) {
                    System.out.println("ingles true");
                    if (fondoInglesBoo)
                        fondoInglesBoo = false;
                    else fondoInglesBoo = true;
                }else {
                    System.out.println("español true");
                    if (fondoEspañolBoo)
                        fondoEspañolBoo = false;
                    else fondoEspañolBoo = true;
                }
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
        stage.addActor(flecha);
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

            if( aux >= .2f){
                Menu.entroGuide = true;
                musicaFondo.pause();
                musica = musicaFondo.getPosition();
                Menu.musica = musica;
                gsm.set(new Menu(gsm));
                scaleMenu= false;
            }
        }
        prefs.fondoInglesBoo = fondoInglesBoo;
        prefs.fondoEspañolBoo = fondoEspañolBoo;
    prefs.flushChafa();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.input.setInputProcessor(stage);
        sb.setProjectionMatrix(cam.combined);
        sprite.begin();
        if(ingles){
            System.out.println("inglesdrwa true");
            if (fondoInglesBoo)
                sprite.draw(fondoIngles, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            else
                sprite.draw(fondoIngles1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }else{
            System.out.println("espdraw true");
            if (fondoEspañolBoo)
                sprite.draw(fondoEspañol, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            else
                sprite.draw(fondoEspañol1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        sprite.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        fondoEspañol.dispose();
        fondoIngles.dispose();
        fondoEspañol1.dispose();
        fondoIngles1.dispose();
        prefs.flushChafa();
        botones.dispose();
        musicaFondo.dispose();
    }
}
