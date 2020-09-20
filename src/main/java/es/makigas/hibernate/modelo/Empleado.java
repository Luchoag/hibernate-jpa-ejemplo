package es.makigas.hibernate.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	//Es necesario importar la anotación de persistence ya que es la estándar de JPA. NO usar la de Hibernate.
@Table(name = "Empleado") //Crea la tabla con nombre Empleado
public class Empleado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Cod_empleado")
	private Long codigo;
	
	@Column(name = "Apellido")
	private String apellido;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Fecha_Nacimiento")
	private Date fechaNacimiento;

	
	public Empleado() {
		
	}

	public Empleado(Long codigo, String apellido, String nombre, Date fechaNacimiento) {
		this.codigo = codigo;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}
	
	
	
	
	
	
}
