package mx.unam.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import mx.unam.Animation.AnimationBoom;

public class Boom2 {
    private Vector3 position;
    private Texture exploTexture;
    private AnimationBoom aniBoom;
    private int x, y;

    public Boom2(int x, int y){
        this.x = x;
        this.y = y;
        position = new Vector3(0,0,0);
        position.set(x, y, 0);
        exploTexture = new Texture("explo2.png");
        aniBoom = new AnimationBoom(new TextureRegion(exploTexture), 19, 1f);
    }

    public void update(float dt){
        aniBoom.update(dt);
    }

    public void setFrameInt(int frame){
        aniBoom.frame = frame;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public TextureRegion getBoom2() {
        return aniBoom.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose(){
        exploTexture.dispose();
    }
}
