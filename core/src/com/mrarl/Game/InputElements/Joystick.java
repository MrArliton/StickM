package com.mrarl.Game.InputElements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mrarl.Constants;
import com.mrarl.Loader;

public class Joystick {
    private Sprite joystick = new Sprite(Loader.getRegion("tochka"));
    private Sprite ring = new Sprite(Loader.getRegion("ring"));
    private  int x;
    private int y;
    private int width;
    private int height;
    private int size;
    boolean back;
    boolean reset = true;
    //
    int sX;
    public Joystick(int x, int y, int size,int cameraWidth,int cameraHeight,boolean right) {
       back = right;
        if(right){
           this.x = sX;
            this.size = size;
           this.width = (int)(size* ((float)cameraWidth/ Constants.width+(float)cameraHeight/Constants.height)/2);
           this.height = (int)(size* ((float)cameraWidth/ Constants.width+(float)cameraHeight/Constants.height)/2);
            this.x = cameraWidth-x-this.width;
            this.y = y;
           ring.setBounds(this.x,this.y,this.width,this.height);
           joystick.setBounds( this.x+width/4,this.y+height/4,width/2,height/2);
       }else{
           this.size = size;
           this.x = x;
           this.y = y;
           this.width = (int)(size* ((float)cameraWidth/ Constants.width+(float)cameraHeight/Constants.height)/2);
           this.height = (int)(size* ((float)cameraWidth/ Constants.width+(float)cameraHeight/Constants.height)/2);
           ring.setBounds(this.x,this.y,this.width,this.height);
           joystick.setBounds(this.x+width/4,this.y+height/4,width/2,height/2);
       }

    }
    public void setReset(boolean bool){
        reset = bool;
    }
    private float[] getXYJoystick(int x,int y){
        float[] buffer = new float[2];
        buffer[0] = x-(ring.getX()+width/2);
        buffer[1] = y-(ring.getY()+width/2);
        return buffer;
    }
    private float[] getReXYJoystick(float x,float y){
        float[] buffer = new float[2];
        buffer[0] = x*width/2+(ring.getX()+width/2);
        buffer[1] = y*width/2+(ring.getY()+width/2);
        return buffer;
    }
    // Function variables
    int pointer;
    boolean taked;
    public void update(int x,int y,int pointer,boolean downed){ // Утсанавливает положение точки
        if(taked){
            if(pointer==this.pointer)
            if(downed){
                if ((Math.pow(getXYJoystick(x,y)[0] / (width / 2),2)+Math.pow(getXYJoystick(x,y)[1] / (width / 2),2)<1)) {
                    joystick.setX(x - width / 4);
                    joystick.setY(y - width / 4);
                }else
                if(x-ring.getX()-width/2 <0) { // Изменение координат джостика за зоной кольца
                    if(y-ring.getY()-width/2>0){
                        //y
                        joystick.setY(-width/2+getReXYJoystick(0,(float)Math.asin(getXYJoystick(x,y)[1]/width/2/Math.pow(Math.pow(getXYJoystick(x,y)[1]/width/2,2)+Math.pow(getXYJoystick(x,y)[0]/width/2,2),1.0/2)))[1]);
                        //x
                        if(Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2))<2.4f) {
                            joystick.setX(width/2+getReXYJoystick(+(float)-Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2)),0)[0]);
                        }else{
                            joystick.setX(width / 2 + getReXYJoystick(-2.4f, 0)[0]);
                        }
                    }else{
                        //y
                        joystick.setY(-width/2+getReXYJoystick(0,(float)Math.asin(getXYJoystick(x,y)[1]/width/2/Math.pow(Math.pow(getXYJoystick(x,y)[1]/width/2,2)+Math.pow(getXYJoystick(x,y)[0]/width/2,2),1.0/2))/4f)[1]);
                        //x
                        if(Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2))<2.4f) {
                            joystick.setX(width/2+getReXYJoystick(+(float)-Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2)),0)[0]);
                        }else{
                            joystick.setX(width / 2 + getReXYJoystick(-2.4f, 0)[0]);
                        }
                    }
                }else {
                    if(y-ring.getY()-width/2>0){
                        //y
                        joystick.setY(-width/2+getReXYJoystick(0,(float)Math.asin(getXYJoystick(x,y)[1]/width/2/Math.pow(Math.pow(getXYJoystick(x,y)[1]/width/2,2)+Math.pow(getXYJoystick(x,y)[0]/width/2,2),1.0/2)))[1]);
                        //x
                        if(Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2))>0.5f) {
                            joystick.setX(width/2+getReXYJoystick(-(float)+Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2)),0)[0]);
                        }else{
                            joystick.setX(width / 2 + getReXYJoystick(-0.5f, 0)[0]);
                        }
                    }else{
                        //y
                        joystick.setY(-width/2+getReXYJoystick(0,(float)Math.asin(getXYJoystick(x,y)[1]/width/2/Math.pow(Math.pow(getXYJoystick(x,y)[1]/width/2,2)+Math.pow(getXYJoystick(x,y)[0]/width/2,2),1.0/2))/4f)[1]);
                        //x
                        if(Math.acos(getXYJoystick(x,y)[0]/width/2/Math.pow((Math.pow(getXYJoystick(x,y)[0]/width/2,2)+Math.pow(getXYJoystick(x,y)[1]/width/2,2)),1.0/2))>0.5f) {
                            joystick.setX(width / 2 + getReXYJoystick(-(float) Math.acos(getXYJoystick(x, y)[0] / width / 2 / Math.pow((Math.pow(getXYJoystick(x, y)[0] / width / 2, 2) + Math.pow(getXYJoystick(x, y)[1] / width / 2, 2)), 1.0 / 2)), 0)[0]);
                        }else{
                            joystick.setX(width / 2 + getReXYJoystick(-0.5f, 0)[0]);
                        }
                    }
                }
            }else{ // Отключаемся от джостика
                taked = false;
                this.pointer = -1;
                if(reset) {
                    joystick.setX(ring.getX() + width / 4);
                    joystick.setY(ring.getY() + width / 4);
                }
                }
        }else{
            if(downed) {
                if ((Math.pow(getXYJoystick(x,y)[0] / (width / 2),2)+Math.pow(getXYJoystick(x,y)[1] / (width / 2),2)<1)) {
                    joystick.setX(x - width / 4);
                    joystick.setY(y - width / 4);
                    taked = true;
                    this.pointer = pointer;
                }
            }
        }
    }
    public void draw(SpriteBatch batch){
        ring.draw(batch);
        joystick.draw(batch);
    }
    //Funtion Variables
    Vector3 otvet = new Vector3();
    public Vector3 getDirection(){
        otvet.x = joystick.getX()-ring.getX()-width/4;
        otvet.y = joystick.getY()-ring.getY()-width/4;
        otvet.z = (float)Math.pow(Math.pow(otvet.x,2)+Math.pow(otvet.y,2),1.0/2);
        return otvet;
    }
    public void resize(int width,int height){
        this.width = (int)(size* ((float)width/ Constants.width+(float)height/Constants.height)/2);
        if(back){
            this.x = (int)(width/2.1f)-sX-this.width;
        }
        this.height = (int)(size* ((float)width/ Constants.width+(float)height/Constants.height)/2);
        joystick.setBounds(this.x+this.width/4,this.y+this.height/4,this.width/2,this.height/2);
        ring.setBounds(this.x,this.y,this.width,this.height);
    }
}
