package mx.shf6.REC.model;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Visitante {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPk; 		//Sys_PK (INT) | PrimaryKey
	private StringProperty nombre; 				//Nombre (VARCHAR)
	private StringProperty apellidoPaterno;
	private StringProperty apellidoMaterno;
	private StringProperty sexo;
	private StringProperty numeroSeguroSocial;
	private ObjectProperty<Date> fechaNacimiento;
	private ObjectProperty<Date> fechaRegistro;
	private StringProperty tipoCredito;
	private StringProperty estado;
	private StringProperty municipio;
	private StringProperty email;
	private ObjectProperty<Integer> asistencia;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Visitante() {
		this(0,"", "", "", "", "", null, null, "", "", "", "", 0);
	}//FIN METODO
	
	//CONSTRUCTOR CON PARAMETROS
	public Visitante(Integer sysPk, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, String numeroSocial, 
			Date fechaNacimiento, Date fechaRegistro, String tipoCredito, String estado, String municipio, String email, int asistencia) {
		this.sysPk = new SimpleObjectProperty<Integer>(sysPk);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
		this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
		this.sexo = new SimpleStringProperty(sexo);
		this.numeroSeguroSocial = new SimpleStringProperty(numeroSocial);
		this.fechaNacimiento = new SimpleObjectProperty<Date>(fechaNacimiento);
		this.fechaRegistro = new SimpleObjectProperty<Date>(fechaRegistro);
		this.tipoCredito = new SimpleStringProperty(tipoCredito);
		this.estado = new SimpleStringProperty(estado);
		this.municipio = new SimpleStringProperty(municipio);
		this.email = new SimpleStringProperty(email);
		this.asistencia = new SimpleObjectProperty<Integer>(asistencia);
	}//FIN METODO
	
	//METODOS PARA ACCESO A "SYSPK"
	public void setSysPk(Integer sysPK) {
		this.sysPk.set(sysPK);
	}//FIN METODO
	
	public Integer getSysPk() {
		return this.sysPk.get();
	}//FIN METODO
	
	public ObjectProperty<Integer> sysPkProperty() {
		return this.sysPk;
	}//FIN METODO
	//FIN METODOS "SYSPK"
	
	//METODOS PARA ACCESO A "NOMBRE"
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}//FIN METODO
	
	public String getNombre() {
		return this.nombre.get();
	}//FIN METODO
	
	public StringProperty nombreProperty() {
		return this.nombre;
	}//FIN METODO
	//FIN METODOS "NOMBRE"
	
	//METODOS PARA ACCESO A "APELLIDO PATERNO"
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno.set(apellidoPaterno);
	}//FIN METODO
		
	public String getApellidoPaterno() {
		return this.apellidoPaterno.get();
	}//FIN METODO
		
	public StringProperty apellidoPaternoProperty() {
		return this.apellidoPaterno;
	}//FIN METODO
	//FIN METODOS "APELLIDO PATERNO "
	
	//METODOS PARA ACCESO A "APELLIDO MATERNO"
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno.set(apellidoMaterno);
	}//FIN METODO
			
	public String getApellidoMaterno() {
		return this.apellidoMaterno.get();
	}//FIN METODO
			
	public StringProperty apellidoMaternoProperty() {
		return this.apellidoMaterno;
	}//FIN METODO
	//FIN METODOS "APELLIDO MATERNO"
	
	//METODOS PARA ACCESO A "SEXO"
	public void setSexo(String sexo) {
		this.sexo.set(sexo);
	}//FIN METODO
				
	public String getSexo() {
		return this.sexo.get();
	}//FIN METODO
				
	public StringProperty sexoProperty() {
		return this.sexo;
	}//FIN METODO
	//FIN METODOS "SEXO"
	
	//METODOS PARA ACCESO A "NUMERO SEGURO SOCIAL"
	public void setNumeroSeguroSocial(String numeroSeguroSocial) {
		this.numeroSeguroSocial.set(numeroSeguroSocial);
	}//FIN METODO
					
	public String getNumeroSeguroSocial() {
		return this.numeroSeguroSocial.get();
	}//FIN METODO
					
	public StringProperty numeroSeguroSocialProperty() {
		return this.numeroSeguroSocial;
	}//FIN METODO
	//FIN METODOS "NUMERO SEGURO SOCIAL"
	
	//METODOS DE ACCESO A "FECHA NACIMIENTO"
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento.set(fechaNacimiento);
	}//FIN METODO
		
	public Date getFechaNacimiento() {
		if (this.fechaNacimiento.get() == null)
			return Date.valueOf("1999-01-01");
		else
			return this.fechaNacimiento.get();
	}//FIN METODO
		
	public ObjectProperty<Date> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}//FIN METODO
	//FIN METODOS "FECHA NACIMIENTO"
	
	//METODOS DE ACCESO A "FECHA REGISTRO"
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro.set(fechaRegistro);
	}//FIN METODO
			
	public Date getFechaRegistro() {
		if (this.fechaRegistro.get() == null)
			return Date.valueOf("1999-01-01");
		else
			return this.fechaRegistro.get();
	}//FIN METODO
			
	public ObjectProperty<Date> fechaRegistroProperty() {
		return this.fechaRegistro;
	}//FIN METODO
	//FIN METODOS "FECHA REGISTRO"
	
	//METODOS PARA ACCESO A "TIPO CREDITO"
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito.set(tipoCredito);
	}//FIN METODOS 
						
	public String getTipoCredito() {
		return this.tipoCredito.get();
	}//FIN METODO
						
	public StringProperty tipoCreditoProperty() {
		return this.tipoCredito;
	}//FIN METODO
	//FIN METODOS "TIPO CREDITO"
	
	//METODOS PARA ACCESO A "ESTADO"
	public void setEstado(String estado) {
		this.estado.set(estado);
	}//FIN METODO
							
	public String getEstado() {
		return this.estado.get();
	}//FIN METODO
							
	public StringProperty estadoProperty() {
		return this.estado;
	}//FIN METODO
	//FIN METODOS "ESTADO"
	
	//METODOS PARA ACCESO A "MUNICIPIO"
	public void setMunicipio(String municipio) {
		this.municipio.set(municipio);
	}//FIN METODO
								
	public String getMunicipio() {
		return this.municipio.get();
	}//FIN METODO
								
	public StringProperty municipioProperty() {
		return this.municipio;
	}//FIN METODO
	//FIN METODOS "MUNICIPIO"
	
	//METODOS PARA ACCESO A "EMAIL"
	public void setEmail(String email) {
		this.email.set(email);
	}//FIN METODO
									
	public String getEmail() {
		return this.email.get();
	}//FIN METODO
									
	public StringProperty emailProperty() {
		return this.email;
	}//FIN METODO
	//FIN METODOS "EMAIL"
	
	//METODOS PARA ACCESO A "ASISTENCIA"
	public void setAsistencia(Integer asistencia) {
		this.asistencia.set(asistencia);
	}//FIN METODO
		
	public Integer getAsistencia() {
		return this.asistencia.get();
	}//FIN METODO
		
	public ObjectProperty<Integer> asistenciaProperty() {
		return this.asistencia;
	}//FIN METODO
	//FIN METODOS "ASISTENCIA"
	
	public String showInformacion() {
		String informacionVenta = "DATOS DEL Visitante:\n Sys_PK: " + this.getSysPk() + "\n"
				+ "Nombre: " + this.getNombre();
		return informacionVenta;
	}//FIN METODO
	
}//FIN CLASE