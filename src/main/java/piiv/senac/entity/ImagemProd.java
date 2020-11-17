package piiv.senac.entity;


import lombok.Data;



@Data
public class ImagemProd {
	    
	    private int id_imagem_produto;
	    private int id_produto;
	    private String endereco_imagem;
	    
	    public ImagemProd() {}
	    
	    
	    public ImagemProd(int id_imagem_produto, int id_produto, String endereco_imagem) {
	    	this.id_produto = id_produto;
	    	this.id_produto = id_produto;
	    	this.endereco_imagem = endereco_imagem;
	        }
	    
	    public ImagemProd(int id_produto, String endereco_imagem) {
	    	this.id_produto = id_produto;
	    	this.endereco_imagem = endereco_imagem;
	        }

	
	    @Override
	    public String toString() {
	    	return "ImagemProd{" + "id_imagem_produto=" + id_imagem_produto + ", id_produto=" + id_produto + ", endereco_imagem=" + endereco_imagem + '}';
	    }

	}
