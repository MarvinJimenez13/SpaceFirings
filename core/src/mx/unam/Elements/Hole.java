package mx.unam.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;
import mx.unam.Animation.Animation;
import mx.unam.States.PlayState;

public class Hole {

    private int x_start, y_start, y_aux;
    private Vector3 position;
    private Texture holeText;
    private Animation animation;
    private Random numberX;
    private Rectangle area;

    public Hole(int x, int y){
        this.x_start = x;
        this.y_start = y;
        position = new Vector3(x, y, 0);
        holeText = new Texture("hole.png");
        numberX = new Random();
        animation = new Animation(new TextureRegion(holeText), 15, 0.51f);
        area = new Rectangle(position.x+100, position.y+80, Gdx.graphics.getWidth()/9, Gdx.graphics.getHeight()/23.68f);
    }

    public void moverAgujero(float dt){
       animation.update(dt);
       if(PlayState.puntos > 80) {
           y_aux = 15;
           y_start -= y_aux;
           position.set(x_start, y_start, 0);
           area.setPosition(position.x + 100, position.y + 80);
       }
        if(!PlayState.back4Bo && !PlayState.back5Bo && PlayState.puntos >80 )
        generate();
    }

    public void generate(){
            if (position.y < -250){
                y_start = Gdx.graphics.getHeight()*8;
                x_start = numberX.nextInt((Gdx.graphics.getWidth())-250);
                position.set(x_start, y_start, 0);
            }

    }

    public TextureRegion getAgu() {
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
      holeText.dispose();
    }

}
