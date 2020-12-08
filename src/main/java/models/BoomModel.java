package main.java.models;

import javafx.scene.image.Image;

public class BoomModel extends ObjectModel {
    private int timer;
    public BoomModel(int x, int y){
        this.setX(x);
        this.setY(y);
        this.setImage(new Image("/main/java/statics/images/bomb.png"));
        this.setTimer(2000);
    }


    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setUp(InitModel initModel ){
        this.setX((int)((initModel.getPlayer().getPointX()+23)/50));
        this.setY((int) ((initModel.getPlayer().getPointY()+25)/50));
    }
}
