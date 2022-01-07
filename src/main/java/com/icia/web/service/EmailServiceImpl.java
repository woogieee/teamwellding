package com.icia.web.service;

import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.icia.web.model.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService
{
	@Inject
	JavaMailSender mailSenderGoogle;
	
	@Override
	public void sendMail(EmailDTO dto) 
	{
		try
		{
			MimeMessage msg = mailSenderGoogle.createMimeMessage(); //이메일 객체
			//수신자 정보
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			//발신자 이메일주소, 이름
			msg.addFrom(new InternetAddress[] {
				new InternetAddress(dto.getSenderMail(), dto.getSenderName())
			});
			
			msg.setSubject("[ "+dto.getSenderName()+" ]님의 견적요청", "utf-8"); //제목
			msg.setText(dto.getSenderName() + " 님의 연락처 : "+ dto.getSenderNumber() +
					"\n\r" + dto.getSenderName() + " 님의 메일주소 : " + dto.getSenderMail() + 
					"\n\r" + "\r\n 요청사항: " + dto.getMessage(), "utf-8"); //텍스트
			mailSenderGoogle.send(msg); //이메일 발신
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int checkMail(EmailDTO dto) {
		double randomVal = Math.random();
		int check = (int)(randomVal *999999)+100000;
		
		try
		{
			MimeMessage msg = mailSenderGoogle.createMimeMessage(); //이메일 객체
			//수신자 정보
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			//발신자 이메일주소, 이름
			msg.addFrom(new InternetAddress[] {
				new InternetAddress(dto.getSenderMail(), dto.getSenderName())
			});
			
			msg.setSubject("웰딩(Wellding) 인증번호 입니다.", "utf-8"); //제목
			msg.setText("인증번호 [ " + check + " ]를 입력란에 입력해주세요" , "utf-8"); //텍스트
			mailSenderGoogle.send(msg); //이메일 발신
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return check;
	}
}
