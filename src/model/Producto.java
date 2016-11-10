/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package model;

import reclamos.ReclamoZona;

/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class Producto
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static Integer intCodigo = 0;
	private String strTitulo;
	private String strDescripcion;
	private float fltPrecio;
	private Boolean bolActivo;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	public Producto(Integer intCodigo, String strTitulo, String strDescripcion, Float fltPrecio, Boolean bolEstado)
	{
		this.setCodigo(intCodigo);
		this.setTitulo(strTitulo);
		this.setDescripcion(strDescripcion);
		this.setPrecio(fltPrecio);
		this.setActivo(bolEstado);
	}
	
	public Producto(String strTitulo, String strDescripcion, float fltPrecio, Boolean bolEstado)
	{
		this.setCodigo();
		this.setTitulo(strTitulo);
		this.setDescripcion(strDescripcion);
		this.setPrecio(fltPrecio);
		this.setActivo(bolEstado);
	}


	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Devuelve el c�digo del producto==========*/
	/*==================================================*/
	/**
	 * @return C�digo de producto
	 */
	public Integer getCodigo()
	{
		/*==================================================*/
		/*================Devuelve el c�digo================*/
		/*==================================================*/
		return intCodigo;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Establece el c�digo del producto=========*/
	/*==================================================*/
	/**
	 * Establece el c�digo del producto
	 * @param strValue C�digo del producto
	 */
	public void setCodigo()
	{
		/*==================================================*/
		/*===============Establece el c�digo================*/
		/*==================================================*/
		this.intCodigo= intCodigo++;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Establece el c�digo del producto=========*/
	/*==================================================*/
	/**
	 * Establece el c�digo del producto
	 * @param strValue C�digo del producto
	 */
	public void setCodigo(Integer intValue)
	{
		/*==================================================*/
		/*===============Establece el c�digo================*/
		/*==================================================*/
		intCodigo = intValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Devuelve el nombre del producto==========*/
	/*==================================================*/
	/**
	 * @return Nombre del producto
	 */
	public String getTitulo()
	{
		/*==================================================*/
		/*================Devuelve el nombre================*/
		/*==================================================*/
		return strTitulo;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Establece el nombre del producto=========*/
	/*==================================================*/
	/**
	 * Establece el nombre del producto
	 * @param strValue Nombre del producto
	 */
	public void setTitulo(String strValue)
	{
		/*==================================================*/
		/*===============Establece el nombre================*/
		/*==================================================*/
		this.strTitulo = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=======Devuelve la descripci�n del producto=======*/
	/*==================================================*/
	/**
	 * @return Descripci�n del producto
	 */
	public String getDescripcion()
	{
		/*==================================================*/
		/*=============Devuelve la descripci�n==============*/
		/*==================================================*/
		return strDescripcion;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*======Establece la descripci�n del producto=======*/
	/*==================================================*/
	/**
	 * Establece la descripci�n del producto.
	 * @param strValue Descripci�n del producto
	 */
	public void setDescripcion(String strValue)
	{
		/*==================================================*/
		/*=============Establece la descipci�n==============*/
		/*==================================================*/
		this.strDescripcion = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Devuelve el precio del producto==========*/
	/*==================================================*/
	/**
	 * @return Precio del producto
	 */
	public float getPrecio()
	{
		/*==================================================*/
		/*===============Devuelve el precio================*/
		/*==================================================*/
		return fltPrecio;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Establece el precio del producto=========*/
	/*==================================================*/
	/**
	 * Establece el precio del producto.
	 * @param fltValue Precio del producto
	 */
	public void setPrecio(float fltValue)
	{
		/*==================================================*/
		/*===============Establece el precio================*/
		/*==================================================*/
		this.fltPrecio = fltValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Devuelve el estado del producto==========*/
	/*==================================================*/
	/**
	 * @return Estado del producto
	 */
	public Boolean getActivo()
	{
		/*==================================================*/
		/*================Devuelve el estado================*/
		/*==================================================*/
		return bolActivo;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==========Cambia el estado del producto===========*/
	/*==================================================*/
	/**
	 * Cambia el estado de actividad del producto
	 * @param bolValue Estado de actividad
	 */
	private void setActivo(Boolean bolValue)
	{
		/*==================================================*/
		/*=================Cambia el estado==================*/
		/*==================================================*/
		this.bolActivo = bolValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====Cambia el estado del producto a inactivo=====*/
	/*==================================================*/
	/**
	 * Cambia el estado del producto a inactivo.
	 */
	public void eliminar()
	{
		/*==================================================*/
		/*===========Cambia el estado a inactivo============*/
		/*==================================================*/
		this.setActivo(false);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*======Modifica la informaci�n del producto========*/
	/*==================================================*/
	/**
	 * Modifica la informaci�n del producto.
	 * @param fltPrecio Precio
	 * @param bolActivo Estado de actividad
	 */
	public void modificatInformacion(float fltPrecio, Boolean bolActivo)
	{
		/*==================================================*/
		/*================Modifica el precio================*/
		/*==================================================*/
		this.fltPrecio = fltPrecio;
		/*==================================================*/
		/*================Modifica el estado================*/
		/*==================================================*/
		this.bolActivo = bolActivo;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*========Compara el producto por su c�digo=========*/
	/*==================================================*/
	/**
	 * @param intCodigo C�digo de producto
	 * @return Coincidencia
	 */
	public Boolean esProducto(Integer intCodigo)
	{
		/*==================================================*/
		/*===============Compara los c�digos================*/
		/*==================================================*/
		return this.getCodigo() == intCodigo ? true : false;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*======================Equals======================*/
	/*==================================================*/
	public boolean equals(Producto objProducto)
	{
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return (this.getCodigo() == objProducto.getCodigo());
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/