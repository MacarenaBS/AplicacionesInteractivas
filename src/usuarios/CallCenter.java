package usuarios;
import javax.swing.JOptionPane;
import connections.ReclamosFacturacionDAO;
import connections.ReclamosInconsistenciasDAO;
import connections.ReclamosZonaDAO;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import model.Factura;
import model.Producto;
import reclamos.ReclamoFacturacion;
import reclamos.ReclamoInconsistencia;
import reclamos.ReclamoZona;
public class CallCenter extends Rol
{
	public void crearReclamoFacturacion(String strDescripcion, Cliente objCliente, Factura objFactura)
	{
		ReclamoFacturacion objReclamo;
		try
		{
			ReclamosFacturacionDAO.getInstance().insertarEnBase(strDescripcion, objCliente,objFactura);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No es posible conectarse a la base de datos.");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Por favor, revise el archivo de configuración, una o más configuraciones no son correctas");
		}
	}
	public void crearReclamoZonaDeEntrega(String strDescripcion, String strZona, Cliente objCliente)
	{
		ReclamoZona objReclamo;
		try
		{
			objReclamo = new ReclamoZona(ReclamosZonaDAO.getInstance().newId(), strDescripcion, objCliente, strZona);
			ReclamosZonaDAO.getInstance().insertar(objReclamo);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No es posible conectarse a la base de datos.");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Por favor, revise el archivo de configuración, una o más configuraciones no son correctas");
		}
	}
	public void crearReclamoInconsistencia(String strDescripcion, Producto objProducto, Integer intCantidad, Cliente objCliente)
	{
		ReclamoInconsistencia objReclamo;
		try
		{
			objReclamo = new ReclamoInconsistencia(ReclamosInconsistenciasDAO.getInstance().newId(), strDescripcion, objProducto, intCantidad, objCliente);
			ReclamosInconsistenciasDAO.getInstance().insertar(objReclamo);
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No es posible conectarse a la base de datos.");
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Por favor, revise el archivo de configuración, una o más configuraciones no son correctas");
		}
	}
	public void crearReclamoCompuesto()
	{
		
	}
	@Override
	public void administrarReclamo(String strNumero, String strDescipcion)
	{
		//El Call Center no puede administrar reclamos
	}
	@Override
	public void cerrarReclamo(String strNumero)
	{
		//El Call Center no puede cerrar reclamos
	}
}