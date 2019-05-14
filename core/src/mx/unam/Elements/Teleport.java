package mx.unam.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import mx.unam.Animation.Animation;

public class Teleport {

    private Vector3 position;
    private Texture teleTexture;
    private Animation teleAnimation;
    private int x, y;

    public Teleport(int x, int y){
        this.x = x;
        this.y = y;
        position = new Vector3(0,0,0);
        position.set(x, y, 0);
        teleTexture = new Texture("teleport.png");
        teleAnimation = new Animation(new TextureRegion(teleTexture), 9, 0.4f);
    }

    public void update(float dt){
        teleAnimation.update(dt);
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public TextureRegion getTeleport() {
        return teleAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose(){
        teleTexture.dispose();
    }
}
