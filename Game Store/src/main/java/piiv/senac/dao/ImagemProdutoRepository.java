package piiv.senac.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import piiv.senac.entity.ImagemProd;
import piiv.senac.util.ConnectionBancoDados;



public class ImagemProdutoRepository {

	public void salvarImagensProduto(int id_produto, String[] imagens) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;

	    try {
	      for (int i=0 ; i<imagens.length ; i++) {
	    	  stmt = con.prepareStatement("insert into imagens_produto(id_produto, endereco_imagem) values(" + id_produto + ", '" + imagens[i] + "');");
	       stmt.executeUpdate();
	      }

	    } catch (SQLException ex) {
	      Logger.getLogger(ImagemProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt);
	    }
	  }

	  public List<ImagemProd> getImagensProduto(int id_produto) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    List<ImagemProd> listaImagens = new ArrayList<>();

	    try {
	    	stmt = con.prepareStatement("SELECT * FROM imagens_produto where id_produto = " + id_produto);
	      rs = stmt.executeQuery();

	      while (rs.next()) {
	        ImagemProd img = new ImagemProd();
	        img.setId_imagem_produto(rs.getInt("id_imagem_produto"));
	        img.setId_produto(id_produto);
	        img.setEndereco_imagem(rs.getString("endereco_imagem"));
	        listaImagens.add(img);
	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	    return listaImagens;
	  }

	  public void alterarImagensProduto(int id_produto) {
	    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	  }

	  public void deletarImagensProduto(int id_produto) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	      stmt = con.prepareStatement("DELETE FROM imagens_produto where id_produto = ?;");
	      stmt.setInt(1, id_produto);
	      stmt.executeUpdate();
	    } catch (SQLException ex) {
	      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	  }
	  public List<ImagemProd> getImagem() {
		    Connection con = ConnectionBancoDados.obterConexao();
		    PreparedStatement stmt = null;
		    ResultSet rs = null;

		    List<ImagemProd> listaImagens = new ArrayList<>();
		      
		    try {
		      stmt = con.prepareStatement("select imagens_produto.* from imagens_produto inner join table_Produtos on (imagens_produto.id_produto = id_produto) where table_produtos.ativo = 1 group by imagens_produto.id_produto;");
		      rs = stmt.executeQuery();

		      while (rs.next()) {
		        ImagemProd i = new ImagemProd();
		        i.setId_imagem_produto(rs.getInt("id"));
		        i.setId_produto(rs.getInt("id_produto"));
		        i.setEndereco_imagem(rs.getString("endereco_imagem"));
		        listaImagens.add(i);
		      }
		    } catch (SQLException ex) {
		      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
		    } finally {
		      ConnectionBancoDados.fecharConexao(con, stmt, rs);
		    }
		    return listaImagens;
		  }

		}
