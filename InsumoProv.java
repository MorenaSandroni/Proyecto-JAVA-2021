package proyecto;

import java.io.Serializable;

public class InsumoProv implements Serializable
{
	private int id;
	private String insumo;
	private double present;
	private String unidadpre;
	private double preciopre;

	public InsumoProv ()
	{

	}
	public InsumoProv (int id, String insumo,double present, String unidadpre, double preciopre)
	{
		this.id = id;
		this.insumo = insumo;
		this.present = present;
		this.unidadpre = unidadpre;
		this.preciopre = preciopre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}

	public double getPresent() {
		return present;
	}

	public void setPresent(double present) {
		this.present = present;
	}

	public String getUnidadpre() {
		return unidadpre;
	}

	public double getPreciopre() {
		return preciopre;
	}

	public void setPreciopre(double preciopre) {
		this.preciopre = preciopre;
	}

	public void setUnidadpre(String unidadpre) {
		this.unidadpre = unidadpre;
	}



}
