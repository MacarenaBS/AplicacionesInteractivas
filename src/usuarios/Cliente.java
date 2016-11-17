package usuarios;
public class Cliente extends Persona
{
	public Cliente(String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail)
	{
		this.setDNI(intDNI);
		this.setNombre(strNombre);
		this.setDomicilio(strDomicilio);
		this.setTelefono(strTelefono);
		this.setMail(strMail);
		this.setActivo(true);
	}
	public Cliente(String strCodigo, String strNombre, Integer intDNI, String strDomicilio, String strTelefono, String strMail, Boolean bolActivo)
	{
		this.setCodigoPersona(strCodigo);
		this.setDNI(intDNI);
		this.setNombre(strNombre);
		this.setDomicilio(strDomicilio);
		this.setTelefono(strTelefono);
		this.setMail(strMail);
		this.setActivo(bolActivo);
	}
	public void eliminarCliente(Integer intDNI)
	{
		this.setActivo(false);
	}
	public void actualizarDatos(String strNombre, String strDomicilio, String strTelefono, String strMail)
	{
		this.setNombre(strNombre);
		this.setDomicilio(strDomicilio);
		this.setTelefono(strTelefono);
		this.setMail(strMail);
	}
	public Boolean esCliente(Integer intValue)
	{
		return this.getDNI() == intValue ? true : false;		
	}
}