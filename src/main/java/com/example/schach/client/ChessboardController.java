package com.example.schach.client;

import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.nio.channels.Pipe;
import java.util.ResourceBundle;

public class ChessboardController implements Initializable {
    public GridPane chessBoardView;
    public ImageView black_left_horse;
    public ImageView black_left_runner;
    public ImageView black_queen;
    public ImageView black_king;
    public ImageView black_right_runner;
    public ImageView black_right_horse;
    public ImageView black_right_tower;
    public ImageView white_bauer0_6;
    public ImageView white_bauer1_6;
    public ImageView white_bauer2_6;
    public ImageView black_left_tower;
    public ImageView white_bauer3_6;
    public ImageView white_bauer4_6;
    public ImageView white_bauer5_6;
    public ImageView white_bauer6_6;
    public ImageView white_bauer7_6;
    public ImageView white_left_tower;
    public ImageView white_left_horse;
    public ImageView white_left_runner;
    public ImageView white_queen;
    public ImageView white_king;
    public ImageView white_right_runner;
    public ImageView white_right_horse;
    public ImageView white_right_tower;
    public ImageView black_bauer;
    public ImageView img;
    public Node node;
    public Node checkField;
    public Node slectedPic;
    public Node clickedpic;
    public ImageView black_bauer1;
    public ImageView black_bauer2;
    public ImageView black_bauer3;
    public ImageView black_bauer4;
    public ImageView black_bauer5;
    public ImageView black_bauer6;
    public ImageView black_bauer7;
    public ImageView black_bauer8;
    public boolean isChoosen = false;
    public boolean pressed = false;
    public static MouseEvent mouseEvent1;
    public int isChoosenInt = 0;
    public Label player1;
    public Label player2;

    public void fieldslected00(MouseEvent mouseEvent) {
        mouseEvent1 = mouseEvent;

        if (clickedpic == (Node) mouseEvent.getSource()) {
            pressed = false;
            isChoosen = true;
            clickedpic = null;
            System.out.println("Feld deaktiviert");
        } else if (clickedpic != (Node) mouseEvent.getSource() && isChoosen == true && pressed == true) {
            slectedPic = (Node) mouseEvent.getSource();
            for (Node n : chessBoardView.getChildren()) {
                if (n == slectedPic) {
                    n.setVisible(false);
                }
            }
            Integer b = null;
            Integer x = GridPane.getRowIndex((Node) slectedPic);
            Integer y = GridPane.getColumnIndex((Node) slectedPic);
            if (x == b) {
                x = 0;
            }
            if (y == b) {
                y = 0;
            }
            if (clickedpic != null) {

                int[] move = movePawn(x, y);
                GridPane.setRowIndex(clickedpic, move[0]);
                GridPane.setColumnIndex(clickedpic, move[1]);

            }

            isChoosen = false;
            pressed = false;

            System.out.println("Feld ersetzt");

        } else {
            clickedpic = (ImageView) mouseEvent.getSource();
            pressed = true;
            isChoosen = true;
            System.out.println("Feld ausgewählt");

        }

    }

    public void mouseDragExited(MouseEvent mouseEvent2) {
        Integer b = null;
        Integer x = GridPane.getRowIndex((Node) mouseEvent2.getSource());
        Integer y = GridPane.getColumnIndex((Node) mouseEvent2.getSource());
        if (x == b) {
            x = 0;
        }
        if (y == b) {
            y = 0;
        }
        if (clickedpic != null) {
            String name2 = clickedpic.getId().replaceAll(".$", "");

        }

        //if(name2.contains("pawn")) {
        int[] move = movePawn(x, y);
        GridPane.setRowIndex(clickedpic, move[0]);
        GridPane.setColumnIndex(clickedpic, move[1]);
        // }


        System.out.println("Feld verschoben");
        isChoosen = false;
        pressed = false;
    }

    public int[] movePawn(int x, int y) {
        Integer newX = x;
        Integer newY = y;
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        String name = clickedpic.getId().replaceAll(".$", "");


        if (name.contains("black")) {
            if (oldX == 1 && newX <= 3) {
                return new int[]{newX, newY};
            } else if (oldX > 1 && newX == oldX + 1) {
                return new int[]{newX, newY};
            } else {
                newY = oldY;
                newX = oldX;
            }

            return new int[]{newX, newY};

        } else if (name.contains("white")) {
            if (oldX == 6 && newX >= 4) {
                return new int[]{newX, newY};
            } else if (oldX < 6 && newX == oldX - 1) {
                return new int[]{newX, newY};
            } else {
                newY = oldY;
                newX = oldX;
            }

            return new int[]{newX, newY};

        }
        return new int[]{newX, newY};

            /*if(newX < oldX && name.contains("black")||((newX > oldX && name.contains("white"))))
            {
                newX = oldX;
                newY = oldY;
               //return new int[]{oldX, oldY};
            }
            //nicht weiter als 2 felder beim ersten zug (weiß)
            if(oldX== 6 && name.contains("white") && newX >= 4|| (oldX != 6 && name.contains("white") && newX <= newX+1)||(oldX== 1 && name.contains("black") && newX <= 3))
            {
                newY = newY;
                newX = newX;
            }

            //immer nur ein feld(black)
            if(oldX != 1 && name.contains("black") && newX <= newX+1)
            {
                newY = newY;
                newX = newX;
            }
            //nicht nach links/rechts
            if(newY != oldY && name.contains("black"))
            {
                newX = oldX;
                newY = oldY;
            }else if(newY != oldY  && name.contains("white"))
            {
                newX = oldX;
                newY = oldY;
            }

            return new int[]{newX, newY};*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1.setText(Client.getUsername());
        player2.setText(Client.getServerUsername());
    }
}
