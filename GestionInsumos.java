package proyecto;

import java.util.ArrayList;

public class GestionInsumos
{
	static GestionCompras compras = new GestionCompras();



	public void menuInsumos()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |             MENÚ DE INSUMOS                |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |    1- Costos pestablecidos                 |");
			System.out.println("\t\t\t |    2- Stock                                |");
			System.out.println("\t\t\t |    3- Compras                              |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir de menú de Insumos             |");
			System.out.println("\t\t\t |--------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : costosPre();
					break;

				case '2' : opcStock ();
				break;

				case '3' : compras.menuCom();
				break;


			}
		}while (opcMod != '0');
	}

	public void costosPre()
	{

			System.out.println("\n|COSTOS PRESTABLECIDOS DE INSUMOS|\n");
			System.out.println("\n|     --- Por unidad o kg ---    |\n");
			System.out.println("\n|1|GRAFITO: " + MetodosGenerales.insumos.get(0).getPrecio() )  ;
			System.out.println("\n|2|TALCO INDUSTRIAL: " + MetodosGenerales.insumos.get(1).getPrecio());
			System.out.println("\n|3|ENVASES: " + MetodosGenerales.insumos.get(2).getPrecio());
			System.out.println("\n|4|ETIQUETAS: " + MetodosGenerales.insumos.get(3).getPrecio());


	}



	public void alertStock()
	{
		for (int i = 0; i < MetodosGenerales.insumos.size(); i++)
		{
			if (MetodosGenerales.insumos.get(i).getStock() <= MetodosGenerales.insumos.get(i).getStockMin())
			{
				System.out.println("\n|EL STOCK DEL INSUMO DE CODIGO: " + MetodosGenerales.insumos.get(i).getId() + " Y DE DESCRIPCION: " + MetodosGenerales.insumos.get(i).getInsumo() + " SE ENCUENTRA BAJO:  " + MetodosGenerales.insumos.get(i).getStock());
			}
		}
	}

	public void veriStock ()
	{
		System.out.println("\n|VERIFICACION DE STOCK|\n");
		int g = 0;
		int t = 0;
		int e = 0;
		int et = 0;
		double ingKg = IBIO.inputDouble("\n| Cantidad de KG a producir: ");
		//stock necesario por kg=
		double stGraf = 0.3;
		double stTalco = 0.7;
		double stEnv = 0.2;
		double stEti = 0.2;

		//Stock necesario
		double stNgraf = ingKg * stGraf;
		double stNtalco = ingKg * stTalco;
		double stNenv = ingKg * stEnv;
		double stNeti = ingKg * stEti;

		System.out.println("\n|El stock que se necesita de cada insumo para producir " + ingKg + "Kg");
		System.out.println("\n| GRAFITO: " + stNgraf);
		System.out.println("\n| TALCO: " + stNtalco);
		System.out.println("\n| ENVASES: " + stNenv);
		System.out.println("\n| ETIQUETAS: " + stNeti + "\n");

		//Consulta si hay el stock necesario
		//Grafito 1, talco 2, envases 3, etiquetas 4.

		if (MetodosGenerales.insumos.get(0).getStock() < stNgraf)
		{
			System.out.println("\n|EL STOCK DE GRAFITO ES INSUFICIENTE");
			g = 1;

		}
		if (MetodosGenerales.insumos.get(1).getStock() < stNtalco)
		{
			System.out.println("\n|EL STOCK DE TALCO INDUSTRIAL ES INSUFICIENTE");
			t = 1;
		}
		if (MetodosGenerales.insumos.get(2).getStock() < stNenv)
		{
			System.out.println("\n|EL STOCK DE ENVASES ES INSUFICIENTE");
			e = 1;
		}
		if (MetodosGenerales.insumos.get(3).getStock() < stNeti)
		{
			System.out.println("\n|EL STOCK DE ETIQUETAS ES INSUFICIENTE");
			et = 1;
		}

		if ((g == 1) || (t == 1) || (e == 1) || (et == 1))
		{
			char opc = IBIO.inputChar("\n|¿DESEA REALIZAR UNA NUEVA COMPRA DE INSUMOS? [S(en mayuscula) por SI, ENTER por NO]");
			if (opc == 'S')
			{
				compras.nuevaCom();
			}
		}



	}

	public void stocks ()
	{
		char opc;
		do
		{
			System.out.println("\n|STOCK DE INSUMOS|\n");
			System.out.println("\n|     --- Por unidad o kg ---    |\n");
			System.out.println("\n|     --- En el caso de querer modificar algun stock minimo ingrese el numero correspondiente ---    |\n");
			System.out.println("\n|1|GRAFITO: " + MetodosGenerales.insumos.get(0).getStock() )  ;
			System.out.println("\n|2|TALCO INDUSTRIAL: " + MetodosGenerales.insumos.get(1).getStock());
			System.out.println("\n|3|ENVASES: " + MetodosGenerales.insumos.get(2).getStock());
			System.out.println("\n|4|ETIQUETAS: " + MetodosGenerales.insumos.get(3).getStock());

			opc = IBIO.inputChar("\n| Ingrese opcion, si no desea realizar ningun cambio ingrese '0'");

		switch (opc)
		{
			case '1' : MetodosGenerales.insumos.get(0).setStockMin(IBIO.inputDouble("\n| Stock minimo nuevo:"));
				break;

			case '2' : MetodosGenerales.insumos.get(1).setStockMin(IBIO.inputDouble("\n| Stock minimo nuevo:"));
			break;

			case '3' :  MetodosGenerales.insumos.get(2).setStockMin(IBIO.inputDouble("\n| Stock minimo nuevo:"));
			break;

			case '4' :  MetodosGenerales.insumos.get(3).setStockMin(IBIO.inputDouble("\n| Stock minimo nuevo:"));
			break;
		}
		}while (opc != '0');

	}


	public void opcStock ()
	{
		char opc;
		do
		{
			System.out.println("\n|MENU DE STOCKS|\n");
			System.out.println("\n|     --- Ingrese una opcion ---    |\n");
			System.out.println("\n|0| Salir");
			System.out.println("\n|1| Verificacion de stock para nuevo pedido");
			System.out.println("\n|2| Stocks por insumo");

			opc = IBIO.inputChar("\n| Ingrese opcion: ");

		switch (opc)
		{
			case '1' : veriStock ();
				break;

			case '2' : stocks ();
			break;
		}
		}while (opc != '0');
	}



}
