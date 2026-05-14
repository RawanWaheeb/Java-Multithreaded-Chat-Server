
package Chat;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ChatHandler extends Thread {

    Socket socket;
    PrintWriter out;

    public ChatHandler(Socket socket) {
        this.socket = socket;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String msg;

            while ((msg = in.readLine()) != null) {
                if (msg == null || msg.trim().isEmpty()) continue;
                System.out.println("Client says: " + msg);

                broadcast(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String msg) {
        for (ChatHandler client : ChatServer.clients) {
            try {
                if (client.out != null) {
                    client.out.println(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

