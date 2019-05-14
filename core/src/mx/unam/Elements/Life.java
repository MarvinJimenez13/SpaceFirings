package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;
import mx.unam.Animation.Animation;
import mx.unam.States.PlayState;


public class Life {


    private int x_start, y_start, y_aux;
    private Vector3 position;
    private Texture lifeText;
    private Animation animation;
    private Random numberX;
    private Rectangle area;

    public Life(int x, int y){
        this.x_start = x;
        this.y_start = y;
        position = new Vector3(x, y, 0);
        lifeText = new Texture("vida.png");
        numberX = new Random();
        animation = new Animation(new TextureRegion(lifeText), 25, 0.4f);
        area = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/4.8f, Gdx.graphics.getHeight()/7.893f);
    }

    public void moveLife(float dt){
        animation.update(dt);
        if(PlayState.puntos > 90){

            if(PlayState.puntos <150)
                y_aux = 10;
            if(PlayState.puntos>=150 && PlayState.puntos<250)
                y_aux = 11;
            if(PlayState.puntos >=250 && PlayState.puntos <350 )
                y_aux = 13;
            if(PlayState.puntos >=350)
                y_aux = 15;
            y_start-= y_aux;
            position.set(x_start, y_start, 0);
            area.setPosition(position.x+100, position.y+80);
            generate();
        }

    }

    public void generate(){
        if (position.y < -250){
            y_start = Gdx.graphics.getHeight()*5;
            x_start = numberX.nextInt((Gdx.graphics.getWidth())-150);
            position.set(x_start, y_start, 0);
        }

    }

    public boolean colision(Rectangle player){
        return player.overlaps(area);
    }

    public TextureRegion getLife() {
        return animation.getFrame();
    }

    public void setX_start(int x_start) {
        this.x_start = x_start;
    }

    public void setY_start(int y_start) {
        this.y_start = y_start;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getArea() {
        return area;
    }

    public void dispose(){
        lifeText.dispose();
    }
}
