package org.test.crud;

import java.awt.image.ImagingOpException;
import java.net.ConnectException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import oracle.net.aso.e;
import oracle.net.aso.p;

public class TestCrud {

	
	private static Connection connection;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectdatabaseoracle() throws ImagingOpException,SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("cargo driver: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Exception driver:" + e.getMessage());
		}
		
		try {
			connection = DriverManager.getConnection(url,"System", "Nirvana2");
			System.out.println("conexion exitosa: oracle 11g");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception connection" + e.getMessage());
		}
		
		
	}

	
	
	public static void agregars_RAEGION(int id, String name) throws ImagingOpException,SQLException{
	
		try {
			connectdatabaseoracle();
			
			//quiere
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
			//PREPAREDsTATEMENT PERMITE EJECUTAR CONSULTAD O SENTENCIAS
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("Agrego registro = " + id + "," + name);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception" +  e.getMessage());
		}
	}
	
	
	public static void modificar_reg(String name, int id) throws ImagingOpException,SQLException{
		
		try {
			connectdatabaseoracle();
			
			//quiere
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			//PREPAREDsTATEMENT PERMITE EJECUTAR CONSULTAD O SENTENCIAS
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("MODIFICO REGISTRO = " + id + "," + name);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception" +  e.getMessage());
		}
	}
	
	
	public static void Eliminar_reg(int id) throws ImagingOpException,SQLException{
		
		try {
			connectdatabaseoracle();
			
			//quiere
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			//PREPAREDsTATEMENT PERMITE EJECUTAR CONSULTAD O SENTENCIAS
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("SE ELIMINO REGISTRO = " + id);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception" +  e.getMessage());
		}
	}
	
	
public static void CONSULTREG () throws ImagingOpException,SQLException{
		
		try {
			connectdatabaseoracle();
			
			//quiere
			String sql = "SELECT * FROM S_REGION";
			//PREPAREDsTATEMENT PERMITE EJECUTAR CONSULTAD O SENTENCIAS
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				System.out.println(result.getInt("id")+ ","+ result.getString("name"));

			}
			ps.close();
			connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception" +  e.getMessage());
		}
	}
	


public static void invocaprocedure (int id, String name) throws ImagingOpException,SQLException{
	
	try {
		connectdatabaseoracle();
		CallableStatement cs = connection.prepareCall("CALL proc (?,?)");
		cs.setInt(1, id);
		cs.setString(2, name);
		cs.execute();
		connection.close();
		System.out.println("EJECUTADO");
		
	
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("exception" +  e.getMessage());
	}
}
	public static void main(String[] args) throws ImagingOpException,SQLException {
		// TODO Auto-generated method stub
		//connectdatabaseoracle();
		
		 //agregars_RAEGION(16, "PEM");
	     //Eliminar_reg(14);
		//modificar_reg("MODIFICAR", 2);
		//CONSULTREG();
		invocaprocedure(17, "REINOSA");

	}

}
