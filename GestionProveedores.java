package proyecto;

import java.util.ArrayList;


public class GestionProveedores
{
	static ArrayList <Proveedor> proveedores = new ArrayList <Proveedor>();


	public void menuProveedor()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |             MENÚ DE PROVEEDORES            |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |    1- Alta                                 |");
			System.out.println("\t\t\t |    2- Modificar                            |");
			System.out.println("\t\t\t |    3- Baja                                 |");
			System.out.println("\t\t\t |    4- Busqueda                             |");
			System.out.println("\t\t\t |    5- Listar                               |");
			System.out.println("\t\t\t |    6- Listar compras pendientes de entrega |");
			System.out.println("\t\t\t |    7- Historial                            |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir de menú de proveedores         |");
			System.out.println("\t\t\t |--------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : cargarProveedor();
					break;

				case '2' : modificarProveedor();
				break;

				case '3' : bajaProveedor();
				break;

				case '4' : buscarProveedor();
				break;

				case '5' : listar();
				break;

				case '6' : listarCompPE ();
				break;

				case '7' : listarHistorial ();
				break;

			}
		}while (opcMod != '0');
	}

	public void cargarProveedor ()
	{
		System.out.println("\n|ALTA PROVEEDOR|\n ");
		String cuilCuit = IBIO.input("\n| Cuil/Cuit: ");
		veriE(cuilCuit);
		if (veriE(cuilCuit))
		{
			System.out.println("\n|El proveedor ya existe\n");
		}
		else
		{


			String razonSoc = IBIO.input("\n| Razon Social: ");
			String dni = IBIO.input("\n| DNI: ");
			String tel = IBIO.input("\n| Telefono/Celular: ");
			String mail = IBIO.input("\n| Mail: ");
			String direccion = IBIO.input("\n| Direccion: ");
			char estado = IBIO.inputChar("\n| Estado del proveedo [A por activo, D por desactivo]");
			InsumoProv insumo = cargarInsP(estado);


			Proveedor nuevo = new Proveedor (razonSoc, dni, cuilCuit, tel, mail, direccion, insumo, estado);
//			proveedores.add(nuevo);
			ordenamientoIns (nuevo);

		}



	}

	public boolean veriE (String cuilCuit) //Verificacion de existencia de Proveedor
	{
		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).getCuilCuit().equalsIgnoreCase(cuilCuit))
			{
				return true;
			}
		}
			return false;

	}

	static void ordenamientoIns (Proveedor nuevo)
	{
		//Ciclo de control principal, asume el primer elemento ubicado, inicia en el segundo
		if(!proveedores.isEmpty())
		{
			boolean inserto = false;
			for (int i = 0; i < proveedores.size(); i++)
			{
				if (proveedores.get(i).getRazonSoc().compareToIgnoreCase(nuevo.getRazonSoc())  > 0)
				{
					proveedores.add(i,nuevo);
					inserto = true;
					break;
				}
			}
			if (!inserto)
			{
				proveedores.add(nuevo);
			}
		}
		else
		{
			proveedores.add(nuevo);
		}
	}

	public InsumoProv cargarInsP(char estado) // cargar insumo proveedor
	{
		System.out.println("\n\n|CARGA DE INSUMO|\n");
		System.out.println("\n | Grafito: 1 \n | Talco industrial: 2 \n | Envases: 3 \n | Etiquetas: 4 ");
		int id = IBIO.inputInt("\n| Ingrese Id: ");
		String descripcion = IBIO.input("\n| Ingrese descripcion: ");
		double cantP = IBIO.inputDouble("\n| Kg o unidades de presentacion: ");
		String uP = IBIO.input("\n| Unidad [KG si es en esa unidad o U si es en enteros] : ");
		double precPre = IBIO.inputDouble("\n| Precio por presentacion: ");
		double precioInicial = precPre/cantP;
		if (estado == 'A')
		{
			MetodosGenerales.insumos.get(id-1).setPrecio(precioInicial);
		}
		return new InsumoProv (id, descripcion ,cantP, uP, precPre);

	}

	public void modificarProveedor ()
	{
		boolean ord = true;
		System.out.println("\n|MODIFICACION DE UN PROVEEDOR|\n");
		String ing = IBIO.input("\n| Cuil/cuit del proveedor a modificar: ");
		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).cuilCuit.equalsIgnoreCase(ing))
			{
				char opc;
				do
				{

					System.out.println("\n0- Salir");
					System.out.println("\n1- Datos personales del proveedor: ");
					System.out.println("\n2- Datos de insumo: ");


					opc = IBIO.inputChar("\n| Ingrese la opcion: ");

					switch (opc)
					{
						case '1' : proveedores.get(i).modProv();
								   ord = false;
							break;

						case '2' : proveedores.get(i).modInsumo();
							break;
					}

					} while (opc != '0');
			}
		}
		if (ord == false)
		{
			ordenamientoburbujeo ();
		}
	}

	static void ordenamientoburbujeo ()
	{
	    Proveedor aux;
		boolean desordenado;
		do {
			desordenado = false;

		for (int i = 0; i < proveedores.size()- 1; i++)
		{
			if (proveedores.get(i).getRazonSoc().compareTo(proveedores.get(i + 1).getRazonSoc())> 0)
			{
				desordenado = true;
				aux = proveedores.get(i);
				proveedores.set(i,proveedores.get(i + 1));
				proveedores.set(i + 1, aux);
			}

		}
		} while (desordenado);
	}

	public void bajaProveedor ()
	{
		char opc;
		System.out.println("\n|BAJA DE UN PROVEEDOR|\n");
		String ing = IBIO.input("| Cuil/cuit: ");

		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).cuilCuit.equalsIgnoreCase(ing))
			{
				proveedores.get(i).verProv();
				opc = IBIO.inputChar("\n| Luego de ver el proveedor seleccionado, [Ingrese 'E' si desea eliminarlo, de lo contrario de ENTER y el mismo seguira en los registros]");
				if (opc == 'E')
				{
					proveedores.remove(i);
					System.out.println("\n|El proveedor se ha eliminado con exito|");
				}
			}
		}

	}

	public void buscarProveedor()
	{
		char opc;
		System.out.println("\n|BUSQUEDA DE UN PROVEEDOR|\n");
		String buscar = IBIO.input("\n| Cuil/cuit: ");

		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).getCuilCuit().equalsIgnoreCase(buscar))
			{
				do
				{
					System.out.println("\n0- Salir");
					System.out.println("\n1- Ver datos personales ");
					System.out.println("\n2- Ver compras pendientes de entrega ");
					System.out.println("\n3- Ver historial ");


					opc = IBIO.inputChar("\n| Ingrese la opcion: ");

					switch (opc)
					{
						case '1' : proveedores.get(i).verProv();
							break;

						case '2' : proveedores.get(i).verCompPE();
							break;

						case '3' : proveedores.get(i).verHis();
						break;
					}

					}while (opc != '0');
			}
		}
	}

	public void listar ()
	{

		System.out.println("\n|LISTAR PROVEEDORES|\n");
		char opc;
		do
		{
			System.out.println("\n0- Salir");
			System.out.println("\n1- Por proveedor: ");
			System.out.println("\n2- Por insumo: ");


			opc = IBIO.inputChar("\n| Ingrese la opcion: ");

			switch (opc)
			{
				case '1' : listarXproveedor();
					break;

				case '2' : listarXinsumo();
				break;
			}

			}while (opc != '0');

	}

	public void listarXproveedor ()
	//Listar seleccionando un proveedor
	{
		char opc;

		for (int i = 0; i < proveedores.size(); i++)
		{

			if (i == 15)
			{
				opc = IBIO.inputChar("\n| Se han listado 16 proveedores, ingrese 'N' si desea dejar de listar, de lo contrario de ENTER:  ");
				if (opc == 'N')
				{
					break;
				}
			}
				proveedores.get(i).verProv();

		}
	}
