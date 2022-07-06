import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Recieve {
	
	int count=0;
	FileWriter fw = null ;
    DataInputStream inStream;
    DataOutputStream outStream;
    String LeaderMessage="";
	
	public Recieve(Socket socket) throws IOException
	{
		
		try{
		    inStream=new DataInputStream(socket.getInputStream());
		    outStream=new DataOutputStream(socket.getOutputStream());
		    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		    int number = 1;
		    String name;
		    while(!LeaderMessage.equals("bye"))
		    {
			    name = "partition"+ number +".txt";
		    	File file = new File(name);
		    	if(file.exists())
		    	{
		           counting(name);
		    	}
		    	else
		    	{
		    		file.createNewFile();
		    		counting(name);
		    	}
		    	write_to_file(name);
		    	number++;
		    }

		  }catch(Exception e){
		    System.out.println(e);
		  }
		
	}
	public void counting(String file) throws IOException
	{
		FileReader fr=new FileReader(new File(file));   //reads the file  
	    BufferedReader l=new BufferedReader(fr);  //creates a buffering character input stream    
	    String line; 
	    count=0;
	    while((line=l.readLine())!=null)  
	    {
	      count++;
	    }
	    fr.close();    
	}
	
	public void write_to_file(String name) throws IOException
	{

		    while(!LeaderMessage.equals("bye") && count<15)
		    {
		      LeaderMessage=inStream.readUTF();
		      if(!LeaderMessage.equals("bye") && count<15)
		      {
		    	  //System.out.println(LeaderMessage);
				  fw = new FileWriter(name, true);
		    	  fw.append(LeaderMessage+"\n");
				  fw.close();
		    	  count++;
		      }
		      
		    }
	}

}
