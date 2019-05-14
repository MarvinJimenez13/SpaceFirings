package mx.unam.Elements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;
import mx.unam.Animation.Animation;


public class ForceField {
    private int x_start, y_start, y_aux;
    private Vector3 position;
    private Texture forceText;
    private Animation animation;
    private Random numberX;
    private Rectangle area;

    public ForceField(int x, int y){
        this.x_start = x;
        this.y_start = y;
        position = new Vector3(x, y, 0);
        forceText = new Texture("force.png");
        numberX = new Random();
        animation = new Animation(new TextureRegion(forceText), 6, .3f);
        area = new Rectangle(position.x, position.y, Gdx.graphics.getWidth()/1.44f,Gdx.graphics.getHeight()/2.368f);
    }

    public void update(float dt){
        animation.update(dt);
        position.set(x_start, y_start, 0);
        area.setPosition(position.x, position.y);
    }


    public TextureRegion getForce() {
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
        forceText.dispose();
    }

}
