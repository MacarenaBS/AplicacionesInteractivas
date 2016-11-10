package test;



import java.util.List;

import reclamos.ReclamoFacturacion;
import model.Accion;
import model.Factura;
import model.ItemFactura;
import model.Producto;
import connections.ClientesDAO;
import connections.FacturasDAO;
import connections.ProductosDAO;
import connections.ReclamosFacturacionDAO;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.ReclamoException;
import usuarios.Cliente;

public class Prueba {

	public static void main(String[] args) throws ParameterException, ConnectionException, ClienteException, FacturasException, ProductosException, ReclamoException {
		Cliente m= new Cliente(1,"abcd", 1234, "Domicilio A", "47434323", "pruebaa@gmail.com", true); //Funciona
//		ClientesDAO.getInstance().modificarCliente(m); //Funciona
//		ClientesDAO.getInstance().insertar(m); //Funciona
		//ClientesDAO.getInstance().eliminar(m); //Funciona
		//Producto p = new Producto(1, "B", "Producto B", (float) 0.5, true);
		//ProductosDAO.getInstance().insertar(p); //Funciona
		//ProductosDAO.getInstance().eliminar(p); //Funciona
		//ProductosDAO.getInstance().modificarProducto(p); //Funciona
//		Factura f = FacturasDAO.getInstance().getFactura(1);
//		System.out.println(f.getNumero());
//		System.out.println(f.getDescripcion());
//		System.out.println(f.getFecha());
//		for (ItemFactura it : f.getProductos())
//		{
//			System.out.println(it.getCantidad());
//			System.out.println(it.getProducto().getTitulo());
//		}
		
/** PRUEBAS RECLAMO FACTURACION */
		
//		ReclamoFacturacion rf= ReclamosFacturacionDAO.getInstance().getReclamo("1"); 
//		System.out.println("RECLAMO FACTURACION: \nDescripcion:"+ rf.getDescripción()+ " Numero: "+rf.getNumero());
//		List<Accion> acciones = rf.getAcciones();
//		
//		if (acciones.isEmpty()){
//			System.out.println("Este reclamo no tiene acciones asignadas");
//		}
//		else
//		{
//			for (Accion acc: acciones){
//				System.out.println("Accion: "+acc.getFecha().toString()+acc.getDescripcion());
//			}
//		} FUNCIONA
	
		
//		ReclamoFacturacion reclamoF= new ReclamoFacturacion("1", "Prueba Facturacion Modificada", "ingresado",ClientesDAO.getInstance().getCliente(1),FacturasDAO.getInstance().getFactura(1));
//		ReclamosFacturacionDAO.getInstance().actualizar(reclamoF); FUNCIONA

		ReclamoFacturacion reclamoF= new ReclamoFacturacion("6", "Prueba Facturacion Ingresada 3", "ingresado",ClientesDAO.getInstance().getCliente(1),FacturasDAO.getInstance().getFactura(1));
		ReclamosFacturacionDAO.getInstance().insertar(reclamoF); //FUNCIONA 
	 
		
		
	}

}
