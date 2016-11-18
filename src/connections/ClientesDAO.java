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
import usuarios.Cliente;
public class ClientesDAO
{
	private static ClientesDAO objInstance;
	private Database objConnection;
	private List<Cliente> colClientes;
	public static ClientesDAO getInstance() throws ConnectionException, ParameterException
	{
		if (objInstance == null)
		{
			objInstance = new ClientesDAO();
		}
		return objInstance;
	}
	private ClientesDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colClientes = new ArrayList<Cliente>();
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
	public Cliente getCliente(Integer intNumero) throws ConnectionException, ClienteException, ParameterException
	{
		Cliente objCliente;
		objCliente = this.getFromCache(intNumero);
		if (objCliente == null)
		{
			objCliente = this.getFromDatabase(intNumero);
		}
		return objCliente;
	}
	private Cliente getFromCache(Integer intNumero) //*
	{
		/*
		Boolean bolEncontro;
		Iterator<Cliente> objIterator;
		Cliente objCliente;
		objCliente = null;
		bolEncontro = false;
		objIterator = this.colClientes.iterator();
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			objCliente = objIterator.next();
			bolEncontro = (objCliente.getCodigoPersona() == intNumero);
		}
		return objCliente;
		*/
		
		for  (Cliente c: this.colClientes){
			if (c.esCliente(intNumero)){
				return c;
			}
		}
		return null;
	}
	private Cliente getFromDatabase(Integer intNumero) throws ConnectionException, ClienteException, ParameterException
	{
		Cliente objCliente;
		ResultSet objClientes;
		objCliente = null;
		try
		{
			objClientes = this.objConnection.getResultSet("SELECT * FROM Clientes WHERE intCodigo = ".concat(String.valueOf(intNumero)));
			if (objClientes.next())
			{
				objCliente = new Cliente(objClientes.getInt("intCodigo"), objClientes.getString("strNombre"), objClientes.getInt("intDNI"), objClientes.getString("strDomicilio"), objClientes.getString("strTelefono"), objClientes.getString("strMail"), objClientes.getInt("bolActivo") == 1 ? true : false);
			}
			else
			{
				throw new ClienteException("No existe ningún cliente con el código ".concat(String.valueOf(intNumero)).concat(" en los registros almacenados."));
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		return objCliente;
	}
	private void insertarEnBase(Cliente objCliente) throws ConnectionException
	{
		try
		{
			this.objConnection.executeQuery("INSERT INTO Clientes (intCodigo, strNombre, intDNI, strDomicilio, strTelefono, strMail, bolActivo)".concat(
											"VALUES ( ").concat(String.valueOf(objCliente.getCodigoPersona())).concat(", '").concat(
												objCliente.getNombre()).concat("', ").concat(
												String.valueOf(objCliente.getDNI())).concat(", '").concat(
												objCliente.getDomicilio()).concat("', '").concat(
												objCliente.getTelefono()).concat("', '").concat(
												objCliente.getMail()).concat("', ").concat(
												String.valueOf(1)).concat(")"));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Cliente");
		}
	}
	public void insertar(Cliente objCliente) throws ConnectionException
	{
		if (!this.colClientes.contains(objCliente))
		{
			this.colClientes.add(objCliente);
		}
		this.insertarEnBase(objCliente);
	}
	private void eliminarEnCache(Cliente objCliente)
	{
		if (this.colClientes.contains(objCliente))
		{
			this.colClientes.remove(objCliente);
		}
//		Boolean bolEncontro;
//		Cliente objActual;
//		Iterator<Cliente> objIterator;
//		bolEncontro = false;
//		objIterator = this.colClientes.iterator();
//		while ((objIterator.hasNext()) && (!bolEncontro))
//		{
//			objActual = objIterator.next();
//			if (objActual.equals(objCliente))
//			{
//				bolEncontro = true;
//				this.colClientes.remove(objCliente);
//			}
//		}		
	}
	private void eliminarEnBase(Cliente objCliente)
	{
		try
		{
			this.objConnection.executeQuery("DELETE FROM Clientes WHERE intCodigo = ".concat(String.valueOf(objCliente.getCodigoPersona())));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al eliminar de la base de datos con un Cliente");
		}
	}
	public void eliminar(Cliente objCliente)
	{
		this.eliminarEnCache(objCliente);
		this.eliminarEnBase(objCliente);
	}
	public void modificarCliente(Cliente objCliente)
	{
		this.eliminarEnCache(objCliente);
		this.colClientes.add(objCliente);
		try
		{
			this.objConnection.executeQuery("UPDATE Clientes SET strNombre = '".concat(objCliente.getNombre()).concat(
											"', intDNI = ").concat(String.valueOf(objCliente.getDNI())).concat(
											", strDomicilio = '").concat(objCliente.getDomicilio()).concat(
											"', strTelefono = '").concat(objCliente.getTelefono()).concat(
											"', strMail = '").concat(objCliente.getMail()).concat(
											"', bolActivo = ").concat(String.valueOf(objCliente.getActivo() == true ? 1 : 0)).concat(
											"WHERE intCodigo = ".concat(String.valueOf(objCliente.getCodigoPersona()))));
		}
		catch (SQLException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Cliente");
		}
	}
	public int newId() throws ConnectionException
	{
		String strAnswer;
		ResultSet objAnswer;
		strAnswer = null;
		int intAnswer=0;
		try
		{
			objAnswer = this.objConnection.getResultSet("SELECT COUNT(*) AS Cantidad FROM Clientes");
			if (objAnswer == null)
			{
				throw new ConnectionException("No se pudo recuperar la consulta de la base de datos");
			}
			else
			{
				objAnswer.next();
//				strAnswer = "CLI".concat(String.valueOf(objAnswer.getInt("Cantidad")));
				intAnswer= objAnswer.getInt("Cantidad")+1;
			}
		}
		catch (SQLException objException)
		{
			throw new ConnectionException("Error en la conexión con la base de datos");
		}
		return intAnswer;
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