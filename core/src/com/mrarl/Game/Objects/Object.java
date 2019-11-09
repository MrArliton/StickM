package com.mrarl.Game.Objects;


import com.badlogic.gdx.physics.box2d.Body;
import com.mrarl.Game.MyWorld;

public class Object {
    MyWorld world;
    public Body body;
    public int index;

    public Object(MyWorld world,int index) {
        this.world = world;
        this.index = index;
    }
}
