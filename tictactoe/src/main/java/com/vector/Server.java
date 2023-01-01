package com.vector;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private boolean running;
    private ClientHandler userX;
    private ClientHandler userO;
    private char turn;

    public Server(boolean running) {
        this.running = running;
    }

    public void run() throws IOException {
        server = new ServerSocket(8989);

        while(running) {
            Socket socket = server.accept();
            ClientHandler handler = new ClientHandler(socket);
            if(userX ==null || userO ==null) {
                if(userX == null) userX = handler;
                else userO = handler;
            }
            new Thread(handler).start();
        }
    }

    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleTurn() throws ClassNotFoundException, IOException {
        Message messageX = new Message();
        Message messageO = new Message();
        System.out.println(turn);
        Message input;
        switch(turn) {
            case 'X':
                messageX.setMessage("Enter coordinates:");
                messageX.setTurn(true);
                messageX.setWaiting(false);
                userX.sendMessage(messageX);
                messageO.setMessage("please wait:");
                messageO.setTurn(false);
                messageO.setWaiting(false);
                userO.sendMessage(messageO);
                input = (Message) userX.in.readObject();
                break;
            case 'O':
                messageO.setMessage("Enter coordinates:");
                messageO.setTurn(true);
                messageO.setWaiting(false);
                userO.sendMessage(messageO);
                messageX.setMessage("please wait:");
                messageX.setTurn(false);
                messageX.setWaiting(false);
                userX.sendMessage(messageX);
                input = (Message) userO.in.readObject();
                break;
            default:
                input =null;
                break;
        }

        userX.sendMessage(input);
        userO.sendMessage(input);
    }
    private class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;


        public ClientHandler(Socket socket) {
            this.socket =socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                out = new ObjectOutputStream(os);
                in = new ObjectInputStream(is);
                System.out.printf("%s connected %n",socket.getInetAddress());
                Message waitMessage;
                if(userO==null) {
                    waitMessage = new Message();
                    waitMessage.setWaiting(true);
                   
                    waitMessage.setMessage("Please wait for other user");
                    sendMessage(waitMessage);
                }

                int i=0;
                while(running){  
                    if(userO !=null & userX !=null) {
                        if(i%2==0)
                            turn = 'X';
                        else
                            turn = 'O';
                        handleTurn();
                    }
                    i++;   
                }

            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                    in.close();
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
    
                e.printStackTrace();
            } 
        }

        public void sendMessage(Message message) {
            try {
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Server(true).run();
    }
}
