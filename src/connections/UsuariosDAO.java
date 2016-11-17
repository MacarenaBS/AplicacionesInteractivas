package connections;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import connections.Database;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.UsuarioException;
import usuarios.Usuario;
public class UsuariosDAO
{
	private static UsuariosDAO objInstance;
	private Database objConnection;
	private List<Usuario> colUsuarios;
	public static UsuariosDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new UsuariosDAO();
		}
		return objInstance;
	}
	private UsuariosDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colUsuarios = new ArrayList<Usuario>();
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
	public Usuario getUsuario(String strUsuario) throws ConnectionException,ParameterException, UsuarioException
	{
		Usuario objUsuario;
		objUsuario = this.getFromCache(strUsuario);
		if (objUsuario == null)
		{
			objUsuario = this.getFromDatabase(strUsuario);
		}
		return objUsuario;
	}
	private Usuario getFromCache(String strUsuario)
	{
		for (Usuario u: this.colUsuarios)
		{
			if (u.soyUsuario(strUsuario))
				return u;
		}
		return null; 
	}
	private Usuario getFromDatabase(String strUsuario) throws ConnectionException, UsuarioException
	{
		Usuario objUsuario;
		ResultSet objUsuariosRs;
		objUsuario = null;
		try
		{
			objUsuariosRs = this.objConnection.getResultSet("SELECT * FROM Usuarios WHERE strNombre = '".concat(strUsuario).concat("'"));
			if (objUsuariosRs.next())
			{
				objUsuario = new Usuario(objUsuariosRs.getString("strNombre"), objUsuariosRs.getString("strPassword"), objUsuariosRs.getString("strRol"));
			}
			else
			{
				throw new UsuarioException("No se ha encontrado el usuario ".concat(strUsuario).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objUsuario;
	}
	private void insertarEnBase(Usuario objUsuario) throws ConnectionException, ParameterException
	{
		try
		{
			this.objConnection.executeQuery("INSERT INTO Usuarios (strNombre, strPassword, strRol)".concat(
											"VALUES ( '").concat((objUsuario.getStrUsername()).concat("', '").concat(
													objUsuario.getStrPassword()).concat("', '").concat(
															objUsuario.getRol()).concat("')")));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo usuario");
		}
	}
	public void insertar(Usuario objUsuario) throws ConnectionException, ParameterException
	{
		if (this.colUsuarios.contains(objUsuario))
		{
			this.colUsuarios.add(objUsuario);
		}
		this.insertarEnBase(objUsuario);
	}
	public void modificarUsuario(Usuario objUsuario)
	{
		this.eliminarEnCache(objUsuario);
		this.colUsuarios.add(objUsuario);
		try
		{
			this.objConnection.executeQuery("UPDATE Usuarios SET strPassword = '".concat(
					objUsuario.getStrPassword()).concat("', strRol='").concat(objUsuario.getRol()).concat("' WHERE strNombre = '").concat((objUsuario.getStrUsername()).concat("'")));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo usuario");
		}
	}
	private void eliminarEnCache(Usuario objUsuario)
	{
		if (this.colUsuarios.contains(objUsuario))
		{
			this.colUsuarios.remove(objUsuario);
		}
	}
	private void eliminarEnBase(Usuario objUsuario)
	{
		try
		{
			this.objConnection.executeQuery("DELETE FROM Usuarios WHERE strNombre = '".concat(objUsuario.getStrUsername().concat("'")));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al eliminar el usuario "+objUsuario.getStrUsername()+" de la base de datos");
		}
	}
	public void eliminar(Usuario objUsuario)
	{
		this.eliminarEnCache(objUsuario);
		this.eliminarEnBase(objUsuario);
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
