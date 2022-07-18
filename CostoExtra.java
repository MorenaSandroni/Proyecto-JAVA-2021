package proyecto;

import java.io.Serializable;

public class CostoExtra implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String detalle;
	private double precio;
	private char estado;

	public CostoExtra (int id,String detalle, double importe, char estado)
	{
		this.id = id;
		this.detalle = detalle;
		this.precio = importe;
		this.estado = estado;
	}

	public void modCosto () //modfica mediante menu de DO WHILE
	{
		char opcMod;
		do
		{
			System.out.println("Modificacion del insumo: "+ this.detalle);
			System.out.println("\n0- Salir");
			System.out.println("\n1- Precio: " + this.precio);
			System.out.println("\n2- Estado: " + this.estado);


			opcMod = IBIO.inputChar("\n| Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' : this.setImporte(IBIO.inputDouble("\n| Ingrese el nuevo precio por kg: "));
					break;

				case '2' : this.setEstado(IBIO.inputChar("\n| Ingrese el estado, A por activo, D por desactivo: "));
				break;

			}
		}while (opcMod != '0');
	}


	public void verCost()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Detalle: " + this.detalle);
		System.out.println("\n|Precio: " + this.precio);
		System.out.println("\n|Estado: " + this.estado);
		System.out.println("-------------------------------------------------------------------------------------------");

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public double getImporte() {
		return precio;
	}

	public void setImporte(double precio) {
		this.precio = precio;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}


}
