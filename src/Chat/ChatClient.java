
package Chat;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;



public class ChatClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("From others: " + msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Enter message: ");

                String msg = sc.nextLine();
                out.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


