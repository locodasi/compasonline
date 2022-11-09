package com.example.ComprasOnline.helpers;


import javax.validation.constraints.NotNull;



public class BusquedaHelper {

	private int idRubro;
	
	@NotNull
	private float precioMin;
	
	@NotNull
	private float precioMax;
	
	public BusquedaHelper() {
		this.idRubro=0;
		this.precioMax = 0;
		this.precioMin = 0;
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public float getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(float precioMin) {
		this.precioMin = precioMin;
	}

	public float getPrecioMax() {
		return precioMax;
	}

	public void setPrecioMax(float precioMax) {
		this.precioMax = precioMax;
	}

	@Override
	public String toString() {
		return "BusquedaHelper [idRubro=" + idRubro + ", precioMin=" + precioMin + ", precioMax=" + precioMax + "]";
	}
	
	
	
	
}
