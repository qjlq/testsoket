//server side programming

import java.net.*;
import java.io.*;

public class serverTest {

    public static void main(String[] args) {
        try {
            boolean flag = true;
            Socket clientSocket = null;

            ServerSocket serverSocket = new ServerSocket(8888);

            // serverSocket.bind(new InetSocketAddress("localhost", 8888));

            System.out.println("Server listen on" + serverSocket.getInetAddress() + serverSocket.getLocalPort());

            while (flag) {
                clientSocket = serverSocket.accept();
                DataInputStream is = new DataInputStream(
                        new BufferedInputStream(clientSocket.getInputStream()));

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                PrintStream os = new PrintStream(
                        new BufferedOutputStream(clientSocket.getOutputStream()));
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            String name = br.readLine();
                            String inputLine;
                            os.println("your name:" + name);
                            while ((inputLine = br.readLine()) != null) {
                                if (inputLine.equals("Stop!")) {
                                    break;
                                }
                                os.println("client: " + name + "said: " + inputLine);
                                System.out.println("client: " + name + "said: " + inputLine);
                                os.flush();
                            }
                        } catch (IOException e) {
                            System.err.println("Exception:" + e);
                        } 
                    }
                });
                thread.start();
            }
            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Exception:" + e);
        }
    }
}