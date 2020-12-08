package main.java.models;

import javafx.scene.image.Image;

import java.util.List;

public class PlayerModel extends ObjectModel {

    private int direction;
    private int boomQuantity;
    private int speed;
    private int heart;
    private int boomSize;
    private double pointX;
    private double pointY;
    private int boomSetNumber;

    public PlayerModel(int x, int y, int direction){
        this.setX(x);
        this.setY(y);
        this.setPointX(x*50);
        this.setPointY(y*50);
        this.setHeart(3);
        this.setSpeed(5);
        this.setDirection(direction);
        this.setBoomQuantity(2);
        this.setDirection(direction);
        this.setImage(new Image(this.getImageURL()));
        this.setBoomSize(1);
        this.setBoomSetNumber(0);
    }

    public int getBoomSetNumber() {
        return boomSetNumber;
    }

    public void setBoomSetNumber(int boomSetNumber) {
        this.boomSetNumber = boomSetNumber;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        switch (direction){
            case 1: {
                this.setImageURL("/main/java/statics/images/player_up.png");
                break;
            }
            case 2: {
                this.setImageURL("/main/java/statics/images/player_right.png");
                break;
            }
            case 3: {
                this.setImageURL("/main/java/statics/images/player_down.png");
                break;
            }
            default: {
                this.setImageURL("/main/java/statics/images/player_left.png");
                break;
            }
        }
        this.setImage(new Image(this.getImageURL()));
    }


    public int getBoomQuantity() {
        return boomQuantity;
    }

    public void setBoomQuantity(int boomQuantity) {
        this.boomQuantity = boomQuantity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getHeart() {
        return heart;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBoomSize() {
        return boomSize;
    }

    public void setBoomSize(int boomSize) {
        this.boomSize = boomSize;
    }

    public int move(int direction, InitModel initModel){
        //0: do not move; 1: move; -1: die; 2: item
        switch (direction){
            case 1:{
                this.setDirection(1);
                for(int i=0;i<this.getSpeed();i++) {
                    int y = (int) ((this.getPointY() - 1) / 50);
                    int x1 = (int) ((this.getPointX()+3) / 50);
                    int x2 = (int) ((this.getPointX() + 42) / 50);
                    if (initModel.getMap().get(y).get(x1) == '#' || initModel.getMap().get(y).get(x1) == '*' || initModel.getMap().get(y).get(x1) == 'b'
                            || initModel.getMap().get(y).get(x2) == '#' || initModel.getMap().get(y).get(x2) == '*' || initModel.getMap().get(y).get(x2) == 'b') {
                        return 0;
                    }
                    if ( initModel.getMap().get(y).get(x1) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y).set(x1,' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y).get(x2) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y).set(x2, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x1) == 'H' ){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y).set(x1, ' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y).get(x2) == 'H'){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y).set(x2, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x1) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+1);
                        initModel.getMap().get(y).set(x1, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x2) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+1);
                        initModel.getMap().get(y).set(x2, ' ');

                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x1) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);

                        initModel.getMap().get(y).set(x1, ' ');

                        return 2;
                    }
                    if ( initModel.getMap().get(y).get(x2) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y).set(x2, ' ');
                        return 2;
                    }
                    this.setPointY(this.getPointY() - 1);
                }

                break;
            }
            case 2:{
                this.setDirection(2);
                for(int i=0;i<this.getSpeed();i++) {
                    int x = (int) ((this.getPointX() + 45 + 1)/50);
                    int y1 = (int) ((this.getPointY()+3)/50);
                    int y2 = (int) ((this.getPointY()+47)/50);
                    if(initModel.getMap().get(y1).get(x) == '#' || initModel.getMap().get(y1).get(x) == '*' || initModel.getMap().get(y1).get(x) == 'b'
                            || initModel.getMap().get(y2).get(x) == '#' || initModel.getMap().get(y2).get(x) == '*' || initModel.getMap().get(y2).get(x) == 'b'){
                        return 0;
                    }if ( initModel.getMap().get(y1).get(x) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y1).set(x,' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y2).get(x) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y2).get(x) == 'H' ){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y1).get(x) == 'H'){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y1).set(x, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y2).get(x) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y1).get(x) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+1);
                        initModel.getMap().get(y1).set(x, ' ');

                        return 2;
                    }
                    if (initModel.getMap().get(y1).get(x) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y1).set(x, ' ');

                        return 2;
                    }
                    if ( initModel.getMap().get(y2).get(x) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    this.setPointX(this.getPointX() + 1);
                }
                break;
            }
            case 3:{
                this.setDirection(3);
                for(int i=0;i<this.getSpeed();i++) {
                    int y = (int) ((this.getPointY() + 50 + 1)/50);
                    int x1 = (int) ((this.getPointX()+3)/50);
                    int x2 = (int) ((this.getPointX() + 42)/50);
                    if(initModel.getMap().get(y).get(x1) == '#' || initModel.getMap().get(y).get(x1) == '*' || initModel.getMap().get(y).get(x1) == 'b'
                            || initModel.getMap().get(y).get(x2) == '#' || initModel.getMap().get(y).get(x2) == '*' || initModel.getMap().get(y).get(x2) == 'b'){
                        return 0;
                    }
                    if ( initModel.getMap().get(y).get(x1) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y).set(x1,' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y).get(x2) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y).set(x2, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x1) == 'H' ){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y).set(x1, ' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y).get(x2) == 'H'){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y).set(x2, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x1) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+1);
                        initModel.getMap().get(y).set(x1, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x2) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+1);
                        initModel.getMap().get(y).set(x2, ' ');

                        return 2;
                    }
                    if (initModel.getMap().get(y).get(x1) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y).set(x1, ' ');

                        return 2;
                    }
                    if ( initModel.getMap().get(y).get(x2) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y).set(x2, ' ');
                        return 2;
                    }
                    this.setPointY(this.getPointY() + 1);
                }
                break;
            }
            case 4:{
                this.setDirection(4);
                for(int i=0;i<this.getSpeed();i++) {
                    int x = (int) ((this.getPointX() - 1)/50);
                    int y1 = (int) ((this.getPointY() + 47)/50);
                    int y2 = (int)((this.getPointY()+3)/50);
                    if(initModel.getMap().get(y1).get(x) == '#' || initModel.getMap().get(y1).get(x) == '*' || initModel.getMap().get(y1).get(x) == 'b'
                            || initModel.getMap().get(y2).get(x) == '#' || initModel.getMap().get(y2).get(x) == '*' || initModel.getMap().get(y2).get(x) == 'b' ){
                        return 0;
                    }
                    if ( initModel.getMap().get(y1).get(x) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y1).set(x,' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y2).get(x) == 'S'){
                        this.setSpeed(this.getSpeed()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y2).get(x) == 'H' ){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    if ( initModel.getMap().get(y1).get(x) == 'H'){
                        this.setHeart(this.getHeart()+1);
                        initModel.getMap().get(y1).set(x, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y2).get(x) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+ 1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    if (initModel.getMap().get(y1).get(x) == 'B'){
                        this.setBoomQuantity(this.getBoomQuantity()+ 1);

                        initModel.getMap().get(y1).set(x, ' ');

                        return 2;
                    }
                    if (initModel.getMap().get(y1).get(x) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y1).set(x, ' ');

                        return 2;
                    }
                    if ( initModel.getMap().get(y2).get(x) == 'Z'){
                        this.setBoomSize(this.getBoomSize()+1);
                        initModel.getMap().get(y2).set(x, ' ');
                        return 2;
                    }
                    this.setPointX(this.getPointX() - 1);
                }
                break;
            }
        }
        return 1;
    }
}
