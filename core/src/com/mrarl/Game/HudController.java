package com.mrarl.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mrarl.Constants;
import com.mrarl.Game.InputElements.Button;
import com.mrarl.Game.InputElements.Joystick;

import java.util.ArrayList;

public class HudController implements InputProcessor {
    OrthographicCamera camera;
    ArrayList<Joystick> joysticks = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();
    //Buffer
    Vector3 projecter;
    public HudController(OrthographicCamera camera){
        this.camera = camera;
        projecter = new Vector3();
        projecter.z = 0;
    }
    public void createJoystick(int x,int y,int size,boolean wallRight){ // X и Y относительно стены
        joysticks.add(new Joystick(x,y,size,(int)camera.viewportWidth,(int)camera.viewportHeight,wallRight));
    }
    public void createButton(int x,int y,int w,int h,boolean wallRight,boolean wallHigh,String name){ // X и Y относительно стены
        if(!wallRight) {
            buttons.add(new Button(x, y, w, h, name,wallHigh));
        }else{
            buttons.add(new Button((int)camera.viewportWidth-x-w-w*(int)(camera.viewportWidth/ Constants.width+(float)camera.viewportHeight/Constants.height)/2, y, w, h, name,wallHigh));
        }
        }
    public Vector3 getJoystickInfo(int index){
        return joysticks.get(index).getDirection();
    }
    public int getButtonInfo(int index){

        return buttons.get(index).isClick();
    }
    public void update(float delta){
        for(int i =0;i<buttons.size();i++){
            buttons.get(i).updateTime(delta);
        }
    }
    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(int i = 0;i<joysticks.size();i++){
            joysticks.get(i).draw(batch);
        }
        for(int i =0;i<buttons.size();i++){
            buttons.get(i).draw(batch);
        }
        batch.end();
    }
    public void resize(int width,int height){
        for(int i = 0;i<joysticks.size();i++){
            joysticks.get(i).resize(width,height);
        }
        for(int i = 0;i<buttons.size();i++){
            buttons.get(i).resize(width,height);
        }
    }
    public void setTimeDownedButton(int index,float value){
        buttons.get(index).setTimeDowned(value);
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        projecter.x = screenX;
        projecter.y = screenY;
        camera.unproject(projecter);
        for(int i =0;i<buttons.size();i++){
            buttons.get(i).update((int)projecter.x,(int)projecter.y,true,pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        projecter.x = screenX;
        projecter.y = screenY;
        camera.unproject(projecter);
        for(int i = 0;i<joysticks.size();i++){
            joysticks.get(i).update((int)projecter.x,(int)projecter.y,pointer,false);
        }
        for(int i =0;i<buttons.size();i++){
            buttons.get(i).update((int)projecter.x,(int)projecter.y,false,pointer);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        projecter.x = screenX;
        projecter.y = screenY;
        camera.unproject(projecter);
        for(int i = 0;i<joysticks.size();i++){
            joysticks.get(i).update((int)projecter.x,(int)projecter.y,pointer,true);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public void dispose(){
        joysticks.clear();
        buttons.clear();
    }
}
