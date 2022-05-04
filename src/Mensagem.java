
public class Mensagem {
	
	String conteudo;

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mensagem [conteudo=");
		builder.append(conteudo);
		builder.append("]");
		return builder.toString();
	}
	
}
