package br.com.kyros.cadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.kyros.cadastro.Conexao;

public class ClienteDAO {
	
	public boolean inserir(Cliente cliente) {
		
		try { 
			 
			Connection conn = Conexao.conecta();
			
			String sql = "insert into cliente values (null, ?, ?, ?, ?, ?)";
			
			PreparedStatement ppStm = conn.prepareStatement(sql);
			
			ppStm.setString(1, cliente.getNome());
			ppStm.setString(2, cliente.getCpf());
			ppStm.setString(3, cliente.getDataNasc());
			ppStm.setString(4, cliente.getEmail());
			ppStm.setString(5, cliente.getTelefone());
			
			ppStm.executeUpdate();
			
			ppStm.close();
			
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	 
	public boolean excluir(Cliente cliente) {
		 
		try {
			
			Connection conn = Conexao.conecta();
			
			String sql = "delete from cliente where id = ?";
			
			PreparedStatement ppStm = conn.prepareStatement(sql);
			 
			ppStm.setInt(1, cliente.getId());
			
			ppStm.executeUpdate();
			
			ppStm.close();
			
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean atualizar(Cliente cliente) {
		 
	try {
			
			Connection conn = new Conexao().conecta();
			
			String sql = "update cliente set nome = ?, cpf = ?, datanasc = ?, email = ?, telefone = ? where id = ?";
			
			PreparedStatement ppStm = conn.prepareStatement(sql);
			
			ppStm.setString(1, cliente.getNome());
			ppStm.setString(2, cliente.getCpf());
			ppStm.setString(3, cliente.getDataNasc());
			ppStm.setString(4, cliente.getEmail());
			ppStm.setString(5, cliente.getTelefone());
			ppStm.setInt(6, cliente.getId());
			
			ppStm.executeUpdate();
			
			ppStm.close();
			
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Cliente> buscarTodosCliente(){
		
		Cliente cli = null;
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			
			Connection conn = new Conexao().conecta();
			
			String sql = "select * from cliente";
			
			PreparedStatement ppStm = conn.prepareStatement(sql);
			
			ppStm.executeQuery(sql);
			
			ResultSet rs = ppStm.executeQuery();
			while(rs.next())
			{				
				cli = new Cliente();
				
				cli.setId(rs.getInt(1));
				cli.setNome(rs.getString(2));
				cli.setCpf(rs.getString(3));
				cli.setDataNasc(rs.getString(4));
				cli.setEmail(rs.getString(5));
				cli.setTelefone(rs.getString(6));
				
				lista.add(cli);
			}
			
			ppStm.close();
			
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		return lista;
	}
	
	public Cliente buscarClientePorID(int id) {
		
		Cliente cli = null;
		
		try {
			
			Connection conn = new Conexao().conecta();
			
			String sql = "select * from cliente where id = ?";
			
			PreparedStatement ppStm = conn.prepareStatement(sql);
			ppStm.setInt(1, id);
			 
			ResultSet rs = ppStm.executeQuery();
			
			if(rs.next())
			{
				cli = new Cliente();
				
				cli.setId(rs.getInt(1));
				cli.setNome(rs.getString(2));
				cli.setCpf(rs.getString(3));
				cli.setDataNasc(rs.getString(4));
				cli.setEmail(rs.getString(5));
				cli.setTelefone(rs.getString(6));
								
			}else
			{
				return cli;
			}
			
			ppStm.close();
			
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		return cli;
	}
	
	public boolean excluir(int id) {
		   
		return excluir(new Cliente(id, "", "", "", "", ""));
	}

}
