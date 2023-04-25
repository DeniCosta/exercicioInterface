package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDados implements InterfaceBancoDados {

	private Connection conexao;

	// informações de conexão com o banco de dados
	private String db_url;
	private String db_user;
	private String db_password;
	private int totalLinhasAfetadas;

	public BancoDados(String db_url, String db_user, String db_password) {
		this.db_url = db_url;
		this.db_user = db_user;
		this.db_password = db_password;
	}

	@Override
	public void conectar() {
		try {
			// realiza a conexão com o banco de dados
			conexao = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conexão bem sucedida!");
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar ao banco de dados.");
			e.printStackTrace();
		}
	}

	@Override
	public void desconectar() {
		try {
			conexao.close();
			System.out.println("Conexão encerrada com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao desconectar do banco de dados: " + e.getMessage());
		}
	}

	@Override
	public void consultar(String db_query) {
		try {
			
			// cria o statement
			PreparedStatement stmt = conexao.prepareStatement(db_query);
			// executa a operação consulta
			ResultSet rs = stmt.executeQuery(db_query);

			// processar os resultados da consulta
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)
						+ "\t" + rs.getString(4));
			}

			// fecha o statement e o resultado da consulta
			rs.close();
			stmt.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int inserirAlterarExcluir(String db_query, int operacao) {
		int linhasAfetadas = 0;
		try {

			// cria o statement
			Statement stmt = conexao.createStatement();
		
			// executa a operação de acordo com o valor do parâmetro operacao
			switch (operacao) {
			case 1:
				// código para consultar no banco de dados
				consultar(db_query);
				// código para processar o resultado da consulta
				System.out.println("Consulta realizada com sucesso");
				break;

			case 2:
				// código para inserir no banco de dados
				linhasAfetadas = stmt.executeUpdate(db_query);
                totalLinhasAfetadas += linhasAfetadas;
                System.out.println("Inserção realizada com sucesso.");
                break;

			case 3:
				// código para alterar no banco de dados
				linhasAfetadas = stmt.executeUpdate(db_query);
                totalLinhasAfetadas += linhasAfetadas;
				System.out.println("Alteração realizada com sucesso");
				break;

			case 4:
				// código para excluir no banco de dados
				linhasAfetadas = stmt.executeUpdate(db_query);
                totalLinhasAfetadas += linhasAfetadas;
				System.out.println("Exclusão realizada com sucesso");
				break;
			default:
				System.out.println("Operação inválida");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a operação no banco de dados.");
			e.printStackTrace();
		}
		return totalLinhasAfetadas;
	}
}
