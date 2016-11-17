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
import exceptions.ProductosException;
import exceptions.ReclamoException;
import reclamos.ReclamoInconsistencia;
public class ReclamosInconsistenciasDAO
{
	private static ReclamosInconsistenciasDAO objInstance;
	private Database objConnection;
	private List<ReclamoInconsistencia> colReclamos;
	public static ReclamosInconsistenciasDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new ReclamosInconsistenciasDAO();
		}
		return objInstance;
	}
	private ReclamosInconsistenciasDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colReclamos = new ArrayList<ReclamoInconsistencia>();
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
	public ReclamoInconsistencia getReclamo(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException, ProductosException
	{
		ReclamoInconsistencia objReclamo;
		objReclamo = this.getFromCache(strNumero);
		if (objReclamo == null)
		{
			objReclamo = this.getFromDatabase(strNumero);
		}
		return objReclamo;
	}
	private ReclamoInconsistencia getFromCache(String strNumero)
	{
//		for (ReclamoInconsistencia ri: this.colReclamos)
//		{
//			if (ri.getNumero().equalsIgnoreCase(strNumero)){
//				return ri;
//			}
//		}
//		return null; 
		Boolean bolEncontro;
		Iterator<ReclamoInconsistencia> objIterator;
		ReclamoInconsistencia objReclamo;
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
	private ReclamoInconsistencia getFromDatabase(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException, ProductosException
	{
//		Integer intCantidad;
		ReclamoInconsistencia objReclamo;
//		ResultSet objCantidad;
		ResultSet objReclamos;
		objReclamo = null;
		try
		{
			objReclamos = this.objConnection.getResultSet("SELECT * FROM ReclamosInconsistencias WHERE strNumero = ".concat(strNumero));
			if (objReclamos.next())
			{
				
				//Un reclamo de inconsistencia es por 1 producto, la cantidad no debería estar ahi también? No habla de hacer nada con la factura en este reclamo. 
				//Verificar con las tablas de la base de datos si se almacena la cantidad en la tabla productos
				objReclamo= new ReclamoInconsistencia(objReclamos.getString("strNumero"), objReclamos.getString("strDescripcion"), objReclamos.getString("strEstado"), ProductosDAO.getInstance().getProducto(objReclamos.getInt("intProducto")), objReclamos.getInt("intCantidad"), ClientesDAO.getInstance().getCliente(objReclamos.getInt("intCliente")));
				objReclamo.agregarAcciones(AccionesDAO.getInstance().getAccion(objReclamo));
//				objCantidad = this.objConnection.getResultSet("SELECT COUNT(*) AS Cantidad FROM Productos WHERE intCodigo = ".concat(String.valueOf(objReclamos.getInt("intCodigo"))));
//				if (objCantidad.next())
//				{
//					intCantidad = objCantidad.getInt("Cantidad");
//					objReclamo = new ReclamoInconsistencia(objReclamos.getString("strNumero"), objReclamos.getString("strDescripcion"), objReclamos.getString("strEstado"), ProductosDAO.getInstance().getProducto(objReclamos.getInt("intCodigo")), intCantidad, ClientesDAO.getInstance().getCliente(objReclamos.getInt("intCliente")));
//				}
			}
			else
			{
				throw new ReclamoException("No existe ningún reclamo de inconsistencia con el número ".concat(strNumero).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objReclamo;
	}
	private void removerDeCache(ReclamoInconsistencia objReclamo)
	{
		if (this.colReclamos.contains(objReclamo))
		{
			this.colReclamos.remove(objReclamo);
		}
//		Boolean bolEncontro;
//		Iterator<ReclamoInconsistencia> objIterator;
//		ReclamoInconsistencia objActual;
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
	private void actualizarBase(ReclamoInconsistencia objReclamo)
	{
		try
		{
			String strQuery = "UPDATE ReclamosInconsistencias SET strDescripcion='"+objReclamo.getDescripción()+"', strEstado='"
			+objReclamo.getEstado().toString()+"' WHERE strNumero='"+objReclamo.getNumero()+"'";
			this.objConnection.executeQuery(strQuery);
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un Reclamo de Inconsistencia");
		}
	}
	public void actualizar(ReclamoInconsistencia objReclamo)
	{
		this.removerDeCache(objReclamo);
		this.colReclamos.add(objReclamo);
		this.actualizarBase(objReclamo);
	}
	private void insertarEnBase(ReclamoInconsistencia objReclamo) throws ConnectionException, ParameterException
	{
		try
		{
//			String strQuery = "INSERT INTO ReclamosInconsistencias (strNumero, strDescripcion, strEstado, intProducto, intCantidad, intCliente) VALUES ("
//					+objReclamo.getNumero()+",'"+objReclamo.getDescripción()+"','"+objReclamo.getEstado().toString()+"',"+objReclamo.getItemFactura().getProducto().getCodigo()+","
//					+objReclamo.getItemFactura().getCantidad()+","+objReclamo.getCliente().getCodigoPersona()+")";		
//			this.objConnection.executeQuery(strQuery);
			this.objConnection.executeQuery("INSERT INTO ReclamosInconsistencias (strNumero, strDescripcion, strEstado, intProducto, intCantidad, intCliente)".concat(
											"VALUES ( '").concat("'").concat(
												objReclamo.getNumero()).concat("', '").concat(
												objReclamo.getDescripción()).concat("', '").concat(
												objReclamo.getEstado().toString()).concat("', ").concat(
												String.valueOf(objReclamo.getItemFactura().getProducto().getCodigo())).concat(", ").concat(String.valueOf(objReclamo.getItemFactura().getCantidad())).concat(
												String.valueOf(objReclamo.getCliente().getCodigoPersona())).concat(")"));
//			No entiendo por que crea el reclamo. El reclamo ya tiene que venir dado como parametro de la función.			
//			objReclamo= new ReclamoInconsistencia(objReclamo.getNumero(), objReclamo.getDescripción(), objReclamo.getItemFactura().getProducto(), objReclamo.getItemFactura().getCantidad(), objReclamo.getCliente());
			//Insertar un item factura. a desarrollar --
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Reclamo de Inconsistencia");
		}
	}
	public void insertar(ReclamoInconsistencia objReclamo) throws ConnectionException, ParameterException
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
			objAnswer = this.objConnection.getResultSet("SELECT COUNT(*) AS Cantidad FROM ReclamosInconsistencias");
			if (objAnswer == null)
			{
				throw new ConnectionException("No se pudo recuperar la consulta de la base de datos");
			}
			else
			{
				objAnswer.next();
				strAnswer = "RECINCON".concat(String.valueOf(objAnswer.getInt("Cantidad")));
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
