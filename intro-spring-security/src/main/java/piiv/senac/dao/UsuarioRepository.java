package piiv.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import piiv.senac.entity.table_Usuarios;
import piiv.senac.util.ConnectionBancoDados;

public class UsuarioRepository {

    public List<table_Usuarios> getTable_Usuarios() {

        Connection con = ConnectionBancoDados.obterConexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<table_Usuarios> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM table_Usuarios where ativo = 1;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                table_Usuarios u = new table_Usuarios();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));;
                usuarios.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBancoDados.fecharConexao(con, stmt, rs);
        }
        return usuarios;
    }

    public void inativarUsuario(int id) {
        Connection con = ConnectionBancoDados.obterConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update table_usuarios set ativo = 0 where id_usuario = ?");

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBancoDados.fecharConexao(con, stmt);
        }
    }

    public void salvarUsuario(table_Usuarios u) {
        Connection con = ConnectionBancoDados.obterConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into table_usuarios (username, password, email, role) values (?, ?, ?, ?);");

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getRole());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBancoDados.fecharConexao(con, stmt);
        }
    }

    public int getUltimoUsuario() {
        Connection con = ConnectionBancoDados.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int usuario_id = 0;

        try {
            stmt = con.prepareStatement("SELECT MAX(id_usuario) as id_usuario FROM table_Usuarios;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario_id = rs.getInt("id_usuario");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBancoDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBancoDados.fecharConexao(con, stmt, rs);
        }
        return usuario_id;
    }

    public table_Usuarios getUsuarios(int id_usuario) {
        Connection con = ConnectionBancoDados.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        table_Usuarios u = new table_Usuarios();

        try {
            stmt = con.prepareStatement("SELECT * FROM table_Usuarios WHERE id_usuario = " + id_usuario);
            rs = stmt.executeQuery();

            rs.next();

            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRole(rs.getString("role"));

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBancoDados.fecharConexao(con, stmt, rs);
        }
        return u;
    }

    public void alterarUsuario(table_Usuarios u) {
        Connection con = ConnectionBancoDados.obterConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update table_Usuarios set username = ?, password = ?, email = ?, role = ? where id_usuario = ?;");

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getRole());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBancoDados.fecharConexao(con, stmt);
        }
    }

}


