package jdbc;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// informações de conexão com o banco de dados
        String db_url = "jdbc:mysql://localhost:3306/reuniao";
        String db_user = "root";
        String db_password = "";
        
     // cria um objeto da classe BancoDados 
        BancoDados db = new BancoDados(db_url, db_user, db_password);
        
     // cria a conexão com o banco de dados
        System.out.println("Conectando ao banco de dados...");
     	db.conectar();
     	
        boolean continuar = true;
        int linhasAfetadas = 0; // variável para armazenar o total de linhas afetadas
        while (continuar) {
        
     // solicita a operação desejada
        System.out.println("Digite o número da operação desejada: ");
        System.out.println("1 - Consultar");
        System.out.println("2 - Inserir");
        System.out.println("3 - Alterar");
        System.out.println("4 - Excluir");
        int operacao = sc.nextInt();
        
        if (operacao == 1) {
            // se a operação for de consulta, chama o método consultar da classe BancoDados
            System.out.println("Digite a query de consulta a ser executada: ");
            sc.nextLine(); // consome a quebra de linha deixada pelo nextInt
            String db_query = sc.nextLine();
            db.consultar(db_query);
            
        } else {
            // se a operação for de inserção, alteração ou exclusão, solicita a query a ser executada
            System.out.println("Digite a query a ser executada: ");
            sc.nextLine(); // consome a quebra de linha deixada pelo nextInt
            String db_query = sc.nextLine();
            int linhasOperacao = db.inserirAlterarExcluir(db_query, operacao);
            linhasAfetadas += linhasOperacao; // atualiza o total de linhas afetadas
        }
		
     // pergunta se deseja realizar outra operação
        System.out.println("Deseja realizar outra operação? (s/n)");
        String resposta = sc.next();
        if (resposta.equalsIgnoreCase("n")) {
            continuar = false;
        }
    }
        System.out.println("Total de linhas afetadas: " + linhasAfetadas); // exibe o total de linhas afetadas
		db.desconectar();
		sc.close();
		
	}
	}