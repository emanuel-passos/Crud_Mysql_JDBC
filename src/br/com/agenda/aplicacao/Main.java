package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatooDao = new ContatoDAO();
		
		Contato contato = new Contato();
		
		contato.setNome("Emanuel");
		contato.setIdade(23);
		contato.setDataCadastro(new Date());
		
		contatooDao.save(contato);

	}

}
