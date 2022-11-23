package app;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.dao.ClientePessoaFisicaDao;
import model.dao.ClientePessoaJuridoDao;
import model.dao.DaoFactory;
import model.entities.Cidades;
import model.entities.ClientePessoaFisica;
import model.entities.ClientePessoaJuridico;
import model.entities.Endereco;
import model.entities.Estados;

public class Test {

	public static void main(String[] args) {
		
		ClientePessoaFisicaDao clienteDao = DaoFactory.createPessoaFisicaDao();
		ClientePessoaJuridoDao clienteDao2 = DaoFactory.createPessoaJuridicaDao();
		
//		Estados estado = new Estados(9);
//		Cidades cidade = new Cidades(894);
//		Endereco endereco = new Endereco("endereço","00000000", "endereco","s/n", cidade,estado);
//		ClientePessoaFisica novoCliente = new ClientePessoaFisica("mario ","000000", null,"email@hotmail.com", endereco , "00000000000",new Date());
//		clienteDao.insert(novoCliente);
//		System.out.println("Inserted! New id = " + novoCliente.getIdCliente());
		
//		Endereco endereco = new Endereco("endereço","00000000", "bandeiras","s/n", 894,9);
//		ClientePessoaJuridico novoCliente2 = new ClientePessoaJuridico("murillo","000000", null,"email@hotmail.com", endereco , "razao","00000000000000");
//		clienteDao2.insert(novoCliente2);
//		System.out.println("Inserted! New id = " + novoCliente2.getIdCliente());
		
//		ClientePessoaFisica cliente = clienteDao.findById(5);
//		
//		System.out.println(cliente);
		
		/*List<ClientePessoaFisica> list = clienteDao.findAll();
		for (ClientePessoaFisica cliente : list) {
			System.out.println(cliente);
		}*/
		
		List<ClientePessoaJuridico> list = clienteDao2.findAll();
		for (ClientePessoaJuridico cliente : list) {
			System.out.println(cliente);
		}
		

	}

}