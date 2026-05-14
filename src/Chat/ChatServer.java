package Chat;

import  java.net.Socket;
import  java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;


public class ChatServer {
    static List<ChatHandler> clients = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server is running");

            while (true) {
                Socket socket = server.accept();
                System.out.println("New client connected!");

                ChatHandler handler = new ChatHandler(socket);
                clients.add(handler);
                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
