// CLASSE DO PACOTE service DA API

package projeto.java.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmail(String destinatario, String assunto, String mensagem){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(mensagem);
            emailSender.send(message);
            System.out.println("Email enviado");
        }catch (Exception e){
            System.out.println("Erro ao enviar email"+e.getLocalizedMessage());
        }
    }
}
