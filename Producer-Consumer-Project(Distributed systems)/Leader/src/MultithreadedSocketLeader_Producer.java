import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class MultithreadedSocketLeader_Producer {
	public static Socket partitionSocket;
	public static   boolean wait=true;
	
  public static void main(String[] args) throws Exception {

	  ServerSocket serverSocket = new ServerSocket(6060);
	  Socket socket;
	  while (true) {
		  socket = serverSocket.accept();
		  DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		  DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		  String clientMsg = dataInputStream.readUTF();
		  if (clientMsg.equals("partition")) {
			  System.out.println("in partition code");
			  partitionSocket=socket;
		  } else if (clientMsg.equals("producer")) {
			  Leader_Producer leader_producer = new Leader_Producer(socket);
			  leader_producer.start();
		  } else if (clientMsg.equals("consumer")) {
			  Leader_Consumer leader_consumer = new Leader_Consumer(socket);
			  leader_consumer.start();
		  } else {
			  socket.close();
			  break;
		  }
	  }
  }
}