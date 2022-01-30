package com.example.schach.client;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ChessboardController {
    public ImageView black_bauer4_1;
    public ImageView black_bauer0_1;
    public ImageView black_bauer1_1;
    public ImageView black_bauer2_1;
    public ImageView black_bauer3_1;
    public ImageView black_bauer5_1;
    public ImageView black_bauer6_1;
    public ImageView black_bauer7_1;
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

        public Node node;
        public Node slectedPic;



    public void fieldslected00(MouseEvent mouseEvent) {
        slectedPic = (ImageView) mouseEvent.getSource();



    }

    public void mouseDragExited(MouseEvent mouseEvent) {
        Integer b = null;
        Integer x = GridPane.getRowIndex((Node) mouseEvent.getSource());
        Integer y = GridPane.getColumnIndex((Node) mouseEvent.getSource());
        if(x == b){
            x = 0;
        }
        if(y == b){
            y = 0;
        }


        GridPane.setRowIndex(slectedPic,x);
        GridPane.setColumnIndex(slectedPic,y);

    }


    public static int[] movePawn()
    {
        Integer x = 0;
        Integer y = 0;


        return new int[]{x, y};
    }

}
