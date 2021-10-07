package org.test.crudcasa;

import java.awt.image.ImagingOpException;
import java.net.ConnectException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.directory.ModificationItem;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.omg.CORBA.PUBLIC_MEMBER;

import oracle.net.aso.e;
import oracle.net.aso.p;

public class PruebaConec {
	
	private static  Connection connection;
	private static  String driver="oracle.jdbc.driver.OracleDriver";
	private static  String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectionbd() throws ImagingOpException, SQLException{
		
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER: ojdbc6.jar ");
		} catch (Exception e) {
			System.out.println("EXCEPTION DRIVER" + e.getMessage());
		}
		
		try {
			connection = DriverManager.getConnection(url,"System","Nirvana2");
			System.out.println("conecxion exitsa: oracle 11g");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception" + e.getMessage());
		}
	}
	
	
	public static void agregarproduc (int ID, String NOMBRE, int PRECIO, String DESCRIPCION) throws ImagingOpException, SQLException{
	
		try {
			connectionbd();
			String sql = "INSERT INTO PRODUCTOSBS (ID, NOMBRE, PRECIO, DESCRIPCION) VALUES (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,ID );
			ps.setString(2, NOMBRE);
			ps.setInt(3, PRECIO);
			ps.setString(4, DESCRIPCION);
			ps.executeQuery();

			ps.close();
			connection.close();
			System.out.println("REGISTRo AGREGADO" + ID + "," + NOMBRE + "," + PRECIO + "," + DESCRIPCION);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EXCEPTION " + e.getMessage());
		}
		
	}
	
	
	public static void modtable(String nombre, int id) throws ImagingOpException, SQLException{
		try {
			connectionbd();
			String sql = "UPDATE PRODUCTOSBS SET NOMBRE = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, id);
		
			ps.executeQuery();
			connection.close();
			System.out.println("modificado registro =" + id + "," + nombre);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception" + e.getMessage());
		}
		
	}
	
	public static void EliminarPRO(int id) throws ImagingOpException,SQLException{
		
		try {
            connectionbd();			
			//quiere
			String sql = "DELETE FROM PRODUCTOSBS WHERE ID = ?";
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

	public static void consultareg ()throws ImagingOpException, SQLException{
	try {
		connectionbd();
		String sql = "SELECT * FROM PRODUCTOSBS";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet re = ps.executeQuery();
		
		while (re.next()) {
			System.out.println(re.getInt("id") + "," + re.getString("nombre"));
			
		}
		ps.close();
		connection.close();
	} catch (Exception e) {
		System.out.println("exception" +  e.getMessage());
	}
	}
	
	
	public static void invoprocedure (int ID, String NOMBRE, int PRECIO, String DESCRIPCION) throws ImagingOpException, SQLException{
	 try {
		
		 connectionbd();
		 CallableStatement cs = connection.prepareCall("CALL PRO_DUCT (?,?,?,?)");
		 cs.setInt(1, ID);
		 cs.setString(2, NOMBRE);
		 cs.setInt(3, PRECIO);
		 cs.setString(4, DESCRIPCION);
		 cs.execute();
			System.out.println("EJECUTADO");

		 } catch (Exception e) {
		// TODO: handle exception
				System.out.println("exception" +  e.getMessage());

	}	
		
	}

	
	

	public static void main(String[] args)throws ImagingOpException, SQLException {
		// TODO Auto-generated method stub
		//connectionbd () ;
		//agregarproduc(6, "lalo", 5, "good fgfgfgf");
		//modtable("EMANUEL",2);
        //EliminarPRO(6);
		invoprocedure(10, "emili", 20, "bot");
		//consultareg();
		
        }

}
