package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;
import mx.unam.Animation.Animation;
import mx.unam.States.PlayState;
import mx.unam.States.Preferencess;

public class Asteroid {

    public int x_start, y_start, y_aux;
    private Texture asteTexture;
    private Vector3 position;
    private Animation aniAste;
    private Random numberX;
    private Boom boom;
    private Boom2 boom2;
    private int counter1 = 0, counter2 =0;
    private static Rectangle bounds;
    private boolean render1 = false, render2 = false, enters = true;
    private SpriteBatch sb;
    private int frameInt1 = 0, frameInt2 = 0;
    private Sound aste;
    private Preferencess prefs;

    public Asteroid(int x, int y){
        this.x_start = x;
        this.y_start = y;
        prefs = new Preferencess();
        sb = new SpriteBatch();
        position = new Vector3(x, y, 0);
        numberX = new Random();
        asteTexture = new Texture("asteanim.png");
        aniAste = new Animation(new TextureRegion(asteTexture), 20, 1.5f);
        bounds = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/4.23f, Gdx.graphics.getHeight()/6.6f);
        boom = new Boom((int)position.x, (int)position.y);
        boom2 = new Boom2((int)position.x, (int)position.y);
        aste =  Gdx.audio.newSound(Gdx.files.internal("audio/meteroros.mp3"));
    }

    public void moverAst(float dt){
        aniAste.update(dt);
        //The explosions are update, but they are not shown until collision is true.
        boom.update(dt);
        boom2.update(dt);
        if(PlayState.puntos <50)
            y_aux = 18;
        if(PlayState.puntos>=50 && PlayState.puntos<150)
        y_aux = 23;
        if(PlayState.puntos >= 150 && PlayState.puntos <350 )
            y_aux = 30;
        if(PlayState.puntos >= 350 && PlayState.puntos< 500)
            y_aux = 35;
        if(PlayState.puntos >= 500 && PlayState.puntos < 1200)
            y_aux =45;
        if(PlayState.puntos >= 1200)
            y_aux = 49;
        y_start -= y_aux;
        position.set(x_start, y_start, 0);
        bounds.setPosition(position.x, position.y);
        generate();
        colisionBalas();
    }


    public void colisionBalas(){

        for (int i = 0; i< PlayState.ship.bulletList.size(); i++){
            Bullet bullet = PlayState.ship.bulletList.get(i);
            if(frameInt2 == 1) {
                boom2.setFrameInt(0);
                PlayState.puntos += 1;
                if(prefs.getSoundAsteBoo())
                aste.play();
            }
            /*
            When there is a collision, it enters several times and that generate
            a problem, so I create auxiliary variables to generate a explosion once.
             */
            if(bullet.colision(bounds)){
                Vector3 vec = new Vector3(position.x, position.y, 0);
                boom2.setPosition(vec);
                Random number = new Random();
                Random number2 = new Random();
                int asteY = number2.nextInt(500);
                int asteX = number.nextInt(Gdx.graphics.getWidth()-150);
                x_start = asteX;
                y_start= Gdx.graphics.getHeight()+asteY;
                render2 = true;
                /*
                When the collison is generated, the frameInt2 increases by 1,
                and will enter the previous condition.
                 */
                frameInt2++;
            }else{
                frameInt2 = 0;
            }
        }

    }
    public void generate(){
        if(frameInt1 == 1) {
            boom.setFrameInt(0);
            if(PlayState.life <= 0){
                PlayState.life = 10;
                enters = true;
            }else {
                if(prefs.getSoundAsteBoo())
                aste.play();
                PlayState.life -= 1;
                enters = false;
            }
        }
        if(PlayState.ship.colision(bounds)){
            Vector3 vec = new Vector3(position.x, position.y, 0);
            boom.setPosition(vec);
            Random number = new Random();
            Random number2 = new Random();
            int asteY = number2.nextInt(300);
            int asteX = number.nextInt(Gdx.graphics.getWidth()-150);
            position.set(asteX, Gdx.graphics.getHeight()+asteY,0);
            render1 = true;
            frameInt1++;
        }else{
            frameInt1 = 0;
        }
        if (position.y < -250){
            y_start = Gdx.graphics.getHeight();
            x_start = numberX.nextInt((Gdx.graphics.getWidth())-150);
            position.set(x_start, y_start, 0);
        }
    }

    public void render(SpriteBatch sb){
        if(render1){
            sb.draw(boom.getBoom(), boom.getPosition().x,boom.getPosition().y, Gdx.graphics.getWidth()/3.214f, Gdx.graphics.getHeight()/5.431f);
            counter1++;
            if(counter1 == 6){
                render1= false;
                counter1 = 0;
        }
        }
        if(render2){
            sb.draw(boom2.getBoom2(), boom2.getPosition().x,boom2.getPosition().y, Gdx.graphics.getWidth()/4.58f, Gdx.graphics.getHeight()/5.17f);

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

    public TextureRegion getAste() {
        return aniAste.getFrame();
    }


    public void dispose(){
        asteTexture.dispose();
        boom.dispose();
        boom2.dispose();
        aste.dispose();
    }


}
