package main.java.models;

import javafx.scene.image.Image;

public class StoneModel extends ObjectModel{

    public StoneModel(int x, int y){
        this.setX(x);
        this.setY(y);
        this.setImageURL("/main/java/statics/images/stone.png");
        this.setImage(new Image(this.getImageURL()));
    }
}
