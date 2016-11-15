package controlador;

import connections.UsuariosDAO;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.UsuarioException;
import usuarios.*;
import usuarios.Usuario;

public class Controlador {
	
	private Usuario objUsuario;
	private static Controlador instance;
	
	private Controlador(){
		
	}
	
	public static Controlador getInstance(){
		
		if (instance == null)
		{
			instance= new Controlador();
		}
		return instance; 
	}
	
	private Usuario getUser(String strUsername) throws ConnectionException, ParameterException, UsuarioException{
		return UsuariosDAO.getInstance().getUsuario(strUsername);
	}
	
//	private boolean verificacionPasswordUsuario(String strUsername, String strPassword){
//		return objUsuario.passwordVerificacion(strUsername, strPassword);
//	}
	
	public boolean Connect(String strUsername, String strPassword) throws ConnectionException, ParameterException, UsuarioException{

		try{
			Usuario s= getUser(strUsername);
			this.objUsuario= s;
			
			if (this.objUsuario.passwordVerificacion(strUsername,strPassword)){
				System.out.println("Connection succesful - Welcome "+s.getStrUsername());
				return true;
			}
			else
			{
				System.out.println("Connection unsuccesful - Username or Password incorrect.");
				this.objUsuario=null;
				return false;
			}
		}
		catch(UsuarioException ex)
		{
			return false;
		}
		
		
		
	}
	
	public Usuario currentUser(){
		return this.objUsuario;
	}
	
	public void disconnect(){
		this.objUsuario=null;
	}
	
	public boolean crearUsuario(String strUsername, String strPassword, String strPermiso)
	{
		Usuario s= new Usuario(strUsername, strPassword, strPermiso);
		try {
			UsuariosDAO.getInstance().insertar(s);
		} catch (ConnectionException | ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

		
		
}
