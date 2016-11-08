// Import some needed classes
import sun.net.*;
import java.net.*;
import java.io.*;

public class MulJoin
{
	public static void main(String[] args) 
	{
		try {
			int i;
			MulticastSocket s = new MulticastSocket(5000);
			while (true) {
				int c = System.in.read();
				if (c == 'j') {
					for (i=1; i<32; i++)
						s.joinGroup(InetAddress.getByName("225.0.0."+i));
					System.out.println("join 225.0.0.1~225.0.0." + (i-1));
				}
				else if (c == 'l') {
					for (i=1; i<32; i++)
						s.leaveGroup(InetAddress.getByName("225.0.0."+i));
					System.out.println("leave 225.0.0.1~225.0.0." + (i-1));
					//s.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}