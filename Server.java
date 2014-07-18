package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Server {
	static ServerSocket server = null;
	static Socket socket = null;

//	static PrintWriter out = null;
	static OutputStream out = null;
	static BufferedReader is = null;
	
	private static String messageStr = null;

	public static void startServer() throws IOException {
		try {			
			server = new ServerSocket(Integer.parseInt(ServerData.getPort()));
		} catch (Exception e) {
			throw new IOException();
		}
	}
	
	public static void startLocalServer() throws IOException  {
		try {			
			server = new ServerSocket(Integer.parseInt(ServerData.getPort()));
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static void connectUser() throws IOException {
		socket = null;
		try {			
			while (socket == null) {
				socket = server.accept();
			}
//			out = new PrintWriter(socket.getOutputStream());
			out = socket.getOutputStream();
			is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			throw e;
		}
	}

	public static String getMessage() throws IOException {
		try {
			if ((messageStr = is.readLine())!= null) {
				return messageStr;				
			} else throw new IOException();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException ignore) {
				/* NOP */
			}
			throw e;
		}
	}

	public static void sendMessage(String text) throws IOException {
		out.write(text.getBytes(Charset.forName("UTF-16")));
		System.out.println(text);
		out.flush();
	}
}

