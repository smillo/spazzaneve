package spazzaneve;

/*
 questa classe è il main del programma,oltre che a creare la parte pricipale della GUI,associa 
 lunghezza,mezzi e costi alle rispettive variabili che verranno usate anche nelle altre classi
 e implementa i metodi esiste e controlla che guardano se i clienti o i clienti nuovi sono già
 registrati


 @author Massimiliano Smillovich

 */
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class Società extends JFrame {

	private JList list_dist, list_mez;
	private JPanel content;
	static JTextArea text, text2;
	private static String display = "";
	protected static String[] lunghezza = { "meno di 250 m (35 €)", "tra 250 e 500 m (60 €)",
			"tra 500 e 1000 m (85 €)", "più di 1000 m (200-250 €)" };
	
	protected static String[] mezzi = { "Pala", "Turbina", "Macchina" };
	protected static int[] costo = { 35, 60, 85, 250, 200 };
	private static Intervento interv;
	private Login log;
	private Logout log2;
	private Registrazione reg;
	protected static LinkedList<Clienti> client = new LinkedList<Clienti>();
	protected static int indice1 = -1, indice2 = -1;
	public static String cliente = null;
	static int prezzo = 0;
	static String lung = null;
	static String mezzo = null;
	protected static LinkedList<Clienti_nuovi> client_new = new LinkedList<Clienti_nuovi>();
	static boolean ok = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Società frame = new Società();
					frame.setVisible(true);
					client.add(new Clienti("Massimiliano", "23082011"));
					client.add(new Clienti("Silvia", "23082011"));
					client_new.add(new Clienti_nuovi("Massimiliano", "gozzi",
							"23082011"));
					client_new.add(new Clienti_nuovi("Silvia", "roncate",
							"23082011"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Società() {

		setTitle("Società di spazzaneve");
		setBounds(100, 100, 825, 491);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		//content.setBackground(Color.orange);
		content.setLayout(null);

		text = new JTextArea(
				"Prima di selezionare qualsiasi tipo di intervento,\nsi prega di effettuare il login!\n"
						+ "se non si è già registrati,si prega di farlo,è veloce e gratuito!");
		text.setBounds(103, 11, 531, 58);
		text.setBackground(new Color(255, 255, 224));
		text.setColumns(10);
		text.setEditable(false);
		content.add(text);

		text2 = new JTextArea();
		text2.setBounds(103, 62, 531, 55);
		text2.setBackground(new Color(255, 255, 224));
		text2.setColumns(10);
		text2.setEditable(false);
		content.add(text2);

		JLabel lbldist = new JLabel("Distanza");
		lbldist.setBounds(80, 128, 77, 23);
		content.add(lbldist);

		JLabel lblMezzi = new JLabel("Mezzi");
		lblMezzi.setBounds(355, 128, 59, 23);
		content.add(lblMezzi);

		list_dist = new JList(lunghezza);
		list_dist.setBounds(30, 162, 191, 203);
		list_dist.setBackground(new Color(240, 255, 240));
		list_dist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		content.add(list_dist);
		list_dist.addListSelectionListener(new ListSelection());

		list_mez = new JList(mezzi);
		list_mez.setBounds(304, 162, 141, 203);
		list_mez.setBackground(new Color(220, 255, 210));
		list_mez.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		content.add(list_mez);
		list_mez.addListSelectionListener(new ListSelection1());

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(555, 147, 141, 32);
		content.add(btnLogin);
		btnLogin.addActionListener(new ButtonListener());

		JButton btnIntervento = new JButton("Intervento");
		btnIntervento.setBounds(555, 207, 141, 34);
		content.add(btnIntervento);
		btnIntervento.addActionListener(new ButtonListener());

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(555, 267, 141, 34);
		content.add(btnLogout);
		btnLogout.addActionListener(new ButtonListener());

		JButton btnRegistrazione = new JButton("Registrazione");
		btnRegistrazione.setBounds(555, 333, 141, 32);
		content.add(btnRegistrazione);
		btnRegistrazione.addActionListener(new ButtonListener());
	

	}

	/*
	 * se il cliente ha già fatto il login,riporta sulla text area la distanza
	 * che il cliente ha selezionato
	 */

	private class ListSelection implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (cliente == null) {
				display = "Prima di selezionare qualsiasi tipo di intervento,\nsi prega di effettuare il login!\n"
						+ "se non si è già registrati,si prega di farlo,è veloce e gratuito!";
				text.setText(display);
			} else {
				indice1 = list_dist.getSelectedIndex();
				display = "Ha selezionato " + lunghezza[indice1];
				text.setText(display);
			}
		}
	}

	/*
	 * se il cliente ha già fatto il login,riporta sulla text area il mezzo che
	 * il cliente ha selezionato
	 */
	private class ListSelection1 implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (cliente == null) {
				display = "Prima di selezionare qualsiasi tipo di intervento,\nsi prega di effettuare il login!\n"
						+ "se non si è già registrati,si prega di farlo,è veloce e gratuito!";
				text.setText(display);
			} else {
				indice2 = list_mez.getSelectedIndex();
				display = "Ha selezionato " + mezzi[indice2];
				text2.setText(display);
			}
		}

	}

	/*
	 * switch dove in base al tasto premuto,si effettuano diverse operazioni
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String decisione = e.getActionCommand();
			switch (decisione) {

			case "Login":
				if (cliente != null)
					JOptionPane.showMessageDialog(null, "Login già effettuato");
				else
					log = new Login();
				break;

			case "Logout":
				if (cliente == null)
					JOptionPane.showMessageDialog(null,
							"Effettuare prima un Login!");
				else {

					cliente = null;
					display = "Effettuare Login";
					text.setText(display);
					display = null;
					text2.setText(display);
					ok = true;
					log2 = new Logout();
				}
				break;

			case "Registrazione":
				if (cliente != null)
					JOptionPane.showMessageDialog(null,
							"Accesso già effettuato");
				else
					reg = new Registrazione();
				break;

			case "Intervento":
				if (ok) {

					if (cliente == null)
						JOptionPane
								.showMessageDialog(null, "Effettuare Login!");

					else if (indice1 == -1 || indice2 == -1)
						JOptionPane.showMessageDialog(null,
								"Selezionare prima il tipo di intervento!");

					if (indice1 == 0) {
						if (indice2 == 0) {

							prezzo = costo[0];
							lung = lunghezza[0];
							mezzo = mezzi[0];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 1) {

							prezzo = costo[0];
							lung = lunghezza[0];
							mezzo = mezzi[1];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 2) {

							prezzo = costo[0];
							lung = lunghezza[0];
							mezzo = mezzi[2];
							interv = new Intervento();
							ok = false;
						}
					}

					else if (indice1 == 1) {
						if (indice2 == 0) {

							prezzo = costo[1];
							lung = lunghezza[1];
							mezzo = mezzi[0];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 1) {

							prezzo = costo[1];
							lung = lunghezza[1];
							mezzo = mezzi[1];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 2) {

							prezzo = costo[1];
							lung = lunghezza[1];
							mezzo = mezzi[2];
							interv = new Intervento();
							ok = false;
						}
					}

					else if (indice1 == 2) {
						if (indice2 == 0) {

							prezzo = costo[2];
							lung = lunghezza[2];
							mezzo = mezzi[0];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 1) {

							prezzo = costo[2];
							lung = lunghezza[2];
							mezzo = mezzi[1];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 2) {

							prezzo = costo[2];
							lung = lunghezza[2];
							mezzo = mezzi[2];
							interv = new Intervento();
							ok = false;
						}
					} else if (indice1 == 3) {
						if (indice2 == 0) {

							prezzo = costo[3];
							lung = lunghezza[3];
							mezzo = mezzi[0];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 1) {

							prezzo = costo[4];
							lung = lunghezza[3];
							mezzo = mezzi[1];
							interv = new Intervento();
							ok = false;
						}

						else if (indice2 == 2) {

							prezzo = costo[4];
							lung = lunghezza[3];
							mezzo = mezzi[2];
							interv = new Intervento();
							ok = false;
						}
					}
				}

				else
					JOptionPane.showMessageDialog(null,
							"é stato già richiesto un intervento!");
			}

		}
	}

	/*
	 * controlla se il cliente esiste paragonando nome e password in tutti i
	 * clienti registrati e salvati nella linkedlist client
	 */
	public static boolean esiste(String user, String psw) {

		for (Clienti c : client) {
			if (user.compareTo(c.getNome()) == 0) {
				if (psw.compareTo(c.getPassword()) == 0) {
					cliente = user;

					display = "Buongiorno, " + cliente
							+ "!\nSelezioni il tipo di intervento desiderato.";
					text.setText(display);
					return true;

				}
			}
		}
		return false;
	}

	/*
	 * controlla se il cliente esiste paragonando nome e indirizzo in tutti i
	 * clienti registrati e salvati nella linkedlist client_new
	 */

	public static boolean controlla(String nome, String indirizzo,
			String password) {

		for (Clienti_nuovi cn : client_new) {
			if (nome.compareTo(cn.getNome()) == 0) {
				if (indirizzo.compareTo(cn.getIndirizzo()) == 0) {

					return false;
				}
			}
		}
		return true;
	}
}
