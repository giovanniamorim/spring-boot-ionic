package com.udemy.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendeOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
