package spazzaneve;

/*
 questa classe permette di pagare con la carta di credito

 @author Massimiliano Smillovich

 */
import java.awt.event.*;
import java.util.regex.Pattern;

import javax.swing.*;

public class Pagamento extends JFrame {

	private JPanel content;
	private JTextField nomeut, numero;
	private JTextArea importo;
	private int indice3 = Intervento.indice3;
	private int indice2 = Intervento.indice2;
	String range = "^[0-9]*$";
	String controllo;

	public Pagamento() {

		setTitle("Pagamento");
		setBounds(200, 200, 550, 400);
		content = new JPanel();
		setContentPane(content);
		content.setLayout(null);

		JLabel imp = new JLabel("Importo:");
		imp.setBounds(13, 125, 91, 14);
		content.add(imp);

		importo = new JTextArea();
		importo.setBounds(177, 120, 174, 29);
		importo.setText(Società.prezzo + ",00");
		importo.setEditable(false);
		content.add(importo);

		JLabel name = new JLabel("Nome Utente:");
		name.setBounds(10, 36, 100, 14);
		content.add(name);

		nomeut = new JTextField();
		nomeut.setBounds(177, 33, 174, 29);
		content.add(nomeut);
		nomeut.setColumns(20);

		JLabel num = new JLabel("Numero carta (10 cifre):");
		num.setBounds(13, 85, 140, 14);
		content.add(num);

		numero = new JTextField();
		numero.setBounds(177, 82, 174, 27);
		content.add(numero);
		numero.setColumns(10);

		JButton btnPag = new JButton("Effettua pagamento");
		btnPag.setBounds(54, 182, 165, 44);
		content.add(btnPag);
		btnPag.addActionListener(new ButtonListener());

		JButton btnAnn = new JButton("Annulla");
		btnAnn.setBounds(288, 184, 107, 41);
		content.add(btnAnn);
		btnAnn.addActionListener(new ButtonListener());

		this.setVisible(true);
		setSize(487, 304);

	}

	/*
	 * premendo il tasto effettua pagamento,se l'utente è registrato e la carta
	 * è composta da 10 cifre,si effettua il pagamento,in caso opposto si stampa
	 * un messaggio d'errore
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String tasto = e.getActionCommand();
			switch (tasto) {

			case "Annulla":
				chiudi();
				Società.ok = true;

				break;

			case "Effettua pagamento":

				controllo = numero.getText();
				if (Pattern.matches(range, controllo)) {

					if (numero.getText() == null) {
						JOptionPane.showMessageDialog(null,
								"Inserire il numero della carta");
					} else if (numero.getText().length() != 10) {
						JOptionPane.showMessageDialog(null,
								"Numero cifre inserite errato");
					} else if (nomeut.getText().compareTo(Società.cliente) != 0) {
						JOptionPane.showMessageDialog(null,
								"Utente non registrato!");
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Pagamento effettuato,sarà subito da lei  il sig."
										+ Intervento.impiegati[indice3]
										+ " con la " + Società.mezzo);
						chiudi();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Il numero della carta è composto da soli numeri!");
				}

			}

		}

	}
	private void chiudi() {
		this.dispose();
	}
}
