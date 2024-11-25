package app;

import javax.swing.JOptionPane;

public class MessageFactory {
	public static void inform(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static final int YES = JOptionPane.YES_OPTION;
	public static int confirm(String message) {
		return JOptionPane.showConfirmDialog(null, message, "Message", JOptionPane.YES_NO_OPTION);
	}
}
