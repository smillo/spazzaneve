package spazzaneve;

/*
 questa classe crea la parte di interfaccia che permette di selezionare un impiegato e il metodo di
 pagamento desiderato

 @author Massimiliano Smillovich

 */
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class Intervento extends JFrame {
	protected static int indice2 = Società.indice2, indice3;
	private JList list_imp, list_pagamento;
	private JPanel content;
	private Pagamento pag;
	private static JTextArea text, text2, text3, esp;
	private static String display = "";
	protected static String[] impiegati = { "Rossi", "Bianchi", "Verdi " };
	private static String[] pagamento = { "Carta di credito", "Contanti" };
	int indice4 = -1;

	public Intervento() {

		setTitle("Tipo di intervento");
		setBounds(100, 100, 764, 470);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(null);

		text = new JTextArea("avendo selezionato come distanza " + Società.lung
				+ "\ne avendo scelto come mezzo la " + Società.mezzo + "\n"
				+ "il costo totale sarà di " + Società.prezzo + " euro");
		text.setBounds(54, 11, 561, 58);
		text.setBackground(new Color(255, 255, 224));
		text.setColumns(10);
		text.setEditable(false);
		content.add(text);

		text2 = new JTextArea(
				"Selezionare l'impiegato e il metodo di pagamento desiderati.. ");
		text2.setBounds(54, 68, 561, 79);
		text2.setBackground(new Color(255, 255, 224));
		text2.setColumns(10);
		text2.setEditable(false);
		content.add(text2);

		text3 = new JTextArea();
		text3.setBounds(54, 100, 460, 30);
		text3.setBackground(new Color(255, 255, 224));
		text3.setColumns(10);
		text3.setEditable(false);
		content.add(text3);

		JLabel lblimp = new JLabel("Impiegati");
		lblimp.setBounds(53, 158, 76, 14);
		content.add(lblimp);

		list_imp = new JList(impiegati);
		list_imp.setBounds(23, 183, 106, 188);
		list_imp.setBackground(new Color(240, 255, 240));
		list_imp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		content.add(list_imp);
		list_imp.addListSelectionListener(new ListSelection());

		JLabel lblpag = new JLabel("Pagamento");
		lblpag.setBounds(341, 158, 106, 24);
		content.add(lblpag);

		list_pagamento = new JList(pagamento);
		list_pagamento.setBounds(325, 183, 100, 188);
		list_pagamento.setBackground(new Color(220, 255, 210));
		list_pagamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		content.add(list_pagamento);
		list_pagamento.addListSelectionListener(new ListSelection1());

		JLabel lblesp = new JLabel("Esperienza");
		lblesp.setBounds(185, 158, 86, 14);
		content.add(lblesp);

		esp = new JTextArea("Pala\nPala,Turbina\nPala,Turbina,Macchina");
		esp.setBounds(146, 183, 139, 188);
		esp.setEditable(false);
		esp.setBackground(new Color(240, 255, 240));
		content.add(esp);

		JButton btnconferma = new JButton("Conferma");
		btnconferma.setBounds(511, 205, 139, 30);
		content.add(btnconferma);
		btnconferma.addActionListener(new ButtonListener());

		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(511, 278, 139, 35);
		content.add(btnAnnulla);
		btnAnnulla.addActionListener(new ButtonListener());
		
		this.setVisible(true);

	}

	/*
	 * stampa sulla textarea l'impiegato scelto
	 */
	private class ListSelection implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			indice3 = list_imp.getSelectedIndex();
			display = "Ha selezionato " + impiegati[indice3];
			text2.setText(display);
		}

	}

	/*
	 * stampa sulla textarea il metodo di pagamento scelto
	 */
	private class ListSelection1 implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			indice4 = list_pagamento.getSelectedIndex();
			display = "Ha selezionato " + pagamento[indice4];
			text3.setText(display);
		}

	}

	/*
	 * se l'impiegato è compatibile con il mezzo,si decide se effettuare il
	 * pagamento via carta di credito (aprendo un nuovo frame) oppure in
	 * contanti
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			
			String decisione = evento.getActionCommand();
			switch (decisione) {

			case "Conferma":

			if (indice3 == -1 || indice4 == -1)
				JOptionPane
						.showMessageDialog(null,
								"Selezionare prima l'impiegato e il metodo di pagamento");

			else if ((indice3 == 0 && Società.mezzo != "Pala")
					|| (indice3 == 1 && Società.mezzo == "Macchina")) {
				JOptionPane
						.showMessageDialog(null,
								"L'impiegato non ha abbastanza esperienza per usare il mezzo");
			} else if (indice3 == 0 && Società.mezzo == "Pala") {

				if (indice4 == 0) {
					pag = new Pagamento();
					chiudi();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Arriverà da lei il prima possibile il sig."
											+ impiegati[indice3]
											+ " con la "
											+ Società.mezzo
											+ " e ritirerà direttamente lui il pagamento");
					chiudi();
				}
			}

			else if (indice3 == 1 && Società.mezzo != "Macchina") {
				if (indice4 == 0) {
					pag = new Pagamento();
					chiudi();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Arriverà da lei il prima possibile il sig."
											+ impiegati[indice3]
											+ " con la "
											+ Società.mezzo
											+ " e ritirerà direttamente lui il pagamento");
					chiudi();
				}
			}

			else {
				if (indice4 == 0) {
					pag = new Pagamento();
					chiudi();
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Arriverà da lei il prima possibile il sig."
											+ impiegati[indice3]
											+ " con la "
											+ Società.mezzo
											+ " e ritirerà direttamente lui il pagamento");
					chiudi();
					
						
				}
				
			}
			break;
			
			case "Annulla":
				
				chiudi();
				Società.ok = true;
		}

	}
}
	private void chiudi() {
		this.dispose();
	}}
