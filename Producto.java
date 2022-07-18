package proyecto;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable
{
	private static final long serialVersionUID = 1L;
	public double costFinal;// son publicos paara poder acceder desde CLASE VENTA.
	public double ganancia;
	private ArrayList<Insumo> insumos = new ArrayList<Insumo>();
	private ArrayList<CostoExtra> costosExtra = new ArrayList<CostoExtra>();




	public Producto()
	{
		super();
	}

	public Producto (ArrayList insumos, ArrayList  costosExtra)
	{
		this.insumos = insumos;
		this.costosExtra = costosExtra;
	}

	public void verIns ()
	{
		System.out.println(insumos.size());
		for (int i = 0; i < insumos.size(); i++)
		{
			insumos.get(i).verIns();
		}
	}
	public void modIns ()
	{
		int opc;
		int contIn = 0;

		for (int i = 0; i < insumos.size(); i++)
		{
			contIn++;
			System.out.println("\n| Insumo numero:" + contIn);
			insumos.get(i).verIns();
		}

		opc = IBIO.inputInt("\n| Ingrese el id del insumo a modificar: ");

		for (int i = 0; i < insumos.size(); i++)
		{
			if (insumos.get(i).getId() == opc)
			{
				insumos.get(i).modIns();
			}
		}
	}

	public void verCE ()
	{
		for (int i = 0; i < costosExtra.size(); i++)
		{
			costosExtra.get(i).verCost();
		}
	}
	public void modCE ()
	{
		int opc;
		int contIn = 0;

		for (int i = 0; i < costosExtra.size(); i++)
		{
			contIn++;
			System.out.println("\n|Costo extra numero:" + contIn);
			costosExtra.get(i).verCost();
		}

		opc = IBIO.inputInt("\n| Ingrese el id del costo a modificar: ");

		for (int i = 0; i < costosExtra.size(); i++)
		{
			if (costosExtra.get(i).getId() == opc)
			{
				costosExtra.get(i).modCosto();
			}
		}
	}

	public void agCE()
	{
		System.out.println("\n|AGREGAR NUEVA VARIABLE DE COSTOS|\n");
		System.out.println("\n|Recuerde que el ID: 0 pertenece a ganancia, y el valor ingresado se utilizara como porcentaje|\n");
		char opc;
		for (int i = 0; i < costosExtra.size(); i++)
		{
			this.costosExtra.get(i).verCost();
		}
		opc = IBIO.inputChar("\n| Luego de ver los costos ya existentes desea seguir agregando el ingresado?, Ingresi S por si, de lo contrario de ENTER");
		if (opc == 'S')
		{
			int id = IBIO.inputInt("\n| ID del nuevo costo:");
			veriE(id);
			if (veriE(id) == false)
			{
				String detalle = IBIO.input("\n| Detalle: ");
				double importe = IBIO.inputDouble("\n| Importe por KG: ");
				char estado = IBIO.inputChar("\n| Estado,[A por activo, D por desactivo]: ");
				CostoExtra nuevo = new CostoExtra (id,detalle, importe, estado);
				MetodosGenerales.costosExtra.add(nuevo);

			}
			else
			{
				System.out.println("\n|El ID ingresado pertenece al siguiente costo: \n" );
				costosExtra.get(id).verCost();
			}
		}




	}

	public boolean veriE (int id) //Verificacion de existencia de costo extra
	{
		for (int i = 0; i < costosExtra.size(); i++)
		{
			if (costosExtra.get(i).getId()== id)
			{
				return true;
			}
		}
			return false;

	}

	private double calCosIns()
	{
		double costTalco = 0;
		double costGraf = 0;
		double costEnv = 0;
		double costEti = 0;
		double costFI = 0;

		for (int i = 0; i < insumos.size(); i++)
		{
			if (insumos.get(i).getId() == 00001)
			{
				costGraf = 30*insumos.get(i).getPrecio()/100;
			}

			if (insumos.get(i).getId() == 00002)
			{
				costTalco = 70*insumos.get(i).getPrecio()/100;
			}

			if (insumos.get(i).getId() == 00003)
			{
				costEnv = insumos.get(i).getPrecio()/5;//Bidones de 5kg.
			}

			if (insumos.get(i).getId() == 00004)
			{
				costEti = insumos.get(i).getPrecio()/5;//Una etiqueta por bidon.
			}
		}

		costFI = costTalco + costGraf + costEnv + costEti;
		return costFI;
		// 70 TALCO 30 GRAFITO, 1 ETIQUETA, 1 ENVASE
	}
	public double calCostF()
	{

		double costFI = calCosIns();
		// 0 -- Ganancia en numero pero luego utilizada como %.
		double aux = 0;
		double costFAux = 0;

		for (int i = 0; i < costosExtra.size(); i++)
		{
			if ((costosExtra.get(i).getId() != 0)&&(costosExtra.get(i).getEstado()=='A'))
			{
				aux = aux + costosExtra.get(i).getImporte();
			}
		}

		costFAux = costFI + aux;

		for (int i = 0; i < costosExtra.size() ; i++)
		{
			if (costosExtra.get(i).getId() == 0)
			{
				ganancia = costosExtra.get(i).getImporte()*costFAux/100;
			}
		}

		costFinal = costFAux + ganancia;
		return costFinal;
	}

	public double [] calcCostosFdes (double kg, double Vdolar, double descFdis)
	{
		calCostF();
		double [] valores = new double [2];
		double costXkg = kg*costFinal;
		double calcDes = descFdis*costXkg/100;
		double cfinal = costXkg - calcDes;
		System.out.println("\n|Costo en pesos: " + cfinal);
		double cfinalDl = cfinal/Vdolar;
		System.out.println("\n|Costo en Dolares: " + cfinalDl);
		valores [0] = cfinal;
		valores [1] = cfinalDl;
		return valores;
	}

	public double [] calcCostos (double kg, double Vdolar, double descDis)
	{
		double [] valores = new double [2];
		calCostF();
		double costXkg = kg*costFinal;
		System.out.println("\n|Costo en pesos: " + costXkg);
		double costXkgD = (kg*costFinal)/Vdolar;
		System.out.println("\n|Costo en Dolares: " + costXkgD);
		valores [0] = costXkg;
		valores [1] = costXkgD;
		return valores;

	}



}


