package com.example.kolos2k22zapierdolesie;


import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    public Socket getSocket() {
        return socket;
    }

    private Socket socket;
    private PrintWriter writer;
    private HelloController helloController;
    public ServerThread(String address, int port, HelloController helloController) throws IOException {
        this.socket = new Socket(address, port);
        this.helloController = helloController;
    }

    public void run(){
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
            String message;
            while ((message = reader.readLine()) != null) {
                helloController.updateWordCountLabel();
                helloController.addToWordList(message);
            }
            System.out.println("closed");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
