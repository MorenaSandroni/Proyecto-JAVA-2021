package proyecto;

import java.util.ArrayList;

public class GestionVendedores
{
	static ArrayList <Vendedor> vendedores = new ArrayList <Vendedor>();


	public void menuVendedor()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |             MENÚ DE VENDEDORES             |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |    1- Alta                                 |");
			System.out.println("\t\t\t |    2- Modificar                            |");
			System.out.println("\t\t\t |    3- Baja                                 |");
			System.out.println("\t\t\t |    4- Busqueda                             |");
			System.out.println("\t\t\t |    5- Listar                               |");
			System.out.println("\t\t\t |    6- Listar ventas por vendedor           |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir de menú de vendedores          |");
			System.out.println("\t\t\t |--------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : cargarVendedor();
					break;

				case '2' : modificarVendedor();
				break;

				case '3' :  bajaVendedor();
				break;

				case '4' :  buscarVendedor();
				break;

				case '5' : listar();
				break;

				case '6' : listarVentas ();
				break;


			}
		}while (opcMod != '0');
	}

	public void cargarVendedor ()
	{
		System.out.println("\n|ALTA VENDEDOR|\n ");
		String cuilCuit = IBIO.input("\n| Cuil/Cuit: ");
		veriE(cuilCuit);
		if (veriE(cuilCuit))
		{
			System.out.println("\n|El vendedor ya existe\n");
		}
		else
		{
			String razonSoc = IBIO.input("\n| Razon Social: ");
			String dni = IBIO.input("\n| DNI: ");
			String tel = IBIO.input("\n| Telefono/Celular: ");
			String mail = IBIO.input("\n| Mail: ");
			String direccion = IBIO.input("\n| Direccion: ");

			Vendedor nuevo = new Vendedor (razonSoc, dni, cuilCuit, tel, mail, direccion);
			ordenamientoIns (nuevo);

		}



	}

	public boolean veriE (String cuilCuit) //Verificacion de existencia de Proveedor
	{
		for (int i = 0; i < vendedores.size(); i++)
		{
			if (vendedores.get(i).getCuilCuit().equalsIgnoreCase(cuilCuit))
			{
				return true;
			}
		}
			return false;

	}

	static void ordenamientoIns (Vendedor nuevo)
	{
		//Ciclo de control principal, asume el primer elemento ubicado, inicia en el segundo
		if(!vendedores.isEmpty())
		{
			boolean inserto = false;
			for (int i = 0; i < vendedores.size(); i++)
			{
				if (vendedores.get(i).getRazonSoc().compareToIgnoreCase(nuevo.getRazonSoc())  > 0)
				{
					vendedores.add(i,nuevo);
					inserto = true;
					break;
				}
			}
			if (!inserto)
			{
				vendedores.add(nuevo);
			}
		}
		else
		{
			vendedores.add(nuevo);
		}
	}
	public void modificarVendedor ()
	{
		String ing = IBIO.input("\n| Cuil/cuit del vendedor  a modificar: ");
		for (int i = 0; i < vendedores.size(); i++)
		{
			if (vendedores.get(i).cuilCuit.equalsIgnoreCase(ing))
			{
				System.out.println("\n");
				vendedores.get(i).modVen();
				ordenamientoburbujeo ();
			}

		}
	}

	static void ordenamientoburbujeo ()
	{
		Vendedor aux;
		boolean desordenado;
		do {
			desordenado = false;

		for (int i = 0; i < vendedores.size()- 1; i++)
		{
			if (vendedores.get(i).getRazonSoc().compareTo(vendedores.get(i + 1).getRazonSoc())> 0)
			{
				desordenado = true;
				aux = vendedores.get(i);
				vendedores.set(i,vendedores.get(i + 1));
				vendedores.set(i + 1, aux);
			}

		}
		} while (desordenado);
	}

	public void bajaVendedor ()
	{
		char opc;
		String ing = IBIO.input("\n| Cuil/cuit del vendedor a modificar: ");

		for (int i = 0; i < vendedores.size(); i++)
		{
			if (vendedores.get(i).cuilCuit.equalsIgnoreCase(ing))
			{
				System.out.println("\n");
				vendedores.get(i).verVend();
				opc = IBIO.inputChar("\n| Luego de ver el proveedor seleccionado, Ingrese 'E' si desea eliminarlo, de lo contrario de ENTER y el mismo seguira en los registros.");
				if (opc == 'E')
				{
					vendedores.remove(i);
				}
			}
		}

	}

	public void buscarVendedor()
	{
		char opc;
		String buscar = IBIO.input("\n| Cuil/cuit de vendedor a buscar: ");

		for (int i = 0; i < vendedores.size(); i++)
		{
			if (vendedores.get(i).getCuilCuit().equalsIgnoreCase(buscar))
			{
				do
				{
					System.out.println("0- Salir");
					System.out.println("1- Ver datos personalesr ");
					System.out.println("2- Ver ventas del vendedor");

					opc = IBIO.inputChar("\n| Ingrese la opcion: ");

					switch (opc)
					{
						case '1' : vendedores.get(i).verVend();
							break;

						case '2' :
							       vendedores.get(i).verVentas();
							break;
					}

					}while (opc != '0');
			}
			else
			{
				System.out.println("\n|El vendedor no existe.\n");
				break;
			}
		}
	}

	public void listar()
	{
		char opc;

		for (int i = 0; i < vendedores.size(); i++)
		{

			if (i == 15)
			{
				opc = IBIO.inputChar("\n| Se han listado 16 vendedores, ingrese 'N' si desea dejar de listar, de lo contrario de ENTER:  ");
				if (opc == 'N')
				{
					break;
				}
			}

				System.out.println("\n");
				vendedores.get(i).verVend();
		}
	}

		public void listarVentas () //listar ventas por proveedor
	{
			char opc;
			String ccBus = IBIO.input("\n| Cuil/cuit del vendedor: ");

			for (int i = 0; i < vendedores.size(); i++)
			{

				if (i == 15)
				{
					opc = IBIO.inputChar("\n |Se han listado 16 proveedores, ingrese 'N' si desea dejar de listar, de lo contrario de ENTER:  ");
					if (opc == 'N')
					{
						break;
					}
				}
				if (vendedores.get(i).cuilCuit.equalsIgnoreCase(ccBus))
				{
					System.out.println("\n");
					vendedores.get(i).verVentas();
				}
			}
	}

 public void buscarVenta()// llamamos una opcion en gestion ventas
 {
	 int id = IBIO.inputInt("\n| Id: ");
			for (int i = 0; i < GestionPedidos.ventas.size(); i++)
			{
				if (GestionPedidos.ventas.get(i).getId() == id)
				{
					GestionPedidos.ventas.get(i).verVen();
				}
			}
		}
 }


