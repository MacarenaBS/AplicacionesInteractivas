/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package connections;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connections.Database;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import model.Accion;
import reclamos.Reclamo;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Acciones Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class AccionesDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static AccionesDAO objInstance;
	private Database objConnection;
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return Instance of the Data Access Object.
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	public static AccionesDAO getInstance() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==========Check If Instance Is Created============*/
		/*==================================================*/
		if (objInstance == null)
		{
			objInstance = new AccionesDAO();
		}
		/*==================================================*/
		/*==================Return Instance=================*/
		/*==================================================*/
		return objInstance;
	}
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Create a new Accion Data Object Connection
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private AccionesDAO() throws ConnectionException, ParameterException
	{
		super();
		this.setConnection();
	}
	/*==================================================*/
	/*===============End Constructor====================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Set Database===================*/
	/*==================================================*/
	/**
	 * Connection to the Database
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private void setConnection() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*===================Set Database===================*/
		/*==================================================*/
		this.objConnection = new Database(ConfigurationFileDAO.getInstance().getParameter("Server Name"), ConfigurationFileDAO.getInstance().getParameter("Database"), ConfigurationFileDAO.getInstance().getParameter("Username"), ConfigurationFileDAO.getInstance().getParameter("Password"));
		/*==================================================*/
		/*=================Check Connection=================*/
		/*==================================================*/
		if (this.getConnection() == null)
		{
			/*==================================================*/
			/*==========No Se Pudo Connectar a la Base==========*/
			/*==================================================*/
			throw new ConnectionException("No se pudo establecer la conexión con la base de datos");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Database===================*/
	/*==================================================*/
	/**
	 * @return Connection to the Database
	 */
	private Connection getConnection()
	{
		/*==================================================*/
		/*==================Return Database=================*/
		/*==================================================*/
		return this.objConnection.getConnection();
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Insertar en Base=================*/
	/*==================================================*/
	/**
	 * Trae las acciones cargadas en la bd del objeto reclamo
	 * @param objReclamo Reclamo asociado a la acción
	 * @param objAccion Acción a insertar
	 */
	public ArrayList<Accion> getAccion(Reclamo objReclamo)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		String strStatement;
		ArrayList<Accion> acciones= new ArrayList<Accion>();
		/*==================================================*/
		/*================Iniciar Statement=================*/
		/*==================================================*/
		strStatement = "SELECT * FROM Acciones WHERE ";
		/*==================================================*/
		/*=============Evaluar Tipo de Reclamo==============*/
		/*==================================================*/
		switch (objReclamo.getClass().getName())
		{
			case "reclamos.ReclamoFacturacion" : strStatement = strStatement.concat("strReclamoFacturacion=").concat(objReclamo.getNumero()); break;
			case "reclamos.ReclamoZona" : strStatement = strStatement.concat("strReclamoZona=").concat(objReclamo.getNumero()); break;
			case "reclamos.ReclamoInconsistencia" : strStatement = strStatement.concat("strReclamoInconsistencia=").concat(objReclamo.getNumero()); break;
		}
		try
		{
			ResultSet objReclamos = this.objConnection.getResultSet(strStatement);
			
			while (objReclamos.next()){
				Accion a= new Accion(objReclamos.getDate("objFecha"), objReclamos.getString("strDescripcion"));
				acciones.add(a);
			}
			
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con una nueva acción");
		}
		return acciones;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	
	public void insertar(Reclamo objReclamo, Accion objAccion)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		String strStatement;
		/*==================================================*/
		/*================Iniciar Statement=================*/
		/*==================================================*/
		strStatement = "INSERT INTO Acciones (objFecha, strDescripcion, ";
		/*==================================================*/
		/*=============Evaluar Tipo de Reclamo==============*/
		/*==================================================*/
		switch (objReclamo.getClass().getName())
		{
			case "reclamos.ReclamoFacturacion" : strStatement = strStatement.concat("strReclamoFacturacion)"); break;
			case "reclamos.ReclamoZona" : strStatement = strStatement.concat("strReclamoZona)"); break;
			case "reclamos.ReclamoInconsistencia" : strStatement = strStatement.concat("strReclamoInconsistencia)"); break;
		}
		try
		{
			
			//Formateo la fecha para insertarla en sql
			System.out.println(String.valueOf(objAccion.getFecha())); 
			
			SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
			String date=sdf.format(objAccion.getFecha());
			System.out.println(date);
			
			
			
			this.objConnection.executeQuery(strStatement.concat(
					"VALUES ( '").concat(
						date).concat("', '").concat(
						objAccion.getDescripcion()).concat("', '").concat(
						objReclamo.getNumero()).concat("')"));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con una nueva acción");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*======================Close=======================*/
	/*==================================================*/
	/**
	 * Close the connection to the database. In Addition it resets the Instance.
	 * @throws ConnectionException No se puede cerrar la conexión con la base de datos
	 */
	public void close() throws ConnectionException
	{
		/*==================================================*/
		/*============Attempts Connection Close=============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*============Close Database Connection=============*/
			/*==================================================*/
			this.objConnection.close();
			/*==================================================*/
			/*==================Reset Instance==================*/
			/*==================================================*/
			objInstance = null;
		}
		/*==================================================*/
		/*===============Catch Closing Errors===============*/
		/*==================================================*/
		catch (SQLException objException)
		{
			/*==================================================*/
			/*=============Error al Cerrar la Base==============*/
			/*==================================================*/
			throw new ConnectionException("No se puede cerrar la conexión con la base de Datos");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/