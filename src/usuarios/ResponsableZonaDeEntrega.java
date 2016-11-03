/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.util.Date;
import javax.swing.JOptionPane;
import connections.ReclamosZonaDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.ReclamoException;
import model.Accion;
import reclamos.ReclamoZona;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class ResponsableZonaDeEntrega extends Rol
{
	/*==================================================*/
	/*===============Administrar Reclamo================*/
	/*==================================================*/
	/**
	 * Agrega una nueva acción a un reclamo del tipo Zona.
	 * @param strNumero Número de reclamo sobre el cual se ejecutará la acción
	 * @throws ConnectionException 
	 * @throws ReclamoException 
	 */
	@Override
	public void administrarReclamo(String strNumero, String strDescripcion)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoZona objReclamo;
		Accion objAccion;
		/*==================================================*/
		/*==================Crea La Acción==================*/
		/*==================================================*/
		objAccion = new Accion(new Date(), strDescripcion);
		try
		{
			/*==================================================*/
			/*===============Recupera El Reclamo================*/
			/*==================================================*/
			objReclamo = ReclamosZonaDAO.getInstance().getReclamo(strNumero);
			/*==================================================*/
			/*=Vincula el Reclamo con la Acción Correspondiente=*/
			/*==================================================*/
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
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==================Cerrar Reclamo==================*/
	/*==================================================*/
	@Override
	/**
	 * Cierra el reclamo especificado
	 * @param strNumero Número de Reclamo
	 */
	public void cerrarReclamo(String strNumero)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoZona objReclamo;
		try
		{
			/*==================================================*/
			/*===============Recupera El Reclamo================*/
			/*==================================================*/
			objReclamo = ReclamosZonaDAO.getInstance().getReclamo(strNumero);
			/*==================================================*/
			/*==================Cerrar Reclamo==================*/
			/*==================================================*/
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
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/