package proyecto;

import java.util.ArrayList;

public class GestionPedidos
{

	static ArrayList <Ventas> ventas = new ArrayList <Ventas>();

	public void menuVentas()
	{
		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |             MENÚ DE PEDIDOS                 |");
			System.out.println("\t\t\t |            ...................              |");
			System.out.println("\t\t\t |    1- Presupuestos                          |");
			System.out.println("\t\t\t |    2- Nuevo                                 |");
			System.out.println("\t\t\t |    3- Modificacion                          |");
			System.out.println("\t\t\t |    4- Baja                                  |");
			System.out.println("\t\t\t |    5- Busqueda                              |");
			System.out.println("\t\t\t |    6- Listar y mostrar estados pendientes   |");
			System.out.println("\t\t\t |            ...................              |");
			System.out.println("\t\t\t |---------------------------------------------|");
			System.out.println("\t\t\t |    0- Salir de menú de pedidos              |");
			System.out.println("\t\t\t |---------------------------------------------|");

			opcMod = IBIO.inputChar("\t\t\t |    Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : presupuestos ();
					break;

				case '2' : nueva();
				break;

				case '3' : modificar();
				break;

				case '4' : eliminar();
				break;

				case '5' : buscar();
				break;

				case '6' : EP();
				break;


			}
		}while (opcMod != '0');

	}


	public void presupuestos ()
	{
		double descDis = 0;
		double kg = IBIO.inputDouble("\n| Kg a producir: ");
		double vDolar = IBIO.inputDouble("\n| Dolar con el que quiere calcular:");
		char cat = IBIO.inputChar("\n| Categoria [D por distribuidor, P por particular]: ");
		if (cat == 'D')
		{
			descDis = GestionClientes.descDis;
		}
		MetodosGenerales.producto.calcCostos (kg, vDolar,descDis);

		char opc = IBIO.inputChar("\n| Ingrese E para enviar el pedido, de lo contraio de ENTER: ");
		if (opc == 'E')
		{
			 nueva();
		}
	}

	public void nueva()
	{
		//autonumerico de numero de pedido
		String fecha = MetodosGenerales.fecha ;
		String ccCli;
		double vDolar;
		Vendedor vendVen = null;
		Cliente clieVen = null;
		char cat = 0 ;
		Producto producto = MetodosGenerales.producto;
		double kg;
		double desc = 0;
		double descDis = 0;
		char estadoEnv = 'P';
		char estadoPag = 'P';
		double [] valores = new double[2];
		char opc = 0;
		double descFdis = 0;
		boolean noExisteVend = true;
		boolean noExisteClie = true;
		int id = 0;
		double [] stocks = new double [4];
		double ganancia;
		int posCli = 0;
		int posVend = 0;
		String ccVend = IBIO.input("\n| Cuil/Cuit del vendedor: ");
		// no hay que recrear la lista, siempre que quiera utilizarla debo llamar a la clase Gestion vendedores

		for (int i = 0; i < GestionVendedores.vendedores.size(); i++)
		{
			if (GestionVendedores.vendedores.get(i).getCuilCuit().equalsIgnoreCase(ccVend))
			{
				posVend = i;
				vendVen = GestionVendedores.vendedores.get(i);
				noExisteVend = false;
				break;
			}
		}
		if (noExisteVend)
		{
			System.out.println("\n| El vendedor no coincide.");
		}
		else
		{
			ccCli = IBIO.input("\n| Cuil/Cuit del Cliente: ");
			for (int i = 0; i < GestionClientes.clientes.size(); i++)
			{
				if (GestionClientes.clientes.get(i).getCuilCuit().equalsIgnoreCase(ccCli))
				{
					posCli = i;
					clieVen = GestionClientes.clientes.get(i);
					if (GestionClientes.clientes.get(i).getCat() == 'D')
					{
						cat = 'D';
						descDis = GestionClientes.descDis;
					}
					noExisteClie = false;
					break;
				}
			}
			if (noExisteClie)
			{
				System.out.println("\n| El cliente no coincide.");
			}

			else
			{


				vDolar = IBIO.inputDouble("\n| Valor del dolar: ");
				kg = IBIO.inputDouble("\n| Cantidad de kg pedidos: ");
				System.out.println(ventas.size());
				char opcFE = IBIO.inputChar("\n| Fecha: " + fecha + "[Si quiere modificar la fecha ingrese 'M', de lo contrario de ENTER]: ");
				if (opcFE == 'M')
				{
					String dia = IBIO.input("\n| Dia modificado [formato dd] : ");
					String mes = IBIO.input("\n| Mes modificado [formato mm] : ");
					String ano = IBIO.input("\n| Año modificado [formato aa] : ");
					fecha = (dia + "/" + mes + "/" + ano);
					MetodosGenerales.validarFecha(fecha);
				}

				opc = IBIO.inputChar("\n| ¿Desea agregar algun descuento? [S por si, ENTER por NO] ");
				if (opc == 'S')
				{
					desc = IBIO.inputDouble("\n| Descuento [SIN %]: ");
					if (cat == 'D')
					{
						descFdis = desc + descDis;
					}
					else
					{
						descFdis = desc;
					}
					/*valores = MetodosGenerales.producto.calcCostosFdes (kg, vDolar, descFdis);
					id = ventas.size()+1; //Tomo el valor de size como id para generar una especie de autonumerico, el size siempre me tirara un valor mas de la posicion de la lista, arrancando asi en 1.
					ganancia = kg*MetodosGenerales.producto.ganancia;
					Ventas Nueva = new Ventas (id,vendVen, vDolar, clieVen,fecha,producto,kg, descFdis,estadoEnv,estadoPag,valores[0], valores[1],stocks[0],stocks[1],stocks[2],stocks[3],ganancia);
					ventas.add(Nueva);
					GestionClientes.clientes.get(posCli).addVen(Nueva);
					stocks = ventas.get(id-1).stocks(kg);
					ventas.get(id-1).setKgGraf(stocks[0]);
					ventas.get(id-1).setKgTalc(stocks[1]);
					ventas.get(id-1).setUenv(stocks[2]);
					ventas.get(id-1).setUeti(stocks[3]);*/
				}

					if (cat == 'D')
					{
						valores = MetodosGenerales.producto.calcCostosFdes (kg, vDolar, descFdis);
						id = ventas.size()+1; //Tomo el valor de size como id para generar una especie de autonumerico, el size siempre me tirara un valor mas de la posicion de la lista, arrancando asi en 1.
						ganancia = kg*MetodosGenerales.producto.ganancia;
						Ventas Nueva = new Ventas (id,vendVen, vDolar, clieVen,fecha,producto,kg, descFdis,estadoEnv,estadoPag,valores[0], valores[1],stocks[0],stocks[1],stocks[2],stocks[3],ganancia);
						ventas.add(Nueva);
						GestionClientes.clientes.get(posCli).addVen(Nueva);
						GestionVendedores.vendedores.get(posVend).addVen(Nueva);
						stocks = ventas.get(id-1).stocks(kg);
						ventas.get(id-1).setKgGraf(stocks[0]);
						ventas.get(id-1).setKgTalc(stocks[1]);
						ventas.get(id-1).setUenv(stocks[2]);
						ventas.get(id-1).setUeti(stocks[3]);
					}
					else
					{
						id = ventas.size()+1;
						valores = MetodosGenerales.producto.calcCostos (kg, vDolar, descDis);
						ganancia = kg*MetodosGenerales.producto.ganancia;
						Ventas Nueva = new Ventas (id,vendVen, vDolar, clieVen,fecha,producto,kg, descFdis,estadoEnv,estadoPag,valores[0], valores[1],stocks[0],stocks[1],stocks[2],stocks[3],ganancia);
						ventas.add(Nueva);
						GestionClientes.clientes.get(posCli).addVen(Nueva);
						GestionVendedores.vendedores.get(posVend).addVen(Nueva);
						stocks = ventas.get(id-1).stocks(kg);
						ventas.get(id-1).setKgGraf(stocks[0]);
						ventas.get(id-1).setKgTalc(stocks[1]);
						ventas.get(id-1).setUenv(stocks[2]);
						ventas.get(id-1).setUeti(stocks[3]);
					}

				}

			}
		}

	public void modificar ()
	{
		System.out.println("\n|MODIFICACION DE PEDIDO|\n");
		int idVen = IBIO.inputInt("\n|Id de Pedido: ");
		for (int i = 0; i < ventas.size(); i++)
		{
			if (ventas.get(i).getId() == idVen)
			{
				ventas.get(i).modVen(idVen);
			}
		}
	}

	public void eliminar ()
	{
		char opc;
		System.out.println("\n|ELIMINACION DE PEDIDO/VENTA|\n");
		int idCom = IBIO.inputInt("\n| Id de Pedido/Venta: ");
		for (int i = 0; i < ventas.size(); i++)
		{
			if (ventas.get(i).getId() == idCom)
			{
				ventas.get(i).verVen();
				opc = IBIO.inputChar("\n| Ingrese E si desea proseguir con la eliminacion, de lo contrario de ENTER: ");
				if (opc == 'E')
				{
					ventas.remove(i);
				}

			}
		}
	}

	public void buscar ()
	{
		char opc;
		do
		{
			System.out.println("\n|BUSCAR PEDIDO|\n");
			System.out.println("\n0- Salir ") ;
			System.out.println("\n1- Por ID de pedido ") ;
			System.out.println("\n2- Por fecha ");
			System.out.println("\n3- Por cliente ");


			opc = IBIO.inputChar("\n| Ingrese opcion: ");

		switch (opc)
		{
			case '1' : xNVen ();
				break;

			case '2' : xFecha ();
			break;

			case '3' : xCli ();
			break;
		}
		}while (opc != '0');
	}

	public void xNVen ()
	{
		System.out.println("\n|BUSCAR POR ID DE PEDIDO|\n");
		int idCom = IBIO.inputInt("\n| Id de pedido: ");
		for (int i = 0; i < ventas.size(); i++)
		{
			if (ventas.get(i).getId() == idCom)
			{
				ventas.get(i).verVen();
			}
		}
	}

	public void xFecha ()
	{
		String dia = IBIO.input("\n| Dia [en formato dd]: ");
		String mes = IBIO.input("\n| Mes [en formato mm]: ");
		String ano = IBIO.input("\n| Año [en formato aa]: ");
		String fechaIng = (dia + "/" + mes + "/" + ano);

		for (int i = 0; i < ventas.size(); i++)
		{
			if (ventas.get(i).getFecha().equalsIgnoreCase(fechaIng))
			{
				ventas.get(i).verVen();
			}
		}

	}

	public void xCli ()
	{
		System.out.println("\n|BUSCAR POR CLIENTE|\n");
		String ccCli = IBIO.input("\n| Cuil/cuit del cliente: ");// despues hacer uno generakl y ver donde mas se puede aplicar
		for (int i = 0; i < GestionClientes.clientes.size(); i++)
		{
			if (GestionClientes.clientes.get(i).getCuilCuit().equalsIgnoreCase(ccCli))
			{
				GestionClientes.clientes.get(i).verCli();
				System.out.println("\n| Lista de ventas: \n");
				GestionClientes.clientes.get(i).verVent();
			}
		}
	}

	public void EP()
	{
		char opc;
		do
		{
			System.out.println("\n| ESTADOS PENDIENTES|");
			System.out.println("\n1- Ver pedidos con estados en 'pendiente' ") ;
			System.out.println("\n2- Modificar los estados de los pedidos ");


			opc = IBIO.inputChar("\n| Ingrese opcion: ");

		switch (opc)
		{
			case '1' : verEP ();
				break;

			case '2' : modEP ();
			break;
		}
		}while (opc != '0');

	}

	public void verEP ()
	{
		System.out.println("\n|PEDIDOS CON ESTADOS EN 'PENDIENTE'|\n");
		for (int i = 0; i < ventas.size(); i++)
		{
			if (ventas.get(i).getEstadoEnv() == 'P' || ventas.get(i).getEstadoPag() == 'P' )
			{
				ventas.get(i).verVen();
			}
		}
	}

	public void modEP ()
	{
		System.out.println("\n|MODIFICAR PEDIDOS CON ESTADOS PENDIENTES|\n");
		int codOpc = IBIO.inputInt("\n| Numero de pedido a modificar: ");
		char opc1;
		char opc2;
		for (int i = 0; i < ventas.size(); i++)
		{
			if (ventas.get(i).getId() == codOpc)
			{
				ventas.get(i).verVen();
				if (ventas.get(i).getEstadoEnv() == 'P' )
				{
					opc1 = IBIO.inputChar("\n| ¿Desea modificar el estado del ENVIO? ['S' por si, ENTER por no] ");
					if (opc1 == 'S')
					{
						ventas.get(i).setEstadoEnv('C');

						MetodosGenerales.insumos.get(0).setStock(MetodosGenerales.insumos.get(0).getStock()-ventas.get(i).getKgGraf());
						MetodosGenerales.insumos.get(1).setStock(MetodosGenerales.insumos.get(1).getStock()-ventas.get(i).getKgTalc());
						MetodosGenerales.insumos.get(2).setStock(MetodosGenerales.insumos.get(2).getStock()-ventas.get(i).getUenv());
						MetodosGenerales.insumos.get(3).setStock(MetodosGenerales.insumos.get(3).getStock()-ventas.get(i).getUeti());
					}
				}

				if (ventas.get(i).getEstadoPag() == 'P' )
				{
					opc2 = IBIO.inputChar("\n| ¿Desea modificar el estado del PAGO? ['S' por si, ENTER por no] ");
					if (opc2 == 'S')
					{
						ventas.get(i).setEstadoPag('C');
					}
				}
			}

		}
	}




}
;