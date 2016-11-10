/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
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
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class CallCenter extends Rol
{
	/*==================================================*/
	/*============Crear Reclamo Facturación=============*/
	/*==================================================*/
	/**
	 * Crea un nuevo reclamo de facturación
	 * @param strDescripcion
	 * @param objCliente
	 * @param objFactura
	 */
	public void crearReclamoFacturacion(String strDescripcion, Cliente objCliente, Factura objFactura)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoFacturacion objReclamo;
		try
		{
			/*==================================================*/
			/*==================Crear Reclamo===================*/
			/*==================================================*/
			objReclamo = new ReclamoFacturacion(ReclamosFacturacionDAO.getInstance().newId(), strDescripcion, objCliente, objFactura);
			/*==================================================*/
			/*===========Agregar Reclamo a las Listas===========*/
			/*==================================================*/
			ReclamosFacturacionDAO.getInstance().insertar(objReclamo);
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
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/**
	 * Crea un nuevo reclamo de zona
	 * @param strDescripcion
	 * @param strZona
	 * @param objCliente
	 */
	public void crearReclamoZonaDeEntrega(String strDescripcion, String strZona, Cliente objCliente)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoZona objReclamo;
		try
		{
			/*==================================================*/
			/*==================Crear Reclamo===================*/
			/*==================================================*/
			objReclamo = new ReclamoZona(ReclamosZonaDAO.getInstance().newId(), strDescripcion, objCliente, strZona);
			/*==================================================*/
			/*===========Agregar Reclamo a las Listas===========*/
			/*==================================================*/
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
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	public void crearReclamoInconsistencia(String strDescripcion, Producto objProducto, Integer intCantidad, Cliente objCliente)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoInconsistencia objReclamo;
		try
		{
			/*==================================================*/
			/*==================Crear Reclamo===================*/
			/*==================================================*/
			objReclamo = new ReclamoInconsistencia(ReclamosInconsistenciasDAO.getInstance().newId(), strDescripcion, objProducto, intCantidad, objCliente);
			/*==================================================*/
			/*===========Agregar Reclamo a las Listas===========*/
			/*==================================================*/
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
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/