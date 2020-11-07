package piiv.senac.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class table_Cliente {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id_cliente;
		private String nome_completo;
	    private String cpf;
	    private String endereco;
	    private String cep;
	    private String usuario;
	    private String senha;
	}


