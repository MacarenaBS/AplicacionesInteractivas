/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class Consulta extends Rol
{
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
