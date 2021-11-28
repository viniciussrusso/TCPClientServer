package clientpackage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vinicius Russo <wwww.github.com/viniciussrusso>
 */
public class TCPClient {

    private static InetAddress host;  // To use localhost

    public static void main(String[] args) throws Exception {

        try {

            host = InetAddress.getLocalHost(); // To get localhost address

            Socket socket = new Socket(host, 1234);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            //Set up stream for keyboard entry...
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "", serverMessage = "";

            while (!clientMessage.equals("CLOSE")) {
                System.out.println("Enter your message to the server: ");
                clientMessage = userEntry.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                serverMessage = inStream.readUTF();
                System.out.println(serverMessage);
                System.out.println("");
            }
            outStream.close();
            outStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
