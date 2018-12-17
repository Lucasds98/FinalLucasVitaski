package View;

import entidades.Sede;

public class viewSede {
			
		private int controleID = 0;
		
		public Sede inserir(String bairro, String cep) {
			
			Sede donoSede = new Sede();
			
			donoSede.setBairro(bairro);
			
			donoSede.setCep(cep);
			
			donoSede.setID(controleID);
			controleID++;
			
			return donoSede;		
		}
	

}
