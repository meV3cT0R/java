package simplechatapplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private ArrayList<ClientHandler> clients;
    private boolean running;
    private ExecutorService executorService;

    public Server() {
        clients = new ArrayList<>();
        running = true;
    }

    public void run() throws IOException {
        while(running) {

            ServerSocket server=null;
            try {
                server = new ServerSocket(8686);
                executorService = Executors.newCachedThreadPool();
                System.out.println("Waiting for client...");
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                
                clients.add(clientHandler);
                executorService.execute(clientHandler);

            } catch (IOException e) {
                if(server!=null)
                    server.close();
            }
        }   
    }

    public void showEveryone(String message) {
        for(ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    class ClientHandler implements Runnable{
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                username = in.readLine();
                
                System.out.printf("%s connected%n",username);
                showEveryone(String.format("%s Entered the chat ",username));
                while(running) {
                    String message = in.readLine();
                    if(message !=null) {
                        System.out.printf("%s:%s%n",username,message);
                        if(message.equals("-1")){
                            in.close();
                            out.close();
                            socket.close();
                        } 
                        
                        showEveryone(String.format("%s:%s%n",username,message));
                    }
                }
            } catch (IOException e) {
                
                try {   
                    in.close();
                    out.close();
                    socket.close();
    
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
            showEveryone(String.format("%s left the chat%n",username));
        }

        public void sendMessage(String message) {
            out.printf("%s%n",message);
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().run();
    }
}