//VER METODO - corregido. ver si funciona
	public void listarXinsumo ()
	{
		char opc;
		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).getInsumo().getId() == (i+1))
			{
				proveedores.get(i).verProv();
			}

		}

	}


	public void listarCompPE () //listar compras pendientes de entrega
	{
		char opc;
		do
		{
			System.out.println("\n0-Salir");
			System.out.println("\n1- Por proveedor ");
			System.out.println("\n2- Por insumo ");
			System.out.println("\n3- General ");


			opc = IBIO.inputChar("\n| Ingrese la opcion: ");

			switch (opc)
			{
				case '1' :  listarXproveedorCE();
					break;

				case '2' : listarXinsumoCE();
					break;

				case '3' : listarGenCE();
				break;
			}

			}while (opc != '0');

	}

	public void listarXinsumoCE()
	{

		int idBus = IBIO.inputInt("\n| Id del insumo: ");

		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).getInsumo().getId() == idBus)
			{

				proveedores.get(i).verCompPE();
			}
		}
	}
	public void listarXproveedorCE()
	{
		String ccBus = IBIO.input("\n| Cuil/cuit del proveedor: ");

		for (int i = 0; i < proveedores.size(); i++)
		{

			if (proveedores.get(i).cuilCuit.equalsIgnoreCase(ccBus))
			{
				proveedores.get(i).verCompPE();
			}
		}
	}

	public void listarGenCE ()
	{
		char opc;
		for (int i = 0; i < proveedores.size(); i++)
		{


			proveedores.get(i).verCompPE();
		}
	}
	public void listarHistorial()
	{
		char opc;
		do
		{
			System.out.println("\n0- Salir");
			System.out.println("\n1- Por proveedor ");
			System.out.println("\n2- Por insumo ");
			System.out.println("\n3- General ");


			opc = IBIO.inputChar("\n| Ingrese la opcion: ");

			switch (opc)
			{
				case '1' :  listarXproveedorH();
					break;

				case '2' : listarXinsumoH();
					break;

				case '3' : listarGenH();
				break;
			}

			}while (opc != '0');
	}

	public void listarXproveedorH ()
	{
		String ccBus = IBIO.input("\n| Cuil/cuit del proveedor: ");

		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).cuilCuit.equalsIgnoreCase(ccBus))
			{
				proveedores.get(i).verHis();
			}
		}
	}

	public void listarXinsumoH()
	{
		int idBus = IBIO.inputInt("\n| Id del insumo: ");

		for (int i = 0; i < proveedores.size(); i++)
		{
			if (proveedores.get(i).getInsumo().getId() == idBus)
			{
					proveedores.get(i).verHis();
			}
		}
	}

	public void listarGenH ()
	{
		char opc;

		for (int i = 0; i < proveedores.size(); i++)
		{
			if (i == 15)
			{
				opc = IBIO.inputChar("\n| Se han listado 16 proveedores, ingrese 'N' si desea dejar de listar, de lo contrario de ENTER:  ");
				if (opc == 'N')
				{
					break;
				}
			}
			System.out.println("\n|Proveedor: " + proveedores.get(i).getCuilCuit() + "," + proveedores.get(i).getRazonSoc() + "|");
			proveedores.get(i).verHis();
		}
	}
}