package usuarios;
public abstract class Persona
{
	private int intCodigoPersona;
	private String strNombre;
	private Integer intDNI;
	private String strDomicilio;
	private String strTelefono;
	private String strMail;
	private Boolean bolActivo;
	public String getNombre()
	{
		return this.strNombre;
	}
	public void setNombre(String strValue)
	{
		this.strNombre = strValue;
	}
	public Integer getDNI()
	{
		return this.intDNI;
	}
	public void setDNI(Integer intValue)
	{
		this.intDNI = intValue;
	}
	public String getDomicilio()
	{
		return this.strDomicilio;
	}
	public void setDomicilio(String strValue)
	{
		this.strDomicilio = strValue;
	}
	public String getTelefono()
	{
		return this.strTelefono;
	}
	public void setTelefono(String strValue)
	{
		this.strTelefono = strValue;
	}
	public String getMail()
	{
		return this.strMail;
	}
	public void setMail(String strValue)
	{
		this.strMail = strValue;
	}
	public Boolean getActivo()
	{
		return bolActivo;
	}
	public void setActivo(Boolean bolActivo)
	{
		this.bolActivo = bolActivo;
	}
	public void setCodigoPersona(int intValue)
	{
		this.intCodigoPersona = intValue;
	}
	public int getCodigoPersona()
	{
		return intCodigoPersona;
	}
	public Boolean soyLaPersona(int intCodigo)
	{
		return (this.getCodigoPersona() == intCodigo ? true : false);
	}
	public boolean equals(Persona objPersona)
	{
		return (this.getCodigoPersona() == objPersona.getCodigoPersona());
	}
}