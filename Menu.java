package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Menu
{
	static GestionProveedores gesProveedores = new GestionProveedores();
	static GestionInsumos gesInsumos = new GestionInsumos();
	static GestionVendedores gesVendedores = new GestionVendedores();
	static GestionClientes gesClientes = new GestionClientes();
	static GestionCompras gesCompras = new GestionCompras();
	static GestionPedidos gesPedidos = new GestionPedidos();
	static GestionProducto gesProducto = new GestionProducto();
	static GestionVentas gesVentas = new GestionVentas();



	public static void main(String[] args) throws IOException, ClassNotFoundException
	{

		//LEEMOS ARCHIVOS
		MetodosGenerales.CheqLeerDatos();
		//TOMAMOS FECHA DEL SISTEMA
		MetodosGenerales.tomarFecha ();
		//CORROBORA STOCK INSUMOS
		gesInsumos.alertStock();


		char opcMod;
		do
		{
			System.out.println("\n\n");
			System.out.println("\t\t\t +============================================+");
			System.out.println("\t\t\t |               EMPRESA MJF                  |");
			System.out.println("\t\t\t +============================================+");
			System.out.println("\t\t\t |              MENÚ PRINCIPAL                |");
			System.out.println("\t\t\t |           -------------------              |");
			System.out.println("\t\t\t |          *seleccione una opcion*           |");
			System.out.println("\t\t\t |           -------------------              |");
			System.out.println("\t\t\t |    1- Proveedores                          |");
			System.out.println("\t\t\t |    2- Insumos                              |");
			System.out.println("\t\t\t |    3- Producto                             |");
			System.out.println("\t\t\t |    4- Vendedores                           |");
			System.out.println("\t\t\t |    5- Clientes                             |");
			System.out.println("\t\t\t |    6- Verificacion de stock                |");
			System.out.println("\t\t\t |    7- Pedidos                              |");
			System.out.println("\t\t\t |    8- Ventas                               |");
			System.out.println("\t\t\t |           -------------------              |");
			System.out.println("\t\t\t +============================================+");
			System.out.println("\t\t\t |    *- Guardar                              |");
			System.out.println("\t\t\t |    0- Salir del sistema                    |");
			System.out.println("\t\t\t +============================================+");


			opcMod = IBIO.inputChar("Ingrese la opcion: ");

			switch (opcMod)
			{
				case '1' : gesProveedores.menuProveedor();
					break;

				case '2' : gesInsumos.menuInsumos() ;
				break;

				case '3' : gesProducto.menuProducto();
				break;

				case '4' : gesVendedores.menuVendedor();
				break;

				case '5' : gesClientes.menuCliente();
				break;

				case '6' : gesInsumos.veriStock();
				break;

				case '7' : gesPedidos.menuVentas();
				break;

				case '8' : gesVentas.menuVentas();
				break;

				case '*' : MetodosGenerales.guardarDatos();
				break;

			}
		}while (opcMod != '0');

	}

}
