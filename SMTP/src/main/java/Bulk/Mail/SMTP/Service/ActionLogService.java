package Bulk.Mail.SMTP.Service;

import Bulk.Mail.SMTP.Entity.MessageLog;
import org.springframework.stereotype.Service;

@Service
public interface ActionLogService {
    String getActionLog(MessageLog msgLog);
}
