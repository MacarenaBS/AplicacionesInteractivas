package usuarios;
import java.util.ArrayList;
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
	public void crearUsuario(String strUsername, String strPassword, String strRol) throws ConnectionException, ParameterException
	{
		Usuario objUsuario;
		objUsuario = new Usuario(strUsername, strPassword, strRol);
		UsuariosDAO.getInstance().insertar(objUsuario);
	}
	public void bajaUsuario(String strUsername) throws ConnectionException, ParameterException, UsuarioException
	{
		UsuariosDAO.getInstance().eliminar(UsuariosDAO.getInstance().getUsuario(strUsername));
	}
	public void modificarUsuario(String strUsuario, String strPassword, String strRol) throws ConnectionException, ParameterException, UsuarioException
	{
		UsuariosDAO.getInstance().modificarUsuario(UsuariosDAO.getInstance().getUsuario(strUsuario));
	}
	public void altaCliente(Integer intDni, String strNombre, String strDomicilio, String strTelefono, String strMail) throws ConnectionException, ParameterException
	{
		Cliente objCliente;
		objCliente = new Cliente(strNombre,intDni,strDomicilio,strTelefono,strMail);
		ClientesDAO.getInstance().insertar(objCliente);
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
		Producto objProducto;
		objProducto = new Producto(strTitulo,strDescripcion,fltPrecio,true);
		ProductosDAO.getInstance().insertar(objProducto);
	}
	public void bajaProducto(Integer intCodigo) throws ConnectionException, ParameterException, ClienteException, ProductosException
	{
		ProductosDAO.getInstance().eliminar(ProductosDAO.getInstance().getProducto(intCodigo));
	}
	public void modificarProducto(Integer intCodigo, String strTitulo, String strDescripcion, float fltPrecio, boolean bolEstado) throws ConnectionException, ParameterException
	{
		Producto objProducto;
		objProducto = new Producto(intCodigo, strTitulo, strDescripcion, fltPrecio,bolEstado);
		ProductosDAO.getInstance().modificarProducto(objProducto);
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
