package messenger;

/*
 * just messanger
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class ServerController {
	MessangerGUI messanger = new MessangerGUI();
	JButton button = messanger.getButton();

	public ServerController() {
		/*
		 * GI have been already initialized
		 */
		messanger.addSystemMessage("Initializing server...");
		try {
			// ServerData.initServerData();
			// Server.startServer();
			Server.startLocalServer();
		} catch (IOException e) {
			messanger.addSystemMessage("Can't create server");
		}
		messanger.addSystemMessage("Server started");
		while (true) {
			messanger.addSystemMessage("Waiting for user...");
			try {
				Server.connectUser();
			} catch (IOException e) {
				messanger.addSystemMessage("Can't connect user");
				e.printStackTrace();
			}
			messanger.addSystemMessage("User connected");

			addListeners();
			while (true) {
				try {
					messanger.addGuestMessage(Server.getMessage());
				} catch (IOException e) {
					messanger.addSystemMessage("Can't get message");
					break;
				}
			}
			messanger.addSystemMessage("User disconnected.");
		}
	}

	public static void main(String[] args) {
		new ServerController();
	}

	private void addListeners() {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String text = messanger.getAreaText();
				if (!text.isEmpty()) {
					try {
						Server.sendMessage(text);
						messanger.addUserMessage(text);
					} catch (IOException e1) {
						messanger.addSystemMessage("Can't send message");
					}
				}
			}
		});
	}

}
