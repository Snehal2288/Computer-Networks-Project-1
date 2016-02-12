import java.io.*;
import java.net.*;
public class Client
{
	public static void main(String[] args)throws IOException
	{
		//Connect with the server
		Socket clientsocket = new Socket("127.0.0.1",2222);
		System.out.println("Client : Server is connected ");

		int bytesinafile=0;
		String fileinputsource = "D:\\Santa Clara University\\COEN233\\Programming Assignment\\Client_Server\\Client.txt";  
		try
		{
			//read the file and store in an array
			PrintWriter printwriter = new PrintWriter(clientsocket.getOutputStream(), true);  
			FileInputStream fileinputstream = new FileInputStream(fileinputsource);       
			byte[] clientarray = new byte[fileinputstream.available()];
			System.out.println("Bytes in a file :" +fileinputstream.available());
			bytesinafile = fileinputstream.read(clientarray);       
			System.out.println("Number of bytes read: "+bytesinafile);
			char[] transferarray = new char[bytesinafile];

			int numberofparts=bytesinafile/5;
			if((bytesinafile%10)!= 0)
				numberofparts++;
			printwriter.println(numberofparts);
			printwriter.println(bytesinafile%10);
			printwriter.println(bytesinafile);
			System.out.println("File will be divided into "+bytesinafile/10+ " parts");
			int temp=0;

			for(int p = 0 ; p < numberofparts ; p++)
			{		
				if(p==(numberofparts-1))
					temp=bytesinafile%10;
				else
					temp=10;
				int h=p*10;
				for(int k=0;k<temp;k++)
				{
					transferarray[k]=(char)(clientarray[h+k]);
				}  
				String transfer=new String(transferarray);
				printwriter.println(transfer);
			}
			fileinputstream.close();
		}
		catch(Exception ex)
		{
		}
		clientsocket.close();
	}

}
