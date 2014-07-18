package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientData {
	private static String serverIP = null;
	private static String serverPort = "4548";

	public static String getServerIP() throws IOException {
		if (serverIP == null) {
			serverIP = getIP();
		}
		return serverIP;
	}

	public static String getServerPort() {
		return serverPort;
	}

	private static String getIP() throws IOException {
		URL website = null;
		try {
			website = new URL("http://www.andrfas.pp.ua/ip.txt");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader rbc = null;
		try {
			rbc = new BufferedReader(
					new InputStreamReader(website.openStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return rbc.readLine();
	}
}
