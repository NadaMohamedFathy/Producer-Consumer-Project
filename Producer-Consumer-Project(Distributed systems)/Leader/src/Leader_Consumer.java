import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Leader_Consumer extends Thread {

    Socket serverConsumer;


    Leader_Consumer(Socket inSocketConsumer) {
        serverConsumer = inSocketConsumer;

    }

    public synchronized void run() {
        DataInputStream inStream=null;
        DataOutputStream outStream=null;
        try {
            System.out.println("In Consumer");
            inStream = new DataInputStream(serverConsumer.getInputStream());
            outStream = new DataOutputStream(serverConsumer.getOutputStream());
            while (true) {
                String Message = "";
                ///////////////////////partition
                Socket partitionSocket = MultithreadedSocketLeader_Producer.partitionSocket;
                DataInputStream inStreamP = new DataInputStream(partitionSocket.getInputStream());
                DataOutputStream outStreamP = new DataOutputStream(partitionSocket.getOutputStream());
                System.out.println("waiting for consumer request");
                while (!MultithreadedSocketLeader_Producer.wait)
                {
                    wait();
                }
                String consumerRequest = inStream.readUTF();
                outStreamP.writeUTF(consumerRequest);
                while (!Message.equals("The End")) {
                    Message = inStreamP.readUTF();
                    outStream.writeUTF(Message);
                    //outStream.flush();
                }
                MultithreadedSocketLeader_Producer.wait=false;
                Leader_Producer leader_producer=new Leader_Producer(null);
                leader_producer.notifyAll();
            }
            //////////////////
        } catch (Exception ex) {
            try {
                inStream.close();
                outStream.close();
                serverConsumer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(ex);

        } finally {
            System.out.println("Client -" + " exit!! ");
        }
    }

}
