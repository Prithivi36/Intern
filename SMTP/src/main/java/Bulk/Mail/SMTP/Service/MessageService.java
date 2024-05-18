package Bulk.Mail.SMTP.Service;

import Bulk.Mail.SMTP.Dto.SentLog;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    String sendMessageOne(String[] to, String subject, String body);
    SentLog  sendMessage(String subject, String body);
}
