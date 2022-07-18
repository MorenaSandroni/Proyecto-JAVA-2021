package proyecto;

import java.util.ArrayList;
import java.util.Date;

public class GestionCompras
{

	static ArrayList <Compras> compras = new ArrayList <Compras>();
	static GestionProveedores GestionProveedores = new GestionProveedores();
	static GestionInsumos GestionInsumos = new GestionInsumos();


	public void menuCom()
	{
		char opc;
		do
		{

		System.out.println("\n\n");
		System.out.println("\t\t\t |--------------------------------------------|");
		System.out.println("\t\t\t |               EMPRESA MJF                  |");
		System.out.println("\t\t\t |--------------------------------------------|");
		System.out.println("\t\t\t |             MENÚ DE COMPRAS                |");
		System.out.println("\t\t\t |           ...................              |");
		System.out.println("\t\t\t |    1- Cargar                               |");
		System.out.println("\t\t\t |    2- Modificar                            |");
		System.out.println("\t\t\t |    3- Baja                                 |");
		System.out.println("\t\t\t |    4- Buscar                               |");
		System.out.println("\t\t\t |    5- Historial                            |");
		System.out.println("\t\t\t |           ...................              |");
		System.out.println("\t\t\t |--------------------------------------------|");
		System.out.println("\t\t\t |    0- Salir de menú de Compras             |");
		System.out.println("\t\t\t |--------------------------------------------|");

		opc = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");


		switch (opc)
		{
			case '1' : nuevaCom() ;
				break;

			case '2' : modCom();
			break;

			case '3' : elimCom() ;
			break;

			case '4' : busCom() ;
			break;

			case '5' : histCom() ;
			break;
		}
		}while (opc != '0');

	}

	public void nuevaCom ()
	{
		int posPr = 0;
		int posIn = 0;
		Proveedor provCom = null;
		Insumo insCom = null;
		int idInsProv = 0;
		int id;
		String fecha = MetodosGenerales.fecha;
		boolean activo = false;
		System.out.println("\n|NUEVA COMPRA|\n");

		String ccProv = IBIO.input("\n| Cuil/Cuit del proveedor: ");

		for (int i = 0; i < GestionProveedores.proveedores.size(); i++)
		{

			if (GestionProveedores.veriE(ccProv))
			{
				if (GestionProveedores.proveedores.get(i).getEstado()=='A')
				{
					activo = true;
					if (GestionProveedores.proveedores.get(i).getCuilCuit().equalsIgnoreCase(ccProv))
					{
							provCom = GestionProveedores.proveedores.get(i);
							idInsProv = GestionProveedores.proveedores.get(i).getInsumo().getId();
							//prueba si lo ubica bien, y si
							//System.out.println(Gesproveedores.proveedores.get(i).getInsumo().getId()+Gesproveedores.proveedores.get(i).getInsumo().getInsumo());//ERROR, mal la referencia
							posPr = i;
							double kgU = IBIO.inputDouble("\n| KG o Unidades encargadas: ");
							double precio = IBIO.inputDouble("\n| Precio total: ");
							double precioFi = precio/kgU;
							double dolar = IBIO.inputDouble("\n| Dolar a la fecha: ");
							char estado = IBIO.inputChar("\n| Estado [E de entregado, N de no entregado]: ");
							char opc = IBIO.inputChar("\n| Fecha: " + fecha + "[Si quiere modificar la fecha ingrese 'M', de lo contrario de ENTER]: ");
							if (opc == 'M')
							{
								String dia = IBIO.input("\n| Dia modificado [formato dd] : ");
								String mes = IBIO.input("\n| Mes modificado [formato mm] : ");
								String ano = IBIO.input("\n| Año modificado [formato aaaa] : ");
								fecha = (dia + "/" + mes + "/" + ano);
								MetodosGenerales.validarFecha(fecha);
							}
							for (int j = 0; j < MetodosGenerales.insumos.size(); j++)
							{
								if ((MetodosGenerales.insumos.get(j).getId() == idInsProv))
								{
									insCom = MetodosGenerales.insumos.get(j);
									posIn = j;
								}
							}
							id = compras.size()+1;
							Compras nueva = new Compras (id,fecha, estado, provCom, insCom, kgU, precio, dolar);
							compras.add(nueva);
							GestionProveedores.proveedores.get(posPr).addCom(nueva);
							MetodosGenerales.insumos.get(posIn).addCom(nueva);
							if (estado == 'E')
							{
								MetodosGenerales.insumos.get(posIn).actStockComp(kgU);
							}

							//si es mayor lo cambie si es menor no lo cambio

							if (precioFi > MetodosGenerales.insumos.get(posIn).getPrecio() )
							{
								MetodosGenerales.insumos.get(posIn).actPrecio(precioFi);
								GestionProveedores.proveedores.get(posPr).actPrecio(precioFi);

							}

						}
				}

			}

			if (activo = false)
			{
				System.out.println("\n|El proveedor se encuentra desactivo.");
			}
		}






	}

