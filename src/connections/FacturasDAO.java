package connections;
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
public class FacturasDAO
{
	private static FacturasDAO objInstance;
	private Database objConnection;
	private List<Factura> colFacturas;
	public static FacturasDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new FacturasDAO();
		}
		return objInstance;
	}
	private FacturasDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colFacturas = new ArrayList<Factura>();
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
	public Factura getFactura(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		Factura objFactura;
		objFactura = this.getFromCache(intNumero);
		if (objFactura == null)
		{
			objFactura = this.getFromDatabase(intNumero);
		}
		return objFactura;
	}
	private Factura getFromCache(Integer intNumero)
	{
//		for (Factura f: this.colFacturas)
//		{
//			if (f.isFactura(intNumero))
//				return f;
//		}
//		return null; 
		Boolean bolEncontro;
		Iterator<Factura> objIterator;
		Factura objFactura;
		objFactura = null;
		bolEncontro = false;
		objIterator = this.colFacturas.iterator();
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			objFactura = objIterator.next();
			bolEncontro = (objFactura.getNumero() == intNumero);
		}
		return objFactura;
	}
	private Factura getFromDatabase(Integer intNumero) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		Factura objFactura;
		ResultSet objFacturas;
		objFactura = null;
		try
		{
			objFacturas = this.objConnection.getResultSet("SELECT Facturas.intNumero, Facturas.strDescripcion, Facturas.objFecha, ProductosFacturas.intProducto, ProductosFacturas.intCantidad FROM Facturas INNER JOIN ProductosFacturas ON (Facturas.intNumero = ProductosFacturas.intFactura) WHERE Facturas.intNumero = ".concat(String.valueOf(intNumero)));
			if (objFacturas.next())
			{
				objFactura = new Factura(objFacturas.getInt("intNumero"), objFacturas.getString("strDescripcion"), objFacturas.getDate("objFecha"));
				objFactura.addItem(ProductosDAO.getInstance().getProducto(objFacturas.getInt("intProducto")), objFacturas.getInt("intCantidad"));
				while (objFacturas.next())
				{
					objFactura.addItem(ProductosDAO.getInstance().getProducto(objFacturas.getInt("intProducto")), objFacturas.getInt("intCantidad"));
				}
			}
			else
			{
				throw new FacturasException("No existe ninguna factura con el número ".concat(String.valueOf(intNumero)).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objFactura;
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

