package hospitalmanag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	

	
	public Doctor(Connection connection) {
		this.connection=connection;
	
		
	}
	public void viewDoctor()
	{	String query ="select*from doctors";
	try {
		PreparedStatement preparedStatement= connection.prepareStatement(query);
		ResultSet resultSet= preparedStatement.executeQuery();
		System.out.println("Patients: ");
		System.out.println("+--------+-----+---+");
		System.out.println("|Doctor Id | Name|Specialization|");
		System.out.println("+------------+----------+---+");
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String  name = resultSet.getString("name");
			
			String Specialization= resultSet.getString("Specialization");
System.out.printf("|%-12s|%-20s|%-18s|\n",id ,name,Specialization);
System.out.println("+-----------------+-----+---+");
			
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		
	}
}
	public boolean getDoctorByID(int id) {
		String query ="SELECT* FROM doctors WHERE id = ?";
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

