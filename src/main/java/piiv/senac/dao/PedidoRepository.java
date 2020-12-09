package piiv.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import piiv.senac.entity.ItensCompra;
import piiv.senac.entity.Pedido;
import piiv.senac.entity.table_Produtos;
import piiv.senac.util.ConnectionBancoDados;

public class PedidoRepository {

	public List<Pedido> getPedido(String cpf) {
		Connection con = ConnectionBancoDados.obterConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Pedido> listPedidos = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM table_Pedidos WHERE CPF = '" + cpf+"'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Pedido p = new Pedido();
				p.setIdPedido(rs.getInt("idPedido"));
				p.setDtCompra(rs.getTimestamp("dtCompra").toLocalDateTime().minusHours(0));
				p.setTotalPedido(rs.getDouble("totalPedido"));
				p.setFormaPagamento(rs.getString("formaPagamento"));
				p.setNumeroPedido(rs.getInt("numeroPedido"));
				p.setItensCompra(getProdutosPedido(p.getIdPedido()));
				listPedidos.add(p);
			}
			return listPedidos;

		} catch (SQLException ex) {
			Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionBancoDados.fecharConexao(con, stmt, rs);
		}
		return listPedidos;
	}

	public List<ItensCompra> getProdutosPedido(int idPedido) {
		Connection con = ConnectionBancoDados.obterConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItensCompra item = new ItensCompra();
		List<ItensCompra> listItens = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM table_itenspedidos WHERE idPedido = " + idPedido);
			rs = stmt.executeQuery();

			while (rs.next()) {
				item.setQuantidade(rs.getInt("quantidade"));
				item.setValorTotal(rs.getDouble("valorTotal"));
				table_Produtos tbProdutos = new table_Produtos();
				ProdutoRepository produtoRepository = new ProdutoRepository();
				tbProdutos = produtoRepository.getProdutos(rs.getInt("id_produto"));
			//	tbProdutos.setPreco_venda(rs.getDouble("preco_venda"));
			//	tbProdutos.setDescricao(rs.getString("descricao"));
				item.setTable_Produtos(tbProdutos);
				listItens.add(item);
			}
			return listItens;
		} catch (SQLException ex) {
			Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionBancoDados.fecharConexao(con, stmt, rs);
		}
		return listItens;
	}

	public void alterarProduto(table_Produtos p) {
		Connection con = ConnectionBancoDados.obterConexao();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"update table_Produtos set descricao = ?, preco_custo = ?, preco_venda = ?, quantidade = ?, codigo_produto = ?, descricao_detalhada= ? where id_produto = ?;");

			stmt.setString(1, p.getDescricao());
			stmt.setDouble(2, p.getPreco_custo());
			stmt.setDouble(3, p.getPreco_venda());
			stmt.setInt(4, p.getQuantidade());
			stmt.setString(5, p.getCodigo_produto());
			stmt.setInt(6, p.getId_produto());
			stmt.setString(7, p.getDescricao_detalhada());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionBancoDados.fecharConexao(con, stmt);
		}
	}

	public void salvar(Pedido p) {
		Connection con = ConnectionBancoDados.obterConexao();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("insert into table_Pedidos values(default, ?,default,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, p.getCliente().getCpf());
			stmt.setDouble(2, p.getTotalPedido());
			stmt.setString(3, "Boleto");
			p.setRandomNumPedido();
			stmt.setInt(4, p.getNumeroPedido());
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int idPedido = generatedKeys.getInt(1);
				p.getItensCompra().forEach(item -> salvarItens(item, idPedido));
			}

		} catch (SQLException ex) {
			Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionBancoDados.fecharConexao(con, stmt);
		}
	}

	public void salvarItens(ItensCompra itensCompra, int idPedido) {
		Connection con = ConnectionBancoDados.obterConexao();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into table_itenspedidos values(?,?,?,?,?)");
			stmt.setInt(1, idPedido);
			stmt.setInt(2, itensCompra.getTable_Produtos().getId_produto());
			stmt.setDouble(3, itensCompra.getTable_Produtos().getPreco_venda());
			stmt.setInt(4, itensCompra.getQuantidade());
			stmt.setDouble(5, itensCompra.getValorTotal());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionBancoDados.fecharConexao(con, stmt);
		}
	}

}