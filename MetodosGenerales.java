package proyecto;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MetodosGenerales implements Serializable
{
	static ArrayList <Insumo> insumos = new ArrayList <Insumo>();
	static ArrayList <CostoExtra> costosExtra = new ArrayList <CostoExtra>();
	static Producto producto;
	static Date fechaCap = new Date();
	static String fecha;
	private static final long serialVersionUID = 1L;

	private static char valueOf(String string) {
		// TODO Auto-generated method stub
		return 0;
	}


//COSTOS EXTRA
	static void leerDatosCostoExtra() throws IOException
	{
	String registroCE;
	String[] datCE;
	File arcArtic = new File("DatosCostosE.csv");
	if (arcArtic.exists())
	{
	FileReader archivoCE = new FileReader("DatosCostosE.csv"); // crea un file reader, lector de archivo
	BufferedReader regCE = new BufferedReader(archivoCE);
	while((registroCE = regCE.readLine()) != null)
	{
	datCE = registroCE.split(",");
	//crea un nuevo objeto, con sus respectivos datos, deben coincidir con el constructor
	CostoExtra CEnue = new CostoExtra(Integer.parseInt(datCE[0]),datCE[1], Double.parseDouble(datCE[2]),datCE[3].charAt(0));
	costosExtra.add(CEnue);
	}
	System.out.println(costosExtra.size()+ " Costos extra cargados.");
	}
	else
	{
	System.out.println("Archivo de Artículos no existe...!!!");
	}
	}

	static void guardarCostExtra()
	{
		String regCosto="";
		try
		{
			File archivo = new File("DatosCostosE.csv");
			//Reemplaza el contenido actual del archivo creando uno nuevo
			FileWriter salida = new FileWriter(archivo);

			for (CostoExtra costoE : costosExtra)
			{
				regCosto = (costoE.getId()+ "," + costoE.getDetalle() + "," + costoE.getImporte() + "," + costoE.getEstado() + "\n"); //poner contrabarra n al final
				salida.write(regCosto);
			}

			salida.close();
		}
		catch (Exception e)
		{
			System.out.println("Error al escribir archivo");
		}

	}

// INSUMOS

	static void leerDatosInsumo() throws IOException
	{
	String registroIns;
	String[] datIns;
	File arcArtic = new File("DatosInsumos.csv");
	if (arcArtic.exists())
	{
	FileReader archivoIns = new FileReader("DatosInsumos.csv"); // crea un file reader, lector de archivo
	BufferedReader regIns = new BufferedReader(archivoIns);
	while((registroIns = regIns.readLine()) != null)
	{
	datIns = registroIns.split(",");
	//crea un nuevo objeto, con sus respectivos datos, deben coincidir con el constructor
	Insumo InsNue = new Insumo(Integer.parseInt(datIns[0]),datIns[1], Double.parseDouble(datIns[2]),Double.parseDouble(datIns[3]), Double.parseDouble(datIns[4]));
	insumos.add(InsNue);
	}
	System.out.println(insumos.size()+ " Insumos cargados.");
	}
	else
	{
	System.out.println("Archivo de Artículos no existe...!!!");
	}
	}

	static void guardarInsumos()
	{
		String regIns ="";
		try
		{
			File archivo = new File("DatosInsumos.csv");
			//Reemplaza el contenido actual del archivo creando uno nuevo
			FileWriter salida = new FileWriter(archivo);

			for (Insumo insumosG : insumos)
			{
				regIns = (insumosG.getId()+ "," + insumosG.getInsumo()+ "," + insumosG.getPrecio() + "," + insumosG.getStock() + "," + insumosG.getStockMin() + "\n");
				salida.write(regIns);
			}

			salida.close();
		}
		catch (Exception e)
		{
			System.out.println("Error al escribir archivo");
		}

	}
	static void tomarFecha()
	{
//		fecha = new Date();

    // El formato de fecha esta¡ especificado
    String formatoFecha = "dd/MM/yy";

    // La cadena de formato de fecha se pasa como un argumento al objeto de formato de fecha
    SimpleDateFormat fechaConFormato = new SimpleDateFormat(formatoFecha);

    // El formato de fecha se aplica a la fecha actual
    fechaConFormato.format(fechaCap);

    System.out.println(fechaConFormato.format(fechaCap));
    fecha = fechaConFormato.format(fechaCap);

	}

	public static void validarFecha1(String fecha)
	{
	        boolean res = validarFecha(fecha);
	        if(res==true)
	        {
	            System.out.println("La fecha es valida");
	        }
	        else
	        {
	            System.out.println("La fecha no es valida");
	        }
	    }

		static boolean validarFecha(String fecha)
		{
	        try
	        {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");
	            // deshabilita el modo permisivo, control de dias y meses fuera de rango
	            formatoFecha.setLenient(false);
	            // si el intento de parseo genera un error, lo captura el catch y retorna false
	            // caso contrario termina retornando true, la fecha es vÃ¡lida
	            formatoFecha.parse(fecha);
	        }
	        catch (ParseException e)
	        {
	        	return false;
	        }
	        return true;
	    }





		// SERIALIZACION

		//Proveedores

		static void cheqArchivoProv () throws IOException, ClassNotFoundException
		{
			File varProv = new File("proveedores.dat");
			if (varProv.exists())
			{
				LeerProveedores();
			}
		}

		static void LeerProveedores () throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new FileInputStream ("proveedores.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Proveedor proveedor = new Proveedor ();
			try
			{
				while ((proveedor = (Proveedor)ois.readObject()) != null)
				{
					GestionProveedores.proveedores.add(proveedor);
				}
			}
			catch (EOFException eof)
			{
				//mensaje por fin de archivo
				System.out.println("Cantidad leidas de archivo de PROVEEDORES: " + GestionProveedores.proveedores.size());
			}

			ois.close ();
		} //fin lectura personas

		static void grabarProveedor () throws IOException
		{
			FileOutputStream fos = new FileOutputStream ("proveedores.dat");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			//Escribir objeto del archivo
					for (Proveedor proveedor : GestionProveedores.proveedores)
						oos.writeObject(proveedor);
					oos.close();
		}

	//Clientes
		static void cheqArchivoCli () throws IOException, ClassNotFoundException
		{
			File varProv = new File("clientes.dat");
			if (varProv.exists())
			{
				LeerCli();
			}
		}

		static void LeerCli () throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new FileInputStream ("clientes.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Cliente cliente = new Cliente ();
			try
			{
				while ((cliente = (Cliente)ois.readObject()) != null)
				{
					GestionClientes.clientes.add(cliente);
				}
			}
			catch (EOFException eof)
			{
				//mensaje por fin de archivo
				System.out.println("Cantidad leidas de archivo de CLIENTES: " + GestionClientes.clientes.size());
			}

			ois.close ();
		} //fin lectura

		static void grabarCli () throws IOException
		{
			FileOutputStream fos = new FileOutputStream ("clientes.dat");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			//Escribir objeto del archivo
					for (Cliente cliente : GestionClientes.clientes)
						oos.writeObject(cliente);
					oos.close();
		}

		//Vendedores

		static void cheqArchivoVend() throws IOException, ClassNotFoundException
		{
			File varProv = new File("vendedores.dat");
			if (varProv.exists())
			{
				LeerVend();
			}
		}


		static void LeerVend () throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new FileInputStream ("vendedores.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Vendedor vendedor = new Vendedor ();
			try
			{
				while ((vendedor = (Vendedor)ois.readObject()) != null)
				{
					GestionVendedores.vendedores.add(vendedor);
				}
			}
			catch (EOFException eof)
			{
				//mensaje por fin de archivo
				System.out.println("Cantidad leidas de archivo de VENDEDORES: " + GestionVendedores.vendedores.size());
			}

			ois.close ();
		} //fin lectura personas

		static void grabarVend () throws IOException
		{
			FileOutputStream fos = new FileOutputStream ("vendedores.dat");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			//Escribir objeto del archivo
					for (Vendedor vendedor : GestionVendedores.vendedores)
						oos.writeObject(vendedor);
					oos.close();
		}

		//compras

		static void cheqArchivoComp () throws IOException, ClassNotFoundException
		{
			File varProv = new File("compras.dat");
			if (varProv.exists())
			{
				LeerComp();
			}
		}

		static void LeerComp () throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new FileInputStream ("compras.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Compras compra = new Compras ();
			try
			{
				while ((compra = (Compras)ois.readObject()) != null)
				{
					GestionCompras.compras.add(compra);
				}
			}
			catch (EOFException eof)
			{
				//mensaje por fin de archivo
				System.out.println("Cantidad leidas de archivo de COMPRAS: " + GestionCompras.compras.size());
			}

			ois.close ();
		} //fin lectura personas

		static void grabarComp () throws IOException
		{
			FileOutputStream fos = new FileOutputStream ("compras.dat");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			//Escribir objeto del archivo
					for (Compras compra : GestionCompras.compras)
						oos.writeObject(compra);
					oos.close();
		}

		//ventas

		static void cheqArchivoVent () throws IOException, ClassNotFoundException
		{
			File varProv = new File("ventas.dat");
			if (varProv.exists())
			{
				LeerVent();
			}
		}

		static void LeerVent () throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new FileInputStream ("ventas.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Ventas venta = new Ventas ();
			try
			{
				while ((venta = (Ventas)ois.readObject()) != null)
				{
					GestionPedidos.ventas.add(venta);
				}
			}
			catch (EOFException eof)
			{
				//mensaje por fin de archivo
				System.out.println("Cantidad leidas de archivo de VENTAS: " + GestionPedidos.ventas.size() + "\n");
			}

			ois.close ();
		} //fin lectura personas

		static void grabarVent () throws IOException
		{
			FileOutputStream fos = new FileOutputStream ("ventas.dat");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			//Escribir objeto del archivo
					for (Ventas venta : GestionPedidos.ventas)
						oos.writeObject(venta);
					oos.close();
		}


		static void CheqLeerDatos () throws IOException, ClassNotFoundException
		{
			leerDatosInsumo();
			leerDatosCostoExtra();
			producto = new Producto(insumos, costosExtra);
			System.out.println(" Producto final cargado.");
			cheqArchivoCli();
			cheqArchivoProv();
			cheqArchivoVend();
			cheqArchivoComp();
			cheqArchivoVent();
		}

		static void guardarDatos () throws IOException
		{
			grabarCli();
			grabarProveedor();
			grabarVend();
			grabarComp();
			grabarVent();
			guardarCostExtra();
			guardarInsumos();

		}





	}




