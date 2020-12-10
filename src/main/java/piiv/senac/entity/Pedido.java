package piiv.senac.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import lombok.Data;

@Data
public class Pedido {
	private int idPedido;
	private int numeroPedido;
	private table_Cliente cliente;
	private Double totalPedido;
	private List<ItensCompra> itensCompra;
	private LocalDateTime dtCompra;
	private String formaPagamento;
	private String status;

	public void setRandomNumPedido() {
		this.numeroPedido = new Random().nextInt(10000);
	}
}