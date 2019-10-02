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
					Department dep = instantiateDepartment(rs);
					Seller obj = instantianteSeller(rs, dep);
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

	private Seller instantianteSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));					
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep =  new Department(); 
		dep.setId(rs.getInt("DepartmentId")); 
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
