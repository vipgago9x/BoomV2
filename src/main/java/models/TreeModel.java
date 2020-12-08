package main.java.models;

import javafx.scene.image.Image;

public class TreeModel extends ObjectModel {

    private boolean isBurst = false;

    public TreeModel(int x, int y){
        this.setX(x);
        this.setY(y);
        this.setImageURL("/main/java/statics/images/tree.png");
        this.setImage(new Image(this.getImageURL()));
    }

    public boolean isBurst() {
        return isBurst;
    }

    public void setBurst(boolean burst) {
        isBurst = burst;
        if(isBurst){
        }
    }

}