	public void modCom ()
	{
		System.out.println("\n|MODIFICACION DE COMPRA|\n");
		int idCom = IBIO.inputInt("\n| Id de compra: ");
		for (int i = 0; i < compras.size(); i++)
		{
			if (compras.get(i).getId() == idCom)
			{
				compras.get(i).modComp(idCom);
			}
		}
	}

	public void elimCom ()
	{
		char opc;
		System.out.println("\n|ELIMINACION DE COMPRA|\n");
		int idCom = IBIO.inputInt("\n| Id de compra: ");
		for (int i = 0; i < compras.size(); i++)
		{
			if (compras.get(i).getId() == idCom)
			{
				compras.get(i).verComp();
				opc = IBIO.inputChar("\n| Ingrese E si desea proseguir con la eliminacion, de lo contrario de ENTER: ");
				if (opc == 'E')
				{
					compras.remove(i);
				}

			}
		}
	}

	public void busCom ()
	{
		char opc;
		do
		{
			System.out.println("\n|BUSCAR COMPRA|\n");
			System.out.println("\n|0|Salir ");
			System.out.println("\n|1|Por ID de compra ") ;
			System.out.println("\n|2|Por fecha ");


			opc = IBIO.inputChar("\n| Ingrese opcion: ");

		switch (opc)
		{
			case '1' : xNCom ();
				break;

			case '2' : xFecha ();
			break;
		}
		}while (opc != '0');
	}

	public void xNCom ()
	{
		System.out.println("\n|BUSCAR POR ID DE COMPRA|\n");
		int idCom = IBIO.inputInt("\n| Id de compra: ");
		for (int i = 0; i < compras.size(); i++)
		{
			if (compras.get(i).getId() == idCom)
			{
				compras.get(i).verComp();
			}
		}
	}

	public void xFecha () // hacer cuando este lo de fecha
	{
		String dia = IBIO.input("\n| Dia [en formato dd]: ");
		String mes = IBIO.input("\n| Mes [en formato mm]: ");
		String ano = IBIO.input("\n| Año [en formato aaaa]: ");
		String fechaIng = (dia + "/" + mes + "/" + ano);

		for (int i = 0; i < compras.size(); i++)
		{
			if (compras.get(i).getFecha().equalsIgnoreCase(fechaIng))
			{
				compras.get(i).verComp();
			}
		}

	}

	public void histCom ()
	{
		char opc;
		do
		{
			System.out.println("\n|HISTORIAL DE COMPRAS|\n");
			System.out.println("\n|0|Salir ") ;
			System.out.println("\n|1|Por proveedor seleccionado ") ;
			System.out.println("\n|2|Por Insumo ");
			System.out.println("\n|3|General ");


			opc = IBIO.inputChar("\n| Ingrese opcion: ");

		switch (opc)
		{
			case '1' : xProvS();
				break;

			//case '2' : xIns ();
			//break;
			case '3' : gnr ();
			break;
 		}
		}while (opc != '0');
	}

	public void xProvS()
	{
		String ccBus = IBIO.input("\n| Cuil/cuit del proveedor: ");

		for (int i = 0; i < GestionProveedores.proveedores.size(); i++)
		{
			if (GestionProveedores.proveedores.get(i).cuilCuit.equalsIgnoreCase(ccBus))
			{
				GestionProveedores.proveedores.get(i).verHis();
				System.out.println("\n");
			}
		}
	}


	public void gnr ()
	{
		for (int i = 0; i < GestionProveedores.proveedores.size(); i++)
		{
			GestionProveedores.proveedores.get(i).verHis();
			System.out.println("\n");
		}
	}

}
