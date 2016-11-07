package test;

import connections.ClientesDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import usuarios.Cliente;

public class Prueba {

	public static void main(String[] args) {
	
		try {
			Cliente e= ClientesDAO.getInstance().getCliente(1);
			Cliente m= new Cliente(6,"Prueba", 1231233, "Domicilio A", "47434323", "pruebaa@gmail.com");
			ClientesDAO.getInstance().insertar(m);
			ClientesDAO.getInstance().eliminar(m);
			System.out.println(e.getNombre()+" "+e.getDNI());
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
