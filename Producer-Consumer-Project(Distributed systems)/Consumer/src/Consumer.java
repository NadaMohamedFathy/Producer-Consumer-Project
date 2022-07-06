import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Consumer {

    public static JTextArea j = new JTextArea();
    public static ArrayList<String> messages = new ArrayList<String>();

    public Consumer() throws IOException {
        Gui gui = new Gui(j);
        Socket socket = null;
        DataInputStream inStream = null;
        DataOutputStream outStream = null;
        try {
            socket = new Socket("localhost", 6060);
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
            outStream.writeUTF("consumer");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String LeaderMessage = "";
                for (int i = 0; i < 1e7; i++) {
                }
                outStream.writeUTF("send");
                System.out.println("sent") ;
                messages.clear();
                while (!LeaderMessage.equals("The End")) {
                    LeaderMessage = inStream.readUTF();
                    if (!LeaderMessage.equals("The End")) {
                        if (messages.size() <= 0) {
                            j.append("Message :" + LeaderMessage + "\r\n");
                            messages.add(LeaderMessage);
                        }
                        else {
                            if (!messages.contains(LeaderMessage)) {
                                j.append("Message :" + LeaderMessage + "\r\n");
                                messages.add(LeaderMessage);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            outStream.close();
            outStream.close();
            socket.close();
            System.out.println(e);
        }
    }

}
