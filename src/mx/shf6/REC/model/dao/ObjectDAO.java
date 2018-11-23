package mx.shf6.REC.model.dao;

import mx.shf6.REC.model.Visitante;

import java.sql.Connection;

public interface ObjectDAO {
	public boolean crear(Connection connection, Object objeto);
	public Visitante leer(Connection connection, String campoBusqueda, String valorBusqueda);
	public boolean modificar(Connection connection, String correo);
	public boolean eliminar(Connection connection, Object objeto);
}