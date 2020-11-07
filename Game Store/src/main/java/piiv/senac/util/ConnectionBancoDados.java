package piiv.senac.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionBancoDados {


	    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/lojavirtual?useTimezone=true&serverTimezone=UTC";
	    private static final String USER = "root";
	    private static final String PASSWORD = "senac";

	    public static Connection obterConexao() {
		try {
		    Class.forName(DRIVER);
		    return DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {
		    throw new RuntimeException("Não foi possível conectar ao Banco de Dados ", e);
		}
	    }

	    public static void fecharConexao(Connection con) {
		if (con != null) {
		    try {
			con.close();
		    } catch (SQLException ex) {
			Logger.getLogger(ConnectionBancoDados.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }

	    public static void fecharConexao(Connection con, PreparedStatement stmt) {
		fecharConexao(con);
		if (stmt != null) {
		    try {
			stmt.close();
		    } catch (SQLException ex) {
			Logger.getLogger(ConnectionBancoDados.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }

	    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
		fecharConexao(con, stmt);
		if (rs != null) {
		    try {
			rs.close();
		    } catch (SQLException ex) {
			Logger.getLogger(ConnectionBancoDados.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }

	}
