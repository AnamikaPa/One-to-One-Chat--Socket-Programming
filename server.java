import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.lang.*;
class server
{
	public static void main(String[] args)
	{  
		try{
			//Server require its own socket
			ServerSocket s1 = new ServerSocket(107);
			System.out.println("Waiting for client....");
			
			//normal socket is required to accept the incoming request to socket s1
			Socket ss = s1.accept();
			System.out.println("Connection Established...."+'\n');
			
			int f=0;
			while(true){
				// to except data from client
				DataInputStream din =new DataInputStream(ss.getInputStream());
				
				String msgin = din.readUTF();
				if(msgin.equals("bye") || msgin.equals("Bye") || msgin.equals("Bbye") || msgin.equals("bbye")){
					f=1;
					break;
				}
				System.out.println("Client: "+ msgin);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				// Taking message from Server
				System.out.print("Server: ");
				String msgout = br.readLine();
				System.out.println("");
				
				//for sending data to client
				DataOutputStream dout=new DataOutputStream(ss.getOutputStream());
				dout.writeUTF(msgout);
				
				dout.flush();
			}
			if(f==1)System.out.println("Client dismissed the Connection.." + "\n");
			ss.close();
		}
		catch(Exception e){
			System.out.println("Error in connection due to "+e);
		}
	}	
}
	
