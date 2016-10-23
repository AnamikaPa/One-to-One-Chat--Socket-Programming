import java.io.*;
import java.net.*;
import java.util.Scanner;  
class cclient
{
	public static void main(String[] args)
	{ 
		Scanner s = new Scanner(System.in);
		System.out.print("Enter IP address: ");
		String ip = s.next();
		try{
			// in brackets ip address of server to connect and port number
			Socket soc = new Socket(ip,107);
			System.out.println("Connection Established... "+"\n");
			
			int f=0;
			while(true){
				// to except data from server
				DataInputStream din =new DataInputStream(soc.getInputStream());

				//to get the full line
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.print("Client: ");
				String msgout = br.readLine();

				//for sending data to server
				DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
				dout.writeUTF(msgout);			
				
				if(msgout.equals("bye") || msgout.equals("Bye") || msgout.equals("Bbye") || msgout.equals("bbye")){
					f=1;
					break;
				}
				//accept the data from server
				String msgin=din.readUTF();
				System.out.println("Server: "+msgin+"\n");
				
				dout.flush();
			}
			if(f==1) System.out.println("\n"+"Connection Dismissed.. :)"+"\n");
			soc.close();
		}
		catch(Exception e){
			System.out.println("Error in connection due to "+e);
		}
	}
}