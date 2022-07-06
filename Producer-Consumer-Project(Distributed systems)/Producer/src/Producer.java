import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


 public class Producer {
	 
	 public Producer() throws UnknownHostException, IOException
	 {
		 Socket socket=new Socket("localhost",6060);
		 DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
		 outStream.writeUTF("producer");
		 outStream.writeUTF("receive");
         Gui p =new Gui(outStream);
	 }
	 /*JTextField txtInput = new JTextField("");
		@Override
	    public void actionPerformed(ActionEvent e)
		{
			//Producer_Gui p =new Producer_Gui();
			try{
			    Socket socket=new Socket("localhost",6060);
			    DataInputStream inStream=new DataInputStream(socket.getInputStream());
			    DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
			    //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			    String ProducerMessage="",LeaderMessage="";
			    while(!ProducerMessage.equals("bye")){
			      //System.out.println("Enter Message :");
			      ProducerMessage=txtInput.getText();//JOptionPane.showInputDialog(null,"To end press 'bye'"+"\r\nEnter Message :");//br.readLine();
			      outStream.writeUTF(ProducerMessage);
			      outStream.flush();
			      LeaderMessage=inStream.readUTF();
			      System.out.println(LeaderMessage);
			    }
			    outStream.close();
			    outStream.close();
			    socket.close();
			  }catch(Exception l){
			    System.out.println(l);
			  }
		}*/

	}
 