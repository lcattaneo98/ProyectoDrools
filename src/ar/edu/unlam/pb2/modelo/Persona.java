package ar.edu.unlam.pb2.modelo;

public class Persona {

	private String ciudad;
	private Integer edad;
	private Double descuento;

	public Persona() {
		this.descuento = 0.0;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

}
