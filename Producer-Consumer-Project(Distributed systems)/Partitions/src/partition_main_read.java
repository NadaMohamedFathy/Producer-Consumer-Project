import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class partition_main_read {
	

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Socket socket=new Socket("localhost",6060);
		DataInputStream inStream=new DataInputStream(socket.getInputStream());
		DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
		outStream.writeUTF("partition");
		while (true) {
			String serverMsg = inStream.readUTF();
			System.out.println("server message received");
			if (serverMsg.equals("receive")) {
				Recieve recieve = new Recieve(socket);
			} else if (serverMsg.equals("send")) {
				System.out.println("Sending");
				Send send = new Send(socket);
			} else {
				System.out.println("Closing Socket");
				socket.close();
				break;
			}
		}
	}

}
