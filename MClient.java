import sun.net.*;
import java.net.*;
import java.io.*;

public class MClient extends Thread
{
	public int port;
	public int group = 1;
	public int sgroup = 1;
	private MulticastSocket socket;
	private boolean threadSuspended = true;

	MClient(int port, int group)
	{
		try 
		{
			socket = new MulticastSocket(port);
			int gid2 = group/256 + 1; 
			System.out.println("join " + "225."+gid2+"."+(group%256)+"."+sgroup);
			socket.joinGroup(InetAddress.getByName("225."+gid2+"."+(group%256)+"."+sgroup));
			//socket.joinGroup(InetAddress.getByName("225.2."+group+"."+sgroup));
			//socket.joinGroup(InetAddress.getByName("225.3."+group+"."+sgroup));
			//socket.joinGroup(InetAddress.getByName("225.4."+group+"."+sgroup));
			//socket.joinGroup(InetAddress.getByName("225.0.0."+group));
			this.port = port;
			this.group = group;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run()
	{
		while (true)
		{
			try 
			{
				susp();
				int gid2 = group/256 + 1; 
				System.out.println("leave " + "225."+gid2+"."+(group%256)+"."+sgroup);
				socket.leaveGroup(InetAddress.getByName("225."+gid2+"."+(group%256)+"."+sgroup));

				int tmp = sgroup;
				sgroup = (sgroup+1) % 256;
				//if (sgroup == 0) sgroup = 1;
				System.out.println("join " + "225."+gid2+"."+(group%256)+"."+sgroup);
				socket.joinGroup(InetAddress.getByName("225."+gid2+"."+(group%256)+"."+sgroup));

				
//				String msg = "Hello_" + group;
				//DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
				//						InetAddress.getByName("225.0."+group+"."+sgroup), port);
//				DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
//										InetAddress.getByName("224.0.0."+group), port);
//				socket.send(hi);
				
				threadSuspended = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}
	
	public void susp() throws Exception
	{
		if (threadSuspended) {
            synchronized(this) {
                while (threadSuspended)
                    wait();
            }
        }
	}
	
	public synchronized void cont() throws Exception
	{
		threadSuspended = false;
        notify();
	}
}