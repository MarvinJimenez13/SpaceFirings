package mx.unam.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.Random;
import mx.unam.Elements.Asteroid2;
import mx.unam.Elements.Asteroid3;
import mx.unam.Elements.BackGround2;
import mx.unam.Elements.BackGround3;
import mx.unam.Elements.BackGround4;
import mx.unam.Elements.BackGround5;
import mx.unam.Elements.BadShip;
import mx.unam.Elements.BadShip2;
import mx.unam.Elements.ForceField;
import mx.unam.Elements.Life;
import mx.unam.MainGame;
import mx.unam.Elements.Hole;
import mx.unam.Elements.Asteroid;
import mx.unam.Elements.Bullet;
import mx.unam.Elements.BackGround;
import mx.unam.Elements.Ship;
import mx.unam.Elements.Teleport;

public class PlayState extends State {

    public static Ship ship;
    private BadShip badShip;
    private BadShip2 badShip2;
    public static boolean available, render = true, back = true, back2Bo = false,
            back3Bo = false, back4Bo = false,back5Bo = false ,forceTrue= false, badShipsDead = false;
    private float accelX;
    private float accelY;
    private Vector3 vector;
    private Bullet bullet;
    private Asteroid aste1, aste2, aste3,aste4;
    private Asteroid2 aste2_1, aste2_2, aste2_3,aste2_4,aste2_5;
    private Asteroid3 aste3_1, aste3_2, aste3_3,aste3_4,aste3_5;
    private Texture backTexture, backAux;
    private int mov = 0, movAux, auxCont = 0, forceNumAux = 4, auxTeleport = 0;
    private Hole hole;
    private Teleport teleport;
    private float counter = 1, aux = 0;
    public static int bool = 0, auxAsteRandom = 0, auxForce = 200;
    private BackGround backTeleport;
    private Skin skin;
    private TextureRegionDrawable textureBar, textureForceBar, textureBadLife, textureBadLife2;
    private ProgressBar bar, forceBar, badLifeBar, badLifeBar2;
    private ProgressBar.ProgressBarStyle barStyle,forceBarStyle, badLifeStyle, badLifeStyle2;
    private Stage stage;
    public static int life = 10;
    private Pixmap pixmap;
    private BackGround2 back2;
    private BackGround3 back3;
    private BackGround4 back4;
    private BackGround5 back5;
    public static int puntos = 0;
    private Skin skins;
    private Label titulo;
    private Random randomAste;
    private ForceField forceField;
    private Texture forceTexture;
    private TextureRegion forceTextureRegion;
    private TextureRegionDrawable forceTextureDraw;
    private ImageButton forceButton;
    private Label forceNum, points;
    public static Life lifeObject;
    static float tamaño = 0.1f;
    private Sound teleports;
    public Music musicaFondo;
    private Preferencess prefs;

