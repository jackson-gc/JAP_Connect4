package CST8221;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NetworkManager {
    public boolean isHost;
    private ServerSocket server;
    private Socket client;
    private int port;
    private Panels viewControl;

    public NetworkManager(int prt, boolean host) {
        this.port = prt;
        this.isHost = host;
    
        try {
            if (isHost) {
                server = new ServerSocket(this.port);
                System.out.println("Server is running and waiting for client connections...");
    
                client = server.accept();
                System.out.println("Client connected: " + client);
    
                startReceiverThread();
            } else {
                
                client = new Socket();
                client.connect(new InetSocketAddress("localhost", port), 5000);

                startReceiverThread();
            }
        } catch (SocketTimeoutException ste) {
            System.out.println("Connection timed out. Client socket not properly connected.");
        } catch (IOException e) {
            System.out.print("Error in network manager constructor... \n");
            e.printStackTrace();
        }
    }
    

    public void sendPacket(char header, String body) {
        try {
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            outputStream.writeChar(header);
            outputStream.writeUTF(body);
            System.out.println("packet out: " + header + body);
        } catch (IOException e) {
            System.out.print("Error in send packet... \n");
            e.printStackTrace();
        }
    }

    

    public void receiverSocket(char header, String body) {
        switch (header) {
            case '=':
                // Handle game state synchronization
                break;
            case '@':
                viewControl.sysPanel.sendChat(body);
                // Display message in the GUI or process it accordingly
                break;
            case '?':
                // Handle game move synchronization
                break;
            case '$':
                // Handle game over message
                break;
            default:
                break;
        }
    }
    
    private void startReceiverThread() {
        Thread receiverThread = new Thread(() -> {
            try {
                DataInputStream inputStream = new DataInputStream(client.getInputStream());
                while (true) {
                    char header = inputStream.readChar();
                    String body = inputStream.readUTF();
                    System.out.println("received packet = " + header + body);
                    receiverSocket(header, body);
                }
            } catch (IOException e) {
                System.out.print("Error in receiver thread... \n");
                e.printStackTrace();
            }
        });
        receiverThread.start();
    }


    

    public void killServer(){
        if(this.server != null){
            try {
                this.server.close();
            } catch (IOException e) {
                System.out.print("Error in kill server... \n");
                e.printStackTrace();
            }
        }
    }

    public Socket getClient(){
        return this.client;
    }


    public void updateViewControl(Panels vc){
        this.viewControl = vc;
    }


}
