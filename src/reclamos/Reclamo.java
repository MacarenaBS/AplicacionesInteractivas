/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package reclamos;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import connections.AccionesDAO;
import enumerations.Estado;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import model.Accion;
import usuarios.Cliente;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * 
 * @author ezequiel.de-luca
 * @version 1.0
 */
public abstract class Reclamo
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private String strNumero;
	private Date objFecha;
	private Cliente objCliente;
	private String strDescripci�n;
	private Estado objEstado;
	protected List<Accion> colAcciones;
	/*==================================================*/
	/*==========Establece el n�mero de reclamo==========*/
	/*==================================================*/
	/**
	 * Establece el identificador del reclamo.
	 * @param strValue
	 */
	protected void setNumero(String strValue)
	{
		/*==================================================*/
		/*===============Establece el n�mero================*/
		/*==================================================*/
		this.strNumero = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Devuelve el n�mero de reclamo===========*/
	/*==================================================*/
	/**
	 * Devuelve el n�mero que identifica al reclamo.
	 * @return String intReclamo
	 */
	public String getNumero()
	{
		/*==================================================*/
		/*================Devuelve el n�mero================*/
		/*==================================================*/
		return this.strNumero;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=======Establece la descripci�n del reclamo=======*/
	/*==================================================*/
	/**
	 * Establece una descripci�n para el reclamo.
	 * @param strValue
	 */
	protected void setDescripcion(String strValue)
	{
		/*==================================================*/
		/*=============Establece la Descripci�n=============*/
		/*==================================================*/
		this.strDescripci�n = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=======Devuelve la descricpci�n del reclamo=======*/
	/*==================================================*/
	/**
	 * Devuelve la descripci�n del reclamo.
	 * @return String strDescription
	 */
	public String getDescripci�n()
	{
		/*==================================================*/
		/*==============Devuelve la descipci�n==============*/
		/*==================================================*/
		return this.strDescripci�n;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Establece el estado del cambio==========*/
	/*==================================================*/
	/**
	 * Establece el estado del reclamo.
	 * @param strValue
	 */
	protected void setEstado(String strValue)
	{
		/*==================================================*/
		/*===============Establece el estado================*/
		/*==================================================*/
		this.objEstado = Estado.valueOf(strValue);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Devuelve el estado del reclamo==========*/
	/*==================================================*/
	/**
	 * Devuelve el estado del reclamo
	 * @return
	 */
	public Estado getEstado()
	{
		/*==================================================*/
		/*================Devuelve el estado================*/
		/*==================================================*/
		return this.objEstado;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Establece la fecha del Reclamo==========*/
	/*==================================================*/
	/**
	 * Establece la fecha del reclamo.
	 * @param strValue
	 */
	protected void setFecha(Date objValue)
	{
		/*==================================================*/
		/*===============Establece La Fecha=================*/
		/*==================================================*/
		this.objFecha = objValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Devuelve la fecha del reclamo===========*/
	/*==================================================*/
	/**
	 * Devuelve la fecha del reclamo
	 * @return
	 */
	public Date getFecha()
	{
		/*==================================================*/
		/*================Devuelve la fecha=================*/
		/*==================================================*/
		return this.objFecha;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Establece el cliente del cambio==========*/
	/*==================================================*/
	/**
	 * Establece el Cliente del reclamo.
	 * @param strValue
	 */
	protected void setCliente(Cliente objCliente)
	{
		/*==================================================*/
		/*===============Establece el Cliente================*/
		/*==================================================*/
		this.objCliente = objCliente;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Devuelve el cliente del reclamo==========*/
	/*==================================================*/
	/**
	 * Devuelve el cliente del reclamo
	 * @return
	 */
	public Cliente getCliente()
	{
		/*==================================================*/
		/*================Devuelve el cliente================*/
		/*==================================================*/
		return this.objCliente;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*Devuelve la lista de acciones asociadas al reclamo*/
	/*==================================================*/
	/**
	 * Devuelve la lista de acciones asociadas al reclamo
	 * @return
	 */
	public List<Accion> getAcciones()
	{
		/*==================================================*/
		/*==========Devuelve la lista de acciones===========*/
		/*==================================================*/
		return this.colAcciones;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*========Agrega una nueva acci�n al reclamo========*/
	/*==================================================*/
	/**
	 * Asocia una existente al reclamo actual.
	 * @param objAccion
	 * @throws ParameterException 
	 * @throws ConnectionException 
	 */
	public void asociarAccion(Accion objAccion) throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==Verifica que la lista de acciones este creada===*/
		/*==================================================*/
		if (this.colAcciones == null)
		{
			/*==================================================*/
			/*============Crea la lista de acciones=============*/
			/*==================================================*/
			this.colAcciones = new ArrayList<Accion>();
		}
		/*==================================================*/
		/*=================Agrega la acci�n=================*/
		/*==================================================*/
		if (!this.colAcciones.contains(objAccion)){
			this.colAcciones.add(objAccion);
		}
		
		/*==================================================*/
		/*=======Insertar Acci�n en la base de datos========*/
		/*==================================================*/
		AccionesDAO.getInstance().insertar(this, objAccion);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===========Cambia el estado del reclamo===========*/
	/*==================================================*/
	/**
	 * Cambia el estado del reclamo por el valor definido. El valor definido debe estar restringido a una lista asociada (Activo, En Progreso, Cancelado, Cerrado)
	 * @param strValue
	 * @throws ParameterException 
	 * @throws ConnectionException 
	 */
	public void cambiarEstado(String strValue) throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Accion objAccion;
		/*==================================================*/
		/*==============Crea una nueva acci�n===============*/
		/*==================================================*/
		objAccion = new Accion(new Date(), "Cambio de estado de ".concat(this.getEstado().toString()).concat(" a ").concat(strValue));
		/*==================================================*/
		/*=================Cambia el estado=================*/
		/*==================================================*/
		this.setEstado(strValue);
		/*==================================================*/
		/*=========Vincula la acci�n con el reclamo=========*/
		/*==================================================*/
		this.asociarAccion(objAccion);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*================Cierra el reclamo=================*/
	/*==================================================*/
	public void cerrar() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*===========Cambia el estado del reclamo===========*/
		/*==================================================*/
		this.cambiarEstado("Cerrado");
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*Verifica si el n�mero de reclamo coincide con el actual*/
	/*==================================================*/
	/**
	 * Devuelve verdadero si el n�mero de reclamo provisto coincide con el reclamo actual y falso en caso contrario.
	 * @param intValue
	 * @return Boolean esReclamo
	 */
	public Boolean esReclamo(String strValue)
	{
		/*==================================================*/
		/*=========Compara el reclamo por el n�mero=========*/
		/*==================================================*/
		return this.getNumero().equalsIgnoreCase(strValue) ? true : false;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*======================Equals======================*/
	/*==================================================*/
	public boolean equals(ReclamoZona objReclamo)
	{
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return (this.getNumero().equalsIgnoreCase(objReclamo.getNumero()));
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	
	public void agregarAcciones(ArrayList<Accion> acciones){
		this.colAcciones = new ArrayList<Accion>(); //* Ver si trae problemas
		this.colAcciones.addAll(acciones);
	}
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/