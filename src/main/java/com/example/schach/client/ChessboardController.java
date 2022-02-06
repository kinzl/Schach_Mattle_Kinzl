package com.example.schach.client;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

public class ChessboardController implements Initializable, Serializable {
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
    public MenuItem openChat;
    public MenuItem exitSystem;

    private boolean isWhiteTurn = true;
    private boolean isBlackTurn = false;
    String farbe;
    private ObservableList<Node> list;
    private boolean isMovePossible = false;
    public static boolean informationListAdded = false;
    public static List<Information> informationList = new ArrayList<>();

    public void fieldslected00(MouseEvent mouseEvent) {
        mouseEvent1 = mouseEvent;
        //System.out.println(chessBoardView.getChildren());

        if (clickedpic == (Node) mouseEvent.getSource()) {
            pressed = false;
            isChoosen = true;
            clickedpic = null;
            System.out.println("Feld deaktiviert");
        } else if (clickedpic != (Node) mouseEvent.getSource() && isChoosen == true && pressed == true) {
            slectedPic = (Node) mouseEvent.getSource();
            String farbeChosen1 = clickedpic.getId();
            String farbeChosen2 = slectedPic.getId();
            Integer b = null;
            Integer x = GridPane.getRowIndex((Node) slectedPic);
            Integer y = GridPane.getColumnIndex((Node) slectedPic);
            Integer oldX = GridPane.getRowIndex((Node) clickedpic);
            Integer oldY = GridPane.getColumnIndex((Node) clickedpic);
            if (x == b) {
                x = 0;
            }
            if (y == b) {
                y = 0;
            }
            if (oldX == b) {
                oldX = 0;
            }
            if (oldY == b) {
                oldY = 0;
            }
            if ((farbeChosen1.contains("white") && farbeChosen2.contains("white")) || (farbeChosen1.contains("black") && farbeChosen2.contains("black"))) {
                System.out.println("Diese Farbe kann nicht geschlagen werden.");
            } else {
                if (farbeChosen1.contains("pawn")) {

                    if (ispawnKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedpic = null;
                        slectedPic = null;
                    }

                } else if (farbeChosen1.contains("king")) {
                    if (isKingKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedpic = null;
                        slectedPic = null;
                    }
                } else if (farbeChosen1.contains("knight")) {
                    if (ishorseKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedpic = null;
                        slectedPic = null;
                    }
                } else if (farbeChosen1.contains("bishop")) {
                    if (isrunnerKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedpic = null;
                        slectedPic = null;
                    }
                } else if (farbeChosen1.contains("rook")) {
                    if (rookKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedpic = null;
                        slectedPic = null;
                    }
                } else if (farbeChosen1.contains("queen")) {
                    if (rookKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else if (isrunnerKill(x, y, oldX, oldY) == true) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == slectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedpic, x);
                        GridPane.setColumnIndex(clickedpic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedpic = null;
                        slectedPic = null;
                    }
                }

            }
            isChoosen = false;
            pressed = false;

            addInformationList();

        } else {
            clickedpic = (ImageView) mouseEvent.getSource();
            System.out.println(clickedpic);
            farbe = clickedpic.getId().replaceAll(".$", "");
            pressed = true;
            isChoosen = true;
            if (clickedpic != null)
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
        movement(name2, x, y);

        isChoosen = false;
        pressed = false;
        clickedpic = null;

        list = chessBoardView.getChildren();
        addInformationList();


    }

    private boolean rookKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if (oldX != x && oldY != y) {
            //Did not move along one rank/file
            return false;
        }

        //First I will assumed the Rook is moving along the rows.
        int offset;

        if (oldX != x) {
            if (oldX < x) {
                offset = 1;
            } else {
                offset = -1;
            }

            for (int x1 = oldX + offset; x1 != x; x1 += offset) {
                for (Node n : this.chessBoardView.getChildren()) {
                    Integer rowN = GridPane.getRowIndex(n);
                    Integer columnN = GridPane.getColumnIndex(n);
                    if (rowN == null) {
                        rowN = 0;
                    }
                    if (columnN == null) {
                        columnN = 0;
                    }
                    if ((rowN.equals(x1) && columnN.equals(oldY) && n instanceof ImageView) && n.isVisible()) {
                        return false;
                    }

                }

            }
        }

        //Now do the same for columns
        if (oldY != y) {
            if (oldY < y) {
                offset = 1;
            } else {
                offset = -1;
            }

            for (int x1 = oldY + offset; x1 != y; x1 += offset) {
                for (Node n : chessBoardView.getChildren()) {
                    Integer rowN = GridPane.getRowIndex(n);
                    Integer columnN = GridPane.getColumnIndex(n);
                    if (rowN == null) {
                        rowN = 0;
                    }
                    if (columnN == null) {
                        columnN = 0;
                    }
                    if ((rowN.equals(oldX) && columnN.equals(x1) && n instanceof ImageView) && n.isVisible()) {
                        return false;
                    }

                }

            }
        }

        return true;
    }

