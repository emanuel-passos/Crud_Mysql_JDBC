package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		
		java.sql.Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			 
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}				
		 
	}
	
	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Resultset rset = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = conn.prepareStatement(sql);
			
			rset = (Resultset) pstm.executeQuery();
			
				while(((ResultSet) rset).next()) {
					
					Contato contato = new Contato();
					
					contato.setId(((ResultSet) rset).getInt("id"));
					contato.setNome(((ResultSet) rset).getString("nome"));
					contato.setIdade(((ResultSet) rset).getInt("idade"));
					contato.setDataCadastro(((ResultSet) rset).getDate("dataCadastro"));
					
					contatos.add(contato);
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null) {
					((Connection) rset).close();
				}
				
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return contatos;
		
	}

}
