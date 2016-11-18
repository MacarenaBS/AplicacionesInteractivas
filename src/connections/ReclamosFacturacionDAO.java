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
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.ReclamoException;
import model.Factura;
import reclamos.ReclamoFacturacion;
import usuarios.Cliente;
public class ReclamosFacturacionDAO
{
	private static ReclamosFacturacionDAO objInstance;
	private Database objConnection;
	private List<ReclamoFacturacion> colReclamos;
	public static ReclamosFacturacionDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new ReclamosFacturacionDAO();
		}
		return objInstance;
	}
	private ReclamosFacturacionDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colReclamos = new ArrayList<ReclamoFacturacion>();
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
	public ReclamoFacturacion getReclamo(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		ReclamoFacturacion objReclamo;
		objReclamo = this.getFromCache(strNumero);
		if (objReclamo == null)
		{
			objReclamo = this.getFromDatabase(strNumero);
		}
		return objReclamo;
	}
	private ReclamoFacturacion getFromCache(String strNumero)
	{
//		for (ReclamoFacturacion rf: this.colReclamos)
//		{
//			if (rf.getNumero().equalsIgnoreCase(strNumero)){
//				return rf;
//			}
//		}
//		return null; 
		Boolean bolEncontro;
		Iterator<ReclamoFacturacion> objIterator;
		ReclamoFacturacion objReclamo;
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
	private ReclamoFacturacion getFromDatabase(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		ReclamoFacturacion objReclamo;
		ResultSet objReclamos;
		objReclamo = null;
		try
		{
			objReclamos = this.objConnection.getResultSet("SELECT * FROM ReclamosFacturacion WHERE strNumero = ".concat(strNumero));
			if (objReclamos.next())
			{
				objReclamo = new ReclamoFacturacion(objReclamos.getString("strNumero"), objReclamos.getString("strDescripcion"), objReclamos.getString("strEstado"), ClientesDAO.getInstance().getCliente(objReclamos.getInt("intCliente")), FacturasDAO.getInstance().getFactura(objReclamos.getInt("intFactura")));
				objReclamo.agregarAcciones(AccionesDAO.getInstance().getAccion(objReclamo));
			}
			else
			{
				throw new ReclamoException("No existe ningún reclamo de facturación con el número ".concat(strNumero).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objReclamo;
	}
	private void removerDeCache(ReclamoFacturacion objReclamo)
	{
		if (this.colReclamos.contains(objReclamo))
		{
			this.colReclamos.remove(objReclamo);
		}
//		Boolean bolEncontro;
//		Iterator<ReclamoFacturacion> objIterator;
//		ReclamoFacturacion objActual;
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
	private void actualizarBase(ReclamoFacturacion objReclamo)
	{
		try
		{
			this.objConnection.executeQuery("UPDATE ReclamosFacturacion ".concat(
												"SET strDescripcion = '").concat(objReclamo.getDescripción()).concat("', ").concat(
												"strEstado = '").concat(objReclamo.getEstado().toString()).concat(
											"' WHERE strNumero = ").concat(String.valueOf(objReclamo.getNumero())));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un Reclamo de Zona");
		}
	}
	public void actualizar(ReclamoFacturacion objReclamo)
	{
		this.removerDeCache(objReclamo); //VER!!
		this.colReclamos.add(objReclamo);
		this.actualizarBase(objReclamo);
	}
	public void insertarEnBase(/*ReclamoFacturacion objReclamo*/String strDescripcion, Cliente objCliente, Factura objFactura) throws ConnectionException, ParameterException
	{
		try
		{
			String codigo= newId();
			this.objConnection.executeQuery("INSERT INTO ReclamosFacturacion (strNumero, strDescripcion, strEstado, intFactura, intCliente)".concat(
											"VALUES ( ").concat("'").concat(
												codigo).concat("', '").concat(
												strDescripcion).concat("', '").concat(
												"ingresado").concat("', ").concat(
												String.valueOf(objFactura.getNumero())).concat(", ").concat(
												String.valueOf(objCliente.getCodigoPersona())).concat(")"));
			
			ReclamoFacturacion objReclamo = new ReclamoFacturacion(codigo,strDescripcion, objCliente, objFactura);
			
			if (!this.colReclamos.contains(objReclamo))
			{
				this.colReclamos.add(objReclamo);
			}
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Reclamo de Facturacion");
		}
	}
//	public void insertar(ReclamoFacturacion objReclamo) throws ConnectionException, ParameterException
//	{
//		if (!this.colReclamos.contains(objReclamo))
//		{
//			this.colReclamos.add(objReclamo);
//		}
//		this.insertarEnBase(objReclamo);
//	}
	public String newId() throws ConnectionException
	{
		String strAnswer;
		ResultSet objAnswer;
		strAnswer = null;
		try
		{
			objAnswer = this.objConnection.getResultSet("SELECT COUNT(*) AS Cantidad FROM ReclamosFacturacion");
			if (objAnswer == null)
			{
				throw new ConnectionException("No se pudo recuperar la consulta de la base de datos");
			}
			else
			{
				objAnswer.next();
				int cantidad= objAnswer.getInt("Cantidad")+1;
				strAnswer = (String.valueOf(cantidad));
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