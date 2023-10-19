package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// From https://stackoverflow.com/questions/15541804/creating-the-serversocket-in-a-separate-thread
public class MultithreadedServer {

        public static final String EOT = "EOT";
        public static final String SHUTDOWN = "SHUTDOWN";
        public static final int PORT = 8000;
        private volatile boolean isShutdown = false;

        public static void main(String[] args) {

            MultithreadedServer server = new MultithreadedServer();
            server.startServer();

        }

        public void startServer() {
            final ExecutorService threads = Executors.newFixedThreadPool(4);

            Runnable serverTask = new Runnable() {

                @Override
                public void run() {
                    // FILL IN CODE
                    // Create a welcoming socket
                    // Listen for client requests
                    // Create a new Runnable Client Task to process each request

                }
            };
            Thread serverThread = new Thread(serverTask);
            serverThread.start();

        }

        class ClientTask implements Runnable {
            private final Socket connectionSocket;

            private ClientTask(Socket connectionSocket) {
                this.connectionSocket = connectionSocket;
            }

            @Override
            public void run() {
                System.out.println("A client connected.");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()))) {
                    String input;
                    while (!connectionSocket.isClosed()) {
                        input = reader.readLine();
                        System.out.println("Server received: " + input); // echo the same string to the console

                        if (input.equals(EOT)) {
                            System.out.println("Server: Closing socket.");
                            connectionSocket.close();
                        } else if (input.equals(SHUTDOWN)) {
                            isShutdown = true;
                            System.out.println("Server: Shutting down.");
                            connectionSocket.close();
                        }
                    }
                }
                catch (IOException e) {
                    System.out.println(e);
                }
                finally {
                    try {
                        if (connectionSocket != null)
                            connectionSocket.close();
                    }
                    catch (IOException e) {
                        System.out.println("Can't close the socket : " + e);
                    }


                }

            }
        }
}
