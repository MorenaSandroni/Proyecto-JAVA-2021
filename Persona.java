package proyecto;

import java.io.Serializable;

public abstract class Persona implements Serializable
{
	protected String razonSoc;
	protected String dni;
	protected String cuilCuit;
	protected String tel;
	protected String mail;
	protected String direccion;


	public Persona()
	{
		super();
	}

	public Persona (String razonSoc, String dni, String cuilCuit, String tel, String mail, String direccion)
	{
		this.razonSoc = razonSoc;
		this.dni = dni;
		this.cuilCuit = cuilCuit;
		this.tel = tel;
		this.mail = mail;
		this.direccion = direccion;

	}

	public String getRazonSoc() {
		return razonSoc;
	}

	public void setRazonSoc(String razonSoc) {
		this.razonSoc = razonSoc;
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuilCuit() {
		return cuilCuit;
	}

	public void setCuilCuit(String cuilCuit) {
		this.cuilCuit = cuilCuit;
	}



	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


}
