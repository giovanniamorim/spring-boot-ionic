package com.udemy.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.domain.dto.ClienteNewDTO;
import com.udemy.cursomc.domain.enums.TipoCliente;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.resources.exception.FieldMessage;
import com.udemy.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override     
	public void initialize(ClienteInsert ann) {     }

	@Override     
	public boolean isValid(ClienteNewDTO clienteDto, ConstraintValidatorContext context) { 
 
    List<FieldMessage> list = new ArrayList<>();

   if(clienteDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(clienteDto.getCpfOuCnpj())) {
	   list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
   }
   
   if(clienteDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(clienteDto.getCpfOuCnpj())) {
	   list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
   }
   
   Cliente email = clienteRepository.findByEmail(clienteDto.getEmail());
   if(email != null) {
	   list.add(new FieldMessage("email", "O email já existe em nossos cadastros"));
   }
   
   
    for (FieldMessage e : list) {             
    	context.disableDefaultConstraintViolation();             
    	context.buildConstraintViolationWithTemplate(e.getMessage())             
    	.addPropertyNode(e.getFieldName()).addConstraintViolation();
    	}         
    return list.isEmpty();     
    } 
} 