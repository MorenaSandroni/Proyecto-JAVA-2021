package proyecto;

import java.io.Serializable;

public class Compras implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String fecha;
	private char estado;// N NO ENTREGADA E ENTREGADA
	private Proveedor proveedor;
	private Insumo insumo;
	private double kgU;
	private double precio;
	private double dolar;


	public Compras()
	{
		super();
	}

	public Compras (int id,String fecha, char estado, Proveedor proveedor, Insumo insumo, double kgU, double precio, double dolar)
	{
		this.id = id;
		this.fecha = fecha;
		this.estado = estado;
		this.proveedor = proveedor;
		this.insumo = insumo;
		this.kgU = kgU;
		this.precio = precio;
		this.dolar = dolar;
	}

	public void modComp(int nCompra)
	{
		char opcMod;
		do
		{
			System.out.println("Compra numero: " + nCompra + "a proveedor" + this.proveedor.getRazonSoc() + "," + this.proveedor.getCuilCuit() + " Por insumo: " + this.insumo.getId());
			System.out.println("\n0- Salir");
			System.out.println("\n1- Fecha: " + this.fecha);
			System.out.println("\n2- Estado: " + this.estado);
			System.out.println("\n3- Cantidad de KG pedidos: " + this.kgU);
			System.out.println("\n4- Precio final en pesos: " + this.precio);
			System.out.println("\n5- Precio dolar a fecha: " + this.dolar);

			opcMod = IBIO.inputChar("\n| Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' :  String dia = IBIO.input("\n| Dia modificado [formato dd] : ");// la fecha se carga desglobazada para evitar confusiones con el separador.
							String mes = IBIO.input("\n| Mes modificado [formato mm] : ");
							String ano = IBIO.input("\n| Año modificado [formato aaaa] : ");
							fecha = (dia + "/" + mes + "/" + ano);
							MetodosGenerales.validarFecha(fecha);
							this.setFecha(fecha);
				break;

				case '2' : this.setEstado(IBIO.inputChar("\n| Ingrese el estado E de entregado N de no entregado: "));
							if (estado == 'E')
							{
								this.insumo.actStockComp(kgU);// si una compra esta entregada se le suma la cantidad comprada de insumos a el stock actual.
							};
				break;

				case '3' : this.setKgU(IBIO.inputDouble("\n| Ingrese la nueva cantidad de KG pedidos: "));
				break;

				case '4' : this.setPrecio(IBIO.inputDouble("\n| Ingrese el nuevo Precio de la compra: "));
				break;

				case '5' : this.setDolar(IBIO.inputDouble("\n| Ingrese el nuevo precio del dolar asociado a la compra: "));
				break;

			}
		}while (opcMod != '0');
	}

	public void verComp()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Fecha: " + this.fecha);
		System.out.println("\n|Estado: "+ this.estado);
		System.out.println("\n|Proveedor: " + this.proveedor.getRazonSoc() + " - " + this.proveedor.getCuilCuit());
		System.out.println("\n|Id insumo: " + this.insumo.getId());
		System.out.println("\n|Kg pedidos: " + this.kgU);
		System.out.println("\n|Precio final: " + this.precio);
		System.out.println("\n|Precio del dolar asociado a la compra: " + this.dolar);
		System.out.println("-------------------------------------------------------------------------------------------");
	}

	public void verPH() //ver para historial
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Fecha: " + this.fecha);
		System.out.println("\n|Kg comorados: "+ this.kgU);
		System.out.println("\n|Precio final: " + this.precio);
		System.out.println("\n|Precio del dolar asociado a la compra: " + this.dolar);
		System.out.println("-------------------------------------------------------------------------------------------");

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public double getKgU() {
		return kgU;
	}

	public void setKgU(double kgU) {
		this.kgU = kgU;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getDolar() {
		return dolar;
	}

	public void setDolar(double dolar) {
		this.dolar = dolar;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


}
