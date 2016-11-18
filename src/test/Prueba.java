package test;



import java.util.Date;
import java.util.List;

import reclamos.ReclamoFacturacion;
import reclamos.ReclamoInconsistencia;
import reclamos.ReclamoZona;
import model.Accion;
import model.Factura;
import model.ItemFactura;
import model.Producto;
import connections.AccionesDAO;
import connections.ClientesDAO;
import connections.FacturasDAO;
import connections.ProductosDAO;
import connections.ReclamosFacturacionDAO;
import connections.ReclamosInconsistenciasDAO;
import connections.ReclamosZonaDAO;
import connections.UsuariosDAO;
import controlador.Controlador;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.FacturasException;
import exceptions.ParameterException;
import exceptions.ProductosException;
import exceptions.ReclamoException;
import usuarios.Cliente;
import usuarios.Usuario;

public class Prueba {

	public static void main(String[] args) throws ParameterException, ConnectionException, ClienteException, FacturasException, ProductosException, ReclamoException {
//		Cliente m= new Cliente(1,"abcd", 1234, "Domicilio A", "47434323", "pruebaa@gmail.com", true); //Funciona
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

//		ReclamoFacturacion reclamoF= new ReclamoFacturacion("1", "Prueba Facturacion Ingresada 1", "ingresado",ClientesDAO.getInstance().getCliente(1),FacturasDAO.getInstance().getFactura(1));
//		ReclamosFacturacionDAO.getInstance().insertar(reclamoF); //FUNCIONA
		
//		ReclamoFacturacion rf= ReclamosFacturacionDAO.getInstance().getReclamo("1");
//		for (Accion a: rf.getAcciones()){
//			System.out.println(a.getDescripcion()); 
//		}
//		

/** PRUEBAS RECLAMO INCONSISTENCIAS **/


//		ReclamoInconsistencia ri= new ReclamoInconsistencia
//				("4", "Prueba reclamo inconsistencia Ingresado 4"
//				, ProductosDAO.getInstance().getProducto(2)
//				, 5, ClientesDAO.getInstance().getCliente(2)); //los saco de la bd porque no tengo ganas de crear objetos nuevos.
//		
//		ReclamosInconsistenciasDAO.getInstance().insertar(ri);
//		Accion acc=new Accion(new Date(),"Prueba");
//		AccionesDAO.getInstance().insertar(ri, acc);
		
		//FUNCIONA, inserta el reclamo y la accion de apertura, pero me tira este mensaje en algun lado: 
		//Instrucción INSERT en conflicto con la restricción FOREIGN KEY "fk_AccionesReclamosInconsistencias". El conflicto ha aparecido en la base de datos "AI", tabla "dbo.ReclamosInconsistencias", column 'strNumero'.
		//en AccionesDAO, cuando la inserta. Igual lo hace pero ??¿¿
		
//		ReclamoInconsistencia ri= ReclamosInconsistenciasDAO.getInstance().getReclamo("4"); //FUNCIONA
//		System.out.println(ri.getDescripción()+" "+ri.getItemFactura().getProducto().getTitulo()+" "+ri.getItemFactura().getCantidad());
		
//		for (Accion a: ri.getAcciones()){
//			System.out.println(a.getDescripcion()); //Me avive recien que hay que traer las acciones de la base de datos y no probé los demás.
//		}
		
		
//		ReclamoInconsistencia ri= new ReclamoInconsistencia
//				("2", "Prueba reclamo inconsistencia Modificado 2"
//				, ProductosDAO.getInstance().getProducto(2)
//				, 5, ClientesDAO.getInstance().getCliente(2));
//		ReclamosInconsistenciasDAO.getInstance().actualizar(ri); //FUNCIONA
		
/** PRUEBAS RECLAMOS ZONA **/ 
//		ReclamoZona rz= new ReclamoZona("1","Reclamo Zona Ingreso 1",ClientesDAO.getInstance().getCliente(3),"Palermo");
//		ReclamosZonaDAO.getInstance().insertar(rz); //FUNCIONA
		
//		ReclamoZona rz= ReclamosZonaDAO.getInstance().getReclamo("1");
////		System.out.println(rz.getDescripción()); //FUNCIONA
//		
//		for (Accion a: rz.getAcciones()){
//			System.out.println(a.getDescripcion()); //Me avive recien que hay que traer las acciones de la base de datos y no probé los demás.
//		}//Este funciona
		
//		ReclamoZona rz= new ReclamoZona("1","Reclamo Zona Modificado 1",ClientesDAO.getInstance().getCliente(3),"Palermo");
//		ReclamosZonaDAO.getInstance().actualizar(rz); //FUNCIONA
		
/** PRUEBA ABM USUARIO **/
		
//		Usuario us= new Usuario("MacarenaBS","contraseña","Administrador"); //agregar restricciones de que solo pueda ponerse roles
//		UsuariosDAO.getInstance().insertar(us); //FUNCIONA
		
//		UsuariosDAO.getInstance().eliminar(us); //FUNCIONA
		
//		Usuario us= new Usuario("MacarenaBS","contraseñaModificada","CallCenter");
//		UsuariosDAO.getInstance().modificarUsuario(us); //FUNCIONA
//		Crear una conexión de prueba
//		Controlador.getInstance().testConnect(); //Funciona
//		Controlador.getInstance().altaCliente("Ezequiel De Luca", "Conde 729 7° A", 32437130, "1568979393", "delucaezequiel@gmail.com"); //Funciona
//		Controlador.getInstance().bajaCliente(1); //Funciona
//		Controlador.getInstance().modificarCliente(1, "Eze", "Conde 729", 32437130, "45510383", "delucaezequiel@hotmail.com", false); //Funciona
	}

}
