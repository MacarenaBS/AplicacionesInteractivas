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
import connections.Database;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import model.Factura;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Facturas Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class FacturasDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static FacturasDAO objInstance;
	private Database objConnection;
	private List<Factura> colFacturas;
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return Instance of the Data Access Object.
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	public static FacturasDAO getInstance() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==========Check If Instance Is Created============*/
		/*==================================================*/
		if (objInstance == null)
		{
			objInstance = new FacturasDAO();
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
	 * Create a new Factura Data Object Connection
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private FacturasDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colFacturas = new ArrayList<Factura>();
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
	/*===================Get Factura====================*/
	/*==================================================*/
	/**
	 * Busca una Factura por el número. Primero Busca en cache y si no lo encuentra lo busca en la base de datos.
	 * @param intNumero Factura a buscar
	 * @return Factura
	 * @throws ConnectionException La query no se pudo ejecutar en la base de datos
	 * @throws ParameterException 
	 * @throws ClienteException 
	 * @throws FacturasException No se encontró la factura especificada
	 * @throws ProductosException No se encontró el producto especificado
	 */
	public Factura getFactura(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Factura objFactura;
		/*==================================================*/
		/*===========Recuperar Factura Del Cache============*/
		/*==================================================*/
		objFactura = this.getFromCache(intNumero);
		/*==================================================*/
		/*==============Verificar Si Encontro===============*/
		/*==================================================*/
		if (objFactura == null)
		{
			/*==================================================*/
			/*===========Recuperar Factura De La Base===========*/
			/*==================================================*/
			objFactura = this.getFromDatabase(intNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objFactura;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Get Factura From Cache==============*/
	/*==================================================*/
	/**
	 * Recupera una Factura de la cache.
	 * @param intNumero Número de factura a recuperar
	 * @return Factura
	 */
	private Factura getFromCache(Integer intNumero)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Iterator<Factura> objIterator;
		Factura objFactura;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objFactura = null;
		bolEncontro = false;
		objIterator = this.colFacturas.iterator();
		/*==================================================*/
		/*=================Loop de Facturas=================*/
		/*==================================================*/
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			/*==================================================*/
			/*=================Obtener Factura==================*/
			/*==================================================*/
			objFactura = objIterator.next();
			/*==================================================*/
			/*==============Verificar Si Coincide===============*/
			/*==================================================*/
			bolEncontro = (objFactura.getNumero() == intNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objFactura;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*============Get Factura From Database=============*/
	/*==================================================*/
	/**
	 * Recupera una Factura de una tabla específica de la base de datos.
	 * @param intNumero Número de reclamo a recuperar
	 * @return ReclamoZona
	 * @throws ConnectionException No se pudo ejecutar la query a la base de datos
	 * @throws ParameterException
	 * @throws ClienteException
	 * @throws FacturasException No se encontró la factura en la base de datos 
	 * @throws ProductosException No se encontró el producto especificado
	 */
	private Factura getFromDatabase(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Factura objFactura;
		ResultSet objFacturas;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objFactura = null;
		/*==================================================*/
		/*==============Attempt Facturas Query==============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*===================Get Facturas===================*/
			/*==================================================*/
			objFacturas = this.objConnection.getResultSet("SELECT * FROM Facturas WHERE intNumero = ".concat(String.valueOf(intNumero)));
			/*==================================================*/
			/*==============Verificar Si Encontró===============*/
			/*==================================================*/
			if (objFacturas.next())
			{
				/*==================================================*/
				/*==================Crear Factura===================*/
				/*==================================================*/
				objFactura = new Factura(objFacturas.getInt("intNumero"), objFacturas.getString("strDescripcion"), objFacturas.getDate("objFecha"));
				/*==================================================*/
				/*===================Agregar Item===================*/
				/*==================================================*/
				objFactura.addItem(ProductosDAO.getInstance().getProducto(objFacturas.getInt("intProducto")), objFacturas.getInt("intCantidad"));
				/*==================================================*/
				/*================Loop Items Factura================*/
				/*==================================================*/
				while (objFacturas.next())
				{
					/*==================================================*/
					/*===================Agregar Item===================*/
					/*==================================================*/
					objFactura.addItem(ProductosDAO.getInstance().getProducto(objFacturas.getInt("intProducto")), objFacturas.getInt("intCantidad"));
				}
			}
			else
			{
				/*==================================================*/
				/*No se Encontró Ningún Registro con el Número Especificado*/
				/*==================================================*/
				throw new FacturasException("No existe ninguna factura con el número ".concat(String.valueOf(intNumero)).concat(" en los registros almacenados."));
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
		return objFactura;
	}
	/*==================================================*/
	/*===================End Function===================*/
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