import java.net.*;
import java.io.*;

public class Dserver{
        public static DatagramSocket ds;
        static byte b[]=new byte[255];
        static int c_port=1001, s_port=782;

public static void main(String args[]){
try{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter Data");
	InetAddress ia = InetAddress.getByName("localhost");
	ds = new DatagramSocket();
while(true) 
	{
		String s=br.readLine();
		if(s.equals("end"))
		{
			b=s.getBytes();
			ds.send(new DatagramPacket(b,b.length,ia,s_port));
		}
		b=s.getBytes();
		ds.send(new DatagramPacket(b,b.length,ia,s_port));
		}
}
catch(Exception e){
System.out.println(e);
}
}
}