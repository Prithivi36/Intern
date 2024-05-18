package Bulk.Mail.SMTP.ServiceImpl;

import Bulk.Mail.SMTP.Dto.SentLog;
import Bulk.Mail.SMTP.Entity.CustomerDetails;
import Bulk.Mail.SMTP.Repo.CustomerDbRepo;
import Bulk.Mail.SMTP.Service.MessageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
        List<String> falseEmails=new ArrayList<>();
        List<CustomerDetails> customerList=customerRepo.findAll();
        List<String> mailIds=new ArrayList<>();
        int batchCounter=0;
        for (CustomerDetails customerDetails : customerList){
            String mail=customerDetails.getMail();
            System.out.println(mail);
            if(mail.isEmpty()) {
                sendLogFailed.add(customerDetails.getFirstName());
                continue;
            }
            if(mail.contains("@")&&mail.contains(".")) {
                mailIds.add(mail);
                batchCounter++;
                sendLog.add(customerDetails.getFirstName());
            }else falseEmails.add(customerDetails.getFirstName()+" : "+customerDetails.getMail());

            if(batchCounter==100){
                sendMessageOne(mailIds.toArray(new String[0]),subject,body);
                batchCounter=0;
                mailIds.clear();
            }
        }
        if(!mailIds.isEmpty()) {
            sendMessageOne(mailIds.toArray(new String[0]),subject,body);
        }
        return new SentLog(
                sendLog,
                sendLogFailed,
                falseEmails
        );
    }
}