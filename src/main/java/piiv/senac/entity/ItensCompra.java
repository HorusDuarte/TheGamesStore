package piiv.senac.entity;

import java.io.Serializable;

import lombok.Data;


public class ItensCompra implements Serializable {

	public ItensCompra() {
		super();
	}
	
	public Long getId_itensCompra() {
		return id_itensCompra;
	}
	public void setId_itensCompra(Long id_itensCompra) {
		this.id_itensCompra = id_itensCompra;
	}
	public table_Produtos getTable_Produtos() {
		return table_Produtos;
	}
	public void setTable_Produtos(table_Produtos table_Produtos) {
		this.table_Produtos = table_Produtos;
	}
	public table_Compra getTable_Compra() {
		return table_Compra;
	}
	public void setTable_Compra(table_Compra table_Compra) {
		this.table_Compra = table_Compra;
	}
	public Integer getQuantidade() {
		if(quantidade == null) {
			quantidade = 0;
		}
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public static long getSerailversionuid() {
		return serailVersionUID;
	}

	private static final long serailVersionUID= 1L;
	
	private Long id_itensCompra;	
	private table_Produtos table_Produtos;
	private table_Compra table_Compra;
	private Integer quantidade;
	private double valorUnitario = 0.;
	private double valorTotal = 0.;
	
}
