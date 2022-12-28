package simplechatapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private String username;
    private Scanner scanner;
    public Client() {
        running = true;
        scanner = new Scanner(System.in);
    }

    public void run() throws UnknownHostException, IOException {
        String hostName = "localhost";
        int port = 8686;
        Socket client = new Socket(hostName, port);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
        
        System.out.print("Enter your name:");
        username = scanner.nextLine();
        out.println(username);
        new Thread(new InputHandler()).start();
        while(running) {
            System.out.println(in.readLine());
        }

    }

    class InputHandler implements Runnable{

        @Override
        public void run() {
            
            System.out.println("Start typing message:");
            while(running) {
                String message = scanner.nextLine();
                out.println(message);
                if(message.equals("-1")) running=false; 
            }
        }

    }
    public static void main(String[] args) throws IOException {
        new Client().run();
    }
}
