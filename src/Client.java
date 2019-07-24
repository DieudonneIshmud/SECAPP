

// client class

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class Client extends Thread
{ 
	static DataInputStream dis;
	static DataOutputStream dos;
	static Thread writeThread;
	static Thread readThread; 
	
	public static void main(String[] args) throws IOException 
	{ 
		try
		{ 
			Scanner scn = new Scanner(System.in); 
			
			// getting localhost ip 
			InetAddress ip = InetAddress.getByName("localhost"); 
	
			// establish the connection with server port 6666
			Socket s = new Socket("137.158.160.249", 6666); 
	
			// obtaining input and out streams 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	
			
			//Registering the client with the server
			System.out.println("Enter \"ID Password\" in that format: ");
			String id = scn.next();
			String password = scn.next(); // will be used when encryption is added.
			dos.writeUTF(id);

			
			// the following loop performs the exchange of 
			// information between client and server
			while (true) 
			{ 
				System.out.println("Enter 1 to see list of connected clients: ");
				System.out.println("Enter 2 to talk to a connected client: ");
				System.out.println("Enter 3 to exit: ");			
				String option = scn.next();
				
				if(option.equals("1"))
				{
					// view list of connected clients from server
					
					dos.writeUTF("ListActiveUsers");
					String list = dis.readUTF();
					System.out.println(list);
				}
				else if(option.equals("2"))
				{					
					// add read and write thread. To allow users to exchange messages
					
					dos.writeUTF("ExchangeMessage");
					initReadThread(dis, dos);
					initWriteThread(dis, dos);	
				}
				else if(option.equals("3"))
				{
					// disconnect the client from server
					
					dos.writeUTF("disconnect");
					System.out.println("Connection closed : " + s);
					break;
				}
				else
					System.out.println(" Please enter 1, 2, or 3");
			} 
			
			// closing resources 
			scn.close(); 
			dis.close(); 
			dos.close(); 
		}catch(Exception e){ e.printStackTrace();} 
	} 
	
	
	//read from the input stream
	public static void initReadThread(DataInputStream dis, DataOutputStream dos)
	{
		readThread = new Thread() 
		{
            @Override
            public void run() 
            {
                try
                {
                    String line;
                    while( (line=dis.readUTF()) != null)
                    {
                    	if(line.equals("exit"))
                    		break;
                    	else
                    		System.out.println(line);
                    }
                } catch(IOException e) {e.printStackTrace();}
            }
        };
        
        readThread.start();
	}
	
	//write to the output stream
	public static void initWriteThread(DataInputStream dis, DataOutputStream dos) throws InterruptedException
	{
		writeThread = new Thread() 
		{
            @Override
            public void run() 
            {
                try
                {
                	System.out.println("Enter \"id, message\" to chat or \"exit\" to quit");
                    while(true) 
                    {
                    	Scanner scanner = new Scanner(System.in);
    					String message = scanner.nextLine();
    					if(message.contains("exit"))
    					{
    						dos.writeUTF(message);
    						break;
    					}
    					else
    						dos.writeUTF(message);
                    }
                } catch(IOException e) { e.printStackTrace();}
            }
        };
        
        writeThread.start();
		writeThread.join();
	}
} 
