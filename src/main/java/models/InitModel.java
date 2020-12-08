package main.java.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InitModel {
    private List<PlayerModel> arrPlayer;
    private PlayerModel player;
    private List<TreeModel> arrTree = new ArrayList<TreeModel>();
    private List<StoneModel> arrStone = new ArrayList<StoneModel>();
    private List<MonsterModel> arrMonster = new ArrayList<MonsterModel>();
    private List<ItemModel> arrItem = new ArrayList<>();
    private List<List<Character>> map = new ArrayList<List<Character>>();

    public InitModel(){
        File file = new File("map.txt");
        try {
            int line = 0;
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                map.add(new ArrayList<>());
                for(int i=0;i<data.length();i++){
                    map.get(line).add(data.charAt(i));
                    if(data.charAt(i) == '#'){
                        arrStone.add(new StoneModel(i,line));
                    } else if(data.charAt(i) == '*'){
                        arrTree.add(new TreeModel(i,line));
                    } else if(data.charAt(i) == 'p'){
                        player = new PlayerModel(i,line,3);
                    } else if(data.charAt(i)=='1'){
                        arrMonster.add(new StupidMonsterModel(i,line,new Random().nextInt(4)+1));
                    } else if(data.charAt(i)=='2'){
                        arrMonster.add(new OnealMonsterModel(i,line,new Random().nextInt(4)+1));
                    }
                }
                line += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<PlayerModel> getArrPlayer() {
        return arrPlayer;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setArrMonster(List<MonsterModel> arrMonster) {
        this.arrMonster = arrMonster;
    }

    public List<MonsterModel> getArrMonster() {
        return arrMonster;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public void setArrPlayer(List<PlayerModel> arrPlayer) {
        this.arrPlayer = arrPlayer;
    }

    public List<StoneModel> getArrStone() {
        return arrStone;
    }

    public void setArrStone(List<StoneModel> arrStone) {
        this.arrStone = arrStone;
    }

    public List<TreeModel> getArrTree() {
        return arrTree;
    }

    public void setArrTree(List<TreeModel> arrTree) {
        this.arrTree = arrTree;
    }

    public List<ItemModel> getArrItem() {
        return arrItem;
    }

    public void setArrItem(List<ItemModel> arrItem) {
        this.arrItem = arrItem;
    }

    public List<List<Character>> getMap() {
        return map;
    }

    public void setMap(List<List<Character>> map) {
        this.map = map;
    }
}
