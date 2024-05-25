package Bulk.Mail.SMTP.ServiceImpl;

import Bulk.Mail.SMTP.Entity.MessageLog;
import Bulk.Mail.SMTP.Repo.MessageRepo;
import Bulk.Mail.SMTP.Service.ActionLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActionLogServiceImpl implements ActionLogService {

    MessageRepo messageRepo;

    @Override
    public String getActionLog(MessageLog msgLog) {
        messageRepo.save(msgLog);

        return "Successfully logged into Database";
    }
}
