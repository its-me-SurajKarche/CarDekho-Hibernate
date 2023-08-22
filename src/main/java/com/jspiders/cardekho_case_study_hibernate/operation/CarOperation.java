package com.jspiders.cardekho_case_study_hibernate.operation;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.cardekho_case_study_hibernate.entity.Car;
import com.jspiders.cardekho_case_study_hibernate.main.CardekhoMenu;


public class CarOperation {

	private static String query;
	private static int result;
	private static Scanner scanner = new Scanner(System.in);
	private static List<Integer>carIds;
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	private static Query query2;
	private static List<Car> details;
	private static Iterator<Car> iterator;
	private static Car car;

	public static void openConnection() {
	
		entityManagerFactory=Persistence.createEntityManagerFactory("cardekho");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}

	public static void closeConnection() {
		
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityTransaction != null) {
				if(entityTransaction.isActive()) {
					entityTransaction.rollback();
				}
			}
			if (result != 0) {
				result = 0;
			}
			if(car!=null) {
				car=null;
			}
			if(iterator!=null) {
				iterator=null;
			}
			if(details!=null) {
				details=null;
			}
	     }

	public static void addCars() {
		try {
			System.out.println("carIds which are already registered are as below:  \n ==> "+carIds()+" <=="+"\n*****************************");
			openConnection();
			System.out.println("how many cars you want to add");
			int amount = scanner.nextInt();
			for (int i = 1; i <= amount; i++) {
				System.out.println("Enter The CarID You Want To Add OR Type Exit To Go To MainMenu");
				String id=scanner.next();
				if (id.equalsIgnoreCase("exit")) {
					i=amount;
				}
				else {
					
					int CarId=Integer.parseInt(id);
					if(carIds.contains(CarId)) {
						System.out.println("Id Already Used For Another Car_Details");
					}
					else {
						int j=0;
						entityTransaction.begin();
						 car = new Car();
							car.setCarID(CarId);
							scanner.nextLine();
							System.out.println("add carName for "+i+" car");
							car.setCarName(scanner.nextLine());
							System.out.println("add brandName for "+i+" car");
							car.setBrandName(scanner.nextLine());
							System.out.println("add fuelType for "+i+" car");
							car.setFuelType(scanner.nextLine());
							System.out.println("add price for "+i+" car");
							car.setPrice(scanner.nextFloat());
							entityManager.persist(car);
							result=++j;
							entityTransaction.commit();	
					}
					
				}
			}
			System.out.println("query ok ,total row(s) affected =>"+result);
			scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static List<Integer> carIds(){
		openConnection();
		entityTransaction.begin();
		query2=entityManager.createQuery("select carID from Car");
		carIds= query2.getResultList();
		entityTransaction.commit();
		closeConnection();
		return carIds;
	}
	public static void searchByName() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Enter Car Name You Want To Search");
			String name=scanner.nextLine();
			query2=entityManager.createQuery("select car from Car car where carName='"+name+"'");
			details=query2.getResultList();
			 iterator=details.iterator();
				entityTransaction.commit();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With CarName :"+name+"\n=======================================");
			while (iterator.hasNext()) {
				car=iterator.next();
				if (name.equalsIgnoreCase(car.getCarName())) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With CarName You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByBrand() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Enter BrandName You Want To Search");
			String name=scanner.nextLine();
			query2=entityManager.createQuery("select car from Car car where brandName='"+name+"'");
			details=query2.getResultList();
			 iterator=details.iterator();
				entityTransaction.commit();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With BrandName :"+name+"\n=======================================");
			while (iterator.hasNext()) {
				car=iterator.next();
				if (name.equalsIgnoreCase(car.getBrandName())) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With BrandName You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByFuelType() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Enter fuelType You Want To Search");
			String name=scanner.nextLine();
			query2=entityManager.createQuery("select car from Car car where fuelType='"+name+"'");
			details=query2.getResultList();
			 iterator=details.iterator();
				entityTransaction.commit();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With fuelType :"+name+"\n=======================================");
			while (iterator.hasNext()) {
				car=iterator.next();
				if (name.equalsIgnoreCase(car.getFuelType())) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With FuelType You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByCarId() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Enter CarID You Want To Search");
			int id=scanner.nextInt();
			scanner.nextLine();
			car=entityManager.find(Car.class,id);
			entityTransaction.commit();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With CarID :"+id+"\n=======================================");
				if (car!=null) {
					flag=false;
					processQuery();
				}
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With CarID You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	public static void searchByPrice() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Enter Price You Want To Search");
			float price= scanner.nextFloat();
			scanner.nextLine();
			query2=entityManager.createQuery("select car from Car car where price="+price+"");
			details=query2.getResultList();
			 iterator=details.iterator();
				entityTransaction.commit();
			boolean flag=true;
			System.out.println("Following Are The Cars We Found With Price :"+price+"\n=======================================");
			while (iterator.hasNext()) {
				car=iterator.next();
				if (price==car.getPrice()) {
					flag=false;
					processQuery();
				}
			}
		
		 if(flag){
			System.out.println("=======================================\n*** No Car Found With Price You Entered ***\n=======================================");
		}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	public static void updateCarName() {
		try {
			System.out.println("How Many Cars CarName You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds().size()) {
				
				openConnection();
				for (int i = 0;  i <amount ; i++) {
					
					System.out.println("Enter CarId Of Car Who's CarName You Want To Change");
					int carId=scanner.nextInt();
					scanner.nextLine();
					int j=0;
					if (carIds.contains(carId)) {
						entityTransaction.begin();
						System.out.println("Enter A New Name For Car With CarId :"+carId);
						String name= scanner.nextLine();
						car=entityManager.find(Car.class, carId);
						car.setCarName(name);
						System.out.println("The New Name For Car With Id :"+carId+" is: "+name);
						entityTransaction.commit();
						result=++j;
					}
					else {
						System.out.println("Enter Valid CarId");
						i=amount;
					}
					}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void updateBrand() {
		try {
			System.out.println("How Many Cars BrandName You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds().size()) {
				openConnection();
				for (int i = 0; i <amount ; i++) {
				
					System.out.println("Enter CarId Of Car Who's BrandName You Want To Change");
					int carId=scanner.nextInt();
					scanner.nextLine();
					if (carIds.contains(carId)) {
						entityTransaction.begin();
						System.out.println("Enter A New Name For Car With CarId :"+carId);
						String name= scanner.nextLine();
						query2=entityManager.createQuery("update Car set brandName='"+name+"' where carID="+carId);
						result=result+query2.executeUpdate();
						System.out.println("The New BrandName For Car With Id :"+carId+"is: "+name);
					entityTransaction.commit();}
					else {
						System.out.println("Enter Valid CarId");
						i=amount;
					}
					}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void updateFuelType() {
		try {
			System.out.println("How Many Cars FuelType You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds().size()) {
				openConnection();
				for (int i = 0; i <amount ; i++) {
					
					System.out.println("Enter CarId Of Car Who's FuelType You Want To Change");
					int carId=scanner.nextInt();
					scanner.nextLine();
					if (carIds.contains(carId)) {
						entityTransaction.begin();
						System.out.println("Enter A New FuelType For Car With CarId :"+carId);
						String name= scanner.nextLine();
						query2=entityManager.createQuery("update Car set fuelType='"+name+"' where carID="+carId);
						result=result+query2.executeUpdate();
						System.out.println("The New BrandName For Car With Id :"+carId+"is: "+name);
						entityTransaction.commit();;}
					else {
						System.out.println("Enter Valid CarId");
						i=amount;
					}
					}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
		
	}
	
	public static void updateId() {
		try {
			System.out.println("How Many Cars IDs You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				int i=0;
				openConnection();
				for (; i <amount ; i++) {
				System.out.println("Enter CarId Of Car Who's id You Want To Change");
				int carId=scanner.nextInt();
				if (carIds.contains(carId)) {
					entityTransaction.begin();
					System.out.println("Enter A New Id For Car With CarId :"+carId);
					int newID= scanner.nextInt();
					query2=entityManager.createQuery("update Car set carID="+newID+"where carID="+carId);
					result=result+query2.executeUpdate();
					System.out.println("The New Price For Car With Id :"+carId+" is: "+newID);
					entityTransaction.commit();}
				else {
					System.out.println("Enter Valid CarId");
					i=amount;
				}
			}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
		
	}
	
	public static void updatePrice() {
		try {
			
			System.out.println("How Many Cars Price You Want To Change");
			int amount=scanner.nextInt();
			if (amount<=carIds.size()) {
				
				openConnection();
				for (int i=0; i <amount ; i++) {
				System.out.println("Enter CarId Of Car Who's Price You Want To Change");
				int carId=scanner.nextInt();
				int j=0;
				if (carIds.contains(carId)) {
					entityTransaction.begin();
					System.out.println("Enter A New Price For Car With CarId :"+carId);
					float price= scanner.nextFloat();
					car=entityManager.find(Car.class,carId)	;
					car.setPrice(price);
					System.out.println("The New Price For Car With Id :"+carId+" is: "+price);
					entityTransaction.commit();
					result=++j;}
				else {
					System.out.println("Enter Valid CarId");
					i=amount;
				}
			}
			System.out.println("Query Ok , Total Row(s) Affected :"+result);
			}
			else {
				System.out.println("That Number Of Cars Not Available In Data Base");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
	}
	
	public static void deleteCar() {
		if(isDatabaseEmpty()) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Their Are No Car_Details Available In DataBase To Delete");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			CardekhoMenu.flag5=false;
		}
		else {
		try {
			System.out.println("DO YOU WANT TO SEE ALL CAR DETAILS BEFORE DELETING THEN PRESS 1 OR TO GO TO MAINMENU PRESS 2 OR TO CONTINUE DELETE PROCESS PRESS ANY OTHER NUMBER");
			int choose= scanner.nextInt();
			if (choose==1) {
				allCarDetails();
			}
			else if (choose==2) {
				CardekhoMenu.flag5=false;
			}
			else {
				System.out.println("How Many Cars You Want To Delete");
				int amount=scanner.nextInt();
				if (amount<=carIds().size()) {
					
					for (int i = 0; i <amount ; i++) {
					System.out.println("Enter CarId Of Car Which You Want To Delete Or Type 'Exit' TO Stop Deleting Process");
					String carId=scanner.next();
					if(carId.equalsIgnoreCase("exit")) {
						i=amount;
					}
					else {
						openConnection();
						int id=Integer.parseInt(carId);
						int j=0;
							if(carIds.contains(id)) {
								entityTransaction.begin();
								car=entityManager.find(Car.class,id);
								entityManager.remove(car);
								entityTransaction.commit();
								System.out.println("Car With Id :"+carId+" Has Been Deleted Successfully");
								result=++j;
							}
							else {
								System.out.println("Id Does Not Match With The Ids In DataBase Please Enter Correct Id");
							}	
					}
				}
				System.out.println("Query Ok , Total Row(s) Affected :"+result);
				}
				else {
					System.out.println("That Number Of Cars Not Available In Data Base");
				}	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		}	
	}
	public static void allCarDetails() {
		if(carIds().size()== 0) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Their Are No Car_Details Available In DataBase To Display");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		else {
			System.out.println("Following Are The All The Car Details Available In DataBase :");
			System.out.println("-------------------------------------------------------------");
		try {
			openConnection();
			entityTransaction.begin();
			query2=entityManager.createQuery("select car from Car car");
			details=query2.getResultList();
			iterator=details.iterator();
			int i=1;
			while (iterator.hasNext()) {
				System.out.print(i++);
				car=(Car) iterator.next();
				processQuery();
			}
			entityTransaction.commit();
			System.out.println("Type 'Back' To Go Back TO Recent On Going Process Or Press Any Key To Exit The Application");
			if (scanner.next().equalsIgnoreCase("back")) {
//				CardekhoMenu.flag=true;
				scanner.nextLine();
				closeConnection();
			}
			else {
				CardekhoMenu.flag=false;
				CardekhoMenu.flag4=false;
				CardekhoMenu.flag5=false;
				System.out.println("!!!!!Thank You!!!!!");
				closeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	public static void processQuery() {
		try {
	        System.out.println("]"+car);
			System.out.println("________________________________________________________________________");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static boolean isDatabaseEmpty() {
		boolean empty=true;
		
			openConnection();
			entityTransaction.begin();
			query="select car from Car car";
		Query query1=entityManager.createQuery(query);
		List<Car>resultList=query1.getResultList ();
			Iterator<Car>cars=resultList.iterator();
			while (cars.hasNext()) {
				cars.next();
				empty=false;
			}
			entityTransaction.commit();
		closeConnection();
		
		return empty;
	}
	
}
