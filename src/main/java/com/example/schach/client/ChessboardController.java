package com.example.schach.client;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.nio.channels.Pipe;

public class ChessboardController {
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
    public MouseEvent mouseEvent1;
    public int isChoosenInt = 0;
    public void fieldslected00(MouseEvent mouseEvent) {
        mouseEvent1 = mouseEvent;

        if (clickedpic ==  (Node) mouseEvent.getSource() ){
            pressed = false;
            isChoosen = true;
            clickedpic = null;
            System.out.println("Feld deaktiviert");
        }else if(clickedpic != (Node) mouseEvent.getSource() && isChoosen == true && pressed == true){
            slectedPic  = (Node) mouseEvent.getSource();
            for (Node n: chessBoardView.getChildren()) {
                if(n == slectedPic){
                    n.setVisible(false);
                }
            }
            Integer b = null;
            Integer x = GridPane.getRowIndex((Node) slectedPic);
            Integer y = GridPane.getColumnIndex((Node) slectedPic);
            if(x == b){
                x = 0;
            }
            if(y == b){
                y = 0;
            }
            int []move = movePawn(x,y);
            GridPane.setRowIndex(clickedpic,move[0]);
            GridPane.setColumnIndex(clickedpic,move[1]);
            isChoosen = false;
            pressed = false;

            System.out.println("Feld ersetzt");

        }else
        {
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
        if(x == b){
            x = 0;
        }
        if(y == b){
            y = 0;
        }
        int []move = movePawn(x,y);
        GridPane.setRowIndex(clickedpic,move[0]);
        GridPane.setColumnIndex(clickedpic,move[1]);
        System.out.println("Feld verschoben");
        //if(slectedPic.equals(black_bauer1)||slectedPic.equals(black_bauer2)|| slectedPic.equals(black_bauer3)|| slectedPic.equals(black_bauer4)|| slectedPic.equals(black_bauer5)|| slectedPic.equals(black_bauer6)|| slectedPic.equals(black_bauer7)|| slectedPic.equals(black_bauer8)){
//            Integer newFieldx  = GridPane.getRowIndex((Node) mouseEvent2.getSource());
//            Integer newFieldy =  GridPane.getColumnIndex((Node) mouseEvent2.getSource());
//            if(newFieldx == b){
//                newFieldx = 0;
//            }
//            if(newFieldy == b){
//                newFieldy = 0;
//            }
//           String name = slectedPic.getId().replaceAll(".$","");
            isChoosen = false;
            pressed = false;
        }

    public static int[] movePawn(int x, int y)
    {
        Integer x1 = x;
        Integer y1 = y;
        return new int[]{x1, y1};

    }

}
