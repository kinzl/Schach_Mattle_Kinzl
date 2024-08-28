package com.example.schach.client;

public class Client {
    private final String IPADDRESS;
    private ChessboardController chessboardController;

    public Client(String IPADDRESS) {
        this.IPADDRESS = IPADDRESS;
    }

    private void runClient() {
        System.out.println("CLIENT: Waiting for Server");
        int PORT = 23;
        ClientThread myClientThread = new ClientThread(IPADDRESS, PORT, chessboardController);
        myClientThread.run();
    }

    public void activate() {
        new Thread(this::runClient).start();
    }

    public void setChessboardController(ChessboardController chessboardController) {
        this.chessboardController = chessboardController;
    }
}
