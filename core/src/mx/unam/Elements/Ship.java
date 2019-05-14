package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import mx.unam.States.PlayState;
import mx.unam.States.Preferencess;

public class Ship {

    private Texture ship;
    private Vector3 position;
    private Bullet bullet;
    public  ArrayList<Bullet> bulletList;
    public static boolean enters = false;
    public static boolean  render = false;
    private Rectangle bounds;
    private Boom boom2;
    private Flash flash;
    private int auxVida= 0, frameInt2 = 0;
    private boolean render1 = false, render2 = false, render3 = false;
    private int counter1 = 0, counter2 =0;
    private Sound shotShip, vida;
    private Preferencess prefs;


    public Ship(int x, int y){
        System.out.println("Ancho: " + Gdx.graphics.getWidth());
        System.out.println("Alto: " + Gdx.graphics.getHeight());
        prefs = new Preferencess();
        ship = new Texture("ship.png");
        position = new Vector3(x,y,0);
        bulletList = new ArrayList<Bullet>();
        boom2 = new Boom((int)position.x, (int)position.y);
        flash = new Flash((int)position.x, (int)position.y);
        bounds = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/5.2f, (Gdx.graphics.getHeight()/9.2f)-75);
        shotShip =  Gdx.audio.newSound(Gdx.files.internal("audio/disparoship.mp3"));
        vida =  Gdx.audio.newSound(Gdx.files.internal("audio/vida.mp3"));
    }

public void update(float dt){
        boom2.update(dt);
        flash.update(dt);

    if(position.x < 0){
        position.x = 0;
    }
     if(position.x > Gdx.graphics.getWidth()- ship.getWidth()){
        position.x = Gdx.graphics.getWidth()- ship.getWidth();
     }

    bounds.setPosition(position.x, position.y);
    if(Gdx.input.justTouched() && render){
        if(prefs.getSoudShipsBoo())
        shotShip.play();
        bulletList.add(bullet = new Bullet(position.x+ (ship.getWidth()/2)-25, position.y));
        enters = true;
    }
    if(enters){
        for (int i = 0; i < bulletList.size(); i++)
            bulletList.get(i).mover();
    }

    for (int i = 0; i < bulletList.size(); i++){
        if(bulletList.get(i).getPosition().y > Gdx.graphics.getHeight()){
            bulletList.remove(i);
        }

    }

    if(!PlayState.forceTrue)
    colisiones();
}

   public void colisiones(){
       for (int i = 0; i< BadShip.listBadBullet.size(); i++){
           BadBullet bullet = BadShip.listBadBullet.get(i);
           if(frameInt2 == 1) {
               boom2.setFrameInt(0);
               PlayState.life-= .5f;
           }
           if(bullet.colision(bounds)){
               Vector3 vec = new Vector3(position.x, position.y, 0);
               boom2.setPosition(vec);
               render2 = true;
               frameInt2++;
           }else{
               frameInt2 = 0;
           }
       }

       for (int i = 0; i< BadShip2.listBadBullet.size(); i++){
           BadBullet bullet = BadShip2.listBadBullet.get(i);
           if(frameInt2 == 1) {
               boom2.setFrameInt(0);
               PlayState.life-= .5f;
           }
           if(bullet.colision(bounds)){
               Vector3 vec = new Vector3(position.x, position.y, 0);
               boom2.setPosition(vec);
               render3 = true;
               frameInt2++;
           }else{
               frameInt2 = 0;
           }
       }

       if(PlayState.lifeObject.colision(bounds)){
           Vector3 vec = new Vector3(position.x, position.y, 0);
           flash.setPosition(vec);
           render1 = true;
           auxVida +=1;
           if(PlayState.life >= 10)
               PlayState.life = 10;
           PlayState.life += 1f;
           System.out.println("LA VIDA ES: " +PlayState.life);
       }else {
           render1 = false;
           auxVida = 0;
       }

       if(auxVida == 1) {
           if(prefs.getExtraSound())
           vida.play();
       }
   }

   public void renderFlash(SpriteBatch sb){
       if(render1){
           sb.draw(flash.getFlash(), flash.getPosition().x-65, flash.getPosition().y-65, Gdx.graphics.getWidth()/2.66f, Gdx.graphics.getHeight()/4.36f);
       }
   }
   public void render(SpriteBatch sb){

       if(render2){
           sb.draw(boom2.getBoom(), boom2.getPosition().x,boom2.getPosition().y, Gdx.graphics.getWidth()/3.214f, Gdx.graphics.getHeight()/5.431f);

           counter2++;
           if(counter2 == 38){
               render2 = false;
               counter2 = 0;

           }
       }

       if(render3){
           sb.draw(boom2.getBoom(), boom2.getPosition().x,boom2.getPosition().y, Gdx.graphics.getWidth()/3.214f, Gdx.graphics.getHeight()/5.431f);

           counter1++;
           if(counter1 == 38){
               render3 = false;
               counter1 = 0;

           }
       }
   }

    public boolean colision(Rectangle player){
        return player.overlaps(bounds);
    }

    public Texture getShip() {
        return ship;
    }

    public static void setRender(boolean render) {
        Ship.render = render;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void dispose(){
        ship.dispose();
        flash.dispose();
        bulletList.clear();
        shotShip.dispose();
        vida.dispose();
}

}
