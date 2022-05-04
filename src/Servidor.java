import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.UnknownHostException;

public class Servidor {



	private static final SocketImpl PORTA = null;

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		// TODO Auto-generated method stub

		
		//socket servidor
		ServerSocket servidor = null;
		//conexão com o cliente
		Socket cliente = null;
		System.out.println("Inicializando SERVIDOR");
		try {
			//instancia um objeto servidor em uma porta especifica
			servidor = new ServerSocket(9090);
			System.out.println("ESPERANDO CONEXAO");
			//aguarda uma solicitacao de conexao
			cliente = servidor.accept();
			System.out.println("CONECTADO:" + cliente.getLocalPort());
			//obtem o fluxo de dados de entrada da conexao
			// 	(de onde le-se as mensagens)
			ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
			
			System.out.println("ESPERANDO MENSAGEM");
			//le uma mensagem
			Mensagem requisicao = (Mensagem) ois.readObject();
			
			//caso a mensagem seja PING responderá com PONG
			if(requisicao.getConteudo().equalsIgnoreCase("PING")) {
				Mensagem resposta = new Mensagem();
				resposta.setConteudo("PONG");
				//obtem o fluxo de dados de saida da conexao
				// 	(onde grava-se as mensagens)
				ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
				//escreve a mensagem de resposta 
				oos.writeObject(resposta);
				//forca os envios dos bytes
				oos.flush();	
			}
			//fecha a conexao com o servidor
			servidor.close();	
		} finally {
			
		}
		
		//instancia um socket e conecta na porta especifica
		System.out.println("INICIALIZANDO CLIENTE");
		Socket s = new Socket("localhost", 9090);
		System.out.println("CLIENTE CONECTADO");
		//obtem o fluxo de dados de saida da conexao
		//	(ondegrava-se as mensagens)
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		Mensagem requisicao = new Mensagem();
		requisicao.setConteudo("PING");
		//escreve a mensagem de requisicao
		oos.writeObject(requisicao);
		//forca o envio de bytes
		oos.flush();
		System.out.println("MENSAGEM ENVIADA");
		System.out.println("ESPERANDO RESPOSTA");
		
		
		
	}

}
