/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectX;

import static java.awt.Color.YELLOW;
import javafx.animation.PathTransition;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Unknown
 */
public class CreateGrid {

    GridPane grid = new GridPane();
    Image image = new Image("/Untitled1.png");
    Circle[] c;
    int NOC;
    int totNOR;
    CreateGrid() {

    }

    CreateGrid(int NOC, int NOR) {
        this.NOC=NOC;
        this.totNOR = NOR + 1;
        c = new Circle[NOC];
        grid.setPrefSize(NOC * 80, totNOR * 80);
        grid.setMaxSize(NOC * 80, totNOR * 80);
        grid.setMinSize(NOC * 80, totNOR * 80);

        for (int i = 0; i < NOC; i++) {
            for (int j = 0; j < totNOR; j++) {
                if (j == 0) {
                    c[i] = new Circle(40);
                    grid.setHalignment(c[i], HPos.CENTER);
                    grid.add(c[i], i, j);
                    c[i].visibleProperty().set(false);
                    continue;
                }
                ImageView img = new ImageView(image);
                grid.add(img, i, j);
            }
        }
        //grid.gridLinesVisibleProperty().set(true);
        System.out.println(grid.getPrefWidth());
    }

    public void hideAllCircles() {
        for (int i = 0; i < c.length; i++) {
            c[i].visibleProperty().set(false);
        }
    }

    public void hideAllCirclesExcept(int x) {
        hideAllCircles();
        c[x].visibleProperty().set(true);
    }
    
    public void changeCColor(Color color){
        for (int i = 0; i < c.length; i++) {
            c[i].setFill(color);
        }
    }
    
    public void restGrid(){
        for (int i = 0; i < NOC; i++) {
            for (int j = 0; j < totNOR; j++) {
                if (j == 0) {
                    c[i].visibleProperty().set(false);
                    continue;
                }
                Circle c = new Circle(40);
                c.setFill(WHITE);
                grid.add(c, i, j);
            }
        }
    }
}
