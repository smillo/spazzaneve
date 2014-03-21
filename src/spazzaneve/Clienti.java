package spazzaneve;

/*
 Questa classe permette la creazione dei clienti

 @author Massimiliano Smillovich

 */
public class Clienti {

	private String nome;
	private String password;

	public Clienti(String nome, String password) {

		this.nome = nome;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}

}
