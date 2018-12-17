package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Sede;
import Conexao.CHXHSQLDB;
public class SedeDAO {
	private final String SQL_INSERIR_SEDE = "INSERT INTO SEDE ("+ "BAIRRO, CEP)" + "VALUES (?, ?)";
	private final String SQL_ALTERAR_SEDE = "UPDATE SEDE SET BAIRRO=?, CEP=? WHERE ID=?;";
	private final String SQL_EXCLUIR_SEDE = "DELETE FROM SEDE WHERE ID=?";
	private final String SQL_SELECIONAR_SEDE = "SELECT * FROM SEDE";
	
private PreparedStatement pst = null;
	
	public boolean inserirSede(Sede umSede) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_SEDE);
			pst.setString(1, umSede.getBairro());
			pst.setString(2, umSede.getCep());
			ret = pst.execute();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;
	}
	
	public ArrayList<Sede> listarSede(){
		ArrayList<Sede> listaSede = new ArrayList<Sede>();
		Connection conn = CHXHSQLDB.conectar();
		Sede umSede;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_SEDE);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				umSede = new Sede();
				umSede.setID(rs.getInt("ID"));
				umSede.setBairro(rs.getString("BAIRRO"));
				umSede.setCep(rs.getString("CEP"));	
				listaSede.add(umSede);
			}
			rs.close();
			pst.close();
			CHXHSQLDB.fecharCNX();		
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return listaSede;
	}
	
	public boolean alterarSede(Sede umSede) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_SEDE);
			pst.setString(1, umSede.getBairro());
			pst.setString(2, umSede.getCep());
			pst.setInt(6, umSede.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}
	
	public boolean excluirSede(Sede umSede) {
		boolean ret = false;
		Connection conn = CHXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_SEDE);
			pst.setInt(1, umSede.getID());
			ret = pst.execute();
			pst.close();
			CHXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}


}
