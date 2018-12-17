package View;

import entidades.produtos;

public class viewProduto {
	
public viewProduto() {
		
	}

	public produtos inserir(String nome, String genero,  String corredor) {
		produtos Produtos = new produtos();
		
		Produtos.setNome(nome);
		
		Produtos.setGenero(genero);
		
		Produtos.setCorredor(corredor);
										
		return Produtos;
	}

}
