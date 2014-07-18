package messenger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.net.ftp.FTPClient;

public class ServerData {
	private static String ftpAdress = "31.170.164.135";
	private static int ftpPort = 21;
	private static String ftpLogin = "u442393473";
	private static String ftpPassword = "59722795";
	private static String serverPort = "4548";
	private static String serverIP = null;
	private static FTPClient ftpClient = null;
	private static File file = null;
	private static FileWriter wrt = null;
	private static FileInputStream fileInputStream = null;
	
	public ServerData() {
		serverIP = getCurrentIP();
	}
	
	public static void initServerData() throws IOException {
		serverIP = getCurrentIP();
		writeIPtoFTP();
	}
	
	public static String getPort() {
		return serverPort;
	}
		
	public static void writeIPtoFTP() throws IOException {
		System.out.println("aAAAA");
		try {
			file = new File("ip.txt");
			System.out.println("aAAAA");
			// ������, ����������� ����������� ������ � ����
			wrt = new FileWriter(file);

			CharSequence cq = serverIP;

			wrt.append(cq);
			cq = "\r\n4548";
			wrt.append(cq);
			// ���������� ����� � ���������� ���� ������ � ����
			wrt.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wrt.close();
		}

		try {
			ftpClient = connectToFTP(ftpAdress, ftpPort, ftpLogin,
					ftpPassword);
			fileInputStream = new FileInputStream(file);
			boolean isUploaded = ftpClient.storeFile("ip.txt", fileInputStream);
			System.out.println("Is uploaded: "+isUploaded);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileInputStream.close();
			ftpClient.logout();
			ftpClient.disconnect();
		}
	}
	
	
	private static FTPClient connectToFTP(String address, int port,
			String username, String password) throws IOException {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(address, port);
		ftpClient.login(username, password);

		return ftpClient;
	}
	
	private static String getCurrentIP() {
        String result = null;
        try {
            BufferedReader reader = null;
            try {
                URL url = new URL("http://myip.by/");
                InputStream inputStream = null;
                inputStream = url.openStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder allText = new StringBuilder();
                char[] buff = new char[1024];
 
                int count = 0;
                while ((count = reader.read(buff)) != -1) {
                    allText.append(buff, 0, count);
                }
// ������ ���������� IP ����� ��������� ��� 
// <a href="whois.php?127.0.0.1">whois 127.0.0.1</a> 
                Integer indStart = allText.indexOf("\">whois ");
                Integer indEnd = allText.indexOf("</a>", indStart);
 
                String ipAddress = new String(allText.substring(indStart + 8, indEnd));
                if (ipAddress.split("\\.").length == 4) { // ����������� (��������) 
                //�������� ��� ��������� ����� �������� ip �������.
                    result = ipAddress;
                }
            } catch (MalformedURLException ex) {
                 ex.printStackTrace();
            } catch (IOException ex) {
                 ex.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
