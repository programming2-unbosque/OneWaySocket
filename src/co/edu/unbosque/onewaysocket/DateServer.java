package co.edu.unbosque.onewaysocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Scanner;

/**
 * Example from: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A simple TCP server. When a client connects, it sends the client the current
 * datetime, then closes the connection. This is arguably the simplest server
 * you can write. Beware though that a client has to be completely served its
 * date before the server will be able to handle another client.
 */
public class DateServer {

    public static void main(String[] args) throws IOException {

        // try-with-resources statement
        // in this way, the socket is automatically closed at the end of the block
        try (
                // local variable type inference
                // a ServerSocket is created for listening on the specified port
                var listener = new ServerSocket(59090)
        ) {

            System.out.println("The date server is running...");

            while (true) {

                // when server accepts a client, the communication for other clients remains blocked
                // a Socket needs to be instantiated for handling in and out messages
                try (var socket = listener.accept()) {

                    System.out.println("A new connection was stablished!");

                    // decoding to bytes and sending the message to the client
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());

                    var in = new Scanner(socket.getInputStream());
                    System.out.println("The message from the client was: " + in.nextLine());

                }

            }

        }

    }

}
