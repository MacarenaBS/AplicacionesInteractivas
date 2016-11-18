package usuarios;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connections.ClientesDAO;
import connections.ProductosDAO;
import connections.UsuariosDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.UsuarioException;
import model.Producto;
import reclamos.Reclamo;
public class Administrador extends Rol
{
	public Administrador()
	{
		this.colReclamos = new ArrayList<Reclamo>();
		this.colUsuarios = new ArrayList<Usuario>();
	}
<<<<<<< HEAD
	public void crearUsuario(String strUsername, String strPassword, String strRol)
=======
	public void crearUsuario(String strUsername, String strPassword, String strRol) throws ConnectionException, ParameterException, SQLException
>>>>>>> branch 'master' of https://github.com/MacarenaBS/AplicacionesInteractivas
	{
		Usuario objUsuario;
		try
		{
			objUsuario = new Usuario(strUsername, strPassword, strRol);
			UsuariosDAO.getInstance().insertar(objUsuario);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (SQLException objException)
		{
			objException.printStackTrace();
		}
	}
	public void bajaUsuario(String strUsername)
	{
		Usuario objUsuario;
		try
		{
			objUsuario = UsuariosDAO.getInstance().getUsuario(strUsername);
			UsuariosDAO.getInstance().eliminar(objUsuario);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (UsuarioException objException)
		{
			JOptionPane.showMessageDialog(null, "El usuario especificado no existe en la base de datos");
		}
	}
	public void modificarUsuario(String strUsuario, String strPassword, String strRol)
	{
		Usuario objUsuario;
		try
		{
			objUsuario = UsuariosDAO.getInstance().getUsuario(strUsuario);
			UsuariosDAO.getInstance().modificarUsuario(objUsuario);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (UsuarioException objException)
		{
			JOptionPane.showMessageDialog(null, "El usuario especificado no existe en la base de datos");
		}
	}
	public void altaCliente(Integer intDni, String strNombre, String strDomicilio, String strTelefono, String strMail)
	{
		Cliente objCliente;
		try
		{
			objCliente = new Cliente(strNombre,intDni,strDomicilio,strTelefono,strMail);
			ClientesDAO.getInstance().insertar(objCliente);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
	}
	public void bajaCliente(Integer intCodCliente)
	{
		Cliente objCliente;
		try
		{
			objCliente = ClientesDAO.getInstance().getCliente(intCodCliente);
			ClientesDAO.getInstance().eliminar(objCliente);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "El cliente especificado no existe en la base de datos");
		}
	}
	public void modificacionCliente(Integer intCodCliente, String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail, boolean bolEstado)
	{
		Cliente objCliente;
		try
		{
			objCliente = ClientesDAO.getInstance().getCliente(intCodCliente);
			objCliente.actualizarDatos(strNombre, strDomicilio, strTelefono, strMail);
			objCliente.setActivo(bolEstado);
			ClientesDAO.getInstance().modificarCliente(objCliente);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "El cliente especificado no existe en la base de datos");
		}
	}
	public void altaProducto(String strTitulo, String strDescripcion, float fltPrecio)
	{
		Producto objProducto;
		try
		{
			objProducto = new Producto(strTitulo,strDescripcion,fltPrecio,true);
			ProductosDAO.getInstance().insertar(objProducto);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "El cliente especificado no existe en la base de datos");
		}
		catch (FacturasException objException)
		{
			objException.printStackTrace(); //Por qué de factura?
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al insertar el producto en la base de datos");
		}
	}
	public void bajaProducto(Integer intCodigo)
	{
		Producto objProducto;
		try
		{
			objProducto = ProductosDAO.getInstance().getProducto(intCodigo);
			ProductosDAO.getInstance().eliminar(objProducto);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "El cliente especificado no existe en la base de datos");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "Error al insertar el producto en la base de datos");
		}
	}
	public void modificarProducto(Integer intCodigo, String strTitulo, String strDescripcion, float fltPrecio, boolean bolEstado)
	{
		Producto objProducto;
		try
		{
			objProducto = new Producto(intCodigo, strTitulo, strDescripcion, fltPrecio,bolEstado);
			ProductosDAO.getInstance().modificarProducto(objProducto);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Uno o más parametros son erroneos");
		}
	}
	@Override
	public void administrarReclamo(String strNumero, String strDescipcion)
	{
		//El Administrador no administra reclamos
	}
	@Override
	public void cerrarReclamo(String strNumero)
	{
		//El Administrador no puede cerrar reclamos
	}
	public boolean soyAdministrador()
	{
		return true; 
	}
}