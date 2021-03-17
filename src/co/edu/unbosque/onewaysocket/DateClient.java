package co.edu.unbosque.onewaysocket;

import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

/**
 * Example from: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A command line client for the date server. Requires the IP address of the
 * server as the sole argument. Exits after printing the response.
 */
public class DateClient {

    public static void main(String[] args) throws IOException {

        // the server IP is required to stablish the connection
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }

        // a Socket is instantiated and a request for pairing with the server is sent
        // listening port on the server must be the same
        var socket = new Socket(args[0], 59090);

        // getting and encoding from bytes the input stream
        var in = new Scanner(socket.getInputStream());
        System.out.println("Hi client, the current datetime is: " + in.nextLine());

    }
}
