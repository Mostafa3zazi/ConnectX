/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectX;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Unknown
 */
public class ConnectWithoutS extends Application {
    int rowCount=6;
    int columnCount=8;
    int connectType=4;
    
    Label l = new Label();
    Button b = new Button("Start");
    int column;
    int row;
    int currentPlayer = 0;
    boolean Winner = true;
    CreateGrid gTop = new CreateGrid(columnCount, rowCount);
    CreateGrid gBack = new CreateGrid(columnCount, rowCount);
    PathTransition pt = new PathTransition();
    boolean Tfinish = true;
    int position;
    CreatePlayer[] player;
    @Override
    public void start(Stage primaryStage) {
        // NOC --- NOR
        player = new CreatePlayer[2];
        player[0] = new CreatePlayer(1, Color.RED, "Mostafa Azazi");
        player[1] = new CreatePlayer(2, Color.YELLOW, "Yousef");
        Winner win = new Winner(columnCount, rowCount, connectType);

        StackPane s = new StackPane();
        s.getChildren().addAll(gBack.grid, gTop.grid);
        s.setPrefSize(gTop.grid.getPrefWidth(), gBack.grid.getPrefHeight()+50);

        gTop.grid.setOnMouseClicked(e -> {
            column = (int) (e.getX() / 80);
            row = win.availablePlaces[column];
            if (!Winner && win.isAvailable(column) && Tfinish) {
                gTop.hideAllCircles();
                addToGrid(player[currentPlayer].getPlayerColor());
                win.updateData(column, player[currentPlayer].getPlayerNum());
                printa(win.result);
                Winner = win.chickWinner(column, player[currentPlayer].getPlayerNum());
                if (Winner) {
                    showResult();
                } else {
                    changePlayer();
                    changeLable();
                }
            }
        });

        gTop.grid.setOnMouseMoved(e -> {
            if (!Winner) {
                position = (int) (e.getX() / 80);
                manageCircles(position);
            }
        });
        gTop.grid.setOnMouseDragged(e -> {
            if (!Winner) {
                position = (int) (e.getX() / 80);
                manageCircles(position);
            }
        });
        b.setOnAction(e->{
            Winner = false;
            currentPlayer=0;
            b.setText("Play again");
            l.setText(player[0].playerName+" turn");
            l.setTextFill(player[0].getPlayerColor());
            l.setTextAlignment(TextAlignment.CENTER);
            l.setFont(new Font("Arial", 30));
            win.restData();
            gBack.restGrid();
            gTop.changeCColor(player[0].getPlayerColor());
        });
        
        HBox h =new HBox(20);
        h.getChildren().addAll(l,b);
        VBox v = new VBox();
        v.getChildren().addAll(h,s);
        Scene scene = new Scene(v, s.getPrefWidth(), s.getPrefHeight());

        primaryStage.setTitle("Connect X");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void addToGrid(Color color) {
        Circle c = new Circle(40);
        c.setFill(color);
        Line l = new Line(0, -80 * row, 0, 0);
        gBack.grid.add(c, column, row);
        pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setNode(c);
        pt.setPath(l);
        pt.play();
        Tfinish = false;
        pt.setOnFinished(e -> {
            Tfinish = true;
            if(!Winner)
            gTop.hideAllCirclesExcept(position);
        });
    }

    public void changePlayer() {
        currentPlayer++;
        if (currentPlayer == CreatePlayer.numOfPlayers) {
            currentPlayer = 0;
        }
        gTop.changeCColor(player[currentPlayer].getPlayerColor());
    }

    public void printa(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }

    public void manageCircles(int position) {
        if (Tfinish) {
            gTop.hideAllCirclesExcept(position);
        } else {
            gTop.hideAllCircles();
        }
    }

    public void changeLable() {
        l.setText(player[currentPlayer].playerName+" turn");
        l.setTextFill(player[currentPlayer].getPlayerColor());
    }
    public void showResult(){
        l.setText(player[currentPlayer].playerName+" WIN");
        l.setTextFill(Color.BLACK);
    }
    

}
