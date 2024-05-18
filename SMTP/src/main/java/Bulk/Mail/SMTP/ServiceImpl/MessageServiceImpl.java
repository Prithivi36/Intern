package Bulk.Mail.SMTP.ServiceImpl;

import Bulk.Mail.SMTP.Dto.CustomerDto;
import Bulk.Mail.SMTP.Dto.SentLog;
import Bulk.Mail.SMTP.Entity.CustomerDetails;
import Bulk.Mail.SMTP.Repo.CustomerDbRepo;
import Bulk.Mail.SMTP.Service.MessageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private JavaMailSender mailSender;
    private CustomerDbRepo customerRepo;
    private ModelMapper modelMapper;

    @Override
    public String sendMessageOne(String[] to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        return "";
    }

    @Override
    public SentLog sendMessage(String subject, String body) {
        List<String> sendLog=new ArrayList<>();
        List<String> sendLogFailed=new ArrayList<>();
        List<CustomerDetails> customerList=customerRepo.findAll();
        for (CustomerDetails customerDetails : customerList){
            String mail=customerDetails.getMail();
            System.out.println(mail);
            if(mail.isEmpty()) {
                sendLogFailed.add(customerDetails.getFirstName());
                continue;
            }
//            sendMessageOne(mail,subject,body);
            sendLog.add(customerDetails.getFirstName());
        }
        return new SentLog(
                sendLog,
                sendLogFailed
        );
    }
}