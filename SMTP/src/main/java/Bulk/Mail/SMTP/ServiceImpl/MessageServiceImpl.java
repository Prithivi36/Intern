package Bulk.Mail.SMTP.ServiceImpl;

import Bulk.Mail.SMTP.Dto.SentLog;
import Bulk.Mail.SMTP.Entity.CustomerDetails;
import Bulk.Mail.SMTP.Entity.MessageLog;
import Bulk.Mail.SMTP.Repo.CustomerDbRepo;
import Bulk.Mail.SMTP.Repo.MessageRepo;
import Bulk.Mail.SMTP.Service.MessageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private JavaMailSender mailSender;
    private CustomerDbRepo customerRepo;
    private MessageRepo messageRepo;
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
    public SseEmitter sendMessage(String subject, String body) {
        SseEmitter emitter = new SseEmitter(0L);

        new Thread(() -> {
            List<String> sendLog = new ArrayList<>();
            List<String> sendLogFailed = new ArrayList<>();
            List<String> falseEmails = new ArrayList<>();
            List<CustomerDetails> customerList = customerRepo.findAll();
            List<String> mailIds = new ArrayList<>();
            int batchCounter = 0;
            int totalCustomers = customerList.size();
            int processedCustomers = 0;

            try {
                for (CustomerDetails customerDetails : customerList) {
                    String mail = customerDetails.getMail();
                    if (mail.isEmpty()) {
                        sendLogFailed.add(customerDetails.getFirstName());
                        continue;
                    }
                    if (mail.contains("@") && mail.contains(".")) {
                        mailIds.add(mail);
                        batchCounter++;
                        sendLog.add(customerDetails.getFirstName());
                    } else {
                        falseEmails.add(customerDetails.getFirstName() + " : " + customerDetails.getMail());
                    }

                    processedCustomers++;
                    double progress = (double)processedCustomers / totalCustomers* 100;
                    emitter.send(SseEmitter.event().name("progress").data("<h1>"+(int)progress + "% completed </h1>"));

                    if (batchCounter == 100) {
                        sendMessageOne(mailIds.toArray(new String[0]), subject, body);
                        batchCounter = 0;
                        mailIds.clear();
                    }
                }

                if (!mailIds.isEmpty()) {
                    sendMessageOne(mailIds.toArray(new String[0]), subject, body);
                }
                MessageLog msgLog=new MessageLog();
                msgLog.setTime(LocalTime.now());
                msgLog.setDate(LocalDate.now());
                msgLog.setMessage(body);
                messageRepo.save(msgLog);
                emitter.send(SseEmitter.event().name("complete").data(new SentLog(sendLog, sendLogFailed, falseEmails)));
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }
}
