package com.example.schach.client;

import com.example.schach.server.MyServerThread;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
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
import java.util.ResourceBundle;

public class ChessboardController implements Initializable, Serializable {
    public GridPane chessBoardView = new GridPane();
    public Node selectedPic;
    public Node clickedPic;
    public boolean isChosen = false;
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
    public Label infoText;
    String color;
    public static boolean informationListAdded = false;
    public static List<Information> informationList = new ArrayList<>();

    public ChessboardController() {
    }

    public void fieldselected(MouseEvent mouseEvent) {
        mouseEvent1 = mouseEvent;

        if (clickedPic == mouseEvent.getSource()) {
            pressed = false;
            isChosen = true;
            clickedPic = null;
            System.out.println("Feld deaktiviert");
        } else if (clickedPic != mouseEvent.getSource() && isChosen && pressed) {
            selectedPic = (Node) mouseEvent.getSource();
            String farbeChosen1 = clickedPic.getId();
            String farbeChosen2 = selectedPic.getId();
            Integer x = GridPane.getRowIndex(selectedPic);
            Integer y = GridPane.getColumnIndex(selectedPic);
            Integer oldX = GridPane.getRowIndex(clickedPic);
            Integer oldY = GridPane.getColumnIndex(clickedPic);

            if ((farbeChosen1.contains("white") && farbeChosen2.contains("white")) || (farbeChosen1.contains("black") && farbeChosen2.contains("black"))) {
                System.out.println("Diese Farbe kann nicht geschlagen werden.");
            } else {
                if (farbeChosen1.contains("pawn")) {

                    if (isPawnKill(x, y, oldX, oldY)) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == selectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedPic, x);
                        GridPane.setColumnIndex(clickedPic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedPic = null;
                        selectedPic = null;
                    }

                } else if (farbeChosen1.contains("king")) {
                    if (isKingKill(x, y, oldX, oldY)) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == selectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedPic, x);
                        GridPane.setColumnIndex(clickedPic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedPic = null;
                        selectedPic = null;
                    }
                } else if (farbeChosen1.contains("knight")) {
                    if (isHorseKill(x, y, oldX, oldY)) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == selectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedPic, x);
                        GridPane.setColumnIndex(clickedPic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedPic = null;
                        selectedPic = null;
                    }
                } else if (farbeChosen1.contains("bishop")) {
                    checkRunner(x, y, oldX, oldY);
                } else if (farbeChosen1.contains("rook")) {
                    if (rookKill(x, y, oldX, oldY)) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == selectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedPic, x);
                        GridPane.setColumnIndex(clickedPic, y);
                        System.out.println("Feld ersetzt");
                    } else {
                        clickedPic = null;
                        selectedPic = null;
                    }
                } else if (farbeChosen1.contains("queen")) {
                    if (rookKill(x, y, oldX, oldY)) {
                        for (Node n : chessBoardView.getChildren()) {
                            if (n == selectedPic) {
                                n.setVisible(false);
                            }
                        }
                        GridPane.setRowIndex(clickedPic, x);
                        GridPane.setColumnIndex(clickedPic, y);
                        System.out.println("Feld ersetzt");
                    } else checkRunner(x, y, oldX, oldY);
                }

            }
            isChosen = false;
            pressed = false;

            addInformationList();

        } else {
            clickedPic = (ImageView) mouseEvent.getSource();
            System.out.println(clickedPic);
            color = clickedPic.getId().replaceAll(".$", "");
            pressed = true;
            isChosen = true;
            if (clickedPic != null)
                System.out.println("Feld ausgewählt");

        }

    }

    private void checkRunner(Integer x, Integer y, Integer oldX, Integer oldY) {
        if (isRunnerKill(x, y, oldX, oldY)) {
            for (Node n : chessBoardView.getChildren()) {
                if (n == selectedPic) {
                    n.setVisible(false);
                }
            }
            GridPane.setRowIndex(clickedPic, x);
            GridPane.setColumnIndex(clickedPic, y);
            System.out.println("Feld ersetzt");
        } else {
            clickedPic = null;
            selectedPic = null;
        }
    }

    public void mouseDragExited(MouseEvent mouseEvent2) {
        Integer x = GridPane.getRowIndex((Node) mouseEvent2.getSource());
        Integer y = GridPane.getColumnIndex((Node) mouseEvent2.getSource());

        String name2 = "";
        if (clickedPic != null) {
            name2 = clickedPic.getId();
        }
        movement(name2, x, y);

        isChosen = false;
        pressed = false;
        clickedPic = null;

        addInformationList();


    }

    private boolean rookKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if (!oldX.equals(x) && !oldY.equals(y)) {
            return false;
        }

        int offset;

        if (!oldX.equals(x)) {
            if (oldX < x) {
                offset = 1;
            } else {
                offset = -1;
            }

            for (int x1 = oldX + offset; x1 != x; x1 += offset) {
                for (Node n : this.chessBoardView.getChildren()) {
                    if (isRookKilled(oldY, x1, n)) return false;

                }

            }
        }

        //Now do the same for columns
        if (!oldY.equals(y)) {
            if (oldY < y) {
                offset = 1;
            } else {
                offset = -1;
            }

            for (int x1 = oldY + offset; x1 != y; x1 += offset) {
                for (Node n : chessBoardView.getChildren()) {
                    if (isRookKilled(x1, oldX, n)) return false;

                }

            }
        }

        return true;
    }

    private boolean isRookKilled(Integer oldY, int oldX, Node n) {
        Integer rowN = GridPane.getRowIndex(n);
        Integer columnN = GridPane.getColumnIndex(n);
        if (rowN == null) {
            rowN = 0;
        }
        if (columnN == null) {
            columnN = 0;
        }
        return (rowN.equals(oldX) && columnN.equals(oldY) && n instanceof ImageView) && n.isVisible();
    }

    private int[] moveRock(Integer x, Integer y) {
        int temp;
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());

        if (!oldX.equals(x) && !oldY.equals(y)) {
            return new int[]{oldX, oldY};
        } else {
            if (!oldX.equals(x)) {
                if (oldX < x) {
                    temp = 1;
                } else {
                    temp = -1;
                }

                for (int x1 = oldX + temp; x1 != x; x1 += temp) {
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
        }
        if (!oldY.equals(y)) {
            if (oldY < y) {
                temp = 1;
            } else {
                temp = -1;
            }

            for (int x1 = oldY + temp; x1 != y; x1 += temp) {
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


        return new int[]{x, y};
    }

    private boolean isRunnerKill(Integer x, Integer y, Integer oldX, Integer oldY) {
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
                    if (isRookKilled(y1, x1, n)) return false;

                }
                y += colOffset;
            }
        }

        return true;


    }

    private int[] moveRunner(Integer x, Integer y) {
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        if (Math.abs(x - oldX) != Math.abs(y - oldY)) {
            return new int[]{oldX, oldY};
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
                        return new int[]{oldX, oldY};
                    }

                }
                y1 += colOffset;
            }
        }

        return new int[]{x, y};


    }

    private int[] moveHorse(Integer x, Integer y) {
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());

        if ((oldX - 2 == x && oldY - 1 == y) || (oldX - 2 == x && oldY + 1 == y)) {
            return new int[]{x, y};
        } else if ((oldX - 1 == x && oldY + 2 == y) || (oldX + 1 == x && oldY + 2 == y)) {
            return new int[]{x, y};
        } else if ((oldX + 2 == x && oldY + 1 == y) || (oldX + 2 == x && oldY - 1 == y)) {
            return new int[]{x, y};
        } else if ((oldX + 1 == x && oldY - 2 == y) || (oldX - 1 == x && oldY - 2 == y)) {
            return new int[]{x, y};
        } else
            return new int[]{oldX, oldY};
    }

    private boolean isHorseKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if ((oldX - 2 == x && oldY - 1 == y) || (oldX - 2 == x && oldY + 1 == y)) {
            return true;
        } else if ((oldX - 1 == x && oldY + 2 == y) || (oldX + 1 == x && oldY + 2 == y)) {
            return true;
        } else if ((oldX + 2 == x && oldY + 1 == y) || (oldX + 2 == x && oldY - 1 == y)) {
            return true;
        } else return (oldX + 1 == x && oldY - 2 == y) || (oldX - 1 == x && oldY - 2 == y);
    }

    private int[] moveKing(Integer x, Integer y) {
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());

        if ((oldX.equals(x) && y == oldY + 1)) {
            return new int[]{x, y};
        } else if ((oldY.equals(y) && x == oldX + 1)) {
            return new int[]{x, y};
        } else if ((oldX.equals(x) && y == oldY - 1)) {
            return new int[]{x, y};
        } else if ((oldY.equals(y) && x == oldX - 1)) {
            return new int[]{x, y};
        } else if ((oldX - 1 == x && oldY + 1 == y) || (oldX + 1 == x && oldY + 1 == y) || (oldX - 1 == x && oldY - 1 == y) || (oldX + 1 == x && oldY - 1 == y)) {
            return new int[]{x, y};
        } else {
            return new int[]{oldX, oldY};
        }
    }

    private boolean isKingKill(Integer x, Integer y, Integer oldX, Integer oldY) {
        if ((oldX.equals(x) && y == oldY + 1) || (oldX.equals(x) && y == oldY - 1)) {
            return true;
        } else if ((oldY.equals(y) && x == oldX + 1) || (oldY.equals(y) && x == oldX - 1)) {
            return true;
        } else if (oldX - 1 == x && oldY - 1 == y) {
            return true;
        } else if (oldX - 1 == x && oldY + 1 == y) {
            return true;
        } else if (oldX + 1 == x && oldY + 1 == y) {
            return true;
        } else return oldX + 1 == x && oldY - 1 == y;
    }

    private int[] movePawn(Integer x, Integer y) {
        Integer newX = x;
        Integer newY = y;
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        String name = clickedPic.getId();

        if (name.contains("black")) {
            if (oldX == 1 && newX <= 3 && newY.equals(oldY)) {
                return new int[]{newX, newY};
            } else if (oldX > 1 && newX == oldX + 1 && newY.equals(oldY)) {
                return new int[]{newX, newY};
            } else {
                newY = oldY;
                newX = oldX;
            }
            return new int[]{newX, newY};
        } else if (name.contains("white")) {
            if (oldX == 6 && newX >= 4 && newY.equals(oldY) && newX < oldX) {
                return new int[]{newX, newY};
            } else if (oldX < 6 && newX == oldX - 1 && newY.equals(oldY) && newX < oldX) {
                return new int[]{newX, newY};
            }

            return new int[]{oldX, oldY};

        }
        return new int[]{oldX, oldY};
    }

    private boolean isPawnKill(Integer newX, Integer newY, Integer oldX, Integer oldY) {
        String colorPawn = clickedPic.getId();

        if (oldX - 1 == newX && oldY - 1 == newY && colorPawn.contains("white")) {
            return true;
        } else if (oldX - 1 == newX && oldY + 1 == newY && colorPawn.contains("white")) {
            return true;
        } else if (oldX + 1 == newX && oldY + 1 == newY && colorPawn.contains("black")) {
            return true;
        } else return oldX + 1 == newX && oldY - 1 == newY && colorPawn.contains("black");
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
    }


    public void updateChessfield() {
        Platform.runLater(() -> {
            System.out.println("UPDATE CHESSFIELD");
            ArrayList<Node> temp = new ArrayList<>();
            List<Node> l = chessBoardView.getChildren();
            for (Node n : l) {
                if (n.getId() == null) continue;
                if (n.getId().contains("white") || n.getId().contains("black")) {
                    temp.add(n);

                }
            }
            chessBoardView.getChildren().removeAll(temp);

            for (Information information : informationList) {
                String nameId = information.getFieldName();
                String name = nameId.substring(0, nameId.length() - 1);
                Integer x = information.getX();
                Integer y = information.getY();
                // Load the image for the chess piece
                Image image = new Image(String.valueOf(this.getClass().getResource("/images/" + name + ".png")));
                ImageView imgV = new ImageView(image);
                imgV.setId(nameId);
                imgV.setOnMouseClicked(this::fieldselected);
                imgV.setFitHeight(65);
                imgV.setFitWidth(65);
                imgV.setPickOnBounds(true);
                imgV.setPreserveRatio(true);
                GridPane.setHalignment(imgV, HPos.CENTER);
                GridPane.setValignment(imgV, VPos.CENTER);

                // Add the ImageView to the GridPane at the correct position
                chessBoardView.add(imgV, y, x);
            }
            informationList.clear();
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Throwable t = new Throwable();
        StackTraceElement[] stackTraceElements = t.getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            if (element.getFileName().contains("JoinAGame")) {
                player1.setText(MyClientThread.getClientUsername());
                player2.setText(MyClientThread.getServerUsername());
                infoText.setText("White begins, black wins      Wait for your opponent");
                //temp
//                Platform.runLater(() -> {
//                    Image image = new Image(String.valueOf(this.getClass().getResource("/images/" + "white_pawn" + ".png")));
//                    ImageView imgV = new ImageView(image);
//                    imgV.setId("white_pawn4");
//                    chessBoardView.add(imgV, 4, 4);
//                });

                break;
            } else if (element.getFileName().contains("CreateAGame")) {
                player1.setText(MyServerThread.getServerUsername());
                player2.setText(MyServerThread.getClientUsername());
                infoText.setText("White begins, black wins      It's your turn");
                break;
            }
        }
    }

    private void movement(String name, int x, int y) {
        if (name.contains("pawn")) {
            int[] move = movePawn(x, y);
            GridPane.setRowIndex(clickedPic, move[0]);
            GridPane.setColumnIndex(clickedPic, move[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("king")) {
            int[] moveKing = moveKing(x, y);
            GridPane.setRowIndex(clickedPic, moveKing[0]);
            GridPane.setColumnIndex(clickedPic, moveKing[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("knight")) {
            int[] moveHorse = moveHorse(x, y);
            GridPane.setRowIndex(clickedPic, moveHorse[0]);
            GridPane.setColumnIndex(clickedPic, moveHorse[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("bishop")) {
            int[] moveRunner = moveRunner(x, y);
            GridPane.setRowIndex(clickedPic, moveRunner[0]);
            GridPane.setColumnIndex(clickedPic, moveRunner[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("rook")) {
            int[] moveRook = moveRock(x, y);
            GridPane.setRowIndex(clickedPic, moveRook[0]);
            GridPane.setColumnIndex(clickedPic, moveRook[1]);
            System.out.println("Feld verschoben");
        } else if (name.contains("queen")) {
            int[] moveQueen = moveQueen(x, y);
            GridPane.setRowIndex(clickedPic, moveQueen[0]);
            GridPane.setColumnIndex(clickedPic, moveQueen[1]);
        }
    }

    private int[] moveQueen(Integer x, Integer y) {
        Integer oldX = GridPane.getRowIndex((Node) mouseEvent1.getSource());
        Integer oldY = GridPane.getColumnIndex((Node) mouseEvent1.getSource());
        if ((rookKill(x, y, oldX, oldY))) {
            return new int[]{x, y};
        } else if (isRunnerKill(x, y, oldX, oldY)) {
            return new int[]{x, y};
        }
        return new int[]{oldX, oldY};
    }

}
