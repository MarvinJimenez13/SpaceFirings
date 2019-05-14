package mx.unam.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import mx.unam.Animation.Animation;

public class BackGround5 {

    private Vector3 position;
    private Texture backTexture;
    private int x, y;

    public BackGround5(int x, int y){
        this.x = x;
        this.y = y;
        position = new Vector3(0,0,0);
        position.set(x, y, 0);
        backTexture = new Texture("fondonebu.jpg");
    }

    public Texture getBackTexture() {
        return backTexture;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose(){
        backTexture.dispose();
    }
}
