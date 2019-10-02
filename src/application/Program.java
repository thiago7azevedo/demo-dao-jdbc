package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===== TEST 1: seller findById =====");
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		/*
		 * ===== TEST 1: seller findById =====
		 * Seller [id=3, name=Alex Grey, email=alex@gmail.com, birthDate=1988-01-15, baseSalary=5000.0, department=Department [id=1, name=Computers]]
		 */
		
		System.out.println("\n===== TEST 2: seller findByDepartment =====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartmnet(department);
		for (Seller obj : list) {
			System.out.println(obj);
			/*
			 * ===== TEST 2: seller findByDepartment =====
			 * Seller [id=6, name=Alex Pink, email=bob@gmail.com, birthDate=1997-03-04, baseSalary=6000.0, department=Department [id=2, name=Electronics]]
			 * Seller [id=2, name=Maria Green, email=maria@gmail.com, birthDate=1979-12-31, baseSalary=6000.0, department=Department [id=2, name=Electronics]]
			 */
		}
		
	}

}
