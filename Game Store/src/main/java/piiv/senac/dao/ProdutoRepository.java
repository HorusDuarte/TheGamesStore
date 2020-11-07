package piiv.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import piiv.senac.entity.table_Produtos;
import piiv.senac.util.ConnectionBancoDados;

public class ProdutoRepository{
	
	public List<table_Produtos> getTable_Produtos() {

	    Connection con = ConnectionBancoDados.obterConexao();
	    
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    List<table_Produtos> produtos = new ArrayList<>();

	    try {
	      stmt = con.prepareStatement("SELECT * FROM table_Produtos where ativo = 1;");
	      rs = stmt.executeQuery();

	      
	      /*
	       *  record.setDescricao(table_Produtos.getDescricao());
					 record.setPreco_custo(table_Produtos.getPreco_custo());
					 record.setPreco_venda(table_Produtos.getPreco_venda());
					 record.setQuantidade(table_Produtos.getQuantidade());
					 record.setCodigo_produto(table_Produtos.getCodigo_produto());
					 record.setEndereco_imagem(table_Produtos.getEndereco_imagem());
					 record.setEndereco_imagem2(table_Produtos.getEndereco_imagem2());
					 record.setEndereco_imagem3(table_Produtos.getEndereco_imagem3());
					 record.setEndereco_imagem4(table_Produtos.getEndereco_imagem4());
					 record.setEndereco_imagem5(table_Produtos.getEndereco_imagem5());
	       */
	      while (rs.next()) {
	        table_Produtos p = new table_Produtos();
	        p.setId_produto(rs.getInt("id_produto"));
	        p.setDescricao(rs.getString("descricao"));
	        p.setPreco_custo(rs.getDouble("preco_custo"));
	        p.setPreco_venda(rs.getDouble("preco_venda"));	        
	        p.setQuantidade(rs.getInt("quantidade"));
	        p.setCodigo_produto(rs.getString("codigo_produto"));
	        produtos.add(p);
	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	    return produtos;
	  }

	  public void inativarProduto(int id_produto) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;

	    try {
	      stmt = con.prepareStatement("update table_Produtos set ativo = 0 where id_produto = ?");

	      stmt.setInt(1, id_produto);

	      stmt.executeUpdate();
	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt);
	    }
	  }

	  public void salvarProduto(table_Produtos p) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;

	    try {
	      stmt = con.prepareStatement("insert into table_Produtos (descricao, preco_custo, preco_venda, quantidade, codigo_produto) values (?, ?, ?, ?, ?);");

	      stmt.setString(1, p.getDescricao());
	      stmt.setDouble(2, p.getPreco_custo());
	      stmt.setDouble(3, p.getPreco_venda());
	      stmt.setInt(4, p.getQuantidade());
	      stmt.setString(5, p.getCodigo_produto());
	     
	      stmt.executeUpdate();
	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	    	ConnectionBancoDados.fecharConexao(con, stmt);
	    }
	  }

	  public int getUltimoProduto() {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    int id_produto = -1;

	    try {
	      stmt = con.prepareStatement("SELECT MAX(id_produto) as id_produto FROM table_Produtos;");
	      rs = stmt.executeQuery();

	      while (rs.next()) {
	        id_produto = rs.getInt("id_produto");

	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(ConnectionBancoDados.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	    	ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	    return id_produto;
	  }

	  public table_Produtos getProdutos(int id_produto) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    table_Produtos p = new table_Produtos();

	    try {
	      stmt = con.prepareStatement("SELECT * FROM table_Produtos WHERE id_produto = " + id_produto);
	      rs = stmt.executeQuery();

	      rs.next();

	        p.setDescricao(rs.getString("descricao"));
	        p.setPreco_custo(rs.getDouble("preco_custo"));
	        p.setPreco_venda(rs.getDouble("preco_venda"));	        
	        p.setQuantidade(rs.getInt("quantidade"));
	        p.setCodigo_produto(rs.getString("codigo_produto"));

	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	    	ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	    return p;
	  }

	  public void alterarProduto(table_Produtos p) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;

	    try {
	      stmt = con.prepareStatement("update table_Produtos set descricao = ?, preco_custo = ?, preco_venda = ?, quantidade = ?, codigo_produto = ? where id_produto = ?;");
	      
	      stmt.setString(1, p.getDescricao());
	      stmt.setDouble(2, p.getPreco_custo());
	      stmt.setDouble(3, p.getPreco_venda());
	      stmt.setInt(4, p.getQuantidade());
	      stmt.setString(5, p.getCodigo_produto());
	      stmt.setInt(6, p.getId_produto());
	      
	      stmt.executeUpdate();
	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	    	ConnectionBancoDados.fecharConexao(con, stmt);
	    }
	  }

	}

	
	
	
	