    public PlayState(GameStateManager gsm){
        super(gsm);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        prefs = new Preferencess();
        teleports =  Gdx.audio.newSound(Gdx.files.internal("audio/teleport.mp3"));
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("audio/musicajuego.mp3"));
        musicaFondo.setVolume(0.5f);
        skins = new Skin(Gdx.files.internal("skin/arcade-ui.json"));
        ship = new mx.unam.Elements.Ship(0,30);
        lifeObject = new Life(400, (int)(Gdx.graphics.getHeight()*2.5f));
        Random number = new Random();
        int num = number.nextInt(2);
        badShip = new BadShip(0,(int)(Gdx.graphics.getHeight()/1.41f), num);
        badShip2 = new BadShip2(0,  (int)(Gdx.graphics.getHeight()/1.41f), num);
        available = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        vector = new Vector3(0,0,0);
        bullet = new Bullet(0,0);
        Random rand = new Random();
        hole = new Hole(rand.nextInt(Gdx.graphics.getWidth()-250), Gdx.graphics.getHeight()*2);
        aste1 = new Asteroid(100, Gdx.graphics.getHeight());
        aste2 = new Asteroid(500, Gdx.graphics.getHeight()+500);
        aste3 = new Asteroid(250, Gdx.graphics.getHeight()+850);
        aste4 = new Asteroid(250, Gdx.graphics.getHeight()+300);
        aste2_1 = new Asteroid2(100, Gdx.graphics.getHeight());
        aste2_2 = new Asteroid2(500, Gdx.graphics.getHeight()+500);
        aste2_3 = new Asteroid2(250, Gdx.graphics.getHeight()+850);
        aste2_4 = new Asteroid2(250, Gdx.graphics.getHeight()+300);
        aste2_5 = new Asteroid2(400, Gdx.graphics.getHeight()+450);
        aste3_1 = new Asteroid3(100, Gdx.graphics.getHeight());
        aste3_2 = new Asteroid3(500, Gdx.graphics.getHeight()+500);
        aste3_3 = new Asteroid3(250, Gdx.graphics.getHeight()+850);
        aste3_4 = new Asteroid3(250, Gdx.graphics.getHeight()+300);
        aste3_5 = new Asteroid3(400, Gdx.graphics.getHeight()+450);
        backTexture = new Texture("stars.jpg");
        backTeleport = new BackGround(0,0);
        backAux = new  Texture("stars.jpg");
        back2 = new BackGround2(0,0);
        back3 = new BackGround3(0,0);
        back4 = new BackGround4(0,0);
        back5 = new BackGround5(0,0);
        randomAste = new Random();
        movAux = Gdx.graphics.getHeight();
        teleport = new Teleport((int)ship.getPosition().x, (int)ship.getPosition().y);
        // Start ProgressBar
        skin = new Skin();
        pixmap = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga.jpg"))));
        barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;
        bar = new ProgressBar(0, 10, 0.5f, false, barStyle);
        bar.setSize(Gdx.graphics.getWidth()-40, 200);
        bar.setPosition(20, (Gdx.graphics.getHeight() - bar.getHeight())-350);
        // End ProgressBar
        //Start ForceBar
        textureForceBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga2.jpg"))));
        forceBarStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureForceBar);
        forceBarStyle.knobBefore = forceBarStyle.knob;
        forceBar = new ProgressBar(0,200,0.5f, true, forceBarStyle);
        forceBar.setSize(500, 200);
        forceBar.setPosition(-220,400);
        forceBar.setVisible(false);
        //End ForceBar
        //Start BadLifeBar
        textureBadLife = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descargavs.jpg"))));
         badLifeStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBadLife);
         badLifeBar = new ProgressBar(0,200,0.5f, false, badLifeStyle);
        badLifeBar.setSize(Gdx.graphics.getWidth()/2.4f, 200);
        badLifeBar.setPosition(badShip.getPosition().x+50,badShip.getPosition().y);
        badLifeBar.setVisible(false);
        //End BadLifeBar
        //Start BadLifeBar2
        textureBadLife2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descargavs.jpg"))));
        badLifeStyle2 = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBadLife2);
        badLifeBar2 = new ProgressBar(0,300,0.5f, false, badLifeStyle2);
        badLifeBar2.setSize(Gdx.graphics.getWidth()/2.77f, 200);
        badLifeBar2.setPosition(badShip2.getPosition().x+50,badShip2.getPosition().y);
        badLifeBar2.setVisible(false);
        //End BadLifeBar2
        points = new Label("+50", skins);
        points.setPosition(100,(Gdx.graphics.getHeight())-500);
        points.setFontScale(tamaño);
        points.setVisible(true);
        titulo = new Label("Score: " + puntos  , skins);
        titulo.setPosition(5,(bar.getY() - bar.getHeight())-5);
        titulo.setFontScale(3);
        forceField = new ForceField((int)ship.getPosition().x, (int)ship.getPosition().y);
        forceTexture = new Texture("buttonforce.png");
        forceTextureRegion = new TextureRegion(forceTexture);
        forceTextureDraw = new TextureRegionDrawable(forceTextureRegion);
        forceButton = new ImageButton(forceTextureDraw);
        forceButton.setPosition(0,400);
        forceButton.setVisible(false);
        forceButton.setWidth(Gdx.graphics.getWidth()/7.2f);
        forceButton.setHeight(Gdx.graphics.getHeight()/11.85f);
        forceNum = new Label("x" + forceNumAux, skins);
        forceNum.setPosition(forceButton.getWidth(), 400);
        forceNum.setFontScale(3f);
        forceNum.setVisible(false);

        forceButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(forceNumAux != 0){
                    forceTrue = true;
                    auxForce = 200;
                    forceBar.setVisible(true);
                    forceNumAux -= 1;
                }
                if (forceNumAux <= 0)
                    forceNumAux =0;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

            }
        });


        if(prefs.getGameBoo()) {
            if (!musicaFondo.isPlaying()) {
                musicaFondo.setLooping(true);
                musicaFondo.play();

            }
        }

        stage.addActor(titulo);
        stage.addActor(points);
        stage.addActor(bar);
        stage.addActor(forceBar);
        stage.addActor(forceButton);
        stage.addActor(forceNum);
        stage.addActor(badLifeBar);
        stage.addActor(badLifeBar2);
    }

    @Override
    protected void handleInput() {
        if(PlayState.life >= 10)
            PlayState.life = 10;

        if(available){
            accelX = Gdx.input.getAccelerometerX();
            if(accelX > 0)
                accelY = -accelX*9;
            if(accelX < 0)
                accelY = -accelX*9;
            /*
            *If world 4 is true, the life bar is placed on the ship, if not then it is
            * placed on top.
            */
            if(back4Bo){
                bar.setSize(ship.getShip().getWidth(), 15);
                bar.setPosition(ship.getPosition().x, ship.getPosition().y + ship.getShip().getHeight());
                titulo.setPosition(5, Gdx.graphics.getHeight()-100);
                if(life >= 7 && life <=10){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descargavs.jpg"))));
                }else if(life >= 4 && life <=6){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga2vs.png"))));
                }else if(life >= 0 && life <=3){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga3vs.png"))));
                }
                barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
                barStyle.knobBefore = barStyle.knob;
                bar.setStyle(barStyle);
                bar.setValue(life);

                badLifeBar.setVisible(true);
                badLifeBar.setSize(Gdx.graphics.getWidth()/2.4f, 15);
                badLifeBar.setPosition(badShip.getPosition().x, badShip.getPosition().y + badShip.getBadTexture().getHeight());
                if(BadShip.aux >= 133 && BadShip.aux <=200){
                    textureBadLife = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descargavs.jpg"))));
                }else if(BadShip.aux >= 66 && BadShip.aux <=132){
                    textureBadLife = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga2vs.png"))));
                }else if(BadShip.aux >= 0 && BadShip.aux <=65){
                    textureBadLife = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga3vs.png"))));
                }
                badLifeStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBadLife);
                badLifeStyle.knobBefore = badLifeStyle.knob;
                badLifeBar.setStyle(badLifeStyle);
                badLifeBar.setValue(BadShip.aux);
                badLifeBar2.setVisible(false);
            }else if(back5Bo){
                bar.setSize(ship.getShip().getWidth(), 15);
                bar.setPosition(ship.getPosition().x, ship.getPosition().y + ship.getShip().getHeight());
                titulo.setPosition(5, Gdx.graphics.getHeight()-100);
                if(life >= 7 && life <=10){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descargavs.jpg"))));
                }else if(life >= 4 && life <=6){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga2vs.png"))));
                }else if(life >= 0 && life <=3){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga3vs.png"))));
                }
                barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
                barStyle.knobBefore = barStyle.knob;
                bar.setStyle(barStyle);
                bar.setValue(life);
                badLifeBar.setVisible(false);



                badLifeBar2.setVisible(true);
                badLifeBar2.setSize(Gdx.graphics.getWidth()/2.77f, 15);
                badLifeBar2.setPosition(badShip2.getPosition().x, badShip2.getPosition().y + badShip2.getBadTexture().getHeight());
                if(BadShip2.aux >= 166 && BadShip2.aux <=300){
                    textureBadLife2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descargavs.jpg"))));
                }else if(BadShip2.aux >= 82 && BadShip2.aux <=165){
                    textureBadLife2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga2vs.png"))));
                }else if(BadShip2.aux >= 0 && BadShip2.aux <=83){
                    textureBadLife2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga3vs.png"))));
                }
                badLifeStyle2 = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBadLife2);
                badLifeStyle2.knobBefore = badLifeStyle2.knob;
                badLifeBar2.setStyle(badLifeStyle2);
                badLifeBar2.setValue(BadShip2.aux);
            }else{
                bar.setSize(Gdx.graphics.getWidth()-40, 80);
                bar.setPosition(20, (Gdx.graphics.getHeight() - bar.getHeight())-150);
                titulo.setPosition(5,(bar.getY() - bar.getHeight())-5);
                if(life >= 7 && life <=10){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga.jpg"))));
                }else if(life >= 4 && life <=6){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga2.png"))));
                }else if(life >= 0 && life <=3){
                    textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("descarga3.png"))));
                }
                barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
                barStyle.knobBefore = barStyle.knob;
                bar.setStyle(barStyle);
                bar.setValue(life);
                badLifeBar.setVisible(false);
                badLifeBar2.setVisible(false);
            }

            vector.add(accelY,0,0);
            ship.setPosition(vector);
        }
        cam.update();
    }

    @Override
    public void update(float dt) {

        if(life <= 0){
            GameOver.tamaño = 0.1f;
            gsm.set(new GameOver(gsm));

        }

        forceBar.setPosition(-220,400);
        forceBar.setSize(500,500);
        titulo.setText("Score: " + puntos);

        if(BadShip.aux <= 0)
            badShipsDead = true;
        if(BadShip2.aux <= 0)
            badShipsDead = true;


        if (tamaño >= 10f) {
            points.setVisible(false);
            tamaño = 0.1f;
            badShipsDead = false;
        }
        if(badShipsDead) {
            points.setVisible(true);
            if (tamaño <= 10f) {
                tamaño += 0.25f;
                points.setFontScale(tamaño);
            }
            System.out.println(tamaño);
        }else {
            points.setVisible(false);
        }

        //Auxiliary variable to see the teleportation time.
        counter = dt;
        /*
        If there is a collision with a black hole, then the bool is 1, it means
        that nothing will be update until it is teleported.
         */
        if(bool == 1){
            render = false;
        }
        if(render){
            //Here the backgrounds are update.
            if(back2Bo){
                back2.update(dt);
            }else if(back3Bo){
                back3.update(dt);
            }else if(back4Bo){
                back4.update(dt);
            }

            if(back){
                aste1.moverAst(dt);
                aste2.moverAst(dt);
                aste3.moverAst(dt);
                aste4.moverAst(dt);
                ship.setRender(true);
                ship.update(dt);
                lifeObject.moveLife(dt);
                BadShip.aux = 200;
                BadShip2.aux = 300;
                hole.moverAgujero(dt);
                handleInput();
                collisions();
                forceTrue = false;
                forceButton.setVisible(false);
                forceBar.setVisible(false);
                forceNum.setVisible(false);
                auxForce = 200;
                forceNumAux = 4;
                badLifeBar.setVisible(false);
                badLifeBar2.setVisible(false);
                badShipsDead = false;
            }else if(back2Bo){
                aste2_1.moverAst(dt);
                aste2_2.moverAst(dt);
                aste2_3.moverAst(dt);
                aste2_4.moverAst(dt);
                aste2_5.moverAst(dt);
                BadShip.aux = 200;
                BadShip2.aux = 300;
                ship.setRender(true);
                ship.update(dt);
                lifeObject.moveLife(dt);
                hole.moverAgujero(dt);
                handleInput();
                collisions();
                forceTrue = false;
                forceButton.setVisible(false);
                forceBar.setVisible(false);
                forceNum.setVisible(false);
                auxForce = 200;
                forceNumAux = 4;
                badLifeBar.setVisible(false);
                badLifeBar2.setVisible(false);
                badShipsDead = false;
            }else if (back3Bo){
                aste3_1.moverAst(dt);
                aste3_2.moverAst(dt);
                aste3_3.moverAst(dt);
                aste3_4.moverAst(dt);
                aste3_5.moverAst(dt);
                BadShip.aux = 200;
                BadShip2.aux = 300;
                ship.setRender(true);
                ship.update(dt);
                lifeObject.moveLife(dt);
                hole.moverAgujero(dt);
                handleInput();
                collisions();
                forceTrue = false;
                forceButton.setVisible(false);
                forceBar.setVisible(false);
                forceNum.setVisible(false);
                auxForce = 200;
                forceNumAux = 4;
                badLifeBar.setVisible(false);
                badLifeBar2.setVisible(false);
                badShipsDead = false;
            }
            else if (back4Bo){
                handleInput();
                badLifeBar.setVisible(true);
                badLifeBar2.setVisible(false);
                ship.setRender(true);
                ship.update(dt);
                badShip.update(dt);
                BadShip2.aux = 300;
                lifeObject.moveLife(dt);badShipsDead = false;

                if(forceNumAux != 0){
                    forceNum.setVisible(true);
                }
                forceButton.setVisible(true);
                if(forceTrue){
                    forceField.setX_start((int)ship.getPosition().x);
                    forceField.setY_start((int)ship.getPosition().y);
                    forceField.update(dt);
                    forceBar.setVisible(true);
                    forceBar.setValue(auxForce);
                    forceButton.setVisible(false);
                    auxForce -= dt;
                }else{
                    if(forceNumAux != 0){
                        forceBar.setValue(auxForce);
                        auxForce +=dt;
                        if(auxForce >= 200){
                            forceButton.setVisible(true);
                        }
                    }
                }
                if(auxForce <=0) {
                    forceTrue = false;
                    forceBar.setVisible(false);
                }

                collisions();
            }else if(back5Bo){
                handleInput();
                ship.setRender(true);
                badLifeBar.setVisible(false);
                badLifeBar2.setVisible(true);
                ship.update(dt);
                BadShip.aux = 200;
                badShip2.update(dt);
                lifeObject.moveLife(dt);
                badShipsDead = false;
                if(forceNumAux != 0){
                    forceNum.setVisible(true);
                }
                forceButton.setVisible(true);
                if(forceTrue){
                    forceField.setX_start((int)ship.getPosition().x);
                    forceField.setY_start((int)ship.getPosition().y);
                    forceField.update(dt);
                    forceBar.setVisible(true);
                    forceBar.setValue(auxForce);
                    forceButton.setVisible(false);
                    auxForce -= dt;
                }else{
                    if(forceNumAux != 0){
                        forceBar.setValue(auxForce);
                        auxForce +=dt;
                        if(auxForce >= 200){
                            forceButton.setVisible(true);
                        }
                    }
                }
                if(auxForce <=0) {
                    forceTrue = false;
                    forceBar.setVisible(false);
                }

                collisions();
            }

            forceNum.setText("x" + forceNumAux);

        }else{
            collisions();
            Vector3 vec = new Vector3(ship.getPosition().x-100, ship.getPosition().y, 0);
            teleport.setPosition(vec);
            teleport.update(dt);
            aux += counter *2;
            backTeleport.update(dt);
            auxTeleport += 1;
            if(auxTeleport == 1) {
                if(prefs.getExtraSound())
                teleports.play();

            }

            if(bool == 0 && aux >.5f){
                auxTeleport = 0;
                hole.setX_start((int) hole.getPosition().x);
                hole.setY_start((int) hole.getPosition().y-100);
                aux = 0;
                bool = 0;
                auxCont = auxAsteRandom;

                auxAsteRandom = randomAste.nextInt(5);
                if(auxCont == auxAsteRandom && auxAsteRandom == 0)
                    auxAsteRandom = 1;
                if(auxCont == auxAsteRandom && auxAsteRandom == 1)
                    auxAsteRandom = 3;
                if(auxCont == auxAsteRandom && auxAsteRandom == 2)
                    auxAsteRandom = 0;
                if(auxCont == auxAsteRandom && auxAsteRandom == 3)
                    auxAsteRandom = 4;
                if(auxCont == auxAsteRandom && auxAsteRandom == 4)
                    auxAsteRandom = 2;
                if(auxAsteRandom == 0){
                    back = true;
                    back2Bo = false;
                    back3Bo = false;
                    back4Bo = false;
                    back5Bo = false;
                }else if(auxAsteRandom == 1){
                    back = false;
                    back2Bo = true;
                    back3Bo  = false;
                    back4Bo = false;
                    back5Bo = false;
                }else if(auxAsteRandom == 2){
                    back = false;
                    back2Bo = false;
                    back3Bo  = true;
                    back4Bo = false;
                    back5Bo = false;
                }else if(auxAsteRandom == 3){
                    back = false;
                    back2Bo = false;
                    back3Bo  = false;
                    back4Bo = true;
                    back5Bo = false;
                }else if(auxAsteRandom == 4){
                    back = false;
                    back2Bo = false;
                    back3Bo  = false;
                    back4Bo = false;
                    back5Bo = true;
                }

                render = true;
            }

            if(!back5Bo && !back4Bo)
                hole.moverAgujero(dt);
        }


    }

    public void collisions(){
            if(ship.colision(hole.getArea())){
                bool = 1;
            }else{
                bool = 0;
            }

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.input.setInputProcessor(stage);
        sb.setProjectionMatrix(cam.combined);
        if(render){
            mov -= 10;
            movAux -= 10;
            if(mov < -Gdx.graphics.getHeight()){
                mov= 0;
            }
            if(movAux < 0){
                movAux= Gdx.graphics.getHeight();
            }
        }

        sb.begin();


        if(render){
            if(back){
                sb.draw(backTexture, 0,mov, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                sb.draw(backAux, 0,movAux, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }else if(back2Bo){
                sb.draw(back2.getFondo(),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
            }else if(back3Bo){
                sb.draw(back3.getFondo(),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
            }else if(back4Bo){
                sb.draw(back4.getFondo(),0,0 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }else if(back5Bo){
                sb.draw(back5.getBackTexture(), 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
            if(!back4Bo && !back5Bo)
            sb.draw(hole.getAgu(), hole.getPosition().x, hole.getPosition().y,Gdx.graphics.getWidth()/2.88f, Gdx.graphics.getHeight()/4.736f);
            sb.draw(lifeObject.getLife(), lifeObject.getPosition().x, lifeObject.getPosition().y);
            sb.draw(ship.getShip(), ship.getPosition().x, ship.getPosition().y, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/9);
            if(back){
                aste1.render(sb);
                sb.draw(aste1.getAste(),aste1.getPosition().x, aste1.getPosition().y, Gdx.graphics.getWidth()/4.23f, Gdx.graphics.getHeight()/6.6f);
                aste2.render(sb);
                sb.draw(aste2.getAste(),aste2.getPosition().x, aste2.getPosition().y,Gdx.graphics.getWidth()/4.23f, Gdx.graphics.getHeight()/6.6f);
                aste3.render(sb);
                sb.draw(aste3.getAste(),aste3.getPosition().x, aste3.getPosition().y,Gdx.graphics.getWidth()/4.23f, Gdx.graphics.getHeight()/6.6f);
                aste4.render(sb);
                sb.draw(aste4.getAste(),aste4.getPosition().x, aste4.getPosition().y, Gdx.graphics.getWidth()/4.23f, Gdx.graphics.getHeight()/6.6f);
            }else if(back2Bo){
                aste2_1.render(sb);
                sb.draw(aste2_1.getAste(),aste2_1.getPosition().x, aste2_1.getPosition().y, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6.6f);
                aste2_2.render(sb);
                sb.draw(aste2_2.getAste(),aste2_2.getPosition().x, aste2_2.getPosition().y, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6.6f);
                aste2_3.render(sb);
                sb.draw(aste2_3.getAste(),aste2_3.getPosition().x, aste2_3.getPosition().y, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6.6f);
                aste2_4.render(sb);
                sb.draw(aste2_4.getAste(),aste2_4.getPosition().x, aste2_4.getPosition().y,Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6.6f);
                aste2_5.render(sb);
                sb.draw(aste2_5.getAste(),aste2_5.getPosition().x, aste2_5.getPosition().y, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6.6f);
            }
            else if(back3Bo){
                aste3_1.render(sb);
                sb.draw(aste3_1.getAste(),aste3_1.getPosition().x, aste3_1.getPosition().y,Gdx.graphics.getWidth()/3.6f, Gdx.graphics.getHeight()/6.4f);
                aste3_2.render(sb);
                sb.draw(aste3_2.getAste(),aste3_2.getPosition().x, aste3_2.getPosition().y,Gdx.graphics.getWidth()/3.6f, Gdx.graphics.getHeight()/6.4f);
                aste3_3.render(sb);
                sb.draw(aste3_3.getAste(),aste3_3.getPosition().x, aste3_3.getPosition().y,Gdx.graphics.getWidth()/3.6f, Gdx.graphics.getHeight()/6.4f);
                aste3_4.render(sb);
                sb.draw(aste3_4.getAste(),aste3_4.getPosition().x, aste3_4.getPosition().y,Gdx.graphics.getWidth()/3.6f, Gdx.graphics.getHeight()/6.4f);
                aste3_5.render(sb);
                sb.draw(aste3_5.getAste(),aste3_5.getPosition().x, aste3_5.getPosition().y,Gdx.graphics.getWidth()/3.6f, Gdx.graphics.getHeight()/6.4f);
            }else if(back4Bo){
                sb.draw(badShip.getBad(), badShip.getPosition().x, badShip.getPosition().y,Gdx.graphics.getWidth()/2.4f, Gdx.graphics.getHeight()/5.92f);
                badShip.render(sb);
                ship.render(sb);
                if(forceTrue){
                    sb.draw(forceField.getForce(), forceField.getPosition().x - (ship.getShip().getWidth()/2),forceField.getPosition().y - (ship.getShip().getHeight()/2), ship.getShip().getWidth()*2, ship.getShip().getWidth()*2);
                }
            }else if(back5Bo){
                sb.draw(badShip2.getBadTexture(), badShip2.getPosition().x, badShip2.getPosition().y,Gdx.graphics.getWidth()/2.77f, Gdx.graphics.getHeight()/5.92f);
                badShip2.render(sb);
                ship.render(sb);
                if(forceTrue) {
                    sb.draw(forceField.getForce(), forceField.getPosition().x - (ship.getShip().getWidth()/2),forceField.getPosition().y - (ship.getShip().getHeight()/2), ship.getShip().getWidth()*2, ship.getShip().getWidth()*2);
                }
            }

            if(Ship.enters){
                for (int i = 0; i < ship.bulletList.size(); i++)
                    sb.draw(ship.bulletList.get(i).getBulletTexture(), ship.bulletList.get(i).getPosition().x, ship.bulletList.get(i).getPosition().y, Gdx.graphics.getWidth()/18, Gdx.graphics.getHeight()/29.6f);
            }
            if(back4Bo){
                for (int i = 0; i < badShip.listBadBullet.size(); i++) {
                    sb.draw(badShip.listBadBullet.get(i).getBulletTexture(), badShip.listBadBullet.get(i).getPosition().x, badShip.listBadBullet.get(i).getPosition().y, Gdx.graphics.getWidth()/13.09f, Gdx.graphics.getHeight()/21.52f);
                }
            }
            if(back5Bo){
                for (int i = 0; i < badShip2.listBadBullet.size(); i++) {
                    sb.draw(badShip2.listBadBullet.get(i).getBulletTexture(), badShip2.listBadBullet.get(i).getPosition().x, badShip2.listBadBullet.get(i).getPosition().y, Gdx.graphics.getWidth()/13.09f, Gdx.graphics.getHeight()/21.52f);
                }
            }
            ship.renderFlash(sb);
        }else{
            sb.draw(backTeleport.getFondo(), 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if(!render)
        sb.draw(teleport.getTeleport(), teleport.getPosition().x, teleport.getPosition().y,Gdx.graphics.getWidth()/2.057f, Gdx.graphics.getHeight()/3.38f);

        sb.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
     ship.dispose();
     aste1.dispose();
     aste2.dispose();
     aste3.dispose();
     aste4.dispose();
     aste2_1.dispose();
     aste2_2.dispose();
     aste2_3.dispose();
     aste2_4.dispose();
     aste2_5.dispose();
     aste3_1.dispose();
     aste3_2.dispose();
     aste3_3.dispose();
     aste3_4.dispose();
     aste3_5.dispose();
     backTexture.dispose();
     backAux.dispose();
     hole.dispose();
     teleport.dispose();
     backTeleport.dispose();
     stage.dispose();
     back2.dispose();
     back3.dispose();
     back4.dispose();
     back5.dispose();
     badShip.dispose();
     badShip2.dispose();
     forceField.dispose();
     forceTexture.dispose();
     lifeObject.dispose();
     teleports.dispose();
     musicaFondo.dispose();
    }
    //3276
}
