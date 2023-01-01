package com.vector;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Tictactoe tictactoe;
    private boolean running;
    private boolean isTurn;
    private boolean waiting;

    public Client(Tictactoe tictactoe,boolean running) {
        this.tictactoe = tictactoe;
        this.running = running;
    }

    public void run(){
        try {
            socket = new Socket("localhost",8989);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            out = new ObjectOutputStream(os);
            in = new ObjectInputStream(is);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello");
            while(running) {
                Message message = (Message) in.readObject();
                isTurn = message.getTurn();
                waiting = message.getWaiting();
                Message outMessage;
                if(!waiting) {
                    tictactoe.printBoard();
                    if(tictactoe.checkWin()) {
                        System.out.printf("%s won the game %n",tictactoe.getWinner());
                        break;
                    }
                    
                    if(isTurn) {
                        System.out.print(message.getMessage());
                        String coor = scanner.nextLine();
                        outMessage = new Message();
                        outMessage.setCoor(coor);
                        out.writeObject(outMessage);
                    }
                    tictactoe.move(((Message) in.readObject()).getCoor());
                    
                }else {
                    System.out.println(message.getMessage());
                }

            }
            scanner.close(); 
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
           
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client(new Tictactoe(),true).run();
    }
}
