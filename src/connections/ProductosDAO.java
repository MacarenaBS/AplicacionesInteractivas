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
import model.Factura;
import model.Producto;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Productos Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class ProductosDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static ProductosDAO objInstance;
	private Database objConnection;
	private List<Producto> colProductos;
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return Instance of the Data Access Object.
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	public static ProductosDAO getInstance() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==========Check If Instance Is Created============*/
		/*==================================================*/
		if (objInstance == null)
		{
			objInstance = new ProductosDAO();
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
	 * Create a new Producto Data Object Connection
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private ProductosDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colProductos = new ArrayList<Producto>();
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
	/*===================Get Producto===================*/
	/*==================================================*/
	/**
	 * Busca unn Producto por el número. Primero Busca en cache y si no lo encuentra lo busca en la base de datos.
	 * @param intNumero Producto a buscar
	 * @return Producto
	 * @throws ConnectionException La query no se pudo ejecutar en la base de datos
	 * @throws ParameterException 
	 * @throws ClienteException 
	 * @throws ProductosException No se encontró el producto especificado
	 */
	public Producto getProducto(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, ProductosException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Producto objProducto;
		/*==================================================*/
		/*===========Recuperar Producto Del Cache===========*/
		/*==================================================*/
		objProducto = this.getFromCache(intNumero);
		/*==================================================*/
		/*==============Verificar Si Encontro===============*/
		/*==================================================*/
		if (objProducto == null)
		{
			/*==================================================*/
			/*===========Recuperar Producto De La Base==========*/
			/*==================================================*/
			objProducto = this.getFromDatabase(intNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objProducto;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Get Producto From Cache=============*/
	/*==================================================*/
	/**
	 * Recupera un Producto de la cache.
	 * @param intNumero Código del producto a recuperar
	 * @return Producto
	 */
	private Producto getFromCache(Integer intNumero)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Iterator<Producto> objIterator;
		Producto objProducto;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objProducto = null;
		bolEncontro = false;
		objIterator = this.colProductos.iterator();
		/*==================================================*/
		/*=================Loop de Productos================*/
		/*==================================================*/
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			/*==================================================*/
			/*=================Obtener Producto=================*/
			/*==================================================*/
			objProducto = objIterator.next();
			/*==================================================*/
			/*==============Verificar Si Coincide===============*/
			/*==================================================*/
			bolEncontro = (objProducto.getCodigo() == intNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objProducto;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*============Get Producto From Database============*/
	/*==================================================*/
	/**
	 * Recupera unn producto de una tabla específica de la base de datos.
	 * @param intNumero Código del producto a recuperar
	 * @return ReclamoZona
	 * @throws ConnectionException No se pudo ejecutar la query a la base de datos
	 * @throws ParameterException
	 * @throws ClienteException 
	 * @throws ProductosException No se encontró el producto especificado
	 */
	private Producto getFromDatabase(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, ProductosException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Producto objProducto;
		ResultSet objProductos;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objProducto = null;
		/*==================================================*/
		/*==============Attempt Producto Query==============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*===================Get Productos==================*/
			/*==================================================*/
			objProductos = this.objConnection.getResultSet("SELECT * FROM Productos WHERE intCodigo = ".concat(String.valueOf(intNumero)));
			/*==================================================*/
			/*==============Verificar Si Encontró===============*/
			/*==================================================*/
			if (objProductos.next())
			{
				/*==================================================*/
				/*==================Crear Producto==================*/
				/*==================================================*/
				objProducto = new Producto(objProductos.getInt("intCodigo"), objProductos.getString("strTitulo"), objProductos.getString("strDescripcion"), objProductos.getFloat("fltPrecio"), objProductos.getBoolean("bolActivo"));
			}
			else
			{
				/*==================================================*/
				/*No se Encontró Ningún Registro con el Número Especificado*/
				/*==================================================*/
				throw new ProductosException("No existe ningunn producto con el número ".concat(String.valueOf(intNumero)).concat(" en los registros almacenados."));
			}
		}
		/*==================================================*/
		/*=========Catch Contact List Query Errors==========*/
		/*==================================================*/
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===========No se pudo ejecutar la query===========*/
			/*==================================================*/
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objProducto;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Insertar en Base=================*/
	/*==================================================*/
	/**
	 * Inserta el Producto en la tabla Productos de la base de datos
	 * @param objProducto Producto a insertar
	 * @throws ProductosException 
	 * @throws FacturasException 
	 * @throws ParameterException 
	 * @throws ClienteException 
	 * @throws ConnectionException 
	 */
	private void insertarEnBase(Producto objProducto, Factura objFactura) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Insert=================*/
			/*==================================================*/
			this.objConnection.executeQuery("INSERT INTO Productos (intCodigo, strTitulo, strDescripcion, fltPrecio, bolActivo, intFactura)".concat(
											"VALUES ( ").concat(
												String.valueOf(objProducto.getCodigo())).concat(", ").concat(
												objProducto.getTitulo()).concat(", ").concat(
												objProducto.getDescripcion()).concat(", ").concat(
												String.valueOf(objProducto.getPrecio())).concat(", ").concat(
												String.valueOf(1)).concat(", ").concat(
												String.valueOf(FacturasDAO.getInstance().getFactura(objFactura.getNumero()))).concat(")"));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo producto");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Insertar=====================*/
	/*==================================================*/
	/**
	 * Inserta un producto en el cache y la tabla correspondiente de la base de datos.
	 * @param objProducto Producto a insertar
	 * @throws ProductosException 
	 * @throws FacturasException 
	 * @throws ParameterException 
	 * @throws ClienteException 
	 * @throws ConnectionException 
	 */
	public void insertar(Producto objProducto, Factura objFactura) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		/*==================================================*/
		/*=========Agrega El Producto a la Cache============*/
		/*==================================================*/
		this.colProductos.add(objProducto);
		/*==================================================*/
		/*=========Inserta el Producto en la Tabla==========*/
		/*==================================================*/
		this.insertarEnBase(objProducto, objFactura);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===============Eliminar de la Cache===============*/
	/*==================================================*/
	/**
	 * Elimina un producto de la cache.
	 * @param objProducto Producto a eliminar
	 */
	private void eliminarEnCache(Producto objProducto)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Producto objActual;
		Iterator<Producto> objIterator;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		bolEncontro = false;
		objIterator = this.colProductos.iterator();
		/*==================================================*/
		/*================Loop de Productos=================*/
		/*==================================================*/
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			/*==================================================*/
			/*=================Obtener Cliente==================*/
			/*==================================================*/
			objActual = objIterator.next();
			/*==================================================*/
			/*==============Verificar Si Coincide===============*/
			/*==================================================*/
			if (objActual.equals(objProducto))
			{
				/*==================================================*/
				/*=============Cambiar Flag de Búsqueda=============*/
				/*==================================================*/
				bolEncontro = true;
				/*==================================================*/
				/*========Elimina El Producto a la Cache============*/
				/*==================================================*/
				this.colProductos.remove(objProducto);
			}
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Eliminar en Base=================*/
	/*==================================================*/
	/**
	 * Elimina el Producto en la tabla Producto de la base de datos
	 * @param objProducto Producto a eliminar
	 */
	private void eliminarEnBase(Producto objProducto)
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Delete=================*/
			/*==================================================*/
			this.objConnection.executeQuery("DELETE FROM Productos WHERE intCodigo = ".concat(String.valueOf(objProducto.getCodigo())));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al eliminar de la base de datos con un producto");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Eliminar=====================*/
	/*==================================================*/
	/**
	 * Elimina un Producto en el cache y la tabla correspondiente de la base de datos.
	 * @param objProducto Producto a eliminar
	 */
	public void eliminar(Producto objProducto)
	{
		/*==================================================*/
		/*========Elimina El Producto a la Cache============*/
		/*==================================================*/
		this.eliminarEnCache(objProducto);
		/*==================================================*/
		/*========Elimina el Producto en la Tabla==========*/
		/*==================================================*/
		this.eliminarEnBase(objProducto);
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