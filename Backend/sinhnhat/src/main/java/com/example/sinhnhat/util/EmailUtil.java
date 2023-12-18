package com.example.sinhnhat.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	  private JavaMailSender javaMailSender;
	public void sendOtpEmail(String email, String verificationCode) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        String emailContent = "<div>"
        		  + "<p>Mã OTP cua ban la: " + verificationCode + "</p>"
        		  + "<p>Gmail cua ban la: " + email + "</p>"
                + "<a href=\"http://localhost:8080/api/user/verify-account?email=" + email + "&otp=" + verificationCode + "\" target=\"_blank\">Verify OTP HERE</a>"
                + "</div>";
        

        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP Để LOGIN");
        mimeMessageHelper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);
    }
	}