package spazzaneve;

/*
 questa classe crea la parte di interfaccia che permette di effettuare il login invocando il 
 metodo esiste della classe società per controllare se il cliente è registrato

 @author Massimiliano Smillovich

 */

import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame {

	private JPanel content;
	private JTextField text;
	private JPasswordField pass;

	public Login() {

		setTitle("effettuare login");
		setBounds(100, 100, 450, 300);
		content = new JPanel();
		setContentPane(content);
		content.setLayout(null);

		JLabel psw = new JLabel("Password:");
		psw.setBounds(13, 73, 91, 14);
		content.add(psw);

		pass = new JPasswordField();
		pass.setBounds(114, 70, 174, 20);
		content.add(pass);

		JLabel user = new JLabel("Username:");
		user.setBounds(13, 28, 88, 14);
		content.add(user);

		text = new JTextField();
		text.setBounds(111, 25, 174, 20);
		content.add(text);
		text.setColumns(20);

		JButton btn = new JButton("Login");
		btn.setBounds(148, 117, 101, 36);
		content.add(btn);
		btn.addActionListener(new ButtonListener());

		this.setVisible(true);
		setSize(371, 223);

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (Società
					.esiste(text.getText(), (new String(pass.getPassword())))) {
				JOptionPane.showMessageDialog(null, "Accesso effettuato");
				chiudi();

			} else
				JOptionPane.showMessageDialog(null,
						"Accesso negato,controllare i dati o registrarsi");

		}

	}

	private void chiudi() {
		this.dispose();
	}
}
