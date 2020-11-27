package piiv.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import piiv.senac.entity.table_Cliente;
import piiv.senac.entity.table_Produtos;
import piiv.senac.util.ConnectionBancoDados;

public class ClientesRepository {

public void salvar(table_Cliente c) {
    Connection con = ConnectionBancoDados.obterConexao();
    PreparedStatement stmt = null;

    try {
        stmt = con.prepareStatement("insert into table_Cliente (nome_completo, cpf, endereco, cep, bairro, cidade, uf, usuario, senha) values (?,?,?,?,?,?,?,?,?);");

        stmt.setString(1, c.getNome_completo());
        stmt.setString(2, c.getCpf());
        stmt.setString(3, c.getEndereco());
        stmt.setString(4, c.getCep());
        stmt.setString(5, c.getBairro());
        stmt.setString(6, c.getCidade());
        stmt.setString(7, c.getUf());   
        stmt.setString(8, c.getUsuario());
        stmt.setString(9, new BCryptPasswordEncoder().encode(c.getSenha()));
        
      
        stmt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ClientesRepository.class.getName()).log(Level.SEVERE, null, ex);
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
    	 p.setBairro(rs.getString("bairro"));
    	 p.setUf(rs.getString("uf"));
    	 p.setCidade(rs.getString("cidade"));

    	 p.setUsuario(rs.getString("usuario"));
    	 p.setSenha(rs.getString("senha"));
    	 
     
        clientes.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ClientesRepository.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConnectionBancoDados.fecharConexao(con, stmt, rs);
    }
    return clientes;
  }

public table_Cliente getClientes(int id_cliente) {
    Connection con = ConnectionBancoDados.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    table_Cliente c = new table_Cliente();

    try {
      stmt = con.prepareStatement("SELECT * FROM table_Cliente WHERE id_cliente = " + id_cliente);
      rs = stmt.executeQuery();

      
      rs.next();
      
      c.setId_cliente(rs.getInt("id_cliente"));
      c.setCep(rs.getString("cep"));
      c.setEndereco(rs.getString("endereco"));
      c.setBairro(rs.getString("bairro"));
      c.setCidade(rs.getString("cidade"));
      c.setUf(rs.getString("uf"));
      c.setNome_completo(rs.getString("nome_completo"));
      c.setSenha(rs.getString("senha"));
      c.setUsuario(rs.getString("usuario"));
     

    } catch (SQLException ex) {
      Logger.getLogger(ClientesRepository.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
    	ConnectionBancoDados.fecharConexao(con, stmt, rs);
    }
    return c;
  }

public void alterarCliente(table_Cliente c) {
    Connection con = ConnectionBancoDados.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update table_Cliente set nome_completo = ?, endereco = ?, cep = ?, bairro=?, cidade=?, uf=?, usuario= ?, senha= ? where id_cliente = ?;");
      
      
      stmt.setString(1, c.getNome_completo());
      stmt.setString(2, c.getEndereco());
      stmt.setString(3, c.getCep());
      stmt.setString(4, c.getBairro());
      stmt.setString(5, c.getCidade());
      stmt.setString(6, c.getUf());
      stmt.setString(7, c.getUsuario());
      stmt.setString(8, new BCryptPasswordEncoder().encode(c.getSenha()));
      stmt.setInt(9, c.getId_cliente());

      
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ClientesRepository.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
    	ConnectionBancoDados.fecharConexao(con, stmt);
    }
  }

	
	
}