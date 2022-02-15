package co.edu.unbosque.onewaysocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Scanner;

/**
 * Example based on: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A simple TCP server. When a client connects, it sends to client the current
 * datetime, then closes the connection. This is arguably the simplest server
 * you can write.
 *
 * Beware though that a client has to be completely served its
 * date before the server will be able to handle another client.
 */
public class DateServer {

    static int PORT = 59090;

    public static void main(String[] args) throws IOException {
        // Try-with-resources statement
        // In this way, the socket is automatically closed at the end of the block
        // Listener is a local variable type inference
        // A ServerSocket is created for listening on the specified port
        try (var listener = new ServerSocket(PORT)) {
            System.out.format("The date server is running and listening on port %d...", PORT);
            System.out.println("\n");

            while (true) {
                // when server accepts a client, the communication for other clients remains blocked
                // A Socket needs to be instantiated for handling in and out messages
                try (var socket = listener.accept()) {
                    System.out.println("A new connection was stablished!");

                    // Decoding to bytes and sending the message to the client
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hi client, the current datetime is: " + new Date().toString());

                    // Receiving a message back from the client
                    var in = new Scanner(socket.getInputStream());
                    System.out.println("[Client] " + in.nextLine());
                }
            }
        }
    }
}
