package com.mrarl.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mrarl.Constants;
import com.mrarl.Game.HudController;
import com.mrarl.Game.MyWorld;
import com.mrarl.Game.WorldController;
import com.mrarl.Loader;

public class GameScreen implements Screen {
    OrthographicCamera camera;
    ExtendViewport viewport; // Для камеры игры
    OrthographicCamera hud; // Все элементы в расчёте из разрешения
    SpriteBatch batch;
    HudController hudController;
    WorldController worldController;
    @Override
    public void show() {
        batch = new SpriteBatch();
        Loader.load("HUD.atlas");
        // Camera
        hud = new OrthographicCamera(Constants.width,Constants.height);
        hud.setToOrtho(false,hud.viewportWidth/2,hud.viewportHeight/2);
        camera = new OrthographicCamera(Constants.heightW*(Constants.width/Constants.height),Constants.heightW);
        camera.setToOrtho(false,camera.viewportWidth/2,camera.viewportHeight/2);
        viewport = new ExtendViewport(camera.viewportWidth,camera.viewportHeight,camera);
        // HUD
        hudController = new HudController(hud);
        hudController.createJoystick(35,25,100,false);
        hudController.createButton(10,50,40,40,false,true,"M");
        Gdx.input.setInputProcessor(hudController);
        // World
        worldController = new WorldController(camera,new MyWorld(new World(new Vector2(0,-10),true),"S",camera));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // World
        worldController.render(batch);
        // HUD
        hudController.update(delta);
        hudController.render(batch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        hud.viewportWidth = width;
        hud.viewportHeight = height;
        hud.setToOrtho(false,width/2,height/2);
        hudController.resize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        hudController.dispose();
        worldController.dispose();
        Loader.dispose();
    }
}
