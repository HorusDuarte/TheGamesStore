package piiv.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import piiv.senac.entity.table_Cliente;
import piiv.senac.entity.table_Produtos;
import piiv.senac.util.ConnectionBancoDados;

public class ClientesRepository {

public void aaaClientes(table_Cliente u) {
    Connection con = ConnectionBancoDados.obterConexao();
    PreparedStatement stmt = null;

    try {
        stmt = con.prepareStatement("insert into table_Cliente (nome_completo,cpf,endereco,cep,usuario,senha) values (?, ?,?,?,?,?);");

        stmt.setString(1, u.getNome_completo());
        stmt.setString(2, u.getCpf());
        stmt.setString(3, u.getEndereco());
        stmt.setString(4, u.getCep());
        stmt.setString(5, u.getUsuario());
        stmt.setString(6, u.getSenha());
        
      
        stmt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioRepository.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        ConnectionBancoDados.fecharConexao(con, stmt);
    }
}

public List<table_Cliente> getTable_Cliente() {

    Connection con = ConnectionBancoDados.obterConexao();
    
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<table_Cliente> clientes = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM table_Cliente ");
      rs = stmt.executeQuery();

      while (rs.next()) {
    	 table_Cliente p = new table_Cliente();
    	 
    	 p.setNome_completo(rs.getString("nome_completo"));

    	 p.setCpf(rs.getString("cpf"));
    	 p.setEndereco(rs.getString("endereco"));
    	 p.setCep(rs.getString("cep"));
    	 p.setUsuario(rs.getString("usuario"));
    	 p.setSenha(rs.getString("senha"));
    	 
     
        clientes.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConnectionBancoDados.fecharConexao(con, stmt, rs);
    }
    return clientes;
  }

	
	
}