    private int[] moveRock(Integer x, Integer y) {
        int temp;
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
        if (oldX != newX && oldY != newY) {
            return new int[]{oldX, oldY};
        } else if (oldX != newX) {
            if (oldX < newX) {
                temp = 1;
            } else {
                temp = -1;
            }

            for (int x1 = oldX + temp; x1 != newX; x1 += temp) {
                for (Node n : chessBoardView.getChildren()) {
                    Integer rowN = GridPane.getRowIndex(n);
                    Integer columnN = GridPane.getColumnIndex(n);
                    if (rowN == null) {
                        rowN = 0;
                    }
                    if (columnN == null) {
                        columnN = 0;
                    }
                    if ((rowN.equals(x1) && columnN.equals(oldY) && n instanceof ImageView)) {
                        return new int[]{oldX, oldY};
                    }

                }
            }

        }
        if (oldY != newY) {
            if (oldY < newY) {
                temp = 1;
            } else {
                temp = -1;
            }

            for (int x1 = oldY + temp; x1 != newY; x1 += temp) {
                for (Node n : chessBoardView.getChildren()) {
                    Integer rowN = GridPane.getRowIndex(n);
                    Integer columnN = GridPane.getColumnIndex(n);
                    if (rowN == null) {
                        rowN = 0;
                    }
                    if (columnN == null) {
                        columnN = 0;
                    }
                    if ((rowN.equals(oldX) && columnN.equals(x1) && n instanceof ImageView)) {
                        return new int[]{oldX, oldY};
                    }

                }
            }
        }


        return new int[]{newX, newY};
    }

    private boolean isrunnerKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if (Math.abs(x - oldX) != Math.abs(y - oldY)) {
            return false;
        } else {
            int rowOffset, colOffset;

            if (oldX < x) {
                rowOffset = 1;
            } else {
                rowOffset = -1;
            }

            if (oldY < y) {
                colOffset = 1;
            } else {
                colOffset = -1;
            }

            int y1 = oldY + colOffset;
            for (int x1 = oldX + rowOffset; x1 != x; x1 += rowOffset) {

                for (Node n : chessBoardView.getChildren()) {
                    Integer rowN = GridPane.getRowIndex(n);
                    Integer columnN = GridPane.getColumnIndex(n);
                    if (rowN == null) {
                        rowN = 0;
                    }
                    if (columnN == null) {
                        columnN = 0;
                    }
                    if ((rowN.equals(x1) && columnN.equals(y1) && n instanceof ImageView) && n.isVisible()) {
                        return false;
                    }

                }
                y += colOffset;
            }
        }

