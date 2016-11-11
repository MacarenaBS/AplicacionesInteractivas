/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;

import java.util.ArrayList;

import connections.ClientesDAO;
import connections.ProductosDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import model.Producto;
import reclamos.Reclamo;

/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class Administrador extends Rol
{
	public Administrador()
	{
		this.colReclamos = new ArrayList<Reclamo>();
		this.colUsuarios = new ArrayList<Usuario>();
	}
	public void crearUsuario(String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strmail, String strUsername, String strPassword)
	{
		
		
	}
	public void bajaUsuario(Integer codPersona)
	{
		
	}
	public void modificarUsuario(Integer intCodigo, String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strmail)
	{
		
	}
	public void altaCliente(Integer intDni, String strNombre, String strDomicilio, String strTelefono, String strMail) throws ConnectionException, ParameterException
	{
		Cliente c= new Cliente(strNombre,intDni,strDomicilio,strTelefono,strMail);
		ClientesDAO.getInstance().insertar(c);
	}
	public void bajaCliente(Integer intCodCliente) throws ConnectionException, ParameterException, ClienteException
	{
		ClientesDAO.getInstance().eliminar(ClientesDAO.getInstance().getCliente(intCodCliente));
	}
	public void modificacionCliente(Integer intCodCliente, String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail, boolean bolEstado) throws ConnectionException, ParameterException
	{
		ClientesDAO.getInstance().modificarCliente(new Cliente(intCodCliente, strNombre, intDNI, strDomicilio, strTelefono, strMail,  bolEstado));
	}
	public void altaProducto(String strTitulo, String strDescripcion, float fltPrecio) throws ConnectionException, ClienteException, ParameterException, FacturasException, ProductosException
	{
		Producto p= new Producto(strTitulo,strDescripcion,fltPrecio,true);
		ProductosDAO.getInstance().insertar(p);
	}
	public void bajaProducto(Integer intCodigo) throws ConnectionException, ParameterException, ClienteException, ProductosException
	{
		ProductosDAO.getInstance().eliminar(ProductosDAO.getInstance().getProducto(intCodigo));
	}
	public void modificarProducto(Integer intCodigo, String strTitulo, String strDescripcion, float fltPrecio, boolean bolEstado) throws ConnectionException, ParameterException
	{
		Producto p= new Producto(intCodigo, strTitulo, strDescripcion, fltPrecio,bolEstado);
		ProductosDAO.getInstance().modificarProducto(p);
	}
	
	public void administrarReclamo(Integer intNroReclamo)
	{

	}
	@Override
	public void administrarReclamo(String strNumero, String strDescipcion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cerrarReclamo(String strNumero) {
		// TODO Auto-generated method stub
		
	}
}
