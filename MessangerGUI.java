package messenger;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class MessangerGUI extends JFrame {
	private JButton button =  new JButton("Send");
	private JTextArea inputArea = null;
	private JTextArea msgArea = null;
	private JScrollPane msgScrollPane = null;
	private JPanel mesPanel = null;
	GridBagConstraints constr = new GridBagConstraints();
	private int gridRow = 1;
	
	private final Color messageColor = new Color(0.8f, 0.9f, 1.0f);
	private final Color systemMessageColor = new Color(0.8f, 0.8f, 0.8f);
	private final Insets messageInsets = new Insets(5, 0, 0, 0);
	private String emptyStr = " ";

	private static final long serialVersionUID = 1L;

//	public static void main(String[] args) {
//		new MessangerGUI();
//	}
	
	public MessangerGUI() {
		super();
		makeShowFrame();
	}

	private void makeShowFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(270, 450));
		addComponents(getContentPane());
		pack();
		setVisible(true);
	}

	private void addComponents(final Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		mesPanel = new JPanel();
		mesPanel.setLayout(new GridBagLayout());
		msgScrollPane = new JScrollPane(mesPanel);
		msgScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		msgScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(msgScrollPane);		
		
		inputArea = new JTextArea(5, 20);
		inputArea.setLineWrap(true);
		inputArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(inputArea);
		scrollPane.setMinimumSize(new Dimension(264, 100));
		scrollPane.setPreferredSize(new Dimension(264, 90));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scrollPane);
		
		button.setMinimumSize(new Dimension(235, 30));
		button.setPreferredSize(new Dimension(240, 30));
		button.setMaximumSize(new Dimension(1300, 40));
		button.setAlignmentX(CENTER_ALIGNMENT);
		pane.add(button);
		
		constr.fill = GridBagConstraints.HORIZONTAL;

		msgArea = new JTextArea(emptyStr);
		msgArea.setPreferredSize(new Dimension(80, 0));
		constr.weightx = 0.5;
		constr.gridy = 0;
		constr.gridx = 0;
		mesPanel.add(msgArea, constr);
		
		msgArea = new JTextArea(emptyStr);
		msgArea.setPreferredSize(new Dimension(80, 0));
		constr.gridx = 1;
		mesPanel.add(msgArea, constr);
		
		msgArea = new JTextArea(emptyStr);
		msgArea.setPreferredSize(new Dimension(80, 0));
		constr.gridx = 2;
		mesPanel.add(msgArea, constr);		
	}
	
	public JButton getButton() {
		return button;
	}
	
	public String getAreaText() {
		return inputArea.getText();
	}
	
	public void addUserMessage(String text) {
		msgArea = new JTextArea(emptyStr);
		msgArea.setOpaque(false);
		msgArea.setEditable(false);
		constr.weightx = 0.5;
		constr.gridy = gridRow;
		constr.gridx = 0;
		constr.gridwidth = 1;
		constr.insets = messageInsets;
		mesPanel.add(msgArea, constr);
		
		msgArea = new JTextArea(text);
		msgArea.setOpaque(true);
		msgArea.setBackground(messageColor);		
		msgArea.setLineWrap(true);
		msgArea.setEditable(false);
		constr.gridy = gridRow++;
		constr.gridx = 1;
		constr.gridwidth = 2;
		mesPanel.add(msgArea, constr);
		
		clearArea();
		redrawFrame();		
	}
	
	public void addGuestMessage(String text) throws IOException {
		msgArea = new JTextArea(text);
		msgArea.setOpaque(true);
		msgArea.setBackground(messageColor);		
		msgArea.setLineWrap(true);
		msgArea.setEditable(false);
		constr.weightx = 0.5;
		constr.gridy = gridRow;
		constr.gridx = 0;
		constr.gridwidth = 2;
		constr.insets = messageInsets;		
		mesPanel.add(msgArea, constr);
		
		msgArea = new JTextArea(emptyStr);
		msgArea.setOpaque(false);
		msgArea.setEditable(false);
		constr.gridy = gridRow++;
		constr.gridx = 2;
		constr.gridwidth = 1;
		mesPanel.add(msgArea, constr);
		
		clearArea();
		redrawFrame();
	}
	
	public void addSystemMessage(String text) {
		msgArea = new JTextArea(text);
		msgArea.setOpaque(true);
		msgArea.setBackground(systemMessageColor);
		msgArea.setEditable(false);
		//TODO add center aligment
		constr.gridy = gridRow++;
		constr.gridx = 0;
		constr.gridwidth = 3;
		mesPanel.add(msgArea, constr);		
		
		clearArea();
		redrawFrame();
	}
	
	private void clearArea() {
		inputArea.setText(null);
	}
	
	private void redrawFrame() {
		getContentPane().validate();
		msgScrollPane.getViewport().scrollRectToVisible(msgArea.getBounds());		
	}
}

//"<html><body style='width:130px; padding:5px 0 5px 5px'><pre>"+text+"</pre></body></html>"