        return true;


    }

    private int[] moveRunner(Integer x, Integer y) {
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
        int row = oldX;
        int column = oldY;
        if (Math.abs(newX - oldX) != Math.abs(newY - oldY)) {
            return new int[]{oldX, oldY};
        } else {
            int rowOffset, colOffset;

            if (oldX < newX) {
                rowOffset = 1;
            } else {
                rowOffset = -1;
            }

            if (oldY < newY) {
                colOffset = 1;
            } else {
                colOffset = -1;
            }

            int y1 = oldY + colOffset;
            for (int x1 = oldX + rowOffset; x1 != newX; x1 += rowOffset) {

                for (Node n : chessBoardView.getChildren()) {
                    Integer rowN = GridPane.getRowIndex(n);
                    Integer columnN = GridPane.getColumnIndex(n);
                    if (rowN == null) {
                        rowN = 0;
                    }
                    if (columnN == null) {
                        columnN = 0;
                    }
                    if ((rowN.equals(x1) && columnN.equals(y1) && n instanceof ImageView) && n.isVisible()) {
                        return new int[]{oldX, oldY};
                    }

                }
                y1 += colOffset;
            }
        }

        return new int[]{newX, newY};


    }

    private int[] moveHorse(Integer x, Integer y) {
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

        if ((oldX - 2 == newX && oldY - 1 == newY) || (oldX - 2 == newX && oldY + 1 == newY)) {
            return new int[]{newX, newY};
        } else if ((oldX - 1 == newX && oldY + 2 == newY) || (oldX + 1 == newX && oldY + 2 == newY)) {
            return new int[]{newX, newY};
        } else if ((oldX + 2 == newX && oldY + 1 == newY) || (oldX + 2 == newX && oldY - 1 == newY)) {
            return new int[]{newX, newY};
        } else if ((oldX + 1 == newX && oldY - 2 == newY) || (oldX - 1 == newX && oldY - 2 == newY)) {
            return new int[]{newX, newY};
        } else
            return new int[]{oldX, oldY};
    }

    private boolean ishorseKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if ((oldX - 2 == x && oldY - 1 == y) || (oldX - 2 == x && oldY + 1 == y)) {
            return true;
        } else if ((oldX - 1 == x && oldY + 2 == y) || (oldX + 1 == x && oldY + 2 == y)) {
            return true;
        } else if ((oldX + 2 == x && oldY + 1 == y) || (oldX + 2 == x && oldY - 1 == y)) {
            return true;
        } else if ((oldX + 1 == x && oldY - 2 == y) || (oldX - 1 == x && oldY - 2 == y)) {
            return true;
        } else
            return false;
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
        if ((oldX == newX && newY == oldY + 1)) {
            return new int[]{newX, newY};
        } else if ((oldY == newY && newX == oldX + 1)) {
            return new int[]{newX, newY};
        } else if ((oldX == newX && newY == oldY - 1)) {
            return new int[]{newX, newY};
        } else if ((oldY == newY && newX == oldX - 1)) {
            return new int[]{newX, newY};
        } else if ((oldX - 1 == newX && oldY + 1 == newY) || (oldX + 1 == newX && oldY + 1 == newY) || (oldX - 1 == newX && oldY - 1 == newY) || (oldX + 1 == newX && oldY - 1 == newY)) {
            return new int[]{newX, newY};
        } else {
            return new int[]{oldX, oldY};
        }
    }

    private boolean isKingKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if ((oldX == x && y == oldY + 1) || (oldX == x && y == oldY - 1)) {
            return true;
        } else if ((oldY == y && x == oldX + 1) || (oldY == y && x == oldX - 1)) {
            return true;
        } else if (oldX - 1 == x && oldY - 1 == y) {
            return true;
        } else if (oldX - 1 == x && oldY + 1 == y) {
            return true;
        } else if (oldX + 1 == x && oldY + 1 == y) {
            return true;
        } else if (oldX + 1 == x && oldY - 1 == y) {
            return true;
        } else
            return false;
    }

    private int[] movePawn(Integer x, Integer y) {
        Integer newX = x;
        Integer newY = y;
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        String name = clickedpic.getId();
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

        if (name.contains("black")) {
            if (oldX == 1 && newX <= 3 && newY == oldY) {
                isMovePossible = true;
                return new int[]{newX, newY};
            } else if (oldX > 1 && newX == oldX + 1 && newY == oldY) {
                isMovePossible = true;
                return new int[]{newX, newY};
            } else {
                isMovePossible = false;
                newY = oldY;
                newX = oldX;
            }
            isMovePossible = false;
            return new int[]{newX, newY};
        } else if (name.contains("white")) {
            if (oldX == 6 && newX >= 4 && newY == oldY && newX < oldX) {
                isMovePossible = true;
                return new int[]{newX, newY};
            } else if (oldX < 6 && newX == oldX - 1 && newY == oldY && newX < oldX) {
                isMovePossible = true;
                return new int[]{newX, newY};
            } else {
                isMovePossible = false;
                newY = oldY;
                newX = oldX;
            }
            isMovePossible = false;
            return new int[]{oldX, oldY};

        }
        isMovePossible = false;
        return new int[]{oldX, oldY};
    }

    private boolean ispawnKill(Integer newX, Integer newY, Integer oldX, Integer oldY) {
        String colurPawn = clickedpic.getId();

        if (oldX - 1 == newX && oldY - 1 == newY && colurPawn.contains("white")) {
            return true;
        } else if (oldX - 1 == newX && oldY + 1 == newY && colurPawn.contains("white")) {
            return true;
        } else if (oldX + 1 == newX && oldY + 1 == newY && colurPawn.contains("black")) {
            return true;
        } else if (oldX + 1 == newX && oldY - 1 == newY && colurPawn.contains("black")) {
            return true;
        } else {
            return false;
        }
    }

    public void addInformationList() {

        Integer rowN;
        Integer columnN;

        for (Node n : chessBoardView.getChildren()) {
            if (n.getTypeSelector().equals("ImageView")) {

                rowN = GridPane.getRowIndex(n);
                columnN = GridPane.getColumnIndex(n);
                if (rowN == null) {
                    rowN = 0;
                }
                if (columnN == null) {
                    columnN = 0;
                }

                informationList.add(new Information(n.getId(), rowN, columnN));
                //System.out.println("n.getID: " + n.getId());
                //System.out.println(rowN + " + " + columnN);
            }
        }
        informationListAdded = true;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        informationListAdded = false;
//        for (int i = 0; i < informationList.size(); i++) {
//            System.out.println("CHESSBOARD: " + informationList.get(i));
//        }
//        try {
//            writer.writeObject("sendInformationList");
//            writer.writeObject(informationList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void updateChessfield() {

        System.out.println("UPDATE CHESSFIELD");

        //informationList.get(i).getFieldName();
        for (int i = 0; i < informationList.size(); i++) {
            System.out.println(informationList.get(i));
            String name = informationList.get(i).getFieldName();
            Integer x = informationList.get(i).getX();
            Integer y = informationList.get(i).getY();
            name = name.substring(0, name.length() - 1);
//            System.out.println(name);


            ImageView imgV = new ImageView();
            Image image = new Image(String.valueOf(this.getClass().getResource("/images/" + name + ".png")));
            imgV.setImage(image);
            System.out.println("imgV: " + imgV);
            clickedpic = imgV;
            clickedpic.setId(name);
            movement(name, x, y);

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1.setText(MyClientThread.getClientUsername().toUpperCase(Locale.ROOT));
        player2.setText(MyClientThread.getServerUsername().toUpperCase(Locale.ROOT));
    }

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
        updateChessfield();
    }

    public boolean isInformationListAdded() {
        return informationListAdded;
    }

    private void movement(String name, int x, int y) {
        if (name.contains("pawn")) {
            int[] move = movePawn(x, y);
            GridPane.setRowIndex(clickedpic, move[0]);
            GridPane.setColumnIndex(clickedpic, move[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("king")) {
            int[] moveKing = moveKing(x, y);
            GridPane.setRowIndex(clickedpic, moveKing[0]);
            GridPane.setColumnIndex(clickedpic, moveKing[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("knight")) {
            int[] moveHorse = moveHorse(x, y);
            GridPane.setRowIndex(clickedpic, moveHorse[0]);
            GridPane.setColumnIndex(clickedpic, moveHorse[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("bishop")) {
            int[] moveRunner = moveRunner(x, y);
            GridPane.setRowIndex(clickedpic, moveRunner[0]);
            GridPane.setColumnIndex(clickedpic, moveRunner[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("rook")) {
            int[] moveRook = moveRock(x, y);
            GridPane.setRowIndex(clickedpic, moveRook[0]);
            GridPane.setColumnIndex(clickedpic, moveRook[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("queen")) {
            int[] moveQueen = moveQueen(x, y);
            GridPane.setRowIndex(clickedpic, moveQueen[0]);
            GridPane.setColumnIndex(clickedpic, moveQueen[1]);
        }
    }

    private int[] moveQueen(Integer x, Integer y) {
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        if ((rookKill(x, y, oldX, oldY)) == true) {
            return new int[]{x, y};
        } else if (isrunnerKill(x, y, oldX, oldY) == true) {
            return new int[]{x, y};
        }
        return new int[]{oldX, oldY};
    }


}
