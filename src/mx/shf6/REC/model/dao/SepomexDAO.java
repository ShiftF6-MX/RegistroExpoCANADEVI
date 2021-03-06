package mx.shf6.REC.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SepomexDAO {

	public ObservableList<String> leerEstados(Connection connection) {
		String query ="";
		ObservableList<String> listaEstados = FXCollections.observableArrayList();
		query="SELECT estado FROM sepomex group by estado order by estado asc";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				listaEstados.add(resultSet.getString(1));
				
			}
		}catch (SQLException e) {
			System.out.println("Error: En m�todo leer");
			e.printStackTrace();
		}
		return listaEstados;
	}
	
	public ObservableList<String> leerMunicipios(Connection connection, String estado) {
		String query ="";
		ObservableList<String> listaEstados = FXCollections.observableArrayList();
		query="SELECT municipio "
				+ "FROM sepomex "
				+ "WHERE Estado='"+estado+"' "
				+ "GROUP BY municipio "
				+ "ORDER BY municipio ASC;";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				listaEstados.add(resultSet.getString(1));
				
			}
		}catch (SQLException e) {
			System.out.println("Error: En m�todo leer");
			e.printStackTrace();
		}
		return listaEstados;
	}

}
