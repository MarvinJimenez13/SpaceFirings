package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

import mx.unam.Animation.Animation;
import mx.unam.States.PlayState;
import mx.unam.States.Preferencess;

public class BadShip {

    private int x,y,mov,binLefRig, frameInt2 = 0,counter1 = 0,counter2 =0;
    private Vector3 position;
    private Texture badTexture;
    private Animation animation;
    public static ArrayList<BadBullet> listBadBullet;
    private Rectangle bounds;
    private Boom boom2;
    private BadBoom badBoom;
    private boolean render1= false, render2 = false;
    public static int aux=200;
    public boolean enters = true;
    private Sound shotShip, explo;
    private Preferencess prefs;


    public BadShip(int x, int y, int binLefRig){
        this.x = x;
        this.y = y;
        prefs = new Preferencess();
        this.binLefRig = binLefRig;
        badTexture = new Texture("naveenemiga.png");
        position = new Vector3(x,y,0);
        listBadBullet = new ArrayList<BadBullet>();
        bounds = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/2.4f, Gdx.graphics.getHeight()/5.92f);
        animation = new Animation(new TextureRegion(badTexture), 4, 1f);
        boom2 = new Boom((int)position.x, (int)position.y);
        badBoom = new BadBoom((int)position.x,(int)position.y);
        shotShip =  Gdx.audio.newSound(Gdx.files.internal("audio/disparobadships.mp3"));
        explo =  Gdx.audio.newSound(Gdx.files.internal("audio/explobad.mp3"));
    }

    public void update(float dt){
        //The explosions are update, but they are not shown until collision is true.
        boom2.update(dt);
        badBoom.update(dt);
        animation.update(dt);
        bounds.setPosition(position.x, position.y);

        mov =10;




        //If enters is true, everything is update.
        if(enters){
            if (binLefRig== 0) {
                x -= mov;
                if (x < 1) {
                    binLefRig = 1;
                }

            }
            if (binLefRig == 1) {
                x += mov;
                if (x > (Gdx.graphics.getWidth()- (badTexture.getWidth()/4))) {
                    binLefRig = 0;
                }
            }

            position.set(x,y,0);
            colisionBalas();
            shot();
        }

        /*
        If the shots are 50, I clean enemy bullets, I stop updating everything and
        increase the auxiliary so that it does not enter again.
         */
        if(aux ==0){
            listBadBullet.clear();
            enters = false;
            badBoom.setFrameInt(0);
            Vector3 vec = new Vector3(position.x-100, position.y-100, 0);
            badBoom.setPosition(vec);
            render1 = true;
            aux--;
            PlayState.puntos += 100;
            if(prefs.getExtraSound())
            explo.play();
        }

    }

    public void shot(){
            if ((position.x % 50) == 0 || position.x <= 0 || position.x >= Gdx.graphics.getWidth()) {
                System.out.println("ABAJO DE 300 NAVE 11111111111111111111111");
                if (prefs.getSoudShipsBoo())
                    shotShip.play();
                listBadBullet.add(new BadBullet(position.x + ((badTexture.getWidth() / 4) / 2), position.y + 10));
            }


            for (int i = 0; i < listBadBullet.size(); i++)
                listBadBullet.get(i).mover();


        for (int i = 0; i < listBadBullet.size(); i++){
            if(listBadBullet.get(i).getPosition().y < 0){
                listBadBullet.remove(i);
            }

        }

    }

    public void colisionBalas() {

        for (int i = 0; i < PlayState.ship.bulletList.size(); i++) {
            Bullet bullet = PlayState.ship.bulletList.get(i);
            if (frameInt2 == 1) {
                boom2.setFrameInt(0);
                aux -=1;
            }
            if (bullet.colision(bounds)) {
                Vector3 vec = new Vector3(position.x+20, position.y, 0);
                boom2.setPosition(vec);
                render2 = true;
                frameInt2++;
            } else {
                frameInt2 = 0;
            }
        }
    }

    public void render(SpriteBatch sb){
        if(render1){
            sb.draw(badBoom.getBoom(), badBoom.getPosition().x-100,badBoom.getPosition().y-100,Gdx.graphics.getWidth()/1.2f,Gdx.graphics.getHeight()/2.368f);

            counter1++;
            if(badBoom.getFrameInt() == 9){
                render1 = false;
                counter1 = 0;
                Random num= new Random();
                int numb =num.nextInt(5);
                PlayState.auxAsteRandom = numb;
                if( PlayState.auxAsteRandom == 0){
                    PlayState.back = true;
                    PlayState.back2Bo = false;
                    PlayState.back3Bo = false;
                    PlayState.back4Bo = false;
                    PlayState.back5Bo = false;
                }else if( PlayState.auxAsteRandom == 1){
                    PlayState.back = false;
                    PlayState.back2Bo = true;
                    PlayState.back3Bo  = false;
                    PlayState.back4Bo = false;
                    PlayState.back5Bo = false;
                }else if( PlayState.auxAsteRandom == 2){
                    PlayState.back = false;
                    PlayState.back2Bo = false;
                    PlayState.back3Bo  = true;
                    PlayState.back4Bo = false;
                    PlayState.back5Bo = false;
                }else if( PlayState.auxAsteRandom == 3){
                    PlayState.back = false;
                    PlayState.back2Bo = false;
                    PlayState.back3Bo  = true;
                    PlayState.back4Bo = false;
                    PlayState.back5Bo = false;
                }else if(PlayState.auxAsteRandom == 4){
                    PlayState.back = false;
                    PlayState.back2Bo = false;
                    PlayState.back3Bo  = false;
                    PlayState.back4Bo = false;
                    PlayState.back5Bo = true;
                }
                enters = true;
                aux =200;
            }
        }
        if(render2){
            sb.draw(boom2.getBoom(), boom2.getPosition().x,boom2.getPosition().y, Gdx.graphics.getWidth()/3.214f, Gdx.graphics.getHeight()/5.431f);

            counter2++;
            if(counter2 == 38){
                render2 = false;
                counter2 = 0;
            }
        }

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBadTexture() {
        return badTexture;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Animation getAnimation() {
        return animation;
    }

    public TextureRegion getBad() {
        return animation.getFrame();
    }

    public void dispose(){
     badTexture.dispose();
     listBadBullet.clear();
     boom2.dispose();
     shotShip.dispose();
     explo.dispose();
    }
}
