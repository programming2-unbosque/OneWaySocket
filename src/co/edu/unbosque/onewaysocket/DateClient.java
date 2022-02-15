package co.edu.unbosque.onewaysocket;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

/**
 * Example based on: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A command line client for consuming the date server. Exits after printing the response.
 */
public class DateClient {

    static String IP = "127.0.0.1";
    static int PORT = 59090;

    public static void main(String[] args) throws IOException {
        // A Socket is instantiated and a request for pairing with the server is sent
        // Listening port on the server must be the same
        var socket = new Socket(IP, PORT);

        // Getting and encoding from bytes the input stream
        var in = new Scanner(socket.getInputStream());
        System.out.println("[Server] " + in.nextLine());

        // Sending thanks message to the server
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Thanks server! I receive the datetime.");
    }
}
