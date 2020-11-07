package piiv.senac.dao;
import piiv.senac.entity.table_Pergunta_Resposta;
import piiv.senac.util.ConnectionBancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerguntaRespostaRepository {


	

	  public void salvarPerguntasRespostas(int id_produto, String[] perguntas, String[] respostas) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;

	    try {
	      for (int i=0 ; i<perguntas.length ; i++) {
	       stmt = con.prepareStatement("insert into table_Pergunta_Resposta(id_produto, pergunta, resposta) values(" + id_produto + ", '" + perguntas[i] + "', '" + respostas[i] + "');");
	       stmt.executeUpdate();
	      }

	    } catch (SQLException ex) {
	      Logger.getLogger(PerguntaRespostaRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt);
	    }
	  }

	  public List<table_Pergunta_Resposta> getPergunta_Resposta(int id_produto) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    List<table_Pergunta_Resposta> listaPerguntasRespostas = new ArrayList<>();

	    try {
	      stmt = con.prepareStatement("SELECT * FROM table_Pergunta_Resposta where id_produto = " + id_produto);
	      rs = stmt.executeQuery();

	      while (rs.next()) {
	        table_Pergunta_Resposta pr = new table_Pergunta_Resposta();
	        pr.setId_perguntaResposta(rs.getInt("id_perguntaResposta"));
	        pr.setId_produto(id_produto);
	        pr.setPergunta(rs.getString("pergunta"));
	        pr.setResposta(rs.getString("resposta"));
	        listaPerguntasRespostas.add(pr);
	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(PerguntaRespostaRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	    return listaPerguntasRespostas;
	  }

	  public void deletarPerguntaResposta(int id_produto) {
	    Connection con = ConnectionBancoDados.obterConexao();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	      stmt = con.prepareStatement("DELETE FROM table_Pergunta_Resposta where id_produto = ?;");
	      stmt.setInt(1, id_produto);
	      stmt.executeUpdate();
	    } catch (SQLException ex) {
	      Logger.getLogger(PerguntaRespostaRepository.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	      ConnectionBancoDados.fecharConexao(con, stmt, rs);
	    }
	  }

	}

