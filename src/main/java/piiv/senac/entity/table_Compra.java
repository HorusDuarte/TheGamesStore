package piiv.senac.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class table_Compra implements Serializable{
	
	public table_Compra() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	
	private int id_compra;
	private table_Cliente cliente;
	private Date dataCompra = new Date();
	private String formaPagamento;
	private Double valorTotal;

	
}
