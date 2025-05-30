import java.net.*;
import java.io.*;

public class Dclient{
	public static DatagramSocket ds;
	static byte b[]=new byte[255];
	static int c_port=1001, s_port=782;

public static void main(String args[]){
try{
ds = new DatagramSocket(s_port);
System.out.println("Its Waiting Bro..");
while(true){
	DatagramPacket dp=new DatagramPacket(b,b.length);
	ds.receive(dp);
	String msg=new String(dp.getData(),0,dp.getLength());
	if(msg.equals("end"))
	break;
	else
	System.out.println(msg);
    }
}
catch(Exception e){
	System.out.println(e);
}
}
}
