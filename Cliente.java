package proyecto;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable
{
	private static final long serialVersionUID = 1L;
	private char cat;
	private ArrayList <Ventas> ventas;


	public Cliente()
	{
		super();
	}

	public Cliente (String razonSoc, String dni, String cuilCuit, String tel, String mail, String direccion, char cat)
	{
		super (razonSoc, dni, cuilCuit, tel, mail, direccion);
		this.cat = cat;
		this.ventas = new ArrayList ();
	}

	public void addVen(Ventas Nueva)
	{
		this.ventas.add(Nueva);
	}

	public void modCli()
	{
		//Menu DO WHILE para modificar el cliente.
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
			System.out.println("\n7- Categoria: " + this.cat);

			opcMod = IBIO.inputChar("\n| Ingrese la opcion: ");
			System.out.println("-------------------------------------------------------------------------------------------");


			switch (opcMod)
			{
			// se hace uso de los sets para modificar la opcion seleccionada.

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

				case '7' : this.setCat(IBIO.inputChar("\n| Ingrese la nueva categoria(D en distribuidor, P en particular): "));
				break;

			}
		}while (opcMod != '0');
	}


	public void verCli()
	{
		char opcV;
		// a traves de this se refiere a ESTE objeto, con ello lo selecciona para mostrarlo

		System.out.println("\n|Razon social: " + this.razonSoc);
		System.out.println("\n|Cuil/Cuit: " + this.cuilCuit);
		System.out.println("\n|Telefono: " + this.tel);
		System.out.println("\n|Mail: " + this.mail);
		System.out.println("\n|Direccion: " + this.direccion);
		System.out.println("\n|Categoria: " + this.cat);
		opcV = IBIO.inputChar("\n| Ingrese S si desea ver la lista de ventas a el cliente, de lo contrario de ENTER");// La lista de ventas puede ser extensa por lo que puede elegir no mostrarla.
		if (opcV == 'S')
			if (!this.ventas.isEmpty())
			{
				for (int i = 0; i < this.ventas.size(); i++)
				{
					this.ventas.get(i).verVen();// metodo dentro de la clase venta que se ejecuta luego de identificar cada venta mediante el recorrido del for.
				}
			}
			else
			{
				System.out.println("\n| No hay registro de ventas.");
			}
		{
			}
	}

	public void verVent()
	{
		System.out.println("\n|Razon social: " + this.razonSoc);
		System.out.println("\n|Cuil/Cuit: " + this.cuilCuit);

		if (!this.ventas.isEmpty())
		{
			for (int i = 0; i < this.ventas.size(); i++)
			{
				//if ((this.ventas.get(i).getEstadoEnv() == 'C')&&(this.ventas.get(i).getEstadoPag()=='C'))// se corrobora que el pedido haya sido enviada y abonada para que cuente como venta. Si asi lo es se muestran datos parciales de la misma.
				//{
					this.ventas.get(i).verH();
			//	}

			}
		}
		else
		{
			System.out.println("\n| No hay registro de ventas.");
		}
	}

	public char getCat() {
		return cat;
	}

	public void setCat(char cat) {
		this.cat = cat;
	}




}
