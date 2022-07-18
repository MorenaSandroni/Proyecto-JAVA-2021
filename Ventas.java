package proyecto;

import java.io.Serializable;

public class Ventas implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Vendedor vendedor;
	private double dolar;
	private Cliente cliente;
	private int id;
	private String fecha;
	private Producto producto;
	private double kg;
	private double desc;
	private double vprecFinal;
	private double vprecFinalDl;
	private double ganancia;
	private char estadoEnv;
	private char estadoPag;
	private double kgGraf;
	private double kgTalc;
	private double Uenv;
	private double Ueti;


	public Ventas()
	{
		super();
	}

	public Ventas (int id, Vendedor vendedor, double dolar, Cliente cliente, String fecha, Producto producto, double kg, double desc, char estadoEnv, char estadoPag, double vprecFinal, double vprecFinalDl, double kgGraf,double kgTalc, double Uenv, double Ueti, double ganancia)
	{
		this.id = id;
		this.vendedor = vendedor;
		this.dolar = dolar;
		this.cliente = cliente;
		//this.id = id++;
		this.fecha = fecha;
		this.producto = producto;
		this.kg = kg;
		this.desc = desc;
		this.estadoEnv = estadoEnv;
		this.estadoPag = estadoPag;
		this.vprecFinal = vprecFinal;
		this.vprecFinalDl = vprecFinalDl;
		this.kgGraf = kgGraf;
		this.kgTalc = kgTalc;
		this.Uenv = Uenv;
		this.Ueti = Ueti;
		this.ganancia = ganancia;
	}

	public void modVen (int nVenta)
	{
		char opcMod;
		char estado;
		do
		{
			System.out.println("Venta numero: " + nVenta + "de Cliente" + this.cliente.getRazonSoc() + "," + this.cliente.getDni());
			System.out.println("0- Salir");
			System.out.println("1- Fecha: " + this.fecha);
			System.out.println("2- Vendedor " + this.vendedor.razonSoc + this.vendedor.dni);
			System.out.println("3- Estado del envio: " + this.estadoEnv);
			System.out.println("4- Estado de pago " + this.estadoPag);

			opcMod = IBIO.inputChar("Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' :  String dia = IBIO.input("|Dia modificado [formato dd] : ");
							String mes = IBIO.input("|Mes modificado [formato mm] : ");
							String ano = IBIO.input("|Año modificado [formato aa] : ");
							fecha = (dia + "/" + mes + "/" + ano);
							MetodosGenerales.validarFecha(fecha);
							this.setFecha(fecha);
					break;

				case '2' : this.vendedor.modVen();
				break;

				case '3' : this.setEstadoEnv(IBIO.inputChar("Ingrese el estado P de PENDIENTE C de COMPLETADO: "));

				break;

				case '4' : this.setEstadoPag(IBIO.inputChar("Ingrese el estado P de PENDIENTE C de COMPLETADO:"));
				break;

			}
		}while (opcMod != '0');

		if ((this.estadoEnv == 'C') & (this.estadoPag == 'C'))
		{
				MetodosGenerales.insumos.get(0).setStock((MetodosGenerales.insumos.get(0).getStock() - kgGraf));
				MetodosGenerales.insumos.get(1).setStock((MetodosGenerales.insumos.get(1).getStock() - kgTalc));
				MetodosGenerales.insumos.get(2).setStock((MetodosGenerales.insumos.get(2).getStock() - Uenv));
				MetodosGenerales.insumos.get(3).setStock((MetodosGenerales.insumos.get(3).getStock() - Ueti));
		}
	}

	public void verVen()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Fecha: " + this.fecha);
		System.out.println("\n|Cliente: " + this.cliente.getRazonSoc() + " - " + this.cliente.getCuilCuit());
		System.out.println("\n|Vendedor: " + this.vendedor.getRazonSoc() + " - " + this.vendedor.getCuilCuit());
		System.out.println("\n|Kg vendidos: " + this.kg);
		System.out.println("\n|Descuentos: " + this.desc);
		System.out.println("\n|Precio final pesos : " + this.vprecFinal);
		System.out.println("\n|Precio final dolares : " + this.vprecFinalDl);
		System.out.println("\n|Estado del envio: " + this.estadoEnv);
		System.out.println("\n|Estado del pago: " + this.estadoPag);
		System.out.println("-------------------------------------------------------------------------------------------");

	}

	public void verH ()
	{
		System.out.println("\n|Id: " + this.id);
		System.out.println("\n|Fecha: " + this.fecha);
		System.out.println("\n|Cliente: " + this.cliente.getRazonSoc() + " - " + this.cliente.getCuilCuit());
		System.out.println("\n|Vendedor: " + this.vendedor.getRazonSoc() + " - " + this.vendedor.getCuilCuit());
		System.out.println("\n|Kg vendidos: " + this.kg);
		System.out.println("\n|Descuentos: " + this.desc);
		System.out.println("\n|Precio final pesos : " + this.vprecFinal);
		System.out.println("\n|Precio final dolares : " + this.vprecFinalDl);
		System.out.println("\n|Ganancia: " + this.ganancia);
		System.out.println("-------------------------------------------------------------------------------------------");

	}

	public double [] stocks (double kg)
	{
		double [] kgIV = new double [4];
		kgIV [0] = 30*kg/100;
		kgIV [1] = 70*kg/100;
		kgIV [2] = kg/5;
		kgIV [3] = kg/5;
		return kgIV;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public double getKg() {
		return kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}

	public double getDesc() {
		return desc;
	}

	public void setDesc(double desc) {
		this.desc = desc;
	}


	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public char getEstadoEnv() {
		return estadoEnv;
	}

	public void setEstadoEnv(char estadoEnv) {
		this.estadoEnv = estadoEnv;
	}

	public char getEstadoPag() {
		return estadoPag;
	}

	public void setEstadoPag(char estadoPag) {
		this.estadoPag = estadoPag;
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

	public double getVprecFinal() {
		return vprecFinal;
	}

	public void setVprecFinal(double vprecFinal) {
		this.vprecFinal = vprecFinal;
	}

	public double getVprecFinalDl() {
		return vprecFinalDl;
	}

	public void setVprecFinalDl(double vprecFinalDl) {
		this.vprecFinalDl = vprecFinalDl;
	}

	public double getKgGraf() {
		return kgGraf;
	}

	public void setKgGraf(double kgGraf) {
		this.kgGraf = kgGraf;
	}

	public double getKgTalc() {
		return kgTalc;
	}

	public void setKgTalc(double kgTalc) {
		this.kgTalc = kgTalc;
	}

	public double getUenv() {
		return Uenv;
	}

	public void setUenv(double uenv) {
		Uenv = uenv;
	}

	public double getUeti() {
		return Ueti;
	}

	public void setUeti(double ueti) {
		Ueti = ueti;
	}




}


