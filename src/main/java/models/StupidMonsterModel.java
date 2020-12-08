package main.java.models;

import javafx.scene.image.Image;

import java.util.List;

public class StupidMonsterModel extends MonsterModel {

    public StupidMonsterModel(int x, int y, int direction){
        super(x,y);
        this.setSpeed(1);

        this.setDirection(direction);
        this.setImageURL("/main/java/statics/images/monster_down.png");
        this.setImage(new Image(this.imageURL));
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

    //1 : moved, 0: redirect, -1: kill player
    public int move(InitModel initModel){
        switch (this.getDirection()){
            case 1:{
                int y = (int) ((this.getPointY() - this.getSpeed())/50);
                int x1 = (int) (this.getPointX()/50);
                int x2 = (int) ((this.getPointX() +45)/50);
                if(initModel.getMap().get(y).get(x1) == '#' || initModel.getMap().get(y).get(x1) == '*' || initModel.getMap().get(y).get(x1) == 'b'
                    || initModel.getMap().get(y).get(x2) == '#' || initModel.getMap().get(y).get(x2) == '*' || initModel.getMap().get(y).get(x2) == 'b'){
                    this.setDirection();
                    return 0;
                }
                this.setPointY(this.getPointY() - this.getSpeed());
                if((this.getPointY()>= initModel.getPlayer().getPointY())
                        &&(this.getPointY() <= initModel.getPlayer().getPointY() +50)
                        &&(((this.getPointX()+5<= initModel.getPlayer().getPointX()+45)&&(this.getPointX()+5>=initModel.getPlayer().getPointX()))
                        || ((this.getPointX()+40<=initModel.getPlayer().getPointX()+45) && (this.getPointX()+45>=initModel.getPlayer().getPointX())))
                ){
                    initModel.getPlayer().setHeart(initModel.getPlayer().getHeart() -1);
                    initModel.getPlayer().setPointX(3*50);
                    initModel.getPlayer().setPointY(3*50);
                    return -1;
                }
                break;
            }
            case 2:{
                int x = (int) ((this.getPointX() + 45 + this.getSpeed())/50);
                int y1 = (int) (this.getPointY()/50);
                int y2 = (int) ((this.getPointY()+50)/50);
                if(initModel.getMap().get(y1).get(x) == '#' || initModel.getMap().get(y1).get(x) == '*' || initModel.getMap().get(y1).get(x) == 'b'
                  || initModel.getMap().get(y2).get(x) == '#' || initModel.getMap().get(y2).get(x) == '*' || initModel.getMap().get(y2).get(x) == 'b'){
                    this.setDirection();
                    return 0;
                }
                this.setPointX(this.getPointX() + this.getSpeed());
                if(((initModel.getPlayer().getPointX()<= this.getPointX() + 45)
                        && (this.getPointX() + 45 <= initModel.getPlayer().getPointX() + 45))
                        && (((initModel.getPlayer().getPointY() <= this.getPointY()+45) && (this.getPointY()+45 <= initModel.getPlayer().getPointY() + 50))
                        || ((initModel.getPlayer().getPointY() +50 >= this.getPointY() + 5) && (this.getPointY()+5 >= initModel.getPlayer().getPointY())))
                ){
                    initModel.getPlayer().setHeart(initModel.getPlayer().getHeart() -1);
                    initModel.getPlayer().setPointX(3*50);
                    initModel.getPlayer().setPointY(3*50);

                    return -1;
                }
                break;
            }
            case 3:{
                int y = (int) ((this.getPointY() + 50 + this.getSpeed())/50);
                int x1 = (int) (this.getPointX()/50);
                int x2 = (int) ((this.getPointX() + 45)/50);
                if(initModel.getMap().get(y).get(x1) == '#' || initModel.getMap().get(y).get(x1) == '*' || initModel.getMap().get(y).get(x1) == 'b'
                  || initModel.getMap().get(y).get(x2) == '#' || initModel.getMap().get(y).get(x2) == '*' || initModel.getMap().get(y).get(x2) == 'b'){
                    this.setDirection();
                    return 0;
                }
                this.setPointY(this.getPointY() + this.getSpeed());
                if((this.getPointY() + 50 >= initModel.getPlayer().getPointY())
                        &&(this.getPointY() + 50 <= initModel.getPlayer().getPointY() +50)
                        &&(((this.getPointX()+5>= initModel.getPlayer().getPointX())&&(this.getPointX()+5<=initModel.getPlayer().getPointX()+45))
                        || ((this.getPointX()+40>=initModel.getPlayer().getPointX()) && (this.getPointX()+40<=initModel.getPlayer().getPointX()+45)))
                ){
                    initModel.getPlayer().setHeart(initModel.getPlayer().getHeart() -1);
                    initModel.getPlayer().setPointX(3*50);
                    initModel.getPlayer().setPointY(3*50);

                    return -1;
                }
                break;
            }
            case 4:{
                int x = (int) ((this.getPointX() - this.getSpeed())/50);
                int y1 = (int) ((this.getPointY() + 50)/50);
                int y2 = (int)(this.getPointY()/50);
                if(initModel.getMap().get(y1).get(x) == '#' || initModel.getMap().get(y1).get(x) == '*' || initModel.getMap().get(y1).get(x) == 'b'
                    || initModel.getMap().get(y2).get(x) == '#' || initModel.getMap().get(y2).get(x) == '*' || initModel.getMap().get(y2).get(x) == 'b' ){
                    this.setDirection();
                    return 0;
                }
                this.setPointX(this.getPointX() - this.getSpeed());
                if(((initModel.getPlayer().getPointX()<= this.getPointX())
                        && (this.getPointX() <= initModel.getPlayer().getPointX() + 45))
                        && (((initModel.getPlayer().getPointY() <= this.getPointY()+45) && (this.getPointY()+45 <= initModel.getPlayer().getPointY()+50))
                        || ((initModel.getPlayer().getPointY() <= this.getPointY()) && (this.getPointY() <= initModel.getPlayer().getPointY()+50)))){
                    initModel.getPlayer().setHeart(initModel.getPlayer().getHeart() -1);
                    initModel.getPlayer().setPointX(3*50);
                    initModel.getPlayer().setPointY(3*50);

                    return -1;
                }
                break;
            }
        }
        return 1;
    }

}
