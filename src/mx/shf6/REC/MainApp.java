package mx.shf6.REC;
	
import java.io.IOException;
import java.sql.Connection;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mx.shf6.REC.utilities.ConnectionDB;
import mx.shf6.REC.utilities.Notificacion;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class MainApp extends Application {
	
	//PROPIEDADES
	private Connection connection;
	private ConnectionDB connectionDB;
	
	//PANTALLAS DEL SISTEMA
	private Stage principalStage;
	private BorderPane pantallaRaiz;
	private AnchorPane pantallaBusqueda;
	
	//VARIABLES
	private double xOffset = 0.0;
	private double yOffset = 0.0;

    //INICIACION DE ESCENARIO
    @Override
    public void start(Stage primaryStage) {
    	//INICIO DE LA CONEXION DE LA BASE DE DATOS
    	this.connectionDB = new ConnectionDB("canadevi","104.254.247.249","Canadevi","&0Ab@Mohjirp");
		this.connection = connectionDB.conectarMySQL();
		
		//INICIA EL ESENARIO PRINCIPAL
		this.principalStage = primaryStage;
		this.principalStage.setMaximized(false);
		this.principalStage.setResizable(false);
		this.principalStage.initStyle(StageStyle.TRANSPARENT);
		
		//INICIA LA INTERFAZ DE USUARIO
		iniciarPantallaRaiz();
		iniciarPantallaBusqueda();
    }//FIN METODO
    
	//INICIAR PANE RAIZ
	private void iniciarPantallaRaiz() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			pantallaRaiz = (BorderPane) fxmlLoader.load();
			Scene principalEscena = new Scene(pantallaRaiz);
			principalEscena.setFill(Color.TRANSPARENT);
			this.principalStage.setScene(principalEscena);
			this.principalStage.show();
			
			//CENTRAR VENTANA EN PANTALLA
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			this.principalStage.setX((primaryScreenBounds.getWidth() - this.principalStage.getWidth()) / 2);
			this.principalStage.setY((primaryScreenBounds.getHeight() - this.principalStage.getHeight()) / 2);
		} catch (IOException | IllegalStateException ex) {
			Notificacion.dialogoException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
    
	//INICIAR PANE PANTALLA BUSQUEDA
	public void iniciarPantallaBusqueda() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("view/Busqueda.fxml"));
			this.pantallaBusqueda = (AnchorPane) fxmlLoader.load();
			
			//SELECCIONAR VENTANA PARA MOVER
			this.pantallaBusqueda.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = principalStage.getX() - event.getScreenX();
	                yOffset = principalStage.getY() - event.getScreenY();
	            }//FIN METODO
	        }); //FIN MOUSE HANDLER
			
			//MOVER VENTANA ARRASTRANDO
			this.pantallaBusqueda.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	principalStage.setX(event.getScreenX() + xOffset);
	            	principalStage.setY(event.getScreenY() + yOffset);
	            }//FIN METODO
	        });//FIN MOUSE HANDLER
			
			this.pantallaRaiz.setCenter(this.pantallaBusqueda);
			this.pantallaRaiz.setTop(null);
			this.pantallaRaiz.setLeft(null);
			
			//Busqueda pantallaEspera = fxmlLoader.getController();
			//pantallaEspera.setMainApp(this);
		} catch (IOException | IllegalStateException ex) {
			Notificacion.dialogoException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
            
  //METODOS DE ACCESO A VARIABLE "CONNECTION"
  	public Connection getConnection() {
  		return this.connection;
  	}//FIN METODO
    
    public static void main(String[] args) {
        launch(args);
    }//FIN METODO	
}