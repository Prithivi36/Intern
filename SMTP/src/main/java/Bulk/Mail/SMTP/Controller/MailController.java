package Bulk.Mail.SMTP.Controller;

import Bulk.Mail.SMTP.Dto.MailStructure;
import Bulk.Mail.SMTP.Dto.SentLog;
import Bulk.Mail.SMTP.Entity.CustomerDetails;
import Bulk.Mail.SMTP.Repo.CustomerDbRepo;
import Bulk.Mail.SMTP.Service.MessageService;
import Bulk.Mail.SMTP.ServiceImpl.MessageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MailController {

    MessageService messageService;
    CustomerDbRepo customerDbRepo;

    @PostMapping("/")
    public SentLog sendMail(@RequestBody MailStructure mailStructure){

        return messageService.sendMessage(mailStructure.getSubject(), mailStructure.getBody());
    }

    @PostMapping("/send")
    public String sendOne(@RequestBody MailStructure mailStructure){
        return messageService.sendMessageOne(new String[]{"soulpubg79@gmail.com","pubgnewstate977@gmail.com","prithivipappian97@gmail.com"}, mailStructure.getSubject(),
                mailStructure.getBody());
    }
    @GetMapping
    public String insertDummy(){
        for(int i=0;i<=100;i++){
            customerDbRepo.save(
                    new CustomerDetails(
                            Long.valueOf(i) ,"customer"+i,"customer"+i+"@gmail.com"
                    )
            );
        }
        return "OK";
    }
}
