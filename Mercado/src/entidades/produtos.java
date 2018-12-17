package entidades;

public class produtos {
	private int ID;
	private String Nome;
	private String Genero;
	private String Corredor;
	private Sede Sede;
	
	public Sede getSede() {
		return Sede;
	}

	public void setSede(Sede sede) {
		Sede = sede;
	}

	public produtos() {
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getGenero() {
		return Genero;
	}
	public void setGenero(String genero) {
		Genero = genero;
	}
	public String getCorredor() {
		return Corredor;
	}
	public void setCorredor(String corredor) {
		Corredor = corredor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Corredor == null) ? 0 : Corredor.hashCode());
		result = prime * result + ((Genero == null) ? 0 : Genero.hashCode());
		result = prime * result + ID;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
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
		produtos other = (produtos) obj;
		if (Corredor == null) {
			if (other.Corredor != null)
				return false;
		} else if (!Corredor.equals(other.Corredor))
			return false;
		if (Genero == null) {
			if (other.Genero != null)
				return false;
		} else if (!Genero.equals(other.Genero))
			return false;
		if (ID != other.ID)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "produtos [ID=" + ID + ", Nome=" + Nome + ", Genero=" + Genero + ", Corredor=" + Corredor + "]";
	}

	public produtos(int iD, String nome, String genero, String corredor) {
		super();
		ID = iD;
		Nome = nome;
		Genero = genero;
		Corredor = corredor;
	}



	

}
