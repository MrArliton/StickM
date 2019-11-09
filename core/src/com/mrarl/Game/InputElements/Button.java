package com.mrarl.Game.InputElements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrarl.Constants;
import com.mrarl.Loader;

public class Button {
    private  Sprite button;
    private Sprite buttonD;
    private  int x;
    private int y;
    private int width;
    private int height;
    private float timeDowned = 0.2f; // Время нажатия
    private boolean isClicked = false;
    boolean yHigh;
    //
   private boolean downed = false;
    public Button(int x, int y, int width, int height,String name,boolean wallHigh) { // Кнопка
        yHigh = wallHigh;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        button = new Sprite(Loader.getRegion(Constants.button+name));
        buttonD = new Sprite(Loader.getRegion(Constants.button+name+"D"));
        button.setBounds(x,y,width,height);
        buttonD.setBounds(x,y,width,height);
    }
    public void setTimeDowned(float downed){ //
        timeDowned = downed;
    }
    public void draw(SpriteBatch batch){
        if(!downed) {
            button.draw(batch);
        }else
        buttonD.draw(batch);
    }
    //Funtion Variables
    int pointer = -1;
    boolean taked = false;
    float buffer;
    public void updateTime(float delta){
        if(buffer<timeDowned){
            buffer+=delta;
        }else{
            taked = false;
            downed = false;
            pointer = -1;
        }
    }
    // =
    public void update(int x,int y,boolean downed,int pointer){
        if(taked){
        if(this.pointer==pointer){
            if(!downed){
                isClicked = true;
            }
        }
        }else{
            if(downed) {
            if(x>button.getX()&&x<button.getX()+width&&y>button.getY()&&y<height+button.getY()){
                buffer = 0;
                  taked = true;
                  this.downed = true;
                  this.pointer = pointer;
              }
              }
        }
    }
    public int isClick(){ // Было ли совершенно нажатие
        if(isClicked){
            return 1;
        }else{
            if(downed){
                return 2;
            }else{
                return 0;
            }
        }
    }
    public void resize(float width,float height){
        if(yHigh){
            button.setBounds(x, height/2-y, this.width * ((int) (width / Constants.width + (float) height / Constants.height) / 2), this.height * ((int) (width / Constants.width + (float) height / Constants.height) / 2));
            buttonD.setBounds(x, height/2-y, this.width * ((int) (width / Constants.width + (float) height / Constants.height) / 2), this.height * ((int) (width / Constants.width + (float) height / Constants.height) / 2));
        }else {
            button.setBounds(x, y, this.width * ((int) (width / Constants.width + (float) height / Constants.height) / 2), this.height * ((int) (width / Constants.width + (float) height / Constants.height) / 2));
            buttonD.setBounds(x, y, this.width * ((int) (width / Constants.width + (float) height / Constants.height) / 2), this.height * ((int) (width / Constants.width + (float) height / Constants.height) / 2));
        }
        }
}
