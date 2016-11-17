package usuarios;
import javax.swing.JOptionPane;
import connections.ReclamosFacturacionDAO;
import connections.ReclamosInconsistenciasDAO;
import connections.ReclamosZonaDAO;
import enumerations.Tipo;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.ReclamoException;
import reclamos.Reclamo;
public class Consulta extends Rol
{
	public Reclamo obtenerReclamo(Tipo objType, String strNumero)
	{
		Reclamo objReclamo;
		objReclamo = null;
		try
		{
			switch (objType.toString())
			{
			case "Cantidad" : objReclamo = ReclamosInconsistenciasDAO.getInstance().getReclamo(strNumero);break;
			case "Producto" : objReclamo = ReclamosInconsistenciasDAO.getInstance().getReclamo(strNumero); break;
			case "Faltante" : objReclamo = ReclamosInconsistenciasDAO.getInstance().getReclamo(strNumero); break;
			case "Zona" : objReclamo = ReclamosZonaDAO.getInstance().getReclamo(strNumero); break;
			case "Facturación" : objReclamo = ReclamosFacturacionDAO.getInstance().getReclamo(strNumero); break;
			}
		}
		catch (ReclamoException objException)
		{
			JOptionPane.showMessageDialog(null, "El reclamo de ".concat(String.valueOf(strNumero)).concat(" no existe en el sistema."));
		}
		catch (ConnectionException objException)
		{
			JOptionPane.showMessageDialog(null, "No es posible conectarse a la base de datos.");
		}
		catch (ClienteException objException)
		{
			JOptionPane.showMessageDialog(null, "No existe un cliente asociado al reclamo ".concat(String.valueOf(strNumero)));
		}
		catch (ParameterException objException)
		{
			JOptionPane.showMessageDialog(null, "Por favor, revise el archivo de configuración, una o más configuraciones no son correctas");
		}
		catch (ProductosException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay productos asociados al reclamo");
		}
		catch (FacturasException objException)
		{
			JOptionPane.showMessageDialog(null, "No hay una factura asociada al reclamo");
		}
		return objReclamo;
	}
	@Override
	public void administrarReclamo(String strNumero, String strDescipcion)
	{
		//El usuario de consulta no puede administrar reclamos
	}
	@Override
	public void cerrarReclamo(String strNumero)
	{
		//El usuario de consulta no puede cerrar reclamos
	}
}