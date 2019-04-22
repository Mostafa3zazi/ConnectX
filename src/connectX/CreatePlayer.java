/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectX;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Unknown
 */
public class CreatePlayer {
    static int numOfPlayers=0;
    String playerName;
    private int playerNum;
    private Color playerColor;
    CreatePlayer(){
        
    }
    CreatePlayer(int playerNum,Color playerColor,String playerName){
        this.playerNum=playerNum;
        this.playerColor=playerColor;
        this.playerName=playerName;
        numOfPlayers++;
    }
    public Circle Circle() {
        Circle c = new Circle(38);
        c.setFill(playerColor);
        return c;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
    public Label showResult() {
        Label lbl=new Label();
        lbl.setText("Playre " + playerNum + " WINS");
        lbl.setTextFill(Color.BLACK);
        return lbl;
    }
}
