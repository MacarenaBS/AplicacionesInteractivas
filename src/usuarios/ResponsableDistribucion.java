package usuarios;
import java.util.Date;
import javax.swing.JOptionPane;
import connections.ReclamosInconsistenciasDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.ReclamoException;
import model.Accion;
import reclamos.ReclamoInconsistencia;
public class ResponsableDistribucion extends Rol
{
	@Override
	public void administrarReclamo(String strNumero, String strDescripcion)
	{
		ReclamoInconsistencia objReclamo;
		Accion objAccion;
		objAccion = new Accion(new Date(), strDescripcion);
		try
		{
			objReclamo = ReclamosInconsistenciasDAO.getInstance().getReclamo(strNumero);
			objReclamo.asociarAccion(objAccion);
		}
		catch (ReclamoException objException)
		{
			JOptionPane.showMessageDialog(null, "El reclamo de facturacion ".concat(String.valueOf(strNumero)).concat(" no existe en el sistema."));
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No es posible conectarse a la base de datos.");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "No existe un cliente asociado al reclamo de facturacion ".concat(String.valueOf(strNumero)));
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Por favor, revise el archivo de configuración, una o más configuraciones no son correctas");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay productos asociados al reclamo");
		}
	}
	@Override
	public void cerrarReclamo(String strNumero)
	{
		ReclamoInconsistencia objReclamo;
		try
		{
			objReclamo = ReclamosInconsistenciasDAO.getInstance().getReclamo(strNumero);
			objReclamo.cerrar();
			ReclamosInconsistenciasDAO.getInstance().actualizar(objReclamo);
		}
		catch (ReclamoException objException)
		{
			JOptionPane.showMessageDialog(null, "El reclamo de distribucion ".concat(strNumero).concat(" no existe en el sistema."));
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No es posible conectarse a la base de datos.");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "No existe un cliente asociado al reclamo de zona ".concat(String.valueOf(strNumero)));
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Por favor, revise el archivo de configuración, una o más configuraciones no son correctas");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay productos asociados al reclamo");
		}
	}
}