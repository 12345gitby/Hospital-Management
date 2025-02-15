package hospitalmanag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {


		private Connection connection;
		
		private Scanner scanner;
		
		public patient(Connection connection,Scanner scanner) {
			this.connection=connection;
			this.scanner =scanner;
			
		}
		// method to add patient
		
	public void addPatient() {
		System.out.print("Enter your name: ");
		String name=scanner.next();
		System.out.print("Enter your age: ");
		int age =scanner.nextInt();
		System.out.print("Enter your Gender: ");
		String gender =scanner.next();
		
		
		try {
			String query = "INSERT INTO patient(name,age,gender) VALUES(?,?,?)";
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setString(1,name);
			preparedStatement.setInt(2,age);
			preparedStatement.setString(3,gender);
			
			int affectrow= preparedStatement.executeUpdate()
	;
			if(affectrow>0) {
		System.out.print("patient added Successfully");
	}else {
		System.out.print("failed to add patient");
	}
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
	}

	// 2nd method to view patient
	public void ViewPatients() {
		String query ="select*from patients";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+--------------+-----+-----+---+");
			System.out.println("|patient Id | Name| age| gender|");
			System.out.println("+--------------+-----+-----+---+");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String  name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String  gender = resultSet.getString("gender");
System.out.printf("|%-12s|%-20s|%-10|%-12s|\n",id,name,age,gender);
  System.out.println("+--------------+-----+-----+---+");
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}
	public boolean getPatientByID(int id) {
		String query ="SELECT* FROM patients WHERE id = ?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
				
			}else {
				return false;
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return false;
		
	}

	}


