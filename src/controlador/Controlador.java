package controlador;
import java.util.Vector;

import javax.swing.JOptionPane;

import connections.UsuariosDAO;
import enumerations.Tipo;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.UsuarioException;
import model.Factura;
import model.Producto;
import reclamos.Reclamo;
import usuarios.*;

public class Controlador
{
	
	private Usuario objUsuario;
	private static Controlador objInstance;
	private Vector<Rol> roles;
	private Rol objRol;
	private Controlador()
	{
//		roles= new Vector<Rol>();
//		Administrador admin = new Administrador();
//		roles.add(admin);
//		CallCenter callCenter = new CallCenter();
//		roles.add(callCenter);
//		Consulta consulta= new Consulta();
//		roles.add(consulta);
//		ResponsableDistribucion rDistribucion= new ResponsableDistribucion();
//		roles.add(rDistribucion);
//		ResponsableFacturacion rFacturacion= new ResponsableFacturacion();
//		roles.add(rFacturacion);
//		ResponsableZonaDeEntrega rZonaDeEntrega= new ResponsableZonaDeEntrega();
//		roles.add(rZonaDeEntrega);
	}
	public static Controlador getInstance()
	{
		if (objInstance == null)
		{
			objInstance= new Controlador();
		}
		return objInstance; 
	}
	/*DEBERIAMOS DEVOLVER VIEWS*/
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
			case "Responsable Facturación" : this.objRol = new ResponsableFacturacion(); break;
			case "Responsable Zona de Entrega" : this.objRol = new ResponsableZonaDeEntrega(); break;
			default : this.objRol = new ResponsableDistribucion(); break; //Suponemos que siempre tiene asignado un rol. Como no quedan más opciones tiene que ser un responsable de cantidad, faltante o producto
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
				System.out.println("Connection succesful - Welcome " + this.objUsuario.getUsername());
				this.setRol();
				bolAnswer = true;
			}
			else
			{
				System.out.println("Connection unsuccesful - Username or Password incorrect.");
				this.objUsuario = null;
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
		this.objUsuario = null;
	}
	public void crearUsuario(String strUsername, String strPassword, String strPermiso)
	{	
		((Administrador) this.objRol).crearUsuario(strUsername, strPassword, strPermiso);
	}
	public void eliminarUsuario(String strUsername)
	{
		((Administrador) this.objRol).bajaUsuario(strUsername);
	}
	public void modificarUsuario(String strUsername, String strPassword, String strRol)
	{
		((Administrador) this.objRol).modificarUsuario(strUsername, strPassword, strRol);
	}
	public void altaCliente(String strNombre, String strDomicilio,Integer intDNI, String strTelefono, String strMail)
	{
		((Administrador) this.objRol).altaCliente(intDNI, strNombre, strDomicilio, strTelefono, strMail);
	}	
	public void bajaCliente(Integer intCodigo)
	{
		((Administrador) this.objRol).bajaCliente(intCodigo);
	}
	public void modificarCliente(Integer intCodigo, String strNombre, String strDomicilio, Integer intDNI, String strTelefono, String strMail, Boolean bolEstado)
	{
		((Administrador) this.objRol).modificacionCliente(intCodigo, strNombre, intDNI, strDomicilio, strTelefono, strMail, bolEstado);
	}
	public void altaProducto(String strTitulo, String strDescripcion, float fltPrecio)
	{
		((Administrador) this.objRol).altaProducto(strTitulo, strDescripcion, fltPrecio);
	}
	public void bajaProducto(Integer intCodigo)
	{
		((Administrador) this.objRol).bajaProducto(intCodigo);
	}
	public void modificarProducto(Integer intCodigo, String strTitulo, String strDescripcion, float fltPrecio, Boolean bolEstado)
	{
		((Administrador) this.objRol).modificarProducto(intCodigo, strTitulo, strDescripcion, fltPrecio, bolEstado);
	}
	public void crearReclamoFacturacion(String strDescripcion, Cliente objCliente, Factura objFactura)
	{
		((CallCenter) this.objRol).crearReclamoFacturacion(strDescripcion, objCliente, objFactura);
	}
	public void crearReclamoZonaDeEntrega(String strDescripcion, String strZona, Cliente objCliente)
	{
		((CallCenter) this.objRol).crearReclamoZonaDeEntrega(strDescripcion, strZona, objCliente);
	}
	public void crearReclamoInconsistencia(String strDescripcion, Producto objProducto, Integer intCantidad, Cliente objCliente)
	{
		((CallCenter) this.objRol).crearReclamoInconsistencia(strDescripcion, objProducto, intCantidad, objCliente);
	}
	private Tipo getTipo(String strTipo)
	{
		Tipo objTipo;
		switch (strTipo.toLowerCase())
		{
			case "facturacion" : objTipo = Tipo.FACTURACION;break;
			case "faltante" : objTipo = Tipo.FALTANTE; break;
			case "producto" : objTipo = Tipo.PRODUCTO; break;
			case "zona" : objTipo = Tipo.ZONA; break;
			default : objTipo = null; break;
		}
		return objTipo;
	}
	public Reclamo buscarReclamo(String strTipo, String strNumero)
	{
		Reclamo objReclamo;
		Tipo objTipo;
		objReclamo = null;
		if (!strNumero.startsWith("REC"))
		{
			JOptionPane.showMessageDialog(null, "Número de reclamo no válido");
		}
		else
		{
			objTipo = this.getTipo(strTipo);
			objReclamo = ((Consulta) this.objRol).obtenerReclamo(objTipo, strNumero);
		}
		return objReclamo;
	}
	public void cerrarReclamoFacturacion(String strNumero)
	{
		((ResponsableFacturacion) this.objRol).cerrarReclamo(strNumero);
	}
	public void cerrarReclamoZonaDeEntrega(String strNumero)
	{
		((ResponsableZonaDeEntrega) this.objRol).cerrarReclamo(strNumero);
	}
	public void cerrarReclamo(String strNumero)
	{
		((ResponsableDistribucion) this.objRol).cerrarReclamo(strNumero);
	}
	public void agregarAccionReclamoFacturación(String strNumero, String strDescripcion)
	{
		((ResponsableFacturacion) this.objRol).administrarReclamo(strNumero, strDescripcion);
	}
	public void agregarAccionReclamoZonaDeEntrega(String strNumero, String strDescripcion)
	{
		((ResponsableZonaDeEntrega) this.objRol).administrarReclamo(strNumero, strDescripcion);
	}
	public void agregarAccion(String strNumero, String strDescripcion)
	{
		((ResponsableDistribucion) this.objRol).administrarReclamo(strNumero, strDescripcion);
	}
	public void testConnect()
	{
		this.objRol = new Administrador();
	}
}