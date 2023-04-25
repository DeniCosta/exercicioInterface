package jdbc;

public interface InterfaceBancoDados {
	
	public void conectar();

	public void desconectar();

	public void consultar(String db_query);

	public int inserirAlterarExcluir(String db_query , int operacao);

}
