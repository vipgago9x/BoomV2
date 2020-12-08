package main.java.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.models.*;
import main.java.statics.sounds.Sound;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class GameController implements Initializable {
    @FXML
    private JFXButton buttonMain;

    @FXML
    private Label heart;

    @FXML
    private AnchorPane gameStage;


    private InitModel initModel;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private int direction = 1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Sound.getInstance().getAudio(Sound.MENU).stop();
        Sound.getInstance().getAudio(Sound.PLAYGAME).loop();
        this.initModel = new InitModel();
        this.heart.setText(String.valueOf(initModel.getPlayer().getHeart()));
        System.out.println(gameStage.getChildren().size());
        //player
        ImageView imageViewPlayer = new ImageView(initModel.getPlayer().getImage());
        imageViewPlayer.setX(initModel.getPlayer().getX());
        imageViewPlayer.setY(initModel.getPlayer().getY());
        gameStage.getChildren().add(imageViewPlayer);

        //monster
        for(int i=0;i<initModel.getArrMonster().size();i++){
            ImageView imageViewMonster = new ImageView(initModel.getArrMonster().get(i).getImage());
            imageViewMonster.setY(initModel.getArrMonster().get(i).getY());
            imageViewMonster.setX(initModel.getArrMonster().get(i).getX());
            gameStage.getChildren().add(imageViewMonster);
        }

        //stone
        for(int i=0;i<initModel.getArrStone().size();i++){
            ImageView imageViewStone = new ImageView(initModel.getArrStone().get(i).getImage());
            imageViewStone.setX(initModel.getArrStone().get(i).getX() * 50);
            imageViewStone.setY(initModel.getArrStone().get(i).getY() * 50);
            gameStage.getChildren().add(imageViewStone);
        }

        //tree
        for(int i=0;i<initModel.getArrTree().size();i++){
            ImageView imageViewTree = new ImageView(initModel.getArrTree().get(i).getImage());
            imageViewTree.setX(initModel.getArrTree().get(i).getX() * 50);
            imageViewTree.setY(initModel.getArrTree().get(i).getY() * 50);
            gameStage.getChildren().add(imageViewTree);
        }
        gameLoop();


    }

    @FXML
    public void handleKeyPressed(KeyEvent event){
        switch (event.getCode()){
            case W: {
                isUp = true;
                direction = 1;
                break;
            }
            case D:{
                isRight = true;
                direction = 2;
                break;
            }
            case S:{
                isDown = true;
                direction = 3;
                break;
            }
            case A:{
                isLeft = true;
                direction = 4;
                break;
            }
            case F:{
                if(initModel.getPlayer().getBoomQuantity() > 0){
                    boomSettingUp();
                }
            }
        }
    }
    @FXML
    public void handleKeyReleased(KeyEvent event){

        switch (event.getCode()){
            case W: {
                isUp = false;
                break;
            }
            case D:{
                isRight = false;
                break;
            }
            case S:{
                isDown = false;
                break;
            }
            case A:{
                isLeft = false;
                break;
            }
        }

    }

    public void gameLoop(){
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if(now - lastUpdate > 20_000_000) {
                    if(initModel.getPlayer().getHeart() <= 0){
                        Sound.getInstance().getAudio(Sound.PLAYGAME).stop();

                        Sound.getInstance().getAudio(Sound.LOSE).play();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("");
                        alert.setTitle("DEAD!!!");
                        alert.setContentText("Game Over!!!");
                        this.stop();
                        alert.setOnHidden(e->{
                            Stage stage = null;
                            stage = (Stage) gameStage.getScene().getWindow();
                            Parent root = null;
                            try {
                                root = new FXMLLoader().load(getClass().getResource("/main/java/fxmls/Main.fxml"));
                                stage.hide();
                                Scene scene = new Scene(root);

                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        });
                        alert.show();
                    }
                    if(initModel.getArrMonster().size() <= 0){
                        Sound.getInstance().getAudio(Sound.PLAYGAME).stop();
                        Sound.getInstance().getAudio(Sound.WIN).play();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("");
                        alert.setTitle("VICTORY!!!");
                        alert.setContentText("Congratulation!!!");
                        this.stop();
                        alert.setOnHidden(e-> {
                            Stage stage = null;
                            stage = (Stage) gameStage.getScene().getWindow();
                            Parent root = null;
                            try {
                                root = new FXMLLoader().load(getClass().getResource("/main/java/fxmls/Main.fxml"));
                                stage.hide();
                                Scene scene = new Scene(root);

                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        });
                        alert.show();
                    }
                    playerMoved();
                    monsterMove();

                    for(int i=0; i < initModel.getArrMonster().size();i++){
                        ImageView imageViewMonster = new ImageView(initModel.getArrMonster().get(i).getImage());
                        imageViewMonster.setX(initModel.getArrMonster().get(i).getPointX());
                        imageViewMonster.setY(initModel.getArrMonster().get(i).getPointY());
                        gameStage.getChildren().remove(5+i);
                        gameStage.getChildren().add(5 + i,imageViewMonster);
                    }

                    ImageView imageViewPlayer = new ImageView(initModel.getPlayer().getImage());
                    imageViewPlayer.setX(initModel.getPlayer().getPointX());
                    imageViewPlayer.setY(initModel.getPlayer().getPointY());
                    gameStage.getChildren().set(4,imageViewPlayer);

                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }
    public void playerMoved(){
        // them buoc chan vao day
//        Sound.getInstance().getAudio(Sound.BUOCCHAN).play();
        int returningValue = 0;
        if(isUp && !isLeft && !isDown && !isRight){
            returningValue = initModel.getPlayer().move(1,initModel);
        }else if(!isUp && isLeft && !isDown && !isRight){
            returningValue = initModel.getPlayer().move(4,initModel);
        }else if(!isUp && !isLeft && isDown && !isRight){
            returningValue = initModel.getPlayer().move(3,initModel);
        }else if(!isUp && !isLeft && !isDown && isRight){
            returningValue = initModel.getPlayer().move(2,initModel);
        }
        if(returningValue == 2){
            this.heart.setText(String.valueOf(initModel.getPlayer().getHeart()));
            Sound.getInstance().getAudio(Sound.ITEM).play();
            gameStage.getChildren().remove(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size(),5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size() + initModel.getArrItem().size());
            initModel.getArrItem().clear();
            for(int i=0;i<initModel.getMap().size();i++){
                for(int j=0;j<initModel.getMap().get(i).size();j++){
                    if(initModel.getMap().get(i).get(j)=='S'){
                        ItemModel itemModel = new ItemModel(j,i);
                        itemModel.setType(1);
                        ImageView imageViewItem = new ImageView(itemModel.getImage());
                        imageViewItem.setY(itemModel.getY()*50);
                        imageViewItem.setX(itemModel.getX()*50);
                        initModel.getArrItem().add(itemModel);
                        gameStage.getChildren().add(5+initModel.getArrStone().size() + initModel.getArrMonster().size() + initModel.getArrTree().size(),imageViewItem);
                    }
                    if(initModel.getMap().get(i).get(j)=='B'){
                        ItemModel itemModel = new ItemModel(j,i);
                        itemModel.setType(2);
                        ImageView imageViewItem = new ImageView(itemModel.getImage());
                        imageViewItem.setY(itemModel.getY()*50);
                        imageViewItem.setX(itemModel.getX()*50);
                        initModel.getArrItem().add(itemModel);

                        gameStage.getChildren().add(5+initModel.getArrStone().size() + initModel.getArrMonster().size() + initModel.getArrTree().size(),imageViewItem);
                    }
                    if(initModel.getMap().get(i).get(j)=='Z'){
                        ItemModel itemModel = new ItemModel(j,i);
                        itemModel.setType(3);
                        ImageView imageViewItem = new ImageView(itemModel.getImage());
                        imageViewItem.setY(itemModel.getY()*50);
                        imageViewItem.setX(itemModel.getX()*50);
                        initModel.getArrItem().add(itemModel);

                        gameStage.getChildren().add(5+initModel.getArrStone().size() + initModel.getArrMonster().size() + initModel.getArrTree().size(),imageViewItem);
                    }
                    if(initModel.getMap().get(i).get(j)=='H'){
                        ItemModel itemModel = new ItemModel(j,i);
                        itemModel.setType(4);
                        ImageView imageViewItem = new ImageView(itemModel.getImage());
                        imageViewItem.setY(itemModel.getY()*50);
                        imageViewItem.setX(itemModel.getX()*50);
                        initModel.getArrItem().add(itemModel);

                        gameStage.getChildren().add(5+initModel.getArrStone().size() + initModel.getArrMonster().size() + initModel.getArrTree().size(),imageViewItem);
                    }
                }
            }
        }
    }
    public void monsterMove(){
        for(int i=0;i<initModel.getArrMonster().size();i++){
            int returning = initModel.getArrMonster().get(i).move(initModel);
            if(returning == -1){
                Sound.getInstance().getAudio(Sound.PLAYER_DIE).play();
                this.heart.setText(String.valueOf(initModel.getPlayer().getHeart()));
            }
        }
    }

    public void boomSettingUp(){
        BoomModel boomModel = new BoomModel((int)((initModel.getPlayer().getPointX()+23)/50),(int) ((initModel.getPlayer().getPointY()+25)/50));
        ImageView imageViewBoom = new ImageView(boomModel.getImage());
        imageViewBoom.setY(boomModel.getY()*50);
        imageViewBoom.setX(boomModel.getX()*50);
        gameStage.getChildren().add(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size()+ initModel.getArrItem().size() + initModel.getPlayer().getBoomSetNumber(),imageViewBoom);
        initModel.getPlayer().setBoomQuantity(initModel.getPlayer().getBoomQuantity()-1);
        initModel.getPlayer().setBoomSetNumber(initModel.getPlayer().getBoomSetNumber()+1);
        initModel.getMap().get(boomModel.getY()).set(boomModel.getX(),'b');
        Timer timer = new Timer();

        //boom nổ
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    boomBursting(boomModel,timer);
                });
            }
        },2000);
    }

    public void boomBursting(BoomModel boomModel, Timer timer){
        for(int i=1;i<=initModel.getPlayer().getBoomSize();i++){
            if(boomModel.getY()-i>=0&& boomModel.getY()<=17) {
                ImageView imageViewUp = new ImageView(new Image("/main/java/statics/images/bombbang_up_1.png"));
                imageViewUp.setX(boomModel.getX() * 50);
                imageViewUp.setY((boomModel.getY() - i) * 50);
                gameStage.getChildren().add(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size() + initModel.getArrItem().size(), imageViewUp);
            }
            if(boomModel.getX() + i >=0 && boomModel.getX()+i <=30) {
                ImageView imageViewRight = new ImageView(new Image("/main/java/statics/images/bombbang_right_1.png"));
                imageViewRight.setX((boomModel.getX() + i) * 50);
                imageViewRight.setY((boomModel.getY()) * 50);
                gameStage.getChildren().add(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size() + initModel.getArrItem().size(), imageViewRight);
            }
            if(boomModel.getY() +i >=0 && boomModel.getY()+i <= 17) {
                ImageView imageViewDown = new ImageView(new Image("/main/java/statics/images/bombbang_down_1.png"));
                imageViewDown.setX((boomModel.getX()) * 50);
                imageViewDown.setY((boomModel.getY() + i) * 50);
                gameStage.getChildren().add(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size() + initModel.getArrItem().size(), imageViewDown);
            }
            if(boomModel.getX()-i >= 0 && boomModel.getX()-i<=30) {
                ImageView imageViewLeft = new ImageView(new Image("/main/java/statics/images/bombbang_left_1.png"));
                imageViewLeft.setX((boomModel.getX() - i) * 50);
                imageViewLeft.setY((boomModel.getY()) * 50);
                gameStage.getChildren().add(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size() + initModel.getArrItem().size(), imageViewLeft);
            }
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    initModel.getMap().get(boomModel.getY()).set(boomModel.getX(),' ');

                    gameStage.getChildren().remove(5 + initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size()+ initModel.getArrItem().size(),gameStage.getChildren().size()-initModel.getPlayer().getBoomSetNumber()+1);
                    initModel.getPlayer().setBoomQuantity(initModel.getPlayer().getBoomQuantity()+1);
                    initModel.getPlayer().setBoomSetNumber(initModel.getPlayer().getBoomSetNumber()-1);
                    int check = 0;
                    for(int i=1;i<=initModel.getPlayer().getBoomSize();i++){
                        if(boomModel.getY()-i>=0 && initModel.getMap().get(boomModel.getY()-i).get(boomModel.getX()) == '*'){
                            ItemModel itemModel = new ItemModel(boomModel.getX(),boomModel.getY()-i);
                            if(itemModel.getType()<5){
                                initModel.getArrItem().add(itemModel);
                                ImageView imageViewItem = new ImageView(itemModel.getImage());
                                imageViewItem.setX(itemModel.getX()*50);
                                imageViewItem.setY(itemModel.getY()*50);
                                gameStage.getChildren().add(5+initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size()+initModel.getArrItem().size()-1,imageViewItem);
                                switch (itemModel.getType()) {
                                    case 1:{
                                        initModel.getMap().get(boomModel.getY() - i).set(boomModel.getX(), 'S');
                                        break;
                                    }
                                    case 2:{
                                        initModel.getMap().get(boomModel.getY() - i).set(boomModel.getX(), 'B');
                                        break;
                                    }
                                    case 3:{
                                        initModel.getMap().get(boomModel.getY() - i).set(boomModel.getX(), 'Z');
                                        break;
                                    }
                                    case 4:{
                                        initModel.getMap().get(boomModel.getY() - i).set(boomModel.getX(), 'H');
                                        break;
                                    }
                                }
                            }
                            else {
                                initModel.getMap().get(boomModel.getY() - i).set(boomModel.getX(), ' ');
                            }
                            check = 1;
                        }
                        if(boomModel.getY()+i<17 && initModel.getMap().get(boomModel.getY() + i).get(boomModel.getX()) == '*'){
                            ItemModel itemModel = new ItemModel(boomModel.getX(),boomModel.getY()+i);
                            if(itemModel.getType()<5){
                                initModel.getArrItem().add(itemModel);
                                ImageView imageViewItem = new ImageView(itemModel.getImage());
                                imageViewItem.setX(itemModel.getX()*50);
                                imageViewItem.setY(itemModel.getY()*50);
                                gameStage.getChildren().add(5+initModel.getArrMonster().size()+ initModel.getArrStone().size() + initModel.getArrTree().size()+initModel.getArrItem().size()-1,imageViewItem);
                                switch (itemModel.getType()) {
                                    case 1:{
                                        initModel.getMap().get(boomModel.getY() + i).set(boomModel.getX(), 'S');
                                        break;
                                    }
                                    case 2:{
                                        initModel.getMap().get(boomModel.getY() + i).set(boomModel.getX(), 'B');
                                        break;
                                    }
                                    case 3:{
                                        initModel.getMap().get(boomModel.getY() + i).set(boomModel.getX(), 'Z');
                                        break;
                                    }
                                    case 4:{
                                        initModel.getMap().get(boomModel.getY() + i).set(boomModel.getX(), 'H');
                                        break;
                                    }
                                }
                            }
                            else {
                                initModel.getMap().get(boomModel.getY() + i).set(boomModel.getX(), ' ');
                            }
                            check =1;
                        }
                        if(boomModel.getX()+i<30 && initModel.getMap().get(boomModel.getY()).get(boomModel.getX()+ i) == '*'){
                            ItemModel itemModel = new ItemModel(boomModel.getX() + i,boomModel.getY());

                            if(itemModel.getType()<5){
                                initModel.getArrItem().add(itemModel);
                                ImageView imageViewItem = new ImageView(itemModel.getImage());
                                imageViewItem.setX(itemModel.getX()*50);
                                imageViewItem.setY(itemModel.getY()*50);
                                gameStage.getChildren().add(5+initModel.getArrMonster().size()+ initModel.getArrStone().size() + initModel.getArrTree().size()+initModel.getArrItem().size()-1,imageViewItem);
                                switch (itemModel.getType()) {
                                    case 1:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX() +i, 'S');
                                        break;
                                    }
                                    case 2:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX() + i, 'B');
                                        break;
                                    }
                                    case 3:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX() + i, 'Z');
                                        break;
                                    }
                                    case 4:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX() + i, 'H');
                                        break;
                                    }
                                }
                            }
                            else {
                                initModel.getMap().get(boomModel.getY()).set(boomModel.getX()+i, ' ');
                            }
                            check = 1;
                        }
                        if(boomModel.getX()-i>=0 && initModel.getMap().get(boomModel.getY()).get(boomModel.getX() - i) == '*'){
                            ItemModel itemModel = new ItemModel(boomModel.getX()-i,boomModel.getY());

                            if(itemModel.getType()<5){
                                initModel.getArrItem().add(itemModel);
                                ImageView imageViewItem = new ImageView(itemModel.getImage());
                                imageViewItem.setX(itemModel.getX()*50);
                                imageViewItem.setY(itemModel.getY()*50);
                                gameStage.getChildren().add(5+initModel.getArrMonster().size() + initModel.getArrStone().size() + initModel.getArrTree().size()+initModel.getArrItem().size()-1,imageViewItem);
                                switch (itemModel.getType()) {
                                    case 1:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX()-i, 'S');
                                        break;
                                    }
                                    case 2:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX()-i, 'B');
                                        break;
                                    }
                                    case 3:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX() -i, 'Z');
                                        break;
                                    }
                                    case 4:{
                                        initModel.getMap().get(boomModel.getY()).set(boomModel.getX() -i, 'H');
                                        break;
                                    }
                                }
                            }
                            else {
                                initModel.getMap().get(boomModel.getY()).set(boomModel.getX()-i, ' ');
                            }
                            check = 1;
                        }

                        //kill monster
                        for(int j=0;j<initModel.getArrMonster().size();j++){
                            if((int)(initModel.getArrMonster().get(j).getPointX()/50) == boomModel.getX() && (int)(initModel.getArrMonster().get(j).getPointY()/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)(initModel.getArrMonster().get(j).getPointX()/50) == boomModel.getX()+i && (int)(initModel.getArrMonster().get(j).getPointY()/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)(initModel.getArrMonster().get(j).getPointX()/50) == boomModel.getX()-i && (int)(initModel.getArrMonster().get(j).getPointY()/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)(initModel.getArrMonster().get(j).getPointX()/50) == boomModel.getX() && (int)(initModel.getArrMonster().get(j).getPointY()/50) == boomModel.getY()+i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)(initModel.getArrMonster().get(j).getPointX()/50) == boomModel.getX() && (int)(initModel.getArrMonster().get(j).getPointY()/50) == boomModel.getY()-i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            }
                            if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY())/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX()+i && (int)((initModel.getArrMonster().get(j).getPointY())/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX()-i && (int)((initModel.getArrMonster().get(j).getPointY())/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY())/50) == boomModel.getY()+i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX())+45/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY())/50) == boomModel.getY()-i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            }
                            if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX()+i && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX()-i && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX()+45)/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()+i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX())+45/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY() + 50)/50) == boomModel.getY()-i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            }

                            if((int)((initModel.getArrMonster().get(j).getPointX())/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX())/50) == boomModel.getX()+i && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX())/50) == boomModel.getX()-i && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX())/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY()+50)/50) == boomModel.getY()+i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            } else if((int)((initModel.getArrMonster().get(j).getPointX())/50) == boomModel.getX() && (int)((initModel.getArrMonster().get(j).getPointY())/50) == boomModel.getY()-i){
                                initModel.getArrMonster().remove(j);
                                gameStage.getChildren().remove(5+j);
                                continue;
                            }
                        }
                    }
                    if(check == 1){
                        gameStage.getChildren().remove(5 + initModel.getArrMonster().size() + initModel.getArrStone().size(),5+initModel.getArrMonster().size()+initModel.getArrStone().size()+initModel.getArrTree().size());
                        initModel.getArrTree().clear();

                        for(int i=0;i<initModel.getMap().size();i++){
                            for(int j=0;j<initModel.getMap().get(i).size();j++){
                                if(initModel.getMap().get(i).get(j)=='*'){
                                    TreeModel treeModel = new TreeModel(j,i);
                                    initModel.getArrTree().add(treeModel);
                                    ImageView imageViewTree = new ImageView(treeModel.getImage());
                                    imageViewTree.setY(treeModel.getY()*50);
                                    imageViewTree.setX(treeModel.getX()*50);
                                    gameStage.getChildren().add(5+initModel.getArrStone().size() + initModel.getArrMonster().size(),imageViewTree);
                                }
                            }
                        }
                    }
                });
            }
        },200);
    }

}
