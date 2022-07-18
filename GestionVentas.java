package proyecto;

public class GestionVentas
{


	public void menuVentas()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                   |");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |        MENÚ DE HISTORIAL DE VENTAS          |");
			System.out.println("\t\t\t |            ...................              |");
			System.out.println("\t\t\t |    1- General                               |");
			System.out.println("\t\t\t |    2- Clientes Distribuidores               |");
			System.out.println("\t\t\t |    3- Clientes Particulares                 |");
			System.out.println("\t\t\t |            ...................              |");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir del historial de ventas         |");
			System.out.println("\t\t\t |---------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : general();
					break;

				case '2' : distribuidores();
				break;

				case '3' :  particulares();
				break;


			}
		}while (opcMod != '0');

	}

	public void general()
	{
		System.out.println("\n|HISTORIAL DE VENTAS GENERAL|\n");
		for (int i = 0; i < GestionPedidos.ventas.size(); i++)
		{
			if ((GestionPedidos.ventas.get(i).getEstadoEnv() == 'C')&&(GestionPedidos.ventas.get(i).getEstadoPag()=='C'))
			{
				GestionPedidos.ventas.get(i).verH();
			}

		}
	}

	public void distribuidores ()
	{
		System.out.println(" \n|HISTORIAL CLIENTES DISTRIBUIDORES|\n");
		for (int i = 0; i < GestionClientes.clientes.size(); i++)
		{
			if ((GestionClientes.clientes.get(i).getCat() == 'D'))

				GestionClientes.clientes.get(i).verVent();
		}
	}

	public void particulares ()
	{
		System.out.println(" \n|HISTORIAL CLIENTES PARTICULARES|\n");
		for (int i = 0; i < GestionClientes.clientes.size(); i++)
		{
			if ((GestionClientes.clientes.get(i).getCat() == 'P'))
			{
				GestionClientes.clientes.get(i).verVent();
			}
		}
	}



}
