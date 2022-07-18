package proyecto;

import java.util.ArrayList;

public class GestionProducto
{

	int dolar;


	public void menuProducto()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |             MENÚ DE PRODUCTO               |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |    1- Ver costo final en PESOS             |");
			System.out.println("\t\t\t |    2- Ver costo final en DOLARES           |");
			System.out.println("\t\t\t |    3- Gestion de costos                    |");
			System.out.println("\t\t\t |           ...................              |");
			System.out.println("\t\t\t |--------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir de menú de Insumos             |");
			System.out.println("\t\t\t |--------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : costoFinalP ();
					break;

				case '2' : costoFinalD ();
				break;

				case '3' : menuGestCos();
				break;


			}
		}while (opcMod != '0');
	}


	public void costoFinalP ()
	{
		System.out.println("\n|VALOR EN PESOS POR UN KG|\n");
		System.out.println("\n|valor: " + MetodosGenerales.producto.calCostF());
	}
	public void costoFinalD ()
	{
		System.out.println("\n|VALOR EN DOLARES POR UN KG|\n");
		double dolar = IBIO.inputDouble("\n| Valor del dolar a calcular: ");
		double costFinalP = MetodosGenerales.producto.calCostF();
		System.out.println("\n|valor: " + costFinalP/dolar);
	}
	public void menuGestCos()
	{
		char opc;
		do
		{
			System.out.println("\n|GESTION DE COSTOS|\n");
			System.out.println("\n0- Salir");
			System.out.println("\n1- Costos de insumos")  ;
			System.out.println("\n2- Costos extra");

			opc = IBIO.inputChar("\n| Ingrese la opcion: ");

		switch (opc)
		{
			case '1' : costosIns() ;
				break;

			case '2' : costosE();
			break;
		}
		}while (opc != '0');

	}

	public void costosIns ()
	{
		char opc;
		do
		{
			System.out.println("\n|GESTION DE COSTOS DE INSUMOS|\n");
			System.out.println("\n0- Salir");
			System.out.println("\n1- Ver")  ;
			System.out.println("\n2- Modificar");

			opc = IBIO.inputChar("\n| Ingrese la opcion: ");

		switch (opc)
		{
			case '1' : MetodosGenerales.producto.verIns();
				break;

			case '2' : MetodosGenerales.producto.modIns();
			break;
		}
		}while (opc != '0');
	}

	public void costosE ()
	{
		char opc;
		do
		{
			System.out.println("\n|GESTION DE COSTOS DE INSUMOS|\n");
			System.out.println("\n0- Salir");
			System.out.println("\n1- Ver")  ;
			System.out.println("\n2- Modificar");
			System.out.println("\n3- Agregar variable");

			opc = IBIO.inputChar("\n| Ingrese la opcion: ");

		switch (opc)
		{
			case '1' : MetodosGenerales.producto.verCE();
				break;

			case '2' : MetodosGenerales.producto.modCE();
			break;

			case '3' : MetodosGenerales.producto.agCE();
			break;
		}
		}while (opc != '0');
	}


}





