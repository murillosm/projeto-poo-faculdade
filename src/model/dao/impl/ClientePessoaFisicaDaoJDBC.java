package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.ClientePessoaFisicaDao;
import model.entities.Cidades;
import model.entities.ClientePessoaFisica;
import model.entities.Endereco;
import model.entities.Estados;

public class ClientePessoaFisicaDaoJDBC implements ClientePessoaFisicaDao {

	private Connection conn;

	public ClientePessoaFisicaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void insert(ClientePessoaFisica obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO cliente" + "(nome_cliente, fonecliente1, fonecliente2,"
					+ " email, endereco, bairro, numero, cep,"
					+ " estado_idestado, cidade_idcidade, cpf, data_nacimento)" + "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); // retornar o id do novo
																								// vendedor inserido.

			pst.setString(1, obj.getNomeCliente());
			pst.setString(2, obj.getFoneCliente1());
			pst.setString(3, obj.getFoneCliente2());
			pst.setString(4, obj.getEmailCliente());
			pst.setString(5, obj.getEnderecoCliente().getEndereco());
			pst.setString(6, obj.getEnderecoCliente().getBairro());
			pst.setString(7, obj.getEnderecoCliente().getNumero());
			pst.setString(8, obj.getEnderecoCliente().getCep());
			pst.setInt(9, obj.getEnderecoCliente().getEstado().getIdEstado());
			pst.setInt(10, obj.getEnderecoCliente().getCidade().getIdCidade());
			pst.setString(11, obj.getCpfCliente());
			pst.setDate(12, new java.sql.Date(obj.getDataNasc().getTime()));

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdCliente(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			// DB.closeConnection();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void update(ClientePessoaFisica obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE cliente " + "SET nome_cliente = ?, fonecliente1 = ?, fonecliente2 = ?,"
					+ " email = ?, endereco = ?, bairro, numero = ?, cep = ?,"
					+ " estado_idestado = ?, cidade_idcidade = ?, cpf = ?, data_nacimento = ?" + "WHERE Idcliente = ?");

			pst.setString(1, obj.getNomeCliente());
			pst.setString(2, obj.getFoneCliente1());
			pst.setString(3, obj.getFoneCliente2());
			pst.setString(4, obj.getEmailCliente());
			pst.setString(5, obj.getEnderecoCliente().getEndereco());
			pst.setString(6, obj.getEnderecoCliente().getBairro());
			pst.setString(7, obj.getEnderecoCliente().getNumero());
			pst.setString(8, obj.getEnderecoCliente().getCep());
			pst.setInt(9, obj.getEnderecoCliente().getEstado().getIdEstado());
			pst.setInt(10, obj.getEnderecoCliente().getCidade().getIdCidade());
			pst.setString(11, obj.getCpfCliente());
			pst.setDate(12, new java.sql.Date(obj.getDataNasc().getTime()));
			pst.setInt(13, obj.getIdCliente());

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			// DB.closeConnection();
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("DELETE FROM cliente WHERE Idcliente = ?");

			pst.setInt(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closePreparedStatement(pst);
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public ClientePessoaFisica findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT idcliente, nome_cliente,cpf,data_nacimento,\r\n"
					+ "fonecliente1,fonecliente2,email,endereco, estado_idestado, cidade_idcidade,\r\n"
					+ "estado.nome_estado,cidade.nome_cidade,bairro,cep,numero\r\n"
					+ "FROM cliente INNER JOIN estado\r\n"
					+ "ON cliente.estado_idEstado = estado.idestado\r\n"
					+ "INNER JOIN cidade\r\n"
					+ "ON cliente.cidade_idCidade = cidade.idcidade\r\n"
					+ "WHERE idcliente = ? AND cpf IS NOT NULL");

			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				ClientePessoaFisica cliente = instantiateCliente(rs);
				return cliente;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<ClientePessoaFisica> findAll() {
		PreparedStatement pst = null;
        ResultSet rs = null;
        try {
        	pst = conn.prepareStatement("SELECT idcliente, nome_cliente,cpf,data_nacimento,\r\n"
					+ "fonecliente1,fonecliente2,email,endereco, estado_idestado, cidade_idcidade,\r\n"
					+ "estado.nome_estado,cidade.nome_cidade,bairro,cep,numero\r\n"
					+ "FROM cliente INNER JOIN estado\r\n" + "ON cliente.estado_idEstado = estado.idestado\r\n"
					+ "INNER JOIN cidade\r\n" + "ON cliente.cidade_idCidade = cidade.idcidade\r\n"
					+ "WHERE cpf IS NOT NULL ORDER BY nome_cliente");

            rs = pst.executeQuery();

            List<ClientePessoaFisica> list = new ArrayList<>();
            //Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
            	ClientePessoaFisica cliente = instantiateCliente(rs);
                list.add(cliente);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
	}
	
	@Override
	public List<ClientePessoaFisica> findAllCliente(String nome) {
		PreparedStatement pst = null;
        ResultSet rs = null;
        try {
        	pst = conn.prepareStatement("SELECT idcliente, nome_cliente, cpf FROM cliente "
        			+ "WHERE cliente.nome_cliente ILIKE '"+nome+"%' AND cpf IS NOT NULL ORDER BY nome_cliente");

            rs = pst.executeQuery();
            List<ClientePessoaFisica> list = new ArrayList<>();

            while (rs.next()) {
            	ClientePessoaFisica cliente = instantiateClientePesquisa(rs);
                list.add(cliente);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
	private ClientePessoaFisica instantiateClientePesquisa(ResultSet rs) throws SQLException {
		ClientePessoaFisica cliente = new ClientePessoaFisica();
		cliente.setIdCliente(rs.getInt("Idcliente"));
		cliente.setNomeCliente(rs.getString("nome_cliente"));
		cliente.setCpfCliente(rs.getString("cpf"));
		return cliente;
	}
	
	private ClientePessoaFisica instantiateCliente(ResultSet rs) throws SQLException {

		Estados estado = new Estados();
		estado.setIdEstado(rs.getInt("estado_idestado"));
		estado.setNomeEstado(rs.getString("nome_estado"));
		//estado.setUF(rs.getString("sigla_estado"));

		Cidades cidade = new Cidades();
		cidade.setIdCidade(rs.getInt("cidade_idcidade"));
		cidade.setNomeCidade(rs.getString("nome_cidade"));
		cidade.setEstado(estado);

		Endereco endereco = new Endereco();
		endereco.setEndereco(rs.getString("endereco"));
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setBairro(rs.getString("bairro"));
		endereco.setCep(rs.getString("cep"));
		endereco.setNumero(rs.getString("numero"));

		ClientePessoaFisica cliente = new ClientePessoaFisica();
		cliente.setIdCliente(rs.getInt("Idcliente"));
		cliente.setNomeCliente(rs.getString("nome_cliente"));
		cliente.setCpfCliente(rs.getString("cpf"));
		cliente.setDataNasc(rs.getDate("data_nacimento"));
		cliente.setFoneCliente1(rs.getString("fonecliente1"));
		cliente.setFoneCliente2(rs.getString("fonecliente2"));
		cliente.setEmailCliente(rs.getString("Email"));
		cliente.setEnderecoCliente(endereco);
		return cliente;
	}
}
