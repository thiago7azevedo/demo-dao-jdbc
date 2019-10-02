package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) { // método criado para atra´ves de um numero de ID, retornar um resultado
				// criação sem framework (spring)
		PreparedStatement st = null; 
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( // recebe a conex]ao aberta com o BD, executando esses valores abaixo
					"SELECT seller.*,department.Name as DepName " 
					+"FROM seller INNER JOIN department " 
					+"ON seller.DepartmentId = department.Id " 
					+ "WHERE seller.Id = ?");
			
				st.setInt(1, id); // 1 = primeiro ? - id éo que vem de parametro de fora
				rs = st.executeQuery(); //recebe o que foi executado
				if (rs.next()) { // verifica se existir uma linha com valores, retorna verdadeiro e cai dentro do if
					Department dep = new Department(); // instancia o objeto dep Department - vai pendurar nos dados do cliente
					dep.setId(rs.getInt("DepartmentId")); // seta o ID pela coluna DepartmentID fazendo umma busca de detInt que é o tipo do campo
					dep.setName(rs.getString("DepName")); // seta o nome pela coluna DepName que é uma variavel string
					//instanciou objeto Department em cima
					// instnacia o objeto Seller (vendendor) a baixo
					Seller obj = new Seller();
					obj.setId(rs.getInt("Id"));
					obj.setName(rs.getString("Name"));
					obj.setEmail(rs.getString("Email"));
					obj.setBaseSalary(rs.getDouble("BaseSalary"));					
					obj.setBirthDate(rs.getDate("BirthDate"));
					obj.setDepartment(dep);					
					return obj;					
				}
				
				return null;			
			}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
