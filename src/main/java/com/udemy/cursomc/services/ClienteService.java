package com.udemy.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Cidade;
import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.domain.Endereco;
import com.udemy.cursomc.domain.dto.ClienteDTO;
import com.udemy.cursomc.domain.dto.ClienteNewDTO;
import com.udemy.cursomc.domain.enums.TipoCliente;
import com.udemy.cursomc.repositories.CidadeRepository;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.repositories.EnderecoRepository;
import com.udemy.cursomc.services.exceptions.DataIntegrityException;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder bc;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Cliente cliente = clienteRepository.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Não foi encontrada nenhum cliente com o id: " + id);
		}
		
		return cliente;
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.save(cliente.getEnderecos());
		return cliente;
	}
	

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que tenha pedidos");
		}
		
	}

	public List<Cliente> findAll() {
		return  clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(),clienteDTO.getNome(), null, clienteDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cliente = new Cliente(null,clienteDTO.getNome(), bc.encode(clienteDTO.getSenha()) ,clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(), null, TipoCliente.toEnum(clienteDTO.getTipo()));
		Cidade cidade = cidadeRepository.findOne(clienteDTO.getCidadeId());
		Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), 
				clienteDTO.getBairro(), clienteDTO.getCep(), cliente,  cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteDTO.getTelefone1());
		if(clienteDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteDTO.getTelefone2());
		}
		if(clienteDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteDTO.getTelefone3());
		}
		
		return cliente;
	}

}
