package mx.shf6.REC.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import mx.shf6.REC.MainApp;
import mx.shf6.REC.model.Visitante;
import mx.shf6.REC.model.dao.VisitanteDAO;

public class Busqueda {
	
	//PROPIEDADES
	private Visitante visitante;
	private VisitanteDAO visitanteDAO;
	private MainApp mainApp;
	    
    //COMPONENTES INTERFAZ DE USUARIO
	@FXML public TextField campoBusqueda;
	@FXML public Button botonBusqueda;
	@FXML public TextField campoNombres;
	@FXML public TextField campoApellidoPaterno;
	@FXML public TextField campoApellidoMaterno;
	@FXML public TextField campoEmail;
	@FXML public TextField campoNumeroSeguro;
	@FXML public ComboBox<String> sexoCombo;
	@FXML public DatePicker fechaNacimiento;
	@FXML public ComboBox<String> creditoCombo;
	@FXML public ComboBox<String> estadoCombo;
	@FXML public ComboBox<String> municipioCombo;
	@FXML public Button botonAsistencia;
	@FXML public Button botonRegistrar;
		
	//INICIALIZA LOS COMPOMENTES QUE SE CONTROLAN EN LA INTERFAZ DE USUARIO
    @FXML private void initialize() {
    	this.visitante = new Visitante();
    	this.visitanteDAO = new VisitanteDAO();  		
    }//FIN METODO 
    
	//ACCESO AL CLASE PRINCIPAL QUE CONTROLA LAS VISTAS
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;     
    }//FIN METODO

    //BUSCA LOS USUARIOS Y/O CORREOS QUE SE ENCUENTREN EL EL TEXTFIELD DE BUSQUEDA
    @FXML private void buscarVisitante() {
    	this.visitante = this.visitanteDAO.buscarVisitante(this.campoBusqueda.getText());    	
    	this.campoNombres.setText(this.visitante.getNombre());
    	this.campoApellidoPaterno.setText(visitante.getApellidoPaterno());
    	this.campoApellidoMaterno.setText(visitante.getApellidoMaterno());
    	this.campoEmail.setText(visitante.getEmail());
    	this.campoNumeroSeguro.setText(visitante.getNumeroSeguroSocial());
    	this.sexoCombo.setValue(visitante.getSexo());
    	LocalDate date = visitante.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	this.fechaNacimiento.setValue(date);
    	this.creditoCombo.setValue(visitante.getTipoCredito());
    	this.estadoCombo.setValue(visitante.getEstado());
    	this.municipioCombo.setValue(visitante.getMunicipio());
    }//FIN METODO    
   
    @FXML private void registrarVisitante() {
		this.visitante.setNombre(this.campoNombres.getText());
    	this.visitante.setApellidoPaterno(this.campoApellidoPaterno.getText());
    	this.visitante.setApellidoMaterno(this.campoApellidoMaterno.getText());
    	this.visitante.setEmail(this.campoEmail.getText());
    	this.visitante.setNumeroSeguroSocial(this.campoNumeroSeguro.getText());
    	this.visitante.setSexo(this.sexoCombo.getValue());
    	Date date = (Date) Date.from(this.fechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	this.visitante.setFechaNacimiento(date);
    	this.visitante.setTipoCredito(this.creditoCombo.getValue());
    	this.visitante.setEstado(this.estadoCombo.getValue());
    	this.visitante.setMunicipio(this.municipioCombo.getValue());
    	this.visitanteDAO.registrarVisitante(this.visitante);
    }//FIN METODO
    
    @FXML private void asistenciaVisitante() {
    	this.visitanteDAO.asistenciaVisitante(this.campoEmail.getText());		
    }//FIN METODO
    
    @FXML private void cerrarSistema() {
    	this.mainApp.stop();
    }//FIN METODO 
    
}//FIN CLASE