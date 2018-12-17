package entidades;

public class Sede {
	private int ID;
	private String Bairro;
	private String Cep;


	public Sede() {
		
	}
	
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getBairro() {
		return Bairro;
	}


	public void setBairro(String bairro) {
		Bairro = bairro;
	}


	public String getCep() {
		return Cep;
	}


	public void setCep(String cep) {
		Cep = cep;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Bairro == null) ? 0 : Bairro.hashCode());
		result = prime * result + ((Cep == null) ? 0 : Cep.hashCode());
		result = prime * result + ID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sede other = (Sede) obj;
		if (Bairro == null) {
			if (other.Bairro != null)
				return false;
		} else if (!Bairro.equals(other.Bairro))
			return false;
		if (Cep == null) {
			if (other.Cep != null)
				return false;
		} else if (!Cep.equals(other.Cep))
			return false;
		if (ID != other.ID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sede [ID=" + ID + ", Bairro=" + Bairro + ", Cep=" + Cep + "]";
	}
	public Sede(int iD, String bairro, String cep) {
		super();
		ID = iD;
		Bairro = bairro;
		Cep = cep;
	}
	

}
