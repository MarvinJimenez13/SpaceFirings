package mx.unam.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import mx.unam.Animation.AnimationBoom;

public class Boom {

    private Vector3 position;
    private Texture exploTexture;
    private AnimationBoom aniBoom;
    private int x, y;

    public Boom(int x, int y){
        this.x = x;
        this.y = y;
        position = new Vector3(0,0,0);
        position.set(x, y, 0);
        exploTexture = new Texture("explo.png");
        aniBoom = new AnimationBoom(new TextureRegion(exploTexture), 6, 0.25f);
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
