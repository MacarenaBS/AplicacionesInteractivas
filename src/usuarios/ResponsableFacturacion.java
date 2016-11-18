package usuarios;
import java.util.Date;
import javax.swing.JOptionPane;
import connections.ReclamosFacturacionDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.ReclamoException;
import model.Accion;
import reclamos.ReclamoFacturacion;
public class ResponsableFacturacion extends Rol
{
	@Override
	public void administrarReclamo(String strNumero, String strDescripcion)
	{
		ReclamoFacturacion objReclamo;
		Accion objAccion;
		objAccion = new Accion(new Date(), strDescripcion);
		try
		{
			objReclamo = ReclamosFacturacionDAO.getInstance().getReclamo(strNumero);
			objReclamo.asociarAccion(objAccion);
			ReclamosFacturacionDAO.getInstance().actualizar(objReclamo);
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
		catch (FacturasException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay una factura asociada al reclamo de facturación");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay productos asociados al reclamo");
		}
	}
	@Override
	public void cerrarReclamo(String strNumero)
	{
		ReclamoFacturacion objReclamo;
		try
		{
			objReclamo = ReclamosFacturacionDAO.getInstance().getReclamo(strNumero);
			objReclamo.cerrar();
		}
		catch (ReclamoException objException)
		{
			JOptionPane.showMessageDialog(null, "El reclamo de zona ".concat(String.valueOf(strNumero)).concat(" no existe en el sistema."));
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
		catch (FacturasException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay una factura asociada al reclamo de facturación");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay productos asociados al reclamo");
		}
	}
}
