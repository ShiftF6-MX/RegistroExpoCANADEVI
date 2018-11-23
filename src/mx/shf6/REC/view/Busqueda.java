package mx.shf6.REC.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import mx.shf6.REC.MainApp;
import mx.shf6.REC.model.Visitante;
import mx.shf6.REC.model.dao.SepomexDAO;
import mx.shf6.REC.model.dao.VisitanteDAO;
import mx.shf6.REC.utilities.Notificacion;

public class Busqueda {
	
	//PROPIEDADES
	private Visitante visitante;
	private VisitanteDAO visitanteDAO;
	private SepomexDAO sepomexDAO;
	private MainApp mainApp;
	private ObservableList<String> listaEstados;
	private ObservableList<String> listaMunicipios;
	private ObservableList<String> listaSexo;
	private ObservableList<String> listaCredito;
	    
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
    	this.sepomexDAO = new SepomexDAO();
    	listaSexo = FXCollections.observableArrayList();
    	listaSexo.add("Hombre");
    	listaSexo.add("Mujer");
    	sexoCombo.setItems(listaSexo);
    	listaCredito = FXCollections.observableArrayList();
    	listaCredito.add("INFONAVIT");
    	listaCredito.add("FOVISSTE");
    	listaCredito.add("ISSFAM");
    	listaCredito.add("BANCARIO");
    	listaCredito.add("NINGUNO");
        creditoCombo.setItems(listaCredito);
    }//FIN METODO 
    
	//ACCESO AL CLASE PRINCIPAL QUE CONTROLA LAS VISTAS
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    	this.listaEstados = sepomexDAO.leerEstados(this.mainApp.getConnection()); 
		estadoCombo.setItems(listaEstados);
		estadoCombo.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
			municipioCombo.getSelectionModel().clearSelection();
			listaMunicipios = this.sepomexDAO.leerMunicipios(this.mainApp.getConnection(), estadoCombo.getValue()); 
			municipioCombo.setItems(listaMunicipios);
		  });//FIN SENTENCIA
    }//FIN METODO

    //BUSCA LOS USUARIOS Y/O CORREOS QUE SE ENCUENTREN EL EL TEXTFIELD DE BUSQUEDA
    @FXML private void buscarVisitante() {
    	this.visitante = this.visitanteDAO.leer(this.mainApp.getConnection(), "", this.campoBusqueda.getText());    	
    	this.campoNombres.setText(this.visitante.getNombre());
    	this.campoApellidoPaterno.setText(visitante.getApellidoPaterno());
    	this.campoApellidoMaterno.setText(visitante.getApellidoMaterno());
    	this.campoEmail.setText(visitante.getEmail());
    	this.campoNumeroSeguro.setText(visitante.getNumeroSeguroSocial());
    	this.sexoCombo.setValue(visitante.getSexo());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	System.out.println(visitante.getFechaNacimiento().toString());
    	LocalDate localDate = LocalDate.parse(visitante.getFechaNacimiento().toString(), formatter);
    	this.fechaNacimiento.setValue(localDate);
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
    	Date gettedDatePickerDate = Date.valueOf(this.fechaNacimiento.getValue());
    	this.visitante.setFechaNacimiento(gettedDatePickerDate);
    	this.visitante.setTipoCredito(this.creditoCombo.getValue());
    	this.visitante.setEstado(this.estadoCombo.getValue());
    	this.visitante.setMunicipio(this.municipioCombo.getValue());
    	this.visitanteDAO.crear(this.mainApp.getConnection(), this.visitante);
    	Notificacion.dialogoAlerta(AlertType.CONFIRMATION, "Mensaje de CANADEVI", "El registro se guardo correctamente");
    	limpiarCampos();
    }//FIN METODO
    
    @FXML private void asistenciaVisitante() {
    	this.visitanteDAO.modificar(this.mainApp.getConnection(), this.campoEmail.getText());
    	Notificacion.dialogoAlerta(AlertType.CONFIRMATION, "Mensaje de CANADEVI", "La asistencia se registro correctamente");
    	limpiarCampos();
    }//FIN METODO
    
    @FXML private void cerrarSistema() {
    	this.mainApp.stop();
    }//FIN METODO
    
    private void limpiarCampos() {
    	this.campoNombres.setText("");
    	this.campoApellidoPaterno.setText("");
    	this.campoApellidoMaterno.setText("");
    	this.campoEmail.setText("");
    	this.campoNumeroSeguro.setText("");
    	this.sexoCombo.setValue("");
    	this.fechaNacimiento.setValue(null);
    	this.creditoCombo.setValue("");
    	this.estadoCombo.setValue("");
    	this.municipioCombo.setValue("");
    }//FIN METODO
    
}//FIN CLASE