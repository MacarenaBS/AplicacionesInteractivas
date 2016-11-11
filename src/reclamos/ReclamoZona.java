/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package reclamos;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.util.ArrayList;
import java.util.Date;

import exceptions.ConnectionException;
import exceptions.ParameterException;
import model.Accion;
import usuarios.Cliente;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class ReclamoZona extends Reclamo
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private String strZona;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Genera un reclamo nuevo
	 * @param strNumero Número de reclamo
	 * @param strDescripcion Descripción
	 * @param strZona Zona de Entrega
	 * @param objCliente Cliente
	 * @throws ParameterException 
	 * @throws ConnectionException 
	 */
	public ReclamoZona(String strNumero, String strDescripcion, String strEstado, String strZona, Cliente objCliente) throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		
		this.setNumero(strNumero);
		this.setDescripcion(strDescripcion);
		this.setEstado(strEstado);
		
		this.setFecha(new Date());
		this.setCliente(objCliente);
		this.setZona(strZona);
		this.colAcciones = new ArrayList<Accion>();
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	public ReclamoZona(String strNumero, String strDescripcion, Cliente objCliente, String strZona) throws ConnectionException, ParameterException
	{
		this.setNumero(strNumero);
		this.setDescripcion(strDescripcion);
		this.setEstado("ingresado");
		this.setFecha(new Date());
		this.setCliente(objCliente);
		
		/*==================================================*/
		/*=============Crear Acción de Apertura=============*/
		/*==================================================*/
		Accion objAccion = new Accion(new Date(), "Apertura de Reclamo");
		/*==================================================*/
		/*==================Asociar Acción==================*/
		/*==================================================*/
		this.asociarAccion(objAccion);
		
		this.setZona(strZona);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Set Zona=====================*/
	/*==================================================*/
	/**
	 * Establece la Zona del Reclamo
	 * @param strValue Zona del reclamo
	 */
	private void setZona(String strValue)
	{
		/*==================================================*/
		/*================Establece la Zona=================*/
		/*==================================================*/
		this.strZona = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Get Zona=====================*/
	/*==================================================*/
	/**
	 * Devuelve la Zona asociada al reclamo.
	 * @return
	 */
	public String getZona()
	{
		/*==================================================*/
		/*=================Devuelve la Zona=================*/
		/*==================================================*/
		return this.strZona;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/