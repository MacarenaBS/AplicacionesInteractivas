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
	 * @param strDomicilio Direcci�n de residencia
	 * @param strTelefono N�mero de contacto telef�nico
	 * @param strMail Direcci�n de correo electr�nico
	 */
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	public Cliente(String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail)
	{
		/*==================================================*/
		/*=========Establece el n�mero de documento=========*/
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
		/*=========Establece el n�mero de tel�fono==========*/
		/*==================================================*/
		this.setTelefono(strTelefono);
		/*==================================================*/
		/*===Establece la direcci�n de correo electr�nico===*/
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
		/*=========Establece el n�mero de documento=========*/
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
		/*=========Establece el n�mero de tel�fono==========*/
		/*==================================================*/
		this.setTelefono(strTelefono);
		/*==================================================*/
		/*===Establece la direcci�n de correo electr�nico===*/
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
	 * @param intDNI N�mero de documento
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
	 * Modifica la informaci�n de un cliente espec�fico.
	 * @param intDNI N�mero de documento
	 * @param strNombre Nombre
	 * @param strDomicilio Domicilio de residencia
	 * @param strTelefono N�mero de contacto telef�nico
	 * @param strMail Direcci�n de correo electr�nico
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
		/*=========Reemplaza el n�mero de tel�fono==========*/
		/*==================================================*/
		this.setTelefono(strTelefono);
		/*==================================================*/
		/*===Reemplaza la direcci�n de correo electr�nico===*/
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
	 * Devuelve verdadero si el n�mero de documento provisto coincide con el del cliente y falso en caso contrario.
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