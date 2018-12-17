package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.produtos;
import Conexao.CHXHSQLDB;

public class ProdutoDAO {
	private final String SQL_INSERIR_PRODUTO = "INSERT INTO PRODUTO ("+ "NOME, GENERO, CORREDOR, IDSEDE)" + "VALUES (?, ?, ?, ?)";
	private final String SQL_ALTERAR_PRODUTO = "UPDATE PRODUTO SET NOME=?, GENERO=?, CORREDOR=?  WHERE ID=?;";
	private final String SQL_EXCLUIR_PRODUTO = "DELETE FROM PRODUTO WHERE ID=?";
	private final String SQL_SELECIONAR_PRODUTO = "SELECT * FROM PRODUTO";
	private final String SQL_PESQUISAR_PRODUTO_NOME = "SELECT DISTINCT * FROM PRODUTO WHERE NOME=?";

	
	
private PreparedStatement pst = null;
	
	public boolean inserirProduto(produtos umProduto) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_PRODUTO);
			pst.setString(1, umProduto.getNome());
			pst.setString(2, umProduto.getGenero());
			pst.setString(3, umProduto.getCorredor());
			pst.setInt(4, umProduto.getSede().getID());
			ret = pst.execute();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;
	}
	
	public ArrayList<produtos> listarProdutos(){
		ArrayList<produtos> listaProdutos = new ArrayList<produtos>();
		Connection conn = CHXHSQLDB.conectar();
		produtos umProduto;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_PRODUTO);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				umProduto = new produtos();
				umProduto.setID(rs.getInt("ID"));
				umProduto.setNome(rs.getString("NOME"));
				umProduto.setGenero(rs.getString("GENERO"));
				umProduto.setCorredor(rs.getString("CORREDOR"));		
				listaProdutos.add(umProduto);
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return listaProdutos;
	}
	
	public boolean alterarProdutos(produtos umProduto) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_PRODUTO);
			pst.setString(1, umProduto.getNome());
			pst.setString(2, umProduto.getGenero());
			pst.setString(3, umProduto.getCorredor());
			pst.setInt(4, umProduto.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}
	
	public boolean excluirProdutos(produtos umProduto) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_PRODUTO);
			pst.setInt(1, umProduto.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}

	public produtos pesquisar(String pesquisar){
		Connection conn = CHXHSQLDB.conectar();
		produtos umProduto = new produtos();
		try {
			pst = conn.prepareStatement(SQL_PESQUISAR_PRODUTO_NOME);
			pst.setString(1, pesquisar);
			ResultSet rs = pst.executeQuery();			
			while(rs.next()) {				
				umProduto.setID(rs.getInt("ID"));
				umProduto.setNome(rs.getString("NOME"));
				umProduto.setGenero(rs.getString("GENERO"));
				umProduto.setCorredor(rs.getString("CORREDOR"));			
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return umProduto;
	}
	

}
