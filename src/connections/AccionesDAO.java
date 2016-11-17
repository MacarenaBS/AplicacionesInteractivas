package connections;
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
public class AccionesDAO
{
	private static AccionesDAO objInstance;
	private Database objConnection;
	public static AccionesDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new AccionesDAO();
		}
		return objInstance;
	}
	private AccionesDAO() throws ConnectionException, ParameterException
	{
		super();
		this.setConnection();
	}
	private void setConnection() throws ConnectionException, ParameterException
	{
		this.objConnection = new Database(ConfigurationFileDAO.getInstance().getParameter("Server Name"), ConfigurationFileDAO.getInstance().getParameter("Database"), ConfigurationFileDAO.getInstance().getParameter("Username"), ConfigurationFileDAO.getInstance().getParameter("Password"));
		if (this.getConnection() == null)
		{
			throw new ConnectionException("No se pudo establecer la conexión con la base de datos");
		}
	}
	private Connection getConnection()
	{
		return this.objConnection.getConnection();
	}
	public ArrayList<Accion> getAccion(Reclamo objReclamo)
	{
		String strStatement;
		Accion objAccion;
		ArrayList<Accion> colAcciones;
		colAcciones = new ArrayList<Accion>();
		strStatement = "SELECT * FROM Acciones WHERE ";
		switch (objReclamo.getClass().getName())
		{
			case "reclamos.ReclamoFacturacion" : strStatement = strStatement.concat("strReclamoFacturacion=").concat(objReclamo.getNumero()); break;
			case "reclamos.ReclamoZona" : strStatement = strStatement.concat("strReclamoZona=").concat(objReclamo.getNumero()); break;
			case "reclamos.ReclamoInconsistencia" : strStatement = strStatement.concat("strReclamoInconsistencia=").concat(objReclamo.getNumero()); break;
		}
		try
		{
			ResultSet objReclamos = this.objConnection.getResultSet(strStatement);
			while (objReclamos.next())
			{
				objAccion = new Accion(objReclamos.getDate("objFecha"), objReclamos.getString("strDescripcion"));
				colAcciones.add(objAccion);
			}
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con una nueva acción");
		}
		return colAcciones;
	}
	public void insertar(Reclamo objReclamo, Accion objAccion)
	{
		String strStatement;
		SimpleDateFormat objSimpleDateFormatter;
		String strDate;
		objSimpleDateFormatter = new SimpleDateFormat("yyyyMMdd");
		strStatement = "INSERT INTO Acciones (objFecha, strDescripcion, ";
		switch (objReclamo.getClass().getName())
		{
			case "reclamos.ReclamoFacturacion" : strStatement = strStatement.concat("strReclamoFacturacion)"); break;
			case "reclamos.ReclamoZona" : strStatement = strStatement.concat("strReclamoZona)"); break;
			case "reclamos.ReclamoInconsistencia" : strStatement = strStatement.concat("strReclamoInconsistencia)"); break;
		}
		try
		{
			strDate = objSimpleDateFormatter.format(objAccion.getFecha());
			this.objConnection.executeQuery(strStatement.concat(
					"VALUES ( '").concat(
						strDate).concat("', '").concat(
						objAccion.getDescripcion()).concat("', '").concat(
						objReclamo.getNumero()).concat("')"));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con una nueva acción");
		}
	}
	public void close() throws ConnectionException
	{
		try
		{
			this.objConnection.close();
			objInstance = null;
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("No se puede cerrar la conexión con la base de Datos");
		}
	}
}
