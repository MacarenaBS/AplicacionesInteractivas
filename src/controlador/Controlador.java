package controlador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import connections.UsuariosDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.UsuarioException;
import usuarios.*;
public class Controlador
{
	private Usuario objUsuario;
	private static Controlador objInstance;
	private List<Rol> colRoles;
	private Rol objRol;
	private Controlador()
	{
		this.colRoles= new ArrayList<Rol>();	
	}
	public static Controlador getInstance()
	{
		if (objInstance == null)
		{
			objInstance= new Controlador();
		}
		return objInstance; 
	}
	public Usuario getUser(String strUsername) throws ConnectionException, ParameterException, UsuarioException
	{
		return UsuariosDAO.getInstance().getUsuario(strUsername);
	}
	private void setRol()
	{
		switch (this.objUsuario.getRol())
		{
			case "Administrador" : this.objRol = new Administrador(); break;
			case "CallCenter" : this.objRol = new CallCenter(); break;
			case "Consulta" : this.objRol = new Consulta(); break;
			case "ResponsableDistribucion" : this.objRol = new ResponsableDistribucion(); break;
			case "ResponsableFacturacion" : this.objRol = new ResponsableFacturacion(); break;
			case "ResponsableZonaDeEntrega" : this.objRol = new ResponsableZonaDeEntrega(); break;
		}
	}
	public Boolean Connect(String strUsername, String strPassword) throws ConnectionException, ParameterException, UsuarioException
	{
		Boolean bolAnswer;
		try
		{
			this.objUsuario = getUser(strUsername);
			if (this.objUsuario.passwordVerificacion(strUsername,strPassword))
			{
				System.out.println("Connection succesful - Welcome "+ this.objUsuario.getStrUsername());
				this.setRol();
				bolAnswer = true;
			}
			else
			{
				System.out.println("Connection unsuccesful - Username or Password incorrect.");
				this.objUsuario=null;
				bolAnswer = false;
			}
		}
		catch(UsuarioException objException)
		{
			bolAnswer = false;
		}
		return bolAnswer;
	}
	public Usuario currentUser()
	{
		return this.objUsuario;
	}
	public void disconnect()
	{
		this.objUsuario=null;
	}
	public Boolean crearUsuario(String strUsername, String strPassword, String strPermiso)
	{	
		Boolean bolAnswer;
		Usuario objUsuario;
		bolAnswer = true;
		objUsuario = new Usuario(strUsername, strPassword, strPermiso);
		try
		{
			UsuariosDAO.getInstance().insertar(objUsuario);
		}
		catch (ConnectionException | ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en la base de datos");
			objException.printStackTrace();
			bolAnswer = false;
		}
		//Tenemos que cambiar Rol y Usuario para que el usuario tenga una lista de roles en lugar de que el rol tenga una lista de usuarios
		//Ojo que solo le estamos pasando un permiso, con lo cual solo le damos de alta un rol. No confundir que me parece que estas tratando de dar de alta al usuario logeado
		for (Rol objRol: colRoles)
		{
			if (objRol.getClass().getSimpleName().equals(strPermiso))
			{
				objRol.addUsuario(objUsuario);
				break;
			}
		}
		return bolAnswer;
	}	
	public Boolean eliminarUsuario(String strUsername)
	{
		Boolean bolAnswer;
		bolAnswer = true;
		Usuario objUsuario;
		try
		{
			objUsuario = UsuariosDAO.getInstance().getUsuario(strUsername);
			try
			{
				UsuariosDAO.getInstance().eliminar(objUsuario);	
				//Todo lo que sigue tiene que ir en el eliminar usuario del DAO
				//for (Rol objRol: this.colRoles)
				//{
				//	if (objRol.getClass().getSimpleName() == objUsuario.getRol())
				//	{
				//		objRol.removeUsuario(objUsuario);
				//	}
				//}
			}
			catch (ConnectionException | ParameterException objException)
			{
				System.out.println("Controlador - eliminarUsuario - "+ objException.getMessage());
				objException.printStackTrace();
				bolAnswer = false;
			}
		}
		catch (ConnectionException | ParameterException | UsuarioException objException)
		{
			JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en la base de datos");
			objException.printStackTrace();
			bolAnswer = false;
		}
		return bolAnswer;
	}
	public Boolean modificarUsuario(String strUsername, String strPassword, String strRol)
	{
		Boolean bolAnswer;
		Usuario objUsuario;
		try 
		{
			objUsuario = UsuariosDAO.getInstance().getUsuario(strUsername);
			objUsuario.setStrPassword(strPassword);
			objUsuario.setRol(strRol);
			UsuariosDAO.getInstance().modificarUsuario(objUsuario);
			//Esto tiene que ir en el modificar usuario del DAO. Solo se le esta agregando un rol
			//for (Rol rol: roles)
			//{
			//	if (rol.getClass().getSimpleName() == s.getRol())
			//	{
			//		rol.removeUsuario(s);
			//		rol.addUsuario(s);
			//	}
			//}
			
			bolAnswer = true;
		}
		catch (ConnectionException | ParameterException | UsuarioException objException)
		{
			System.out.println("Controlador - modificarUsuario - "+ objException.getMessage());
			objException.printStackTrace();
			bolAnswer= false;
		}
		return bolAnswer;
	}
	public void altaCliente(String strNombre, String strDomicilio, Integer intDNI, String strTelefono, String strMail)
	{
		try
		{
			((Administrador) this.objRol).altaCliente(intDNI, strNombre, strDomicilio, strTelefono, strMail);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Parámetro no válido");
		}
	}
	public void bajaCliente(String strCodigo)
	{
		try
		{
			((Administrador) this.objRol).bajaCliente(strCodigo);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Parámetro no válido");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "El cliente no existe en la base de datos");
		}
	}
	public void modificarCliente(String strCodigo, String strNombre, String strDomicilio, Integer intDNI, String strTelefono, String strMail, Boolean bolEstado)
	{
		try
		{
			((Administrador) this.objRol).modificacionCliente(strCodigo, strNombre, intDNI, strDomicilio, strTelefono, strMail, bolEstado);
		}	
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Parámetro no válido");
		}
	}
	public void altaProducto(String strTitulo, String strDescripcion, float fltPrecio)
	{
		try
		{
			((Administrador) this.objRol).altaProducto(strTitulo, strDescripcion, fltPrecio);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "El cliente no existe en la base de datos");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Parámetro no válido");
		}
		catch (FacturasException objException)
		{
			JOptionPane.showMessageDialog(null, "Error con la factura asociada");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "Error con la creación del producto");
		}
	}
	public void bajaProducto(Integer intCodigo) throws ConnectionException, ParameterException, ClienteException, ProductosException
	{
		((Administrador) this.objRol).bajaProducto(intCodigo);
	}
	public void modificarProducto(Integer intCodigo, String strTitulo, String strDescripcion, float fltPrecio, boolean bolEstado) throws ConnectionException, ParameterException
	{
		((Administrador) this.objRol).modificarProducto(intCodigo, strTitulo, strDescripcion, fltPrecio, bolEstado);
	}
}
