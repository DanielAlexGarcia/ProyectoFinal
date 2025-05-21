package Modelo;

public class Donador {
	private String colonia, calle, numExt, numInt, numContacto, email;
	private int ID, IDTipoDonador;
	private Integer IDRelacionUni, IDClase, IDProgDonacion;
	
	public Donador(String colonia, String calle, String numExt,String numInt, String numCont, String email, int id, int idRelacion, int idtiporelacion,int idClase, int idproDonacion) {
		this.colonia = colonia;
		this.calle = calle;
		this.numExt = numExt;
		this.numInt = numInt;
		this.numContacto = numCont;
		this.email = email;
		this.ID = id;
		this.IDRelacionUni = idRelacion;
		this.IDTipoDonador = idtiporelacion;
		this.IDClase = idClase;
	}
	// Constructor sin id
	public Donador(String colonia, String calle, String numExt,String numInt, String numCont, String email, int idRelacion, int idtiporelacion,int idClase, int idproDonacion) {
		this.colonia = colonia;
		this.calle = calle;
		this.numExt = numExt;
		this.numInt = numInt;
		this.numContacto = numCont;
		this.email = email;
		this.IDRelacionUni = idRelacion;
		this.IDTipoDonador = idtiporelacion;
		this.IDClase = idClase;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumExt() {
		return numExt;
	}

	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}

	public String getNumInt() {
		return numInt;
	}

	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}

	public String getNumContacto() {
		return numContacto;
	}

	public void setNumContacto(String numContacto) {
		this.numContacto = numContacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Integer getIDRelacionUni() {
		return IDRelacionUni;
	}

	public void setIDRelacionUni(Integer iDRelacionUni) {
		IDRelacionUni = iDRelacionUni;
	}

	public int getIDTipoDonador() {
		return IDTipoDonador;
	}

	public void setIDTipoDonador(int iDTipoDonador) {
		IDTipoDonador = iDTipoDonador;
	}

	public Integer getIDClase() {
		return IDClase;
	}

	public void setIDClase(Integer iDClase) {
		IDClase = iDClase;
	}

	public Integer getIDProgDonacion() {
		return IDProgDonacion;
	}

	public void setIDProgDonacion(Integer iDProgDonacion) {
		IDProgDonacion = iDProgDonacion;
	}

	@Override
	public String toString() {
		return "Donador{"+ '\''
				+ "ID donador: " + ID + '\''
				+ "Colonia: " + colonia+ '\''
				+ "Calle: " + calle+ '\''
				+ "Numero exterior: " +numExt+ '\''
				+ "Numero interior: " +numInt+ '\''
				+ "Telefono de contacto: "+ numContacto+ '\''
				+ "Email: " +email+ '\''
				+ "ID relacionUniversidad: "+ IDRelacionUni+ '\''
				+ "ID tipo de donador: "+IDTipoDonador+ '\''
				+ "ID de clase: " + IDClase+ '\''
				+ "ID programa de donacion: " +IDProgDonacion+ '\'' +" }";
				
		
	}
}
