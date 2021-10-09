package com.marcelocosta.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.marcelocosta.cursomc.domain.Pedido;

public interface EmailService {
	
	//contrato, quis operacoes o seervico de email deve oferecer
	
	void sendOrderConfirmationEmail(Pedido obj);//email de confirmacao de emial recebendo um pedido
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
		

}
