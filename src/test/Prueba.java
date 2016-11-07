package test;

import model.Factura;
import model.ItemFactura;
import model.Producto;
import connections.ClientesDAO;
import connections.FacturasDAO;
import connections.ProductosDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import usuarios.Cliente;

public class Prueba {

	public static void main(String[] args) throws ParameterException, ConnectionException, ClienteException, FacturasException, ProductosException {
		//Cliente m= new Cliente(1,"abcd", 1234, "Domicilio A", "47434323", "pruebaa@gmail.com", true); //Funciona
		//ClientesDAO.getInstance().modificarCliente(m); //Funciona
		//ClientesDAO.getInstance().insertar(m); //Funciona
		//ClientesDAO.getInstance().eliminar(m); //Funciona
		//Producto p = new Producto(1, "B", "Producto B", (float) 0.5, true);
		//ProductosDAO.getInstance().insertar(p); //Funciona
		//ProductosDAO.getInstance().eliminar(p); //Funciona
		//ProductosDAO.getInstance().modificarProducto(p); //Funciona
		Factura f = FacturasDAO.getInstance().getFactura(1);
		System.out.println(f.getNumero());
		System.out.println(f.getDescripcion());
		System.out.println(f.getFecha());
		for (ItemFactura it : f.getProductos())
		{
			System.out.println(it.getCantidad());
			System.out.println(it.getProducto().getTitulo());
		}
	}

}
