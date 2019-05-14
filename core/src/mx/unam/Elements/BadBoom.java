package mx.unam.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import mx.unam.Animation.AnimationBadBoom;
import mx.unam.Animation.AnimationBoom;

public class BadBoom {

    private Vector3 position;
    private Texture exploTexture;
    private AnimationBadBoom aniBoom;
    private int x, y;

    public BadBoom(int x, int y){
        this.x = x;
        this.y = y;
        position = new Vector3(0,0,0);
        position.set(x, y, 0);
        exploTexture = new Texture("explonavee.png");
        aniBoom = new AnimationBadBoom(new TextureRegion(exploTexture), 10, 1.5f);
    }

    public void update(float dt){
        aniBoom.update(dt);
    }

    public void setFrameInt(int frame){
        aniBoom.frame = frame;
    }

    public int getFrameInt(){
        return aniBoom.getFrameInt();
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public TextureRegion getBoom() {
        return aniBoom.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose(){
        exploTexture.dispose();
    }
}
