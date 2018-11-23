package mx.shf6.REC.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.shf6.REC.model.Visitante;
import mx.shf6.REC.utilities.Notificacion;


public class VisitanteDAO implements ObjectDAO {	
	@Override
	public boolean crear(Connection connection, Object Visitante){	
		Visitante visitante=(Visitante)Visitante;
		String query=" INSERT INTO informacionvivienda (nombre, apellidoPaterno, apellidoMaterno, sexo, numeroSeguroSocial, fechaNacimiento, fechaRegistro, tipoCredito, estado, municipio, email, asistencia) "
				+ "values ( ?, ?, ?, ?, ?, ?, curdate(), ?, ?, ?, ?, 1)";
		try {	
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, visitante.getNombre());
			preparedStatement.setString(2, visitante.getApellidoPaterno());
			preparedStatement.setString(3, visitante.getApellidoMaterno());
			preparedStatement.setString(4, visitante.getSexo());
			preparedStatement.setString(5, visitante.getNumeroSeguroSocial());
			preparedStatement.setDate(6, visitante.getFechaNacimiento());
			preparedStatement.setString(7, visitante.getTipoCredito());
			preparedStatement.setString(8, visitante.getEstado());
			preparedStatement.setString(9, visitante.getMunicipio());
			preparedStatement.setString(10, visitante.getEmail());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.dialogoException(e);
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO
	
	@Override
	public Visitante leer(Connection connection, String campoBusqueda, String valorBusqueda) {
		String query ="";
		Visitante visitante = new Visitante();
		if(campoBusqueda.isEmpty() && valorBusqueda.isEmpty()) {
			query="SELECT * FROM visitantes ORDER BY apellidoPaterno;";
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					visitante = new Visitante();	
					visitante.setSysPk(resultSet.getInt(1));
					visitante.setNombre(resultSet.getString(2));
					visitante.setApellidoPaterno(resultSet.getString(3));
					visitante.setApellidoMaterno(resultSet.getString(4));
					visitante.setSexo(resultSet.getString(5));
					visitante.setNumeroSeguroSocial(resultSet.getString(6));
					visitante.setFechaNacimiento(resultSet.getDate(7));
					visitante.setFechaRegistro(resultSet.getDate(8));
					visitante.setTipoCredito(resultSet.getString(9));
					visitante.setEstado(resultSet.getString(10));
					visitante.setMunicipio(resultSet.getString(11));
					visitante.setEmail(resultSet.getString(12));
				}//FIN WHILE
			}catch (SQLException e) {
				Notificacion.dialogoException(e);
			}//FIN TRY/CATCH
		}else {
			if(campoBusqueda.isEmpty()) {
				query="SELECT * "
						+ "FROM visitantes "
						+ "WHERE concat(nombre, ' ', apellidoPaterno, ' ', apellidoMaterno) like '%" + valorBusqueda +"%' "
						+ "OR email like '%" + valorBusqueda +"%' ORDER BY apellidoPaterno;";	
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					while (resultSet.next()) {
						visitante = new Visitante();	
						visitante.setSysPk(resultSet.getInt(1));
						visitante.setNombre(resultSet.getString(2));
						visitante.setApellidoPaterno(resultSet.getString(3));
						visitante.setApellidoMaterno(resultSet.getString(4));
						visitante.setSexo(resultSet.getString(5));
						visitante.setNumeroSeguroSocial(resultSet.getString(6));
						visitante.setFechaNacimiento(resultSet.getDate(7));
						visitante.setFechaRegistro(resultSet.getDate(8));
						visitante.setTipoCredito(resultSet.getString(9));
						visitante.setEstado(resultSet.getString(10));
						visitante.setMunicipio(resultSet.getString(11));
						visitante.setEmail(resultSet.getString(12));
					}//FIN WHILE
				}catch (SQLException e) {
					Notificacion.dialogoException(e);
				}//FIN TRY/CATCH
			}else {
				query="SELECT * FROM visitantes WHERE "+campoBusqueda+" = ? ORDER BY apellidoPaterno;";	
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, valorBusqueda);
					ResultSet resultSet=preparedStatement.executeQuery();
					while (resultSet.next()) {
						visitante = new Visitante();	
						visitante.setSysPk(resultSet.getInt(1));
						visitante.setNombre(resultSet.getString(2));
						visitante.setApellidoPaterno(resultSet.getString(3));
						visitante.setApellidoMaterno(resultSet.getString(4));
						visitante.setSexo(resultSet.getString(5));
						visitante.setNumeroSeguroSocial(resultSet.getString(6));
						visitante.setFechaNacimiento(resultSet.getDate(7));
						visitante.setFechaRegistro(resultSet.getDate(8));
						visitante.setTipoCredito(resultSet.getString(9));
						visitante.setEstado(resultSet.getString(10));
						visitante.setMunicipio(resultSet.getString(11));
						visitante.setEmail(resultSet.getString(12));
					}//FIN WHILE
				}catch (SQLException e) {
					Notificacion.dialogoException(e);
				}//FIN TRY-CATCH
			}//FIN IF-ELSE		
		}//FIN IF-ELSE
		return visitante;
	}//FIN METODO

	@Override
	public boolean modificar(Connection connection, String correo) {
		String query = "UPDATE visitantes SET fechaRegistro = curdate(), asistencia = 1 WHERE email = ?";
		try {		
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, correo);
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.dialogoException(e);
			return false;
		}//FIN TRY-CATCH
	}//FIN METODO

	@Override
	public boolean eliminar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}

}
