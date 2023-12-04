
//server side programming

import java.net.*;
import java.io.*;

public class serverTest {

    public static void main(String[] args) {
        try {
            boolean flag = true;
            Socket clientSocket = null;
            String inputLine;

            ServerSocket serverSocket = new ServerSocket();

            serverSocket.bind(new InetSocketAddress("localhost", 8888));

            System.out.println("Server listen on" + serverSocket.getInetAddress() + serverSocket.getLocalPort());

            while (flag) {
                clientSocket = serverSocket.accept();
                DataInputStream is = new DataInputStream(
                        new BufferedInputStream(clientSocket.getInputStream()));
                
                BufferedReader br = new BufferedReader(new InputStreamReader(is));


                PrintStream os = new PrintStream(
                        new BufferedOutputStream(clientSocket.getOutputStream()));

                while ((inputLine = br.readLine()) != null) {
                    if (inputLine.equals("Stop!")) {
                        flag = false;
                        break;
                    }
                    os.println(inputLine + "1234");
                    System.out.println("client: " + inputLine);
                    os.flush();
                }
                os.close();
                is.close();
                clientSocket.close();
            }
            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Exception:" + e);
        }
    }
}