import sun.net.*;
import java.net.*;
import java.io.*;

public class MClient6 extends Thread
{
	public int port;
	public int group = 1;
	private MulticastSocket socket;
	private boolean threadSuspended = true;

	MClient6(int port, int group)
	{
		try 
		{
			socket = new MulticastSocket(port);
			System.out.println("join " + "ff02::"+group);
			socket.joinGroup(InetAddress.getByName("ff02::"+group));
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
				System.out.println("leave " + "ff02::" + group);
				socket.leaveGroup(InetAddress.getByName("ff02::" + group ));

				group++;
				System.out.println("join ff02::" + group);
				socket.joinGroup(InetAddress.getByName("ff02::" + group));

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