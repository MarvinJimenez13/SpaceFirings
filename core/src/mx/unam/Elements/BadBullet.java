package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import mx.unam.States.PlayState;


public class BadBullet {

    private float y_aux;
    private float x_start, y_start;
    private Vector3 position;
    private Texture bulletTexture;
    private Rectangle bounds;


    public BadBullet(float x, float y){
        x_start = x;
        y_start = y;
        position = new Vector3(x_start, y_start, 0);
        bulletTexture = new Texture("badbullet.png");
       bounds = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/13.09f, Gdx.graphics.getHeight()/21.52f);
    }


    public void mover() {
        if(PlayState.back5Bo) {
            if(PlayState.puntos <300)
                y_aux = 25;
            if(PlayState.puntos>=300)
            y_aux = 35;
            bulletTexture = new Texture("badbullet2.png");
        }
        if(PlayState.back4Bo) {

            if(PlayState.puntos <300)
                y_aux = 25;
            if(PlayState.puntos>=300)
                y_aux = 35;
            bulletTexture = new Texture("badbullet.png");
        }
        y_start -= y_aux;
        position.set(x_start, y_start, 0);
        bounds.setPosition(position.x, position.y);
    }

    public boolean colision(Rectangle player){
        return player.overlaps(bounds);
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
