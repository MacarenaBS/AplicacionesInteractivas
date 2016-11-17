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
import model.Producto;
public class ProductosDAO
{
	private static ProductosDAO objInstance;
	private Database objConnection;
	private List<Producto> colProductos;
	public static ProductosDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new ProductosDAO();
		}
		return objInstance;
	}
	private ProductosDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colProductos = new ArrayList<Producto>();
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
	public Producto getProducto(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, ProductosException
	{
		Producto objProducto;
		objProducto = this.getFromCache(intNumero);
		if (objProducto == null)
		{
			objProducto = this.getFromDatabase(intNumero);
		}
		return objProducto;
	}
	private Producto getFromCache(Integer intNumero)
	{		
//		for (Producto p: this.colProductos)
//		{
//			if (p.esProducto(intNumero))
//				return p;
//		}
//		return null; 
		Boolean bolEncontro;
		Iterator<Producto> objIterator;
		Producto objProducto;
		objProducto = null;
		bolEncontro = false;
		objIterator = this.colProductos.iterator();
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			objProducto = objIterator.next();
			bolEncontro = (objProducto.getCodigo() == intNumero);
		}
		return objProducto;
	}
	private Producto getFromDatabase(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, ProductosException
	{
		Producto objProducto;
		ResultSet objProductos;
		objProducto = null;
		try
		{
			objProductos = this.objConnection.getResultSet("SELECT * FROM Productos WHERE intCodigo = ".concat(String.valueOf(intNumero)));
			if (objProductos.next())
			{
				objProducto = new Producto(objProductos.getInt("intCodigo"), objProductos.getString("strTitulo"), objProductos.getString("strDescripcion"), objProductos.getFloat("fltPrecio"), objProductos.getInt("bolActivo") == 1 ? true : false);
			}
			else
			{
				throw new ProductosException("No existe ningunn producto con el número ".concat(String.valueOf(intNumero)).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objProducto;
	}
	private void insertarEnBase(Producto objProducto) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		try
		{
			this.objConnection.executeQuery("INSERT INTO Productos (intCodigo, strTitulo, strDescripcion, fltPrecio, bolActivo)".concat(
											"VALUES ( ").concat(
												String.valueOf(objProducto.getCodigo())).concat(", '").concat(
												objProducto.getTitulo()).concat("', '").concat(
												objProducto.getDescripcion()).concat("', ").concat(
												String.valueOf(objProducto.getPrecio())).concat(", ").concat(
												String.valueOf(1)).concat(")"));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo producto");
		}
	}
	public void insertar(Producto objProducto) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		if (this.colProductos.contains(objProducto))
		{
			this.colProductos.add(objProducto);
		}
		this.insertarEnBase(objProducto);
	}
	public void modificarProducto(Producto objProducto)
	{
		this.eliminarEnCache(objProducto);
		this.colProductos.add(objProducto);
		try
		{
			this.objConnection.executeQuery("UPDATE Productos SET strTitulo = '".concat(
											objProducto.getTitulo()).concat("', strDescripcion = '").concat(
											objProducto.getDescripcion()).concat("', fltPrecio = ").concat(
											String.valueOf(objProducto.getPrecio())).concat(", bolActivo = ").concat(objProducto.getBolActivo()?
											String.valueOf(1): String.valueOf(0)).concat(" WHERE intCodigo = ").concat(String.valueOf(objProducto.getCodigo())));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo producto");
		}
	}
	private void eliminarEnCache(Producto objProducto)
	{
		if (this.colProductos.contains(objProducto))
		{
			this.colProductos.remove(objProducto);
		}
//		Boolean bolEncontro;
//		Producto objActual;
//		Iterator<Producto> objIterator;
//		bolEncontro = false;
//		objIterator = this.colProductos.iterator();
//		while ((objIterator.hasNext()) && (!bolEncontro))
//		{
//			objActual = objIterator.next();
//			if (objActual.equals(objProducto))
//			{
//				bolEncontro = true;
//				this.colProductos.remove(objProducto);
//			}
//		}
	}
	private void eliminarEnBase(Producto objProducto)
	{
		try
		{
			this.objConnection.executeQuery("DELETE FROM Productos WHERE intCodigo = ".concat(String.valueOf(objProducto.getCodigo())));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al eliminar de la base de datos con un producto");
		}
	}
	public void eliminar(Producto objProducto)
	{
		this.eliminarEnCache(objProducto);
		this.eliminarEnBase(objProducto);
	}
	public String newID()
	{
		String strAnswer;
		ResultSet objResultSet;
		strAnswer = null;
		try
		{
			objResultSet = this.objConnection.getResultSet("SELECT COUNT(*) AS cantidad FROM productos");
			objResultSet.next();
			strAnswer = "PROD".concat(String.valueOf(objResultSet.getInt("cantidad")));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la cantidad de productos de la base");
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
