package spazzaneve;

/*
 questa classe permette ad un utente esterno di registrarsi e verrà salvato sia tra i clienti che
 tra i clienti nuovi

 @author Massimiliano Smillovich

 */
import java.awt.event.*;
import javax.swing.*;

public class Registrazione extends JFrame {

	private JPanel content;
	private JTextField nome;
	private JPasswordField password;
	private JTextField indirizzo;

	public Registrazione() {

		setTitle("Effettuare Registrazione");
		setBounds(200, 200, 550, 400);
		content = new JPanel();
		setContentPane(content);
		content.setLayout(null);

		JLabel ind = new JLabel("Indirizzo:");
		ind.setBounds(13, 80, 88, 14);
		content.add(ind);

		indirizzo = new JTextField();
		indirizzo.setBounds(139, 77, 174, 28);
		content.add(indirizzo);

		JLabel name = new JLabel("Nome:");
		name.setBounds(13, 33, 78, 14);
		content.add(name);

		nome = new JTextField();
		nome.setBounds(139, 30, 174, 28);
		content.add(nome);
		nome.setColumns(20);

		JLabel pass = new JLabel("Password:");
		pass.setBounds(13, 125, 91, 14);
		content.add(pass);

		password = new JPasswordField();
		password.setBounds(139, 122, 174, 28);
		content.add(password);
		password.setColumns(20);

		JButton btn = new JButton("Registrati");
		btn.setBounds(170, 177, 108, 42);
		content.add(btn);
		btn.addActionListener(new ButtonListener());

		this.setVisible(true);
		setSize(444, 288);

	}

	/*
	 * se il cliente non è già noto alla società,viene aggiunto con 2 metodi,sia
	 * ai clienti che ai clienti nuovi
	 */

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (nome.getText().length() != 0
					&& indirizzo.getText().length() != 0
					&& password.getPassword().length != 0) {

				if (Società.controlla(nome.getText(), indirizzo.getText(),
						(new String(password.getPassword())))) {

					JOptionPane.showMessageDialog(null,
							"Registrazione effettuata");
					aggiungi(nome.getText(),
							(new String(password.getPassword())));
					aggiungi_nuovo(nome.getText(), indirizzo.getText(),
							(new String(password.getPassword())));
					chiudi();

				} else
					JOptionPane.showMessageDialog(null,
							"Cliente già registrato!");
			} else
				JOptionPane.showMessageDialog(null,
						"Inserire i dati in tutti i campi");
		}
	}

	public void aggiungi(String nome, String password) {
		Società.client.add(new Clienti(nome, password));

	}

	public void aggiungi_nuovo(String nome, String indirizzo, String password) {
		Società.client_new.add(new Clienti_nuovi(nome, indirizzo, password));
	}

	private void chiudi() {
		this.dispose();
	}
}