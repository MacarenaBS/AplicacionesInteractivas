package usuarios;
public abstract class Persona
{
	private String strCodigoPersona;
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
	protected void setNombre(String strValue)
	{
		this.strNombre = strValue;
	}
	public Integer getDNI()
	{
		return this.intDNI;
	}
	protected void setDNI(Integer intValue)
	{
		this.intDNI = intValue;
	}
	public String getDomicilio()
	{
		return this.strDomicilio;
	}
	protected void setDomicilio(String strValue)
	{
		this.strDomicilio = strValue;
	}
	public String getTelefono()
	{
		return this.strTelefono;
	}
	protected void setTelefono(String strValue)
	{
		this.strTelefono = strValue;
	}
	public String getMail()
	{
		return this.strMail;
	}
	protected void setMail(String strValue)
	{
		this.strMail = strValue;
	}
	public Boolean getActivo()
	{
		return bolActivo;
	}
	protected void setActivo(Boolean bolActivo)
	{
		this.bolActivo = bolActivo;
	}
	protected void setCodigoPersona(String strValue)
	{
		this.strCodigoPersona = strValue;
	}
	public String getCodigoPersona()
	{
		return strCodigoPersona;
	}
	public Boolean soyLaPersona(String strCodigo)
	{
		return (this.getCodigoPersona() == strCodigo ? true : false);
	}
	public boolean equals(Persona objPersona)
	{
		return (this.getCodigoPersona() == objPersona.getCodigoPersona());
	}
}
