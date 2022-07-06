import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Send {
	

    DataInputStream inStream;
    DataOutputStream outStream;
	
	public Send(Socket socket) throws UnknownHostException, IOException
	{
		System.out.println("In Send");
		try{
		    inStream=new DataInputStream(socket.getInputStream());
		    outStream=new DataOutputStream(socket.getOutputStream());
		    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		    int number = 1;
		    while(true)
		    {
		    	String name = "partition"+number+".txt";
		    	File file = new File(name);
		    	if(file.exists())
		    	{
		    		
		           sending(name);
		          
		    	}
		    	else
		    	{
		    		break;
		    	}
		    	number++;
		    }
		    outStream.writeUTF("The End");
		  }catch(Exception e){
		    System.out.println(e);
		  }
		
	}
	public void sending(String name) throws IOException
	{
		FileReader fr=new FileReader(new File(name)); //reads the file  
	    BufferedReader l=new BufferedReader(fr);  //creates a buffering character input stream    
	    String line="";  
	    while((line=l.readLine())!=null)  
	    {
	      outStream.writeUTF(line);
	      outStream.flush();
	    }
	    fr.close();    
	}

}
