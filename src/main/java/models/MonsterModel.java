package main.java.models;

import javafx.scene.image.Image;

import java.util.List;


public class MonsterModel extends ObjectModel{
    protected int direction;
    private int type;
    protected double pointX;
    protected double pointY;
    protected double speed;
    public MonsterModel(int x, int y){
        this.setX(x);
        this.setY(y);
        this.setPointX(x*50);
        this.setPointY(y*50);
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        switch (direction){
            case 1:{
                this.setImageURL("/main/java/statics/images/monster_up.png");
                break;
            }
            case 2:{
                this.setImageURL("/main/java/statics/images/monster_right.png");
                break;
            }
            case 3:{
                this.setImageURL("/main/java/statics/images/monster_down.png");
                break;
            }
            case 4:{
                this.setImageURL("/main/java/statics/images/monster_left.png");
                break;
            }
        }
        this.setImage(new Image(this.getImageURL()));
    }
    public void setDirection() {
        switch (this.direction){
            case 1: this.setDirection(2); break;
            case 2: this.setDirection(3); break;
            case 3: this.setDirection(4); break;
            case 4: this.setDirection(1); break;
        }
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int move(InitModel initModel){
        return 1;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
