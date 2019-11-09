package com.mrarl.Game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mrarl.Constants;
import com.mrarl.Game.MyWorld;
import com.mrarl.Loader;

public class Wall extends Object {
    private Sprite wall;
    private boolean texture = true;
    // Координаты относительно середины тела
    private float x;
    private float y;
    // Масштабируется по кругу от середины
    private float width;
    private float height;
    public Wall(float x, float y, float width, float height, float angle, String wall, MyWorld world) {
        super(world,1);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(wall!="") {
            this.wall = new Sprite(Loader.getRegion(Constants.wall + wall));
        }else{
        texture = false;
        }
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(x,y);
        bDef.angle = angle;
        body = world.getWorld().createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        fDef.shape = shape;
        body.createFixture(fDef);
        this.wall.setBounds(x-width/2,y-height/2,width/2,height/2);
    }

    public void draw(SpriteBatch batch){
        if(texture) {
            wall.draw(batch);
        }
    }

}
