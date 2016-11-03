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
import exceptions.ParameterException;
import usuarios.Cliente;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Clientes Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class ClientesDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static ClientesDAO objInstance;
	private Database objConnection;
	private List<Cliente> colClientes;
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return Instance of the Data Access Object.
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	public static ClientesDAO getInstance() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==========Check If Instance Is Created============*/
		/*==================================================*/
		if (objInstance == null)
		{
			objInstance = new ClientesDAO();
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
	 * Create a new Cliente Data Object Connection
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private ClientesDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colClientes = new ArrayList<Cliente>();
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
	/*===================End Function===================*/
	/*==================================================*/
	/**
	 * Busca un Cliente por su código. Primero Busca en cache y si no lo encuentra lo busca en la base de datos.
	 * @param intNumero Código de cliente a buscar
	 * @return Cliente
	 * @throws ConnectionException La query no se pudo ejecutar en la base de datos
	 * @throws ClienteException No se pudo encontrar el cliente con el código especificado 
	 */
	public Cliente getCliente(Integer intNumero) throws ConnectionException, ClienteException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Cliente objCliente;
		/*==================================================*/
		/*===========Recuperar Cliente Del Cache============*/
		/*==================================================*/
		objCliente = this.getFromCache(intNumero);
		/*==================================================*/
		/*==============Verificar Si Encontro===============*/
		/*==================================================*/
		if (objCliente == null)
		{
			/*==================================================*/
			/*===========Recuperar Cliente De La Base===========*/
			/*==================================================*/
			objCliente = this.getFromDatabase(intNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objCliente;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Get Cliente From Cache==============*/
	/*==================================================*/
	/**
	 * Recupera un cliente de la cache.
	 * @param intNumero Código de cliente a recuperar
	 * @return Cliente
	 */
	private Cliente getFromCache(Integer intNumero)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Iterator<Cliente> objIterator;
		Cliente objCliente;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objCliente = null;
		bolEncontro = false;
		objIterator = this.colClientes.iterator();
		/*==================================================*/
		/*=================Loop de Clientes=================*/
		/*==================================================*/
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			/*==================================================*/
			/*=================Obtener Cliente==================*/
			/*==================================================*/
			objCliente = objIterator.next();
			/*==================================================*/
			/*==============Verificar Si Coincide===============*/
			/*==================================================*/
			bolEncontro = (objCliente.getCodigoPersona() == intNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objCliente;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*============Get Cliente From Database=============*/
	/*==================================================*/
	/**
	 * Recupera un cliente de una tabla específica de la base de datos.
	 * @param intNumero Código de cliente a recuperar
	 * @return Cliente
	 * @throws ConnectionException No se pudo ejecutar la query a la base de datos
	 * @throws ClienteException No se pudo encontrar el cliente con el código especificado
	 */
	private Cliente getFromDatabase(Integer intNumero) throws ConnectionException, ClienteException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Cliente objCliente;
		ResultSet objClientes;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objCliente = null;
		/*==================================================*/
		/*==============Attempt Clientes Query==============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*===================Get Reclamos===================*/
			/*==================================================*/
			objClientes = this.objConnection.getResultSet("SELECT * FROM Clientes WHERE intCodigo = ".concat(String.valueOf(intNumero)));
			/*==================================================*/
			/*==============Verificar Si Encontró===============*/
			/*==================================================*/
			if (objClientes.next())
			{
				/*==================================================*/
				/*==================Crear Cliente===================*/
				/*==================================================*/
				objCliente = new Cliente(objClientes.getInt("intCodigo"), objClientes.getString("strNombre"), objClientes.getInt("intDNI"), objClientes.getString("strDomicilio"), objClientes.getString("strTelefono"), objClientes.getString("strMail"));
			}
			else
			{
				/*==================================================*/
				/*No se Encontró Ningún Registro con el Número Especificado*/
				/*==================================================*/
				throw new ClienteException("No existe ningún cliente con el código ".concat(String.valueOf(intNumero)).concat(" en los registros almacenados."));
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
		return objCliente;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Insertar en Base=================*/
	/*==================================================*/
	/**
	 * Inserta el Cliente en la tabla Clientes de la base de datos
	 * @param objCliente Cliente a insertar
	 */
	private void insertarEnBase(Cliente objCliente)
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Insert=================*/
			/*==================================================*/
			this.objConnection.executeQuery("INSERT INTO Clientes (intCodigo, strNombre, intDNI, strDomicilio, strTelefono, strMail, bolActivo)".concat(
											"VALUES ( ").concat(
												String.valueOf(objCliente.getCodigoPersona())).concat(", ").concat(
												objCliente.getNombre()).concat(", ").concat(
												String.valueOf(objCliente.getDNI())).concat(", ").concat(
												objCliente.getDomicilio()).concat(", ").concat(
												objCliente.getTelefono()).concat(", ").concat(
												objCliente.getMail()).concat(", ").concat(
												String.valueOf(1)).concat(")"));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Cliente");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Insertar=====================*/
	/*==================================================*/
	/**
	 * Inserta un cliente en el cache y la tabla correspondiente de la base de datos.
	 * @param objCliente Cliente a insertar
	 */
	public void insertar(Cliente objCliente)
	{
		/*==================================================*/
		/*==========Agrega El Cliente a la Cache============*/
		/*==================================================*/
		this.colClientes.add(objCliente);
		/*==================================================*/
		/*==========Inserta el Cliente en la Tabla==========*/
		/*==================================================*/
		this.insertarEnBase(objCliente);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===============Eliminar de la Cache===============*/
	/*==================================================*/
	/**
	 * Elimina un cliente de la cache.
	 * @param objCliente Cliente a eliminar
	 */
	private void eliminarEnCache(Cliente objCliente)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Cliente objActual;
		Iterator<Cliente> objIterator;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		bolEncontro = false;
		objIterator = this.colClientes.iterator();
		/*==================================================*/
		/*=================Loop de Clientes=================*/
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
			if (objActual.equals(objCliente))
			{
				/*==================================================*/
				/*=============Cambiar Flag de Búsqueda=============*/
				/*==================================================*/
				bolEncontro = true;
				/*==================================================*/
				/*=========Elimina El Cliente a la Cache============*/
				/*==================================================*/
				this.colClientes.remove(objCliente);
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
	 * Elimina el Cliente en la tabla Clientes de la base de datos
	 * @param objCliente Cliente a eliminar
	 */
	private void eliminarEnBase(Cliente objCliente)
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Delete=================*/
			/*==================================================*/
			this.objConnection.executeQuery("DELETE FROM Clientes WHERE intCodigo = ".concat(String.valueOf(objCliente.getCodigoPersona())));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al eliminar de la base de datos con un Cliente");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Eliminar=====================*/
	/*==================================================*/
	/**
	 * Elimina un cliente en el cache y la tabla correspondiente de la base de datos.
	 * @param objCliente Cliente a eliminar
	 */
	public void eliminar(Cliente objCliente)
	{
		/*==================================================*/
		/*=========Elimina El Cliente a la Cache============*/
		/*==================================================*/
		this.eliminarEnCache(objCliente);
		/*==================================================*/
		/*=========Elimina el Cliente en la Tabla==========*/
		/*==================================================*/
		this.eliminarEnBase(objCliente);
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