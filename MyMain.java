import sun.net.*;
import java.net.*;
import java.io.*;

public class MyMain
{
	public static int DELAY = 50;
	public static int NUM_CLIENT = 256;
	public static int BASE_PORT = 9000;
	public static MClient[] mclient = new MClient[4096];
	public static void main(String[] args) 
	{
		try {
			if (args.length > 0)
				NUM_CLIENT = Integer.parseInt(args[0]);
			if (args.length > 1)
				DELAY = Integer.parseInt(args[1]);

			System.out.println("Start " + NUM_CLIENT + " clients with " + DELAY + "ms delay");

			for (int i=0; i<NUM_CLIENT; i++) {
				mclient[i] = new MClient(i + BASE_PORT, i);
				mclient[i].start();
				Thread.sleep(DELAY);
			}

			while (true) {
				int c = System.in.read();
				if (c == 's') {
					for (int i=0; i<NUM_CLIENT; i++) {
						mclient[i].cont();
						Thread.sleep(DELAY);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}