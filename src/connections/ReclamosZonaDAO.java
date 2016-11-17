package connections;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import connections.Database;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.ReclamoException;
import reclamos.ReclamoZona;
public class ReclamosZonaDAO
{
	private static ReclamosZonaDAO objInstance;
	private Database objConnection;
	private List<ReclamoZona> colReclamos;
	public static ReclamosZonaDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new ReclamosZonaDAO();
		}
		return objInstance;
	}
	private ReclamosZonaDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colReclamos = new ArrayList<ReclamoZona>();
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
	public ReclamoZona getReclamo(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException
	{
		ReclamoZona objReclamo;
		objReclamo = this.getFromCache(strNumero);
		if (objReclamo == null)
		{
			objReclamo = this.getFromDatabase(strNumero);
		}
		return objReclamo;
	}
	private ReclamoZona getFromCache(String strNumero)
	{
		Boolean bolEncontro;
		Iterator<ReclamoZona> objIterator;
		ReclamoZona objReclamo;
		objReclamo = null;
		bolEncontro = false;
		objIterator = this.colReclamos.iterator();
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			objReclamo = objIterator.next();
			bolEncontro = (objReclamo.getNumero().equalsIgnoreCase(strNumero));
		}
		return objReclamo;
	}
	private ReclamoZona getFromDatabase(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException
	{
		ReclamoZona objReclamo;
		ResultSet objReclamos;
		objReclamo = null;
		try
		{
			objReclamos = this.objConnection.getResultSet("SELECT * FROM ReclamosZona WHERE strNumero = ".concat(strNumero));
			if (objReclamos.next())
			{
				objReclamo = new ReclamoZona(objReclamos.getString("strNumero"), objReclamos.getString("strDescripcion"), objReclamos.getString("strEstado"), objReclamos.getString("strZona"), ClientesDAO.getInstance().getCliente(objReclamos.getInt("intCliente")));
				objReclamo.agregarAcciones(AccionesDAO.getInstance().getAccion(objReclamo));			
			}
			else
			{
				throw new ReclamoException("No existe ningún reclamo de zona con el número ".concat(strNumero).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objReclamo;
	}
	private void removerDeCache(ReclamoZona objReclamo)
	{
		if (this.colReclamos.contains(objReclamo))
		{
			this.colReclamos.remove(objReclamo);
		}
//		Boolean bolEncontro;
//		Iterator<ReclamoZona> objIterator;
//		ReclamoZona objActual;
//		bolEncontro = false;
//		objIterator = this.colReclamos.iterator();
//		while ((objIterator.hasNext()) && (!bolEncontro))
//		{
//			objActual = objIterator.next();
//			if (objActual.equals(objReclamo))
//			{
//				this.colReclamos.remove(objActual);
//				bolEncontro = true;
//			}
//		}
	}
	private void actualizarBase(ReclamoZona objReclamo)
	{
		try
		{
			this.objConnection.executeQuery("UPDATE ReclamosZona ".concat(
												"SET strDescripcion = '").concat(objReclamo.getDescripción()).concat("', ").concat(
												"strEstado = '").concat(objReclamo.getEstado().toString()).concat("', ").concat(
												"strZona = '").concat(objReclamo.getZona()).concat(
											"' WHERE strNumero = ").concat(String.valueOf(objReclamo.getNumero())));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un Reclamo de Zona");
		}
	}
	public void actualizar(ReclamoZona objReclamo)
	{
		this.removerDeCache(objReclamo);
		this.colReclamos.add(objReclamo);
		this.actualizarBase(objReclamo);
	}
	private void insertarEnBase(ReclamoZona objReclamo) throws ConnectionException, ParameterException
	{
		try
		{
			this.objConnection.executeQuery("INSERT INTO ReclamosZona (strNumero, strDescripcion, strEstado, strZona, intCliente)".concat(
											"VALUES ( ").concat("'").concat(
												objReclamo.getNumero()).concat("', '").concat(
												objReclamo.getDescripción()).concat("', '").concat(
												objReclamo.getEstado().toString()).concat("', '").concat(
												objReclamo.getZona()).concat("', ").concat(
												String.valueOf(objReclamo.getCliente().getCodigoPersona())).concat(")"));
			
			objReclamo= new ReclamoZona(objReclamo.getNumero(),objReclamo.getDescripción(),objReclamo.getCliente(),objReclamo.getZona());
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Reclamo de Zona");
		}
	}
	public void insertar(ReclamoZona objReclamo) throws ConnectionException, ParameterException
	{
		if (!this.colReclamos.contains(objReclamo))
		{
			this.colReclamos.add(objReclamo);
		}
		this.insertarEnBase(objReclamo);
	}
	public String newId() throws ConnectionException
	{
		String strAnswer;
		ResultSet objAnswer;
		strAnswer = null;
		try
		{
			objAnswer = this.objConnection.getResultSet("SELECT COUNT(*) AS Cantidad FROM ReclamosZona");
			if (objAnswer == null)
			{
				throw new ConnectionException("No se pudo recuperar la consulta de la base de datos");
			}
			else
			{
				objAnswer.next();
				strAnswer = "RECZONA".concat(String.valueOf(objAnswer.getInt("Cantidad")));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error en la conexión con la base de datos");
		}
		return strAnswer;
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
