/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;

import java.util.ArrayList;
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
	public void altaCliente(Integer intDni, String strNombre, String strDomicilio, String strTelefono, String strMail)
	{
		
	}
	public void bajaCliente(Integer intCodCliente)
	{
		
	}
	public void modificacionCliente(Integer intCodCliente, Integer intDNI, String strDomicilio, String strTelefono, String strMail)
	{
		
	}
	public void altaProducto(String strTitulo, String strDescripcion, float fltPrecio)
	{
		
	}
	public void bajaProducto(Integer intCodigo)
	{
		
	}
	public void modificarProducto(Integer intCodigo, String strTitulo, String strDescripcion, float fltPrecio)
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
