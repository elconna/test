// Import some needed classes
import sun.net.*;
import java.net.*;
import java.io.*;

public class MGroup
{
	public static void main(String[] args) 
	{
		try {
			int i;
			MulticastSocket s = new MulticastSocket(5000);
			s.joinGroup(InetAddress.getByName("225.0.0.1"));
			s.joinGroup(InetAddress.getByName("225.128.0.1"));
			s.joinGroup(InetAddress.getByName("227.0.0.1"));
			s.joinGroup(InetAddress.getByName("227.128.0.1"));
			Thread.sleep(10);
			s.leaveGroup(InetAddress.getByName("225.0.0.1"));
			s.leaveGroup(InetAddress.getByName("225.128.0.1"));
			s.leaveGroup(InetAddress.getByName("227.0.0.1"));
			s.leaveGroup(InetAddress.getByName("227.128.0.1"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}