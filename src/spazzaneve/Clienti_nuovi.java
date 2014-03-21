package spazzaneve;

/*
 questa classe permette l'introduzione di clienti nuovi

 @author Massimiliano Smillovich

 */
public class Clienti_nuovi {

	private String nome;
	private String indirizzo;
	private String password;

	public Clienti_nuovi(String nome, String indirizzo, String password) {

		this.nome = nome;
		this.indirizzo = indirizzo;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getPassword() {
		return password;
	}

}
