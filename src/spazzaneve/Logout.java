package spazzaneve;

/*
 questa classe permette ad un utente di fare il logout 

 @author Massimiliano Smillovich

 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Logout extends JFrame {

	JPanel content;

	public Logout() {
		setTitle("Messaggio");
		setBounds(100, 100, 450, 300);
		content = new JPanel();
		setContentPane(content);
		content.setLayout(null);
		JOptionPane.showMessageDialog(null, "Arrivederci!");

	}

}