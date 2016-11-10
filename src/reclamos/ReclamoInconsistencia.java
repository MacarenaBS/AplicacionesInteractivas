/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package reclamos;
import java.util.Date;

import exceptions.ConnectionException;
import exceptions.ParameterException;
import model.Accion;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import model.ItemFactura;
import model.Producto;
import usuarios.Cliente;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class ReclamoInconsistencia extends Reclamo
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private ItemFactura objItemFactura;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Crea un reclamos existente en la base de datos
	 * @param strNumero Número de Reclamo
	 * @param strDescription Descripcion
	 * @param strEstado Estado
	 * @param objProducto Producto
	 * @param intCantidad Cantidad de producto asociado
	 * @param objCliente Cliente
	 */
	public ReclamoInconsistencia(String strNumero, String strDescripcion, String strEstado, Producto objProducto, Integer intCantidad, Cliente objCliente)
	{
		this.setNumero(strNumero);
		this.setDescripcion(strDescripcion);
		this.setEstado(strEstado);
		this.setCliente(objCliente);
		this.setItemFactura(intCantidad, objProducto);
		
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Crea un reclamos nuevo
	 * @param intNumero Número de reclamo
	 * @param strDescription Descripcion
	 * @param objProducto Producto
	 * @param intCantidad Cantidad de producto asociado
	 * @param objCliente Cliente
	 * @throws ParameterException 
	 * @throws ConnectionException 
	 */
	public ReclamoInconsistencia(String strNumero, String strDescripcion, Producto objProducto, Integer intCantidad, Cliente objCliente) throws ConnectionException, ParameterException
	{
		this.setNumero(strNumero);
		this.setDescripcion(strDescripcion);
		this.setEstado("ingresado");
		this.setCliente(objCliente);
		this.setItemFactura(intCantidad, objProducto);
		
		/*==================================================*/
		/*=============Crear Acción de Apertura=============*/
		/*==================================================*/
		Accion objAccion = new Accion(new Date(), "Apertura de Reclamo");
		/*==================================================*/
		/*==================Asociar Acción==================*/
		/*==================================================*/
		this.asociarAccion(objAccion);

	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Set Item Factura=================*/
	/*==================================================*/
	/**
	 * 
	 * @param intCantidad
	 * @param objProducto
	 */
	private void setItemFactura(Integer intCantidad, Producto objProducto)
	{
		this.objItemFactura.setCantidad(intCantidad);
		this.objItemFactura.setProducto(objProducto);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Get Item Factura=================*/
	/*==================================================*/
	/**
	 * @return Item Factura
	 */
	public ItemFactura getItemFactura()
	{
		/*==================================================*/
		/*=================Devuelve el Item=================*/
		/*==================================================*/
		return this.objItemFactura;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/