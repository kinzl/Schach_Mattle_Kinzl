package com.example.schach.client;

import com.example.schach.server.MyServerThread;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChessboardController implements Initializable, Serializable {
    public GridPane chessBoardView = new GridPane();
//    public static GridPane chessBoardView;
    public Node slectedPic;
    public Node clickedpic;
    public boolean isChoosen = false;
    public boolean pressed = false;
    public static MouseEvent mouseEvent1;
    public Label player1;
    public Label player2;
    public MenuItem openChat;
    public MenuItem exitSystem;
    public ImageView white_king1;
    public ImageView black_rook1;
    public ImageView white_bishop1;
    public ImageView black_knight1;
    public ImageView black_bishop1;
    public ImageView black_queen1;
    public ImageView black_king1;
    public ImageView white_pawn0;
    public ImageView white_pawn1;
    public ImageView white_pawn2;
    public ImageView black_bishop2;
    public ImageView black_knight2;
    public ImageView black_rook2;
    public ImageView black_pawn1;
    public ImageView black_pawn2;
    public ImageView black_pawn3;
    public ImageView black_pawn4;
    public ImageView black_pawn5;
    public ImageView black_pawn6;
    public ImageView black_pawn7;
    public ImageView black_pawn8;
    public ImageView white_bishop2;
    public ImageView white_knight2;
    public ImageView white_rook2;
    public ImageView white_queen1;
    public ImageView white_knight1;
    public ImageView white_pawn4;
    public ImageView white_pawn7;
    public ImageView white_pawn5;
    public ImageView white_pawn3;
    public ImageView white_rook1;
    public ImageView white_pawn6;
    public Pane pane00;
    public Pane pane01;
    public Pane pane02;
    public Pane pane03;
    public Pane pane04;
    public Pane pane05;
    public Pane pane06;
    public Pane pane07;
    public Pane pane10;
    public Pane pane11;
    public Pane pane12;
    public Pane pane13;
    public Pane pane14;
    public Pane pane15;
    public Pane pane16;
    public Pane pane17;
    public Pane pane20;
    public Pane pane21;
    public Pane pane22;
    public Pane pane23;
    public Pane pane24;
    public Pane pane25;
    public Pane pane26;
    public Pane pane27;
    public Pane pane30;
    public Pane pane31;
    public Pane pane32;
    public Pane pane33;
    public Pane pane34;
    public Pane pane35;
    public Pane pane36;
    public Pane pane37;
    public Pane pane40;
    public Pane pane41;
    public Pane pane42;
    public Pane pane43;
    public Pane pane44;
    public Pane pane45;
    public Pane pane46;
    public Pane pane47;
    public Pane pane50;
    public Pane pane51;
    public Pane pane52;
    public Pane pane53;
    public Pane pane54;
    public Pane pane55;
    public Pane pane56;
    public Pane pane57;
    public Pane pane60;
    public Pane pane61;
    public Pane pane62;
    public Pane pane63;
    public Pane pane64;
    public Pane pane65;
    public Pane pane66;
    public Pane pane67;
    public Pane pane70;
    public Pane pane71;
    public Pane pane72;
    public Pane pane73;
    public Pane pane74;
    public Pane pane75;
    public Pane pane76;
    public Pane pane77;
    public Label TimerPlayer1;
    public Label TimerPlayer2;
    public Label TimeOverAll;


    private boolean isWhiteTurn = true;
    private boolean isBlackTurn = false;
    String farbe;
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

//        for (int i = 0; i < informationList.size(); i++) {
//            System.out.println(informationList.get(i));
//        }
        informationListAdded = true;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        informationListAdded = false;
    }

    public void updateChessfield() {

        System.out.println("UPDATE CHESSFIELD");
        chessBoardView.getChildren().clear();
        //System.out.println(chessBoardView.getChildren().size());
        //chessBoardView = new GridPane();
        for (int i = 0; i < informationList.size(); i++) {
            String name = informationList.get(i).getFieldName();
            Integer x = informationList.get(i).getX();
            Integer y = informationList.get(i).getY();
            name = name.substring(0, name.length() - 1);
//            System.out.println(name);System.out.println(name + " " + x + " " + y);

            for (Node n : chessBoardView.getChildren()) {
                if(n.getId().contains(name)){
                    GridPane.setRowIndex(n, x);
                    GridPane.setColumnIndex(n, y);
                }
            }

            /*ImageView imgV = new ImageView();
            Image image = new Image(String.valueOf(this.getClass().getResource("/images/" + name + ".png")));
            imgV.setImage(image);
            imgV.setId(name);
            //System.out.println("imgV: " + imgV);
            clickedpic = imgV;
            clickedpic.setId(name);
            //movement(name, x,y);
            //chessBoardView.getChildren().add(imgV);
            for (int j = 0; j < informationList.size(); j++) {
                System.out.println("Update chessfield: " + informationList.get(i));
            }
            GridPane.setRowIndex(imgV, x);
            GridPane.setColumnIndex(imgV, y);*/


        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            player1.setText(MyClientThread.getClientUsername().toUpperCase(Locale.ROOT));
            player2.setText(MyClientThread.getServerUsername().toUpperCase(Locale.ROOT));
        }catch (Exception e) {
            player1.setText(MyServerThread.getServerUsername());
            player2.setText(MyServerThread.getClientUsername());
        }
//ChessboardController.chessBoardView = new GridPane();
    }

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        ChessboardController.informationList = informationList;
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
