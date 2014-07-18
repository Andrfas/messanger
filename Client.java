package messenger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client {
	static Socket socket = null;

	static PrintWriter out = null;
//	static BufferedReader inp = null;
	static InputStreamReader inp = null;

	// public static void main(String[] args) {
	// try {
	// socket = new Socket("localhost",
	// Integer.parseInt(ClientData.getServerPort()));
	// System.out.println("Connected.");
	// new StreamOut().start();
	// new StreamIn().start();
	// } catch (Exception e) {
	// // e.printStackTrace();
	// System.out.println("There no connection to the server.");
	// }
	// }

	public static void makeConnection() throws IOException {
		socket = new Socket("localhost", Integer.parseInt(ClientData
				.getServerPort()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		 inp = new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-16"));
//		inp = socket.getInputStream();
	}

	public static void sendMessage(String text) throws IOException {
		try {
			out.println(text);
			out.flush();
		} catch (Exception e) {
			try {
				socket.close();
			} catch (IOException e1) {
				throw new IOException();
			}
		}
	}

	public static String getMessage() throws IOException {
		try {
			char[] b = new char[64*1024];
			inp.read(b);
			return new String(b);
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException ignore) {
				/* NOP */
			}
			throw new IOException();
		}
	}

	// public static class StreamIn extends Thread {
	// public void run() {
	// BufferedReader inp = null;
	// while (true) {
	// try {
	// inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	// System.out.println(inp.readLine());
	// } catch (Exception e) {
	// try {
	// socket.close();
	// } catch (Exception e1) {
	// //e1.printStackTrace();
	// } finally {
	// System.out.println("Server disconnected.");
	// break;
	// }
	// }
	// }
	// }
	// }

	// public static class StreamOut extends Thread {
	// public void run() {
	// BufferedReader inpKb = new BufferedReader(new
	// InputStreamReader(System.in));
	// PrintWriter out = null;
	//
	// while (true) {
	// try {
	// out = new PrintWriter(socket.getOutputStream());;
	// out.println(inpKb.readLine());
	// out.flush();
	// } catch (Exception e) {
	// // e.printStackTrace();
	// try {
	// socket.close();
	// } catch (Exception e1) {
	// // e1.printStackTrace();
	// } finally {
	// System.out.println("Server disconnected.");
	// break;
	// }
	//
	// }
	// }
	// }
	// }

}
