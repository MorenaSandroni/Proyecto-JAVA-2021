package proyecto;

import java.io.Serializable;
import java.util.ArrayList;

public class Proveedor extends Persona implements Serializable
{
	private static final long serialVersionUID = 1L;
	private InsumoProv insumo;
	private char estado;
	private ArrayList <Compras> compras;




	public Proveedor()
	{
		super();
	}

	public Proveedor (String razonSoc, String dni, String cuilCuit, String tel, String mail, String direccion, InsumoProv insumo, char estado)
	{
		super (razonSoc,dni, cuilCuit, tel, mail, direccion);
		this.insumo = insumo;
		this.estado = estado;
		this.compras = new ArrayList ();
	}

	public void addCom(Compras nueva)
	{
		this.compras.add(nueva);
	}

	public void vercom()// en metodos va a estar una booleana con un estado en TRUE que luego, cuando se pregunta si se quiere seguir mostrando puede cambiar a FALSE, todo esto dentro de un while.
	{
			for (int i = 0; i < this.compras.size(); i++)
			{
				this.compras.get(i).verComp();
			}
	}

	public void verProv()
	{
		char opcC;

		System.out.println("\n| PROVEEDOR: " + this.razonSoc + " |");
		System.out.println("\n| Cuil/Cuit: " + this.cuilCuit);
		System.out.println("\n| Telefono: " + this.tel);
		System.out.println("\n| Mail: " + this.mail);
		System.out.println("\n| Direccion: " + this.direccion);
		System.out.println("\n| Estado: " + this.estado);
		System.out.println("\n| Insumo: ");
		this.verInsumo();//datos de INSUMOPROV

		opcC= IBIO.inputChar("\n| Ingrese S si desea ver la lista de compras al proveedor, de lo contrario de ENTER");

		if (opcC == 'S')
		{
			if (!this.compras.isEmpty())
			{
				for (int i = 0; i < this.compras.size(); i++)
				{
					this.compras.get(i).verComp();
				}
			}
			else
			{
				System.out.println("\n| No hay registro de compras.");
			}

		}


	}
	public void verProvPr()
	{


		System.out.println("\n| PROVEEDOR: " + this.razonSoc + " |");
		System.out.println("\n| Cuil/Cuit: " + this.cuilCuit);
		System.out.println("\n| Telefono: " + this.tel);
		System.out.println("\n| Mail: " + this.mail);
		System.out.println("\n| Direccion: " + this.direccion);
		System.out.println("-------------------------------------------------------------------------------------------");

	}

	public void verInsumo()
	{
		System.out.println( "\n");
		System.out.println("|Id: " + this.insumo.getId() + "\n");
		System.out.println("|Descripcion: " + this.insumo.getInsumo() + "\n");
		System.out.println("|Presentacion: " + this.insumo.getPresent() + this.insumo.getUnidadpre() + "\n");
		System.out.println("|Precio por presentacion: " + this.insumo.getPreciopre() + "\n");
		System.out.println("-------------------------------------------------------------------------------------------");


	}

	public void modProv()
	{
		char opcMod;
		do
		{
			System.out.println("\n0- Salir");
			System.out.println("\n1- Razon social: " + this.razonSoc);
			System.out.println("\n2- Dni: " + this.dni);
			System.out.println("\n3- Cuil/cuit: " + this.cuilCuit);
			System.out.println("\n4- Telefono: " + this.tel);
			System.out.println("\n5- Mail: " + this.mail);
			System.out.println("\n6- Direccion: " + this.direccion);
			System.out.println("\n7- Estado: " + this.estado);

			opcMod = IBIO.inputChar("\n| Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' : this.setRazonSoc(IBIO.input("\n| Nueva Razon Social: "));
					break;

				case '2' : this.setDni(IBIO.input("\n| Nuevo DNI: "));
				break;

				case '3' : this.setCuilCuit(IBIO.input("\n| Nuevo Cuil/Cuit: "));
				break;

				case '4' : this.setTel(IBIO.input("\n| Nuevo Telefono: "));
				break;

				case '5' : this.setMail(IBIO.input("\n| Nuevo Mail: "));
				break;

				case '6' : this.setDireccion(IBIO.input("\n| Nueva Direccion: "));
				break;

				case '7' : this.setEstado(IBIO.inputChar("\n| Nuevo Estado (A en activo, D en desactivo): "));
				break;

			}
		}while (opcMod != '0');
	}

	public void verHis()
	{
		System.out.println("\n| Razon Social: " + this.razonSoc);
		System.out.println("\n| Cuil/cuit" + this.cuilCuit);
		System.out.println("\n| Telefono: " + this.tel);
		System.out.println("\n| Insumo| \n" + "\n" + " id: " + this.insumo.getId() + "\n" + " Descricion: " + this.insumo.getInsumo());// Muestra datos particulares insumo cuando este creada la clase INSUMOPROV
		if (!this.compras.isEmpty())
		{
			for (int i = 0; i < this.compras.size(); i++)
			{
				this.compras.get(i).verPH();
			}
		}
		else
		{
			System.out.println("\n| No hay registro de compras.");
		}
	}

	public void verCompPE()
	{
		boolean hay = false;
		for (int i = 0; i < this.compras.size(); i++)
		{
			if (this.compras.get(i).getEstado() == 'N')
			{
				System.out.println("\n|Proveedor: " + this.cuilCuit + "," + this.razonSoc + "|");
				this.compras.get(i).verComp();
				hay = true;
			}

		}
		if (hay = false)
		{
			System.out.println("\n|No hay compras pendientes de entrega|\n");
		}
	}



	public void modInsumo()
	{
		char opcMod;
		do
		{
			System.out.println("\n0- Salir");
			System.out.println("\n1- Descripcion Insumo: " + this.insumo.getInsumo());
			System.out.println("\n2- Presentacion del Insumo: " + this.insumo.getPresent());
			System.out.println("\n3- Unidad de la presentacion del Insumo: " + this.insumo.getUnidadpre());
			System.out.println("\n4- Precio de la presentacion del Insumo: " + this.insumo.getPreciopre());

			opcMod = IBIO.inputChar("Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' : this.insumo.setInsumo(IBIO.input("\n| Nueva descripcion del producto: "));
					break;

				case '2' : this.insumo.setPresent(IBIO.inputDouble("\n| Nuevos Kg o unidades de presentacion: "));
				break;

				case '3' : this.insumo.setUnidadpre(IBIO.input("\n| Nueva unidad de presentacion [KG si es en esa unidad o U si es en enteros]"));
				break;

				case '4' : this.insumo.setPreciopre(IBIO.inputDouble("\n| Nuevo precio de la presentacion: "));
				break;

			}
		}while (opcMod != '0');
	}

	public void actPrecio(double precioFi)
	{
		this.insumo.setPreciopre(precioFi);
	}

	public InsumoProv getInsumo() {
		return insumo;
	}

	public void setInsumo(InsumoProv insumo) {
		this.insumo = insumo;
	}


	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}


}
