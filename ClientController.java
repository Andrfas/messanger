package messenger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class ClientController {
	MessangerGUI messanger = new MessangerGUI();
	JButton button = messanger.getButton();

	/*
	 * GUI have already been created
	 */
	public ClientController() {
		messanger.addSystemMessage("Connection...");
		connect();
		messanger.addSystemMessage("Connection occurred");
		addListeners();
		while (true) {
			try {
				messanger.addGuestMessage(Client.getMessage());
			} catch (IOException e) {
				messanger.addSystemMessage("Can't add guest message");
				System.out.println("aaa");
				break;
			}
		}
	}

	public static void main(String[] args) {
		new ClientController();
	}

	private void connect() {
		while (true) {
			try {
				Client.makeConnection();
				break;
			} catch (IOException ignore) {
				/*NOP*/
			}
		}
	}

	private void addListeners() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = messanger.getAreaText();
				if (!text.isEmpty()) {
					try {
						Client.sendMessage(text);
						messanger.addUserMessage(text);
					} catch (IOException e1) {
						messanger.addSystemMessage("Can't add user message.");
					}
				}
			}

		});
	}

}
