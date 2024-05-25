package Bulk.Mail.SMTP.Service;

import Bulk.Mail.SMTP.Dto.SentLog;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public interface MessageService {
    String sendMessageOne(String[] to, String subject, String body);
    SseEmitter sendMessage(String subject, String body);
}
