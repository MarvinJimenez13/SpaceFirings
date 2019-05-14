package mx.unam.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class AnimationBadBoom {

    private ArrayList<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    public int frame;

    public AnimationBadBoom(TextureRegion region, int frameCount, float cycleTime){

        frames = new ArrayList<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public  void update(float dt){
        currentFrameTime += dt;

        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime =0;
        }
        if(frame >= frameCount)
            frame =9;
    }

    public int getFrameInt(){
        return frame;
    }

    public  TextureRegion getFrame(){
        return  frames.get(frame);
    }
}
