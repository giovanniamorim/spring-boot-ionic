package com.udemy.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.udemy.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendeOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);

}
