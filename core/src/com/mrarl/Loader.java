package com.mrarl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Loader {
    private static TextureAtlas atlas = new TextureAtlas("HUD.atlas");
    public static TextureAtlas.AtlasRegion getRegion(String name){
        return atlas.findRegion(name);
    }
    public static void load(String name){
       atlas = new TextureAtlas(name);
    }
    public static void dispose(){
        atlas.dispose();
    }
}
