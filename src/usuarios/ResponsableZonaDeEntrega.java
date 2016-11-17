package usuarios;
import java.util.Date;
import javax.swing.JOptionPane;
import connections.ReclamosZonaDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.ReclamoException;
import model.Accion;
import reclamos.ReclamoZona;
public class ResponsableZonaDeEntrega extends Rol
{
	@Override
	public void administrarReclamo(String strNumero, String strDescripcion)
	{
		ReclamoZona objReclamo;
		Accion objAccion;
		objAccion = new Accion(new Date(), strDescripcion);
		try
		{
			objReclamo = ReclamosZonaDAO.getInstance().getReclamo(strNumero);
			objReclamo.asociarAccion(objAccion);
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
	}
	@Override
	public void cerrarReclamo(String strNumero)
	{
		ReclamoZona objReclamo;
		try
		{
			objReclamo = ReclamosZonaDAO.getInstance().getReclamo(strNumero);
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
	}
}