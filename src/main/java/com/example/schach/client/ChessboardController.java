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
    private boolean isWhiteTurn = true;
    private boolean isBlackTurn = false;
    String farbe;
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

                    GridPane.setRowIndex(clickedpic, x);
                    GridPane.setColumnIndex(clickedpic,y);
                }
            isChoosen = false;
            pressed = false;

            System.out.println("Feld ersetzt");

        } else {
            clickedpic = (ImageView) mouseEvent.getSource();
            farbe = clickedpic.getId().replaceAll(".$", "");
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
        String name2 = "";
        if (clickedpic != null) {
             name2 = clickedpic.getId();
        }
        if(name2.contains("bauer")) {
            int[] move = movePawn(x, y);
            GridPane.setRowIndex(clickedpic, move[0]);
            GridPane.setColumnIndex(clickedpic, move[1]);

        }else if(name2.contains("king1"))
        {
            int[] moveKing = moveKing(x, y);
            GridPane.setRowIndex(clickedpic, moveKing[0]);
            GridPane.setColumnIndex(clickedpic, moveKing[1]);
        }



        System.out.println("Feld verschoben");
        isChoosen = false;
        pressed = false;
        clickedpic = null;
    }

    private int[] moveKing(Integer x, Integer y) {
        Integer newX = x;
        Integer newY = y;
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        String name = clickedpic.getId();
        Integer b = null;
        if (oldX == b) {
            oldX = 0;
        }
        if (oldY == b) {
            oldY = 0;
        }
        if((oldX == newX && newY == oldY+1))
        {
            return new int[]{newX,newY };
        }else if((oldY == newY && newX == oldX+1))
        {
            return new int[]{newX,newY };
        }else if((oldX == newX && newY == oldY-1))
        {
            return new int[]{newX,newY };
        }else if((oldY == newY && newX == oldX-1))
        {
            return new int[]{newX,newY };
        }else if((oldX-1 == newX && oldY+1 == newY)||(oldX+1 == newX && oldY+1 == newY)||(oldX -1 == newX && oldY-1 == newY)||(oldX+1 ==newX && oldY-1 == newY))
        {
            return new int[]{newX,newY };
        }else{
            return new int[]{oldX,oldY };
        }
    }

    private int[] movePawn(Integer x, Integer y) {
        Integer newX = x;
        Integer newY = y;
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        String name = clickedpic.getId().replaceAll(".$", "");
        Integer b = null;
        if (newX == b) {
            newX = 0;
        }
        if (newY == b) {
            newY = 0;
        }
        if (oldX == b) {
            oldX = 0;
        }
        if (oldY == b) {
            oldY = 0;
        }

        if (name.contains("black") ) {
            if (oldX == 1 && newX <= 3 && newY == oldY) {
                return new int[]{newX, newY};
            } else if (oldX > 1 && newX == oldX + 1 && newY == oldY) {
                return new int[]{newX, newY};
            } else {
                newY = oldY;
                newX = oldX;
            }

            return new int[]{newX, newY};
        } else if (name.contains("white") ) {
            if (oldX == 6 && newX >= 4 && newY == oldY) {
                return new int[]{newX, newY};
            } else if (oldX < 6 && newX == oldX - 1 && newY == oldY) {
                return new int[]{newX, newY};
            } else {
                newY = oldY;
                newX = oldX;
            }

            return new int[]{newX, newY};

        }
        return new int[]{newX, newY};
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1.setText(Client.getUsername());
        player2.setText(Client.getServerUsername());
    }
}
