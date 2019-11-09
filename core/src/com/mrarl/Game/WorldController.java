package com.mrarl.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrarl.Game.Objects.Player.Player;
import com.mrarl.Game.Objects.Wall;

import java.util.ArrayList;

public class WorldController {
    OrthographicCamera camera; // Камера следящая за миром
    MyWorld world; // Мир
    Player player; // Управление персонажем
    ArrayList<Wall> walls = new ArrayList<>();

    public WorldController(OrthographicCamera camera, MyWorld world) {
        this.camera = camera;
        this.world = world;
    }

    public void render(SpriteBatch batch){
        world.render(batch);
        for(int i = 0;i<walls.size();i++){
            walls.get(i).draw(batch);
        }
    }
    public void dispose(){
        world.dipose();
    }
}
