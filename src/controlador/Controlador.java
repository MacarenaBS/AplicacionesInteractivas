package controlador;

import java.util.Vector;

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
import usuarios.*;

public class Controlador {
	
	private Usuario objUsuario;
	private static Controlador instance;
	private Vector<Rol> roles;
	
	private Controlador(){
		
		roles= new Vector<Rol>();
		
		Administrador admin = new Administrador();
		roles.add(admin);
		CallCenter callCenter = new CallCenter();
		roles.add(callCenter);
		Consulta consulta= new Consulta();
		roles.add(consulta);
		ResponsableDistribucion rDistribucion= new ResponsableDistribucion();
		roles.add(rDistribucion);
		ResponsableFacturacion rFacturacion= new ResponsableFacturacion();
		roles.add(rFacturacion);
		ResponsableZonaDeEntrega rZonaDeEntrega= new ResponsableZonaDeEntrega();
		roles.add(rZonaDeEntrega);
		
		
	}
	
	public static Controlador getInstance(){
		
		if (instance == null)
		{
			instance= new Controlador();
		}
		return instance; 
	}
	/**DEBERIAMOS DEVOLVER VIEWS**/
	public Usuario getUser(String strUsername) throws ConnectionException, ParameterException, UsuarioException{
		return UsuariosDAO.getInstance().getUsuario(strUsername);
	}
	
	public boolean Connect(String strUsername, String strPassword) throws ConnectionException, ParameterException, UsuarioException{

		try{
			Usuario s= getUser(strUsername);
			this.objUsuario= s;
			
			if (this.objUsuario.passwordVerificacion(strUsername,strPassword)){
				System.out.println("Connection succesful - Welcome "+s.getStrUsername());
				return true;
			}
			else
			{
				System.out.println("Connection unsuccesful - Username or Password incorrect.");
				this.objUsuario=null;
				return false;
			}
		}
		catch(UsuarioException ex)
		{
			return false;
		}
		
		
		
	}
	
	public Usuario currentUser(){
		return this.objUsuario;
	}
	
	public void disconnect(){
		this.objUsuario=null;
	}
	
	public boolean crearUsuario(String strUsername, String strPassword, String strPermiso)
	{	
		Usuario s= new Usuario(strUsername, strPassword, strPermiso);
		try {
			UsuariosDAO.getInstance().insertar(s);
		} catch (ConnectionException | ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		for (Rol rol: roles)
		{
			if (rol.getClass().getSimpleName().equals(strPermiso))
			{
				rol.addUsuario(s);
				break;
			}
		}
		
		return true;
		
	}
	
	public boolean eliminarUsuario(String strUsername)
	{
		Usuario s;
		
		try {
			s = UsuariosDAO.getInstance().getUsuario(strUsername);
		} catch (ConnectionException | ParameterException | UsuarioException e) {
			System.out.println("Controlador - eliminarUsuario - "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		try {
			UsuariosDAO.getInstance().eliminar(s);
			
			for (Rol rol: roles)
			{
				if (rol.getClass().getSimpleName() == s.getRol())
				{
					rol.removeUsuario(s);
				}
			}
			
		} catch (ConnectionException | ParameterException e) {
			System.out.println("Controlador - eliminarUsuario - "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean modificarUsuario(String strUsername, String strPassword, String strRol)
	{
		try 
		{
			Usuario s= UsuariosDAO.getInstance().getUsuario(strUsername);
			s.setStrPassword(strPassword);
			s.setRol(strRol);
			UsuariosDAO.getInstance().modificarUsuario(s);
			
			for (Rol rol: roles)
			{
				if (rol.getClass().getSimpleName() == s.getRol())
				{
					rol.removeUsuario(s);
					rol.addUsuario(s);
				}
			}
			
			return true;
		} catch (ConnectionException | ParameterException | UsuarioException e) {
			System.out.println("Controlador - modificarUsuario - "+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean crearProducto(String strTitulo, String strDescripcion, float fltPrecio, Boolean bolEstado)
	{
		Producto p= new Producto(strTitulo, strDescripcion, fltPrecio, bolEstado);
		try {
			ProductosDAO.getInstance().insertar(p);
			return true;
		} catch (ConnectionException | ClienteException | ParameterException | FacturasException
				| ProductosException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Producto getProducto(int intNumero){
		try {
			return ProductosDAO.getInstance().getProducto(intNumero);
		} catch (ConnectionException | ClienteException | ParameterException | ProductosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean eliminarProducto(int intnumero){
		Producto p= getProducto(intnumero);
		try {
			ProductosDAO.getInstance().eliminar(p);
		} catch (ConnectionException | ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean crearCliente(String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail)
	{
		Cliente c= new Cliente(strNombre,intDNI,strDomicilio,strTelefono,strMail);
		try {
			ClientesDAO.getInstance().insertar(c);
			return true;
		} catch (ConnectionException | ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
		
		
}
