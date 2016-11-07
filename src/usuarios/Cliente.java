/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class Cliente extends Persona
{
	/**
	 * Crea un nuevo cliente en el sistema.
	 * @param strNombre Nombre del cliente
	 * @param strDomicilio Dirección de residencia
	 * @param strTelefono Número de contacto telefónico
	 * @param strMail Dirección de correo electrónico
	 */
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	public Cliente(String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail)
	{
		/*==================================================*/
		/*=========Establece el número de documento=========*/
		/*==================================================*/
		this.setDNI(intDNI);
		/*==================================================*/
		/*===============Establece el nombre================*/
		/*==================================================*/
		this.setNombre(strNombre);
		/*==================================================*/
		/*==============Establece el domicilio==============*/
		/*==================================================*/
		this.setDomicilio(strDomicilio);
		/*==================================================*/
		/*=========Establece el número de teléfono==========*/
		/*==================================================*/
		this.setTelefono(strTelefono);
		/*==================================================*/
		/*===Establece la dirección de correo electrónico===*/
		/*==================================================*/
		this.setMail(strMail);
		/*==================================================*/
		/*===========Crea al usuario como activo============*/
		/*==================================================*/
		this.setActivo(true);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	public Cliente(Integer intCodigo, String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail, Boolean bolActivo)
	{
		/*==================================================*/
		/*=========Establece el codigo del cliente==========*/
		/*==================================================*/
		this.setCodigoPersona(intCodigo);
		/*==================================================*/
		/*=========Establece el número de documento=========*/
		/*==================================================*/
		this.setDNI(intDNI);
		/*==================================================*/
		/*===============Establece el nombre================*/
		/*==================================================*/
		this.setNombre(strNombre);
		/*==================================================*/
		/*==============Establece el domicilio==============*/
		/*==================================================*/
		this.setDomicilio(strDomicilio);
		/*==================================================*/
		/*=========Establece el número de teléfono==========*/
		/*==================================================*/
		this.setTelefono(strTelefono);
		/*==================================================*/
		/*===Establece la dirección de correo electrónico===*/
		/*==================================================*/
		this.setMail(strMail);
		/*==================================================*/
		/*===========Crea al usuario como activo============*/
		/*==================================================*/
		this.setActivo(bolActivo);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*====Cambia el estado de un cliente a inactivo=====*/
	/*==================================================*/
	/**
	 * Cambia el estado de un cliente de Activo a Inactivo.
	 * @param intDNI Número de documento
	 */
	public void eliminarCliente(Integer intDNI)
	{
		/*==================================================*/
		/*=================Cambio el estado=================*/
		/*==================================================*/		
		this.setActivo(false);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=========Reemplaza los datos del cliente==========*/
	/*==================================================*/
	/**
	 * Modifica la información de un cliente específico.
	 * @param intDNI Número de documento
	 * @param strNombre Nombre
	 * @param strDomicilio Domicilio de residencia
	 * @param strTelefono Número de contacto telefónico
	 * @param strMail Dirección de correo electrónico
	 */
	public void actualizarDatos(String strNombre, String strDomicilio, String strTelefono, String strMail)
	{
		/*==================================================*/
		/*===============Reemplaza el nombre================*/
		/*==================================================*/
		this.setNombre(strNombre);
		/*==================================================*/
		/*==============Reemplaza el domicilio==============*/
		/*==================================================*/
		this.setDomicilio(strDomicilio);
		/*==================================================*/
		/*=========Reemplaza el número de teléfono==========*/
		/*==================================================*/
		this.setTelefono(strTelefono);
		/*==================================================*/
		/*===Reemplaza la dirección de correo electrónico===*/
		/*==================================================*/
		this.setMail(strMail);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*Informa si el dni ingresado pertenece a un cliente*/
	/*==================================================*/
	/**
	 * Devuelve verdadero si el número de documento provisto coincide con el del cliente y falso en caso contrario.
	 * @param intValue
	 * @return Boolean esCliente
	 */
	public Boolean esCliente(Integer intValue)
	{
		/*==================================================*/
		/*===============Evalua si es cliente===============*/
		/*==================================================*/
		return this.getDNI() == intValue ? true : false;		
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/