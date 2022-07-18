package proyecto;

import java.util.ArrayList;

import AgendaConsultorioFinal.Turno;

public class GestionClientes
{

	static ArrayList <Cliente> clientes = new ArrayList <Cliente>();
	static double descDis;

	public void menuCliente()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |             MENÚ DE CLIENTES                |");
			System.out.println("\t\t\t |            ...................              |");
			System.out.println("\t\t\t |    1- % de descuento clientes distribuidores|");
			System.out.println("\t\t\t |    2- Cargar                                |");
			System.out.println("\t\t\t |    3- Modificar                             |");
			System.out.println("\t\t\t |    4- Baja                                  |");
			System.out.println("\t\t\t |    5- Busqueda                              |");
			System.out.println("\t\t\t |    6- Listar                                |");
			System.out.println("\t\t\t |    7- Listar compras por cliente            |");
			System.out.println("\t\t\t |            ...................              |");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir de menú de vendedores           |");
			System.out.println("\t\t\t |---------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : ModDesDis ();
				break;

				case '2' : cargarCliente();
					break;

				case '3' : modificarCliente();
				break;

				case '4' :  bajaCliente();
				break;

				case '5' :  buscarCliente();
				break;

				case '6' : listarClientes();
				break;

				case '7' : listarCompras ();
				break;


			}
		}while (opcMod != '0');
	}

	public void ModDesDis ()
	{
		System.out.println("\n|Descuento distribuidores: " + descDis);
		char opc = IBIO.inputChar("\n| Ingrese M si desea modificar, de lo contrario de ENTER: ");
		if (opc == 'M')
		{
			descDis = IBIO.inputDouble("\n| Nuevo descuento distribuidores: ");
		}

	}

	public void cargarCliente ()
	{

		System.out.println("\n|ALTA CLIENTE|\n ");
		String cuilCuit = IBIO.input("\n| Cuil/Cuit: ");
		veriE(cuilCuit);
		if (veriE(cuilCuit))
		{
			System.out.println("\n|El cliente ya existe|\n");
		}
		else
		{
			String razonSoc = IBIO.input("\n| Razon Social: ");
			String dni = IBIO.input("\n| DNI: ");
			String tel = IBIO.input("\n| Telefono/Celular: ");
			String mail = IBIO.input("\n| Mail: ");
			String direccion = IBIO.input("\n| Direccion: ");
			char cat = IBIO.inputChar("\n| Categoria[P particular, D distribuidor]: ");

			Cliente nuevo = new Cliente (razonSoc, dni, cuilCuit, tel, mail, direccion,cat);
			ordenamientoIns (nuevo);

		}
	}

	public boolean veriE (String cuilCuit) //Verificacion de existencia de Proveedor
	{
		for (int i = 0; i < clientes.size(); i++)
		{
			if (clientes.get(i).getCuilCuit().equalsIgnoreCase(cuilCuit))
			{
				return true;
			}
		}
			return false;

	}

	static void ordenamientoIns (Cliente nuevo)
	{
		//Ciclo de control principal, asume el primer elemento ubicado, inicia en el segundo
		if(!clientes.isEmpty())
		{
			boolean inserto = false;
			for (int i = 0; i < clientes.size(); i++)
			{
				if (clientes.get(i).getRazonSoc().compareToIgnoreCase(nuevo.getRazonSoc())  > 0)
				{
					clientes.add(i,nuevo);
					inserto = true;
					break;
				}
			}
			if (!inserto)
			{
				clientes.add(nuevo);
			}
		}
		else
		{
			clientes.add(nuevo);
		}
	}

	static void ordenamientoburbujeo ()
	{
		Cliente aux;
		boolean desordenado;
		do {
			desordenado = false;

		for (int i = 0; i < clientes.size()- 1; i++)
		{
			if (clientes.get(i).getRazonSoc().compareTo(clientes.get(i + 1).getRazonSoc())> 0)
			{
				desordenado = true;
				aux = clientes.get(i);
				clientes.set(i,clientes.get(i + 1));
				clientes.set(i + 1, aux);
			}

		}
		} while (desordenado);
	}



	public void modificarCliente ()
	{

		String ing = IBIO.input("\n| Cuil/cuit del cliente a modificar: ");
		for (int i = 0; i < clientes.size(); i++)
		{
			if (clientes.get(i).cuilCuit.equalsIgnoreCase(ing))
			{
				clientes.get(i).modCli();
				ordenamientoburbujeo();
			}

		}
	}

	public void bajaCliente ()
	{
		char opc;
		String ing = IBIO.input("\n| Cuil/cuit del cliente a dar de baja: ");

		for (int i = 0; i < clientes.size(); i++)
		{
			if (clientes.get(i).cuilCuit.equalsIgnoreCase(ing))
			{
				clientes.get(i).verCli();
				opc = IBIO.inputChar("\n| Luego de ver el cliente seleccionado, Ingrese 'E' si desea eliminarlo, de lo contrario de ENTER y el mismo seguira en los registros.");
				if (opc == 'E')
				{
					clientes.remove(i);
				}
			}
		}

	}

	public void buscarCliente()
	{
		char opc;
		String buscar = IBIO.input("\n| Cuil/cuit de cliente a buscar: ");

		for (int i = 0; i < clientes.size(); i++)
		{
			if (clientes.get(i).getCuilCuit().equalsIgnoreCase(buscar))
			{
				do
				{
					System.out.println("0- Salir");
					System.out.println("1- Ver datos personalesr ");
					System.out.println("2- Ver compras del cliente");

					opc = IBIO.inputChar("\n| Ingrese la opcion: ");

					switch (opc)
					{
						case '1' : clientes.get(i).verCli();
							break;

						case '2' : clientes.get(i).verCli();
						           clientes.get(i).verVent();
							break;
					}

					}while (opc != '0');
			}
		}
	}

	public void listarClientes ()
	{
		char opc;
		for (int i = 0; i < clientes.size(); i++)
		{
			if (i == 15)
			{
				opc = IBIO.inputChar("\n|  Se han listado 16 clientes, ingrese 'N' si desea dejar de listar, de lo contrario de ENTER:  ");
				if (opc == 'N')
				{
					break;
				}
			}

			clientes.get(i).verCli();
		}
	}

	public void listarCompras () //listar compras por cliente
	{
			char opc;
			String ccBus = IBIO.input("\n| Ingrese el cuil/cuit del cliente: ");
			for (int i = 0; i < clientes.size(); i++)
			{
				if (clientes.get(i).cuilCuit.equalsIgnoreCase(ccBus))
				{
					clientes.get(i).verVent();
				}
			}

	}
}




