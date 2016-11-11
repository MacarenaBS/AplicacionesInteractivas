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
import exceptions.UsuarioException;
import model.Factura;
import model.Producto;
import usuarios.Usuario;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Productos Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class UsuariosDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static UsuariosDAO objInstance;
	private Database objConnection;
	private List<Usuario> colUsuarios;
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return Instance of the Data Access Object.
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	public static UsuariosDAO getInstance() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==========Check If Instance Is Created============*/
		/*==================================================*/
		if (objInstance == null)
		{
			objInstance = new UsuariosDAO();
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
	private UsuariosDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colUsuarios = new ArrayList<Usuario>();
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
	 * @throws UsuarioException 
	 */
	public Usuario getUsuario(String strUsuario) throws ConnectionException,ParameterException, UsuarioException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Usuario objUsuario;
		/*==================================================*/
		/*===========Recuperar Producto Del Cache===========*/
		/*==================================================*/
		objUsuario = this.getFromCache(strUsuario);
		/*==================================================*/
		/*==============Verificar Si Encontro===============*/
		/*==================================================*/
		if (objUsuario == null)
		{
			/*==================================================*/
			/*===========Recuperar Producto De La Base==========*/
			/*==================================================*/
			objUsuario = this.getFromDatabase(strUsuario);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objUsuario;
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
	private Usuario getFromCache(String strUsuario)
	{
		
		for (Usuario u: this.colUsuarios){
			if (u.soyUsuario(strUsuario))
				return u;
		}
		return null; 
		
//		/*==================================================*/
//		/*====================Variables=====================*/
//		/*==================================================*/
//		Boolean bolEncontro;
//		Iterator<Producto> objIterator;
//		Producto objProducto;
//		/*==================================================*/
//		/*===============Initialize Variables===============*/
//		/*==================================================*/
//		objProducto = null;
//		bolEncontro = false;
//		objIterator = this.colUsuarios.iterator();
//		/*==================================================*/
//		/*=================Loop de Productos================*/
//		/*==================================================*/
//		while ((objIterator.hasNext()) && (!bolEncontro))
//		{
//			/*==================================================*/
//			/*=================Obtener Producto=================*/
//			/*==================================================*/
//			objProducto = objIterator.next();
//			/*==================================================*/
//			/*==============Verificar Si Coincide===============*/
//			/*==================================================*/
//			bolEncontro = (objProducto.getCodigo() == intNumero);
//		}
//		/*==================================================*/
//		/*==================Return Results==================*/
//		/*==================================================*/
//		return objProducto;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*============Get Producto From Database============*/
	/*==================================================*/
	/**
	 * Recupera un usuario de una tabla específica de la base de datos.
	 * @param strUsuario Nombre del usuario a recuperar
	 * @return Usuario
	 * @throws ConnectionException No se pudo ejecutar la query a la base de datos
	 * @throws UsuarioException No se encuentra el usuario buscado
	 */
	private Usuario getFromDatabase(String strUsuario) throws ConnectionException, UsuarioException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Usuario objUsuario;
		ResultSet objUsuariosRs;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objUsuario = null;
		/*==================================================*/
		/*==============Attempt Producto Query==============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*===================Get Productos==================*/
			/*==================================================*/
			objUsuariosRs = this.objConnection.getResultSet("SELECT * FROM Usuarios WHERE strNombre = ".concat(strUsuario));
			/*==================================================*/
			/*==============Verificar Si Encontró===============*/
			/*==================================================*/
			if (objUsuariosRs.next())
			{
				/*==================================================*/
				/*==================Crear Producto==================*/
				/*==================================================*/
				
				objUsuario = new Usuario(objUsuariosRs.getString("strNombre"), objUsuariosRs.getString("strPassword"), objUsuariosRs.getString("strRol"));
			}
			else
			{
				/*==================================================*/
				/*No se Encontró Ningún Registro con el Número Especificado*/
				/*==================================================*/
				throw new UsuarioException("No se ha encontrado el usuario ".concat(strUsuario).concat(" en los registros almacenados."));
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
		return objUsuario;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Insertar en Base=================*/
	/*==================================================*/
	/**
	 * Inserta el Usuario en la tabla Usuarios de la base de datos
	 * @param objUsuario Usuario a insertar
	 * @throws ProductosException 
	 * @throws ConnectionException 
	 */
	private void insertarEnBase(Usuario objUsuario) throws ConnectionException, ParameterException
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Insert=================*/
			/*==================================================*/
			this.objConnection.executeQuery("INSERT INTO Usuarios (strNombre, strPassword, strRol)".concat(
											"VALUES ( '").concat((objUsuario.getStrUsername()).concat("', '").concat(
													objUsuario.getStrPassword()).concat("', '").concat(
															objUsuario.getRol()).concat("')")));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo usuario");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Insertar=====================*/
	/*==================================================*/
	/**
	 * Inserta un usuario en el cache y la tabla correspondiente de la base de datos.
	 * @param objProducto Producto a insertar
	 * @throws ProductosException 
	 * @throws FacturasException 
	 * @throws ParameterException 
	 * @throws ClienteException 
	 * @throws ConnectionException 
	 */
	public void insertar(Usuario objUsuario) throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*=========Agrega El Producto a la Cache============*/
		/*==================================================*/
		if (this.colUsuarios.contains(objUsuario))
		{
			this.colUsuarios.add(objUsuario);
		}
		
		/*==================================================*/
		/*=========Inserta el Producto en la Tabla==========*/
		/*==================================================*/
		this.insertarEnBase(objUsuario);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	public void modificarUsuario(Usuario objUsuario)
	{
		this.eliminarEnCache(objUsuario);
		this.colUsuarios.add(objUsuario);
		try
		{
			/*==================================================*/
			/*================Ejecuta el Insert=================*/
			/*==================================================*/
			this.objConnection.executeQuery("UPDATE Usuarios SET strPassword = '".concat(
					objUsuario.getStrPassword()).concat("', strRol='").concat(objUsuario.getRol()).concat("' WHERE strNombre = '").concat((objUsuario.getStrUsername()).concat("'")));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo usuario");
		}
	}
	/*==================================================*/
	/*===============Eliminar de la Cache===============*/
	/*==================================================*/
	/**
	 * Elimina un usuario de la cache.
	 * @param objUsuario Usuario a eliminar
	 */
	private void eliminarEnCache(Usuario objUsuario)
	{
		if (this.colUsuarios.contains(objUsuario))
		{
			this.colUsuarios.remove(objUsuario);
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Eliminar en Base=================*/
	/*==================================================*/
	/**
	 * Elimina el Usuario en la tabla Usuarios de la base de datos
	 * @param objUsuario Usuario a eliminar
	 */
	private void eliminarEnBase(Usuario objUsuario)
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Delete=================*/
			/*==================================================*/
			this.objConnection.executeQuery("DELETE FROM Usuarios WHERE strNombre = '".concat(objUsuario.getStrUsername().concat("'")));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al eliminar el usuario "+objUsuario.getStrUsername()+" de la base de datos");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Eliminar=====================*/
	/*==================================================*/
	/**
	 * Elimina un Usuario en el cache y la tabla correspondiente de la base de datos.
	 * @param objUsuario Usuario a eliminar
	 */
	public void eliminar(Usuario objUsuario)
	{
		/*==================================================*/
		/*========Elimina El Producto a la Cache============*/
		/*==================================================*/
		this.eliminarEnCache(objUsuario);
		/*==================================================*/
		/*========Elimina el Producto en la Tabla==========*/
		/*==================================================*/
		this.eliminarEnBase(objUsuario);
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