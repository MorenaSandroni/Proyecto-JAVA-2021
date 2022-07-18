package proyecto;

import java.io.Serializable;
import java.util.ArrayList;

public class Insumo implements Serializable
{

	private int id;
	private String insumo; //Grafito 00001, talco 00002, envases 00003, etiquetas 00004.
	private double precio;
	private double stock;
	private double stockMin;
	private ArrayList <Compras> compras;

	//constructor con ID y Nombre
	public Insumo (int id, String insumo, double precio,double stock, double stockMin)
	{
		this.id = id;
		this.insumo = insumo;
		this.precio = precio;
		this.stock = stock;
		this.stockMin = stockMin;
		this.compras = new ArrayList ();
	}

	public void modIns()
	{
		char opcMod;
		do
		{
			System.out.println("\n0- Salir");
			System.out.println("\n1- Precio: " + this.precio);
			System.out.println("\n2- stock Minimo: " + this.stockMin);


			opcMod = IBIO.inputChar("\n| Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' : this.setPrecio(IBIO.inputDouble("\n| Ingrese el nuevo precio del insumo: "));
					break;

				case '2' : this.setStockMin(IBIO.inputDouble("\n| Ingrese la nueva cantidad de stock minimo del insumo: "));
				break;

			}
		}while (opcMod != '0');
	}


	public void verIns ()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Insumo: " + this.insumo);
		System.out.println("\n|Precio: " + this.precio);
		System.out.println("\n|Stock: " + this.stock);
		System.out.println("-------------------------------------------------------------------------------------------");

	}

	public void verStock()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Insumo: " + this.insumo);
		System.out.println("\n|Stock: " + this.stock);
		System.out.println("-------------------------------------------------------------------------------------------");

	}

	public void addCom(Compras nueva)
	{
		this.compras.add(nueva);
	}

	public void verHis ()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Insumo: " + this.insumo);
		System.out.println("\n|Precio: " + this.precio);
		for (int i = 0; i < compras.size(); i++)
		{
			compras.get(i).verComp();
		}

	}

	public void actStockComp(double cant) //actualiza el stock cuando se compra
	{
		this.setStock(this.getStock() + cant);
		System.out.println(this.id);
	}

	public void actStockVent(double cant) //actualiza el stock cuando se compra
	{
		this.setStock(this.getStock() - cant);
		System.out.println(this.id);
	}

	public void actPrecio(double precioFi) //actualiza el precio del insumo mediante la compra si es necesario
	{
		this.setPrecio(precioFi);
		System.out.println(this.precio);
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getStockMin() {
		return stockMin;
	}

	public void setStockMin(double stockMin) {
		this.stockMin = stockMin;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}



}
