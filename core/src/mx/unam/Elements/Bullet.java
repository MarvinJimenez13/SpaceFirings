package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bullet {

    private float y_aux;
    private float x_start, y_start;
    private Vector3 position;
    private Texture bulletTexture;
    private Rectangle area;


   public Bullet(float x, float y){
       x_start = x;
       y_start = y;
       position = new Vector3(x_start, y_start, 0);
       bulletTexture = new Texture("bullet.png");
       area = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/18, Gdx.graphics.getHeight()/29.6f);
   }


    public void mover() {
        y_aux = 50;
        y_start += y_aux;
        position.set(x_start, y_start, 0);
        area.setPosition(position.x, position.y);
    }

    public boolean colision(Rectangle player){
        return player.overlaps(area);
    }

    public void dispose(){
       bulletTexture.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBulletTexture() {
        return bulletTexture;
    }


}
