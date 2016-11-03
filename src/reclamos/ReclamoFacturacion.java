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
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import model.Factura;
import usuarios.Cliente;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class ReclamoFacturacion extends Reclamo
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private Factura objFactura;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Genera un reclamo nuevo
	 * @param strNumero Número de reclamo
	 * @param strDescripcion Descripción
	 * @param objCliente Cliente
	 * @param objFactura Factura
	 */
	public ReclamoFacturacion(String strNumero, String strDescripcion, Cliente objCliente, Factura objFactura)
	{
		this.setNumero(strNumero);
		this.setDescripcion(strDescripcion);
		this.setEstado("INGRESADO");
		this.setFecha(new Date());
		this.setCliente(objCliente);
		this.colAcciones = new ArrayList<Accion>();
		this.setFactura(objFactura);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Carga un reclamo de facturación de la base de datos
	 * @param strNumero Número de Reclamo
	 * @param strDescripcion Descripción
	 * @param strEstado Estado
	 * @param objCliente Cliente
	 * @param objFactura Factura
	 * @throws ParameterException 
	 * @throws ConnectionException 
	 */
	public ReclamoFacturacion(String strNumero, String strDescripcion, String strEstado, Cliente objCliente, Factura objFactura) throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Accion objAccion;
		this.setNumero(strNumero);
		this.setDescripcion(strDescripcion);
		this.setEstado(strEstado);
		this.setFecha(new Date());
		this.setCliente(objCliente);
		this.colAcciones = new ArrayList<Accion>();
		this.setFactura(objFactura);
		/*==================================================*/
		/*=============Crear Acción de Apertura=============*/
		/*==================================================*/
		objAccion = new Accion(new Date(), "Apertura de Reclamo");
		/*==================================================*/
		/*==================Asociar Acción==================*/
		/*==================================================*/
		this.asociarAccion(objAccion);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Set Zona=====================*/
	/*==================================================*/
	/**
	 * Vincula una factura con el reclamo.
	 * @param objFactura Factura a vincular
	 */
	private void setFactura(Factura objFactura)
	{
		/*==================================================*/
		/*================Vincula La Factura================*/
		/*==================================================*/
		this.objFactura = objFactura;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Factura====================*/
	/*==================================================*/
	/**
	 * Devuelve la Factura asociada al reclamo.
	 * @return Factura
	 */
	public Factura getFactura()
	{
		/*==================================================*/
		/*===============Devuelve la Factura================*/
		/*==================================================*/
		return this.objFactura;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/