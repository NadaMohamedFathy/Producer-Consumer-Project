import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Leader_Producer extends Thread {

    Socket serverProducer;


    Leader_Producer(Socket inSocketProducer) {
        serverProducer = inSocketProducer;
    }



    public synchronized void run() {

        DataInputStream inStream = null;
        DataOutputStream outStream = null;

        try {
            System.out.println("In Producer");
            inStream = new DataInputStream(serverProducer.getInputStream());
            outStream = new DataOutputStream(serverProducer.getOutputStream());
            String clientMessage = "", serverMessage = "Message sent..";
            ///////////////////////partition
            Socket partitionSocket = MultithreadedSocketLeader_Producer.partitionSocket;
            DataInputStream inStreamP = new DataInputStream(partitionSocket.getInputStream());
            DataOutputStream outStreamP = new DataOutputStream(partitionSocket.getOutputStream());
            while (true) {
                System.out.println("waiting for producer request");
                /*while (MultithreadedSocketLeader_Producer.wait)
                {
                    wait();
                }*/
                String producerRequest = inStream.readUTF();
                outStreamP.writeUTF(producerRequest);
                //clientMessage = inStream.readUTF();
                while (!clientMessage.equals("bye")) {
                    clientMessage = inStream.readUTF();
                    outStreamP.writeUTF(clientMessage);
                    outStreamP.flush();
                    outStream.writeUTF(serverMessage);
                    outStream.flush();

                }
                MultithreadedSocketLeader_Producer.wait=true;
                Leader_Consumer leader_consumer = new Leader_Consumer(null);
                leader_consumer.notifyAll();
            }
            //////////////////
        } catch (Exception ex) {
            System.out.println(ex);
            try {
                inStream.close();
                outStream.close();
                serverProducer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println("Producer -" + " exit!! ");
        }

    }
}
