package application;

import java.util.Date;
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
		}
			/*===== TEST 2: seller findByDepartment =====
Seller [id=6, name=Alex Pink, email=bob@gmail.com, birthDate=1997-03-04, baseSalary=6000.0, department=Department [id=2, name=Electronics]]
Seller [id=2, name=Maria Green, email=maria@gmail.com, birthDate=1979-12-31, baseSalary=6000.0, department=Department [id=2, name=Electronics]]* /
		*/
	
		System.out.println("\n===== TEST 3: seller findAll =====");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
			/*
			 /*
			 * ===== TEST 3: seller findAll =====
			 * Seller [id=3, name=Alex Grey, email=alex@gmail.com, birthDate=1988-01-15, baseSalary=5000.0, department=Department [id=1, name=Computers]]
			 * Seller [id=6, name=Alex Pink, email=bob@gmail.com, birthDate=1997-03-04, baseSalary=6000.0, department=Department [id=2, name=Electronics]]
			 * Seller [id=1, name=Bob Brown, email=bob@gmail.com, birthDate=1998-04-21, baseSalary=5000.0, department=Department [id=1, name=Computers]]
			 * Seller [id=7, name=Carl Purple, email=carl@gmail.com, birthDate=1985-04-22, baseSalary=3000.0, department=Department [id=4, name=Books]]
			 * Seller [id=5, name=Donald Blue, email=donald@gmail.com, birthDate=2000-01-09, baseSalary=4000.0, department=Department [id=3, name=Fashion]]
			 * Seller [id=2, name=Maria Green, email=maria@gmail.com, birthDate=1979-12-31, baseSalary=6000.0, department=Department [id=2, name=Electronics]]
			 * Seller [id=4, name=Martha Red, email=martha@gmail.com, birthDate=1993-11-30, baseSalary=3000.0, department=Department [id=4, name=Books]]
			 * Seller [id=8, name=Thiago Azevedo, email=thiago7azevedo@gmail.com, birthDate=1987-03-15, baseSalary=5000.0, department=Department [id=1, name=Computers]]
			 * Seller [id=9, name=Thiago Azevedo, email=thiago7azevedo@gmail.com, birthDate=1987-03-15, baseSalary=5000.0, department=Department [id=1, name=Computers]]
			 **/
		}

		System.out.println("\n===== TEST 4: seller insert =====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		/*
		 * ===== TEST 4: seller insert =====
		 * Inserted! New id = 10
		 */
		System.out.println("\n===== TEST 5: seller update =====");
		seller = sellerDao.findById(1);
		seller.setName("Marths Waine");
		sellerDao.update(seller);
		System.out.println("Update completed");
		
		
	}

}
