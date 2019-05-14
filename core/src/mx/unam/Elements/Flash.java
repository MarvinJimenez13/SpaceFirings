package mx.unam.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import mx.unam.Animation.Animation;
import mx.unam.Animation.AnimationBoom;

public class Flash {


    private Vector3 position;
    private Texture exploTexture;
    private Animation flashAnimation;
    private int x, y;

    public Flash(int x, int y){
        this.x = x;
        this.y = y;
        position = new Vector3(0,0,0);
        position.set(x, y, 0);
        exploTexture = new Texture("flash.png");
        flashAnimation = new Animation(new TextureRegion(exploTexture), 4, 0.3f);
    }

    public void update(float dt){
        flashAnimation.update(dt);
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public TextureRegion getFlash() {
        return flashAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose(){
        exploTexture.dispose();
    }
}
