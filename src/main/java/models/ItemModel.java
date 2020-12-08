package main.java.models;

import javafx.scene.image.Image;

import java.util.Random;

public class ItemModel extends ObjectModel {
    private int type;

    public ItemModel(int x, int y){
        this.x = x;
        this.y = y;
        this.setType(new Random().nextInt(16)+1);
        switch (this.getType()){
            case 1: {
                this.setImageURL("/main/java/statics/images/item_shoe.png");
                this.setImage(new Image(this.getImageURL()));
                break;
            }
            case 2: {
                this.setImageURL("/main/java/statics/images/item_bomb.png");
                this.setImage(new Image(this.getImageURL()));
                break;
            }
            case 3:{
                this.setImageURL("/main/java/statics/images/item_bombsize.png");
                this.setImage(new Image(this.getImageURL()));
                break;
            }
            case 4:{
                this.setImageURL("/main/java/statics/images/heart_2.png");
                this.setImage(new Image(this.getImageURL()));
            }
            default:{
                break;
            }

        }
    }
    public void setType(int type) {

        this.type = type;
        switch (this.getType()){
            case 1: {
                this.setImageURL("/main/java/statics/images/item_shoe.png");
                this.setImage(new Image(this.getImageURL()));
                break;
            }
            case 2: {
                this.setImageURL("/main/java/statics/images/item_bomb.png");
                this.setImage(new Image(this.getImageURL()));
                break;
            }
            case 3:{
                this.setImageURL("/main/java/statics/images/item_bombsize.png");
                this.setImage(new Image(this.getImageURL()));
                break;
            }
            case 4:{
                this.setImageURL("/main/java/statics/images/heart_2.png");
                this.setImage(new Image(this.getImageURL()));
            }
            default:{
                break;
            }

        }
    }

    public int getType() {
        return type;
    }

}
