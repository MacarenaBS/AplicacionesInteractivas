package test;

import connections.ClientesDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;

public class Prueba {

	public static void main(String[] args) {
	
		try {
			ClientesDAO.getInstance().getCliente(1);
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
		
		try {
			ClientesDAO.getInstance().getCliente(2);
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
