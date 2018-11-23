package mx.shf6.REC.testing;

import java.util.ArrayList;

import mx.shf6.REC.model.Visitante;
import mx.shf6.REC.model.dao.VisitanteDAO;
import mx.shf6.REC.utilities.ConnectionDB;

public class JoelTest {
	static ConnectionDB connectionDB =  new ConnectionDB("canadevi", "192.168.0.216", "conn01", "Simons83Mx");
	
	public static void testLeerSolicitud() {
		VisitanteDAO  visitanteDAO = new VisitanteDAO(); 
		ArrayList <Object> resultadoSolicitud = visitanteDAO.leer(connectionDB.conectarMySQL(), "", "");      
    	for(Object vistante: resultadoSolicitud) {
    		System.out.println(((Visitante) vistante).getNombre());
    		System.out.println(((Visitante) vistante).getApellidoPaterno());
    	}
	}
	
	public static void main(String[] args) throws Exception{
		testLeerSolicitud();		
    }

}
