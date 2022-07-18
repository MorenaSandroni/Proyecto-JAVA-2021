package proyecto;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor extends Persona implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList <Ventas> ventas;
	private double kgVend;


	public Vendedor()
	{
		super();
	}

	public Vendedor (String razonSoc, String dni, String cuilCuit, String tel, String mail, String direccion)
	{
		super (razonSoc, dni, cuilCuit, tel, mail, direccion);
		this.ventas = new ArrayList ();
	}

	public void addVen(Ventas Nueva)
	{
		this.ventas.add(Nueva);
	}

	public void verVend()
	{
		char opcV;
		System.out.println("\n|Razon social: " + this.razonSoc);
		System.out.println("\n|Cuil/Cuit: " + this.cuilCuit);
		System.out.println("\n|Telefono: " + this.tel);
		System.out.println("\n|Mail: " + this.mail);
		System.out.println("\n|Direccion: " + this.direccion);
		System.out.println("\n|Kg vendidos: " + this.kgVend);
		opcV = IBIO.inputChar("\n| Ingrese S si desea ver la lista de ventas a el cliente, de lo contrario de ENTER");

		if (opcV == 'S')
		{
			if (!this.ventas.isEmpty())
			{
				for (int i = 0; i < this.ventas.size(); i++)
				{
					this.ventas.get(i).verVen();
				}
			}
			else
			{
				System.out.println("\n| No hay registro de ventas.");
			}
		}
	}

	public void verVentas ()
	{
		if (!this.ventas.isEmpty())
		{
			for (int i = 0; i < this.ventas.size(); i++)
			{
				this.ventas.get(i).verVen();
			}
		}
		else
		{
			System.out.println("\n| No hay registro de ventas.");
		}
	}

	public void modVen()
	{
		char opcMod;
		do
		{
			System.out.println("0- Salir");
			System.out.println("1- Razon social: " + this.razonSoc);
			System.out.println("2- Dni: " + this.dni);
			System.out.println("3- Cuil/cuit: " + this.cuilCuit);
			System.out.println("4- Telefono: " + this.tel);
			System.out.println("5- Mail: " + this.mail);
			System.out.println("6- Direccion: " + this.direccion);

			opcMod = IBIO.inputChar("\n| Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
				case '1' : this.setRazonSoc(IBIO.input("\n| Ingrese la nueva Razon Social: "));
					break;

				case '2' : this.setDni(IBIO.input("\n| Ingrese el nuevo DNI: "));
				break;

				case '3' : this.setCuilCuit(IBIO.input("\n| Ingrese el nuevo Cuil/Cuit: "));
				break;

				case '4' : this.setTel(IBIO.input("\n| Ingrese el nuevo Telefono: "));
				break;

				case '5' : this.setMail(IBIO.input("\n| Ingrese el nuevo Mail: "));
				break;

				case '6' : this.setDireccion(IBIO.input("\n| Ingrese la nueva Direccion: "));
				break;

			}
		}while (opcMod != '0');
	}


}
