package com.mrarl.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mrarl.Constants;
import com.mrarl.Loader;

public class MyWorld {
    private World world;
    private Sprite backwall;
    Box2DDebugRenderer rdebug;
    OrthographicCamera camera;
    //
    float timeStep = 1/60f;
    int velocityIterations = 4;
    int postionIteration = 4;
    public MyWorld(World world,String nameFone,OrthographicCamera camera) {
        this.camera = camera;
        this.world = world;
        backwall = new Sprite(Loader.getRegion(Constants.fone+nameFone));
        if(Constants.debug){
            rdebug = new Box2DDebugRenderer();
        }
    }
    public World getWorld(){
        return world;
    }
    public void setTimeStep(float timeStep){
    this.timeStep = timeStep;
    }
    public void setVelocityIterations(int velocityIterations){
    this.velocityIterations = velocityIterations;
    }
    public void setPostionIterations(int postionIteration){
    this.postionIteration = postionIteration;
    }
    public void render(SpriteBatch batch){
        backwall.draw(batch);
        if(Constants.debug){
            rdebug.render(world,camera.combined);
        }
        world.step(timeStep,velocityIterations,postionIteration);
    }
    public void dipose(){
        world.dispose();
    }
}
