package Bulk.Mail.SMTP.Controller;

import Bulk.Mail.SMTP.Dto.MailStructure;
import Bulk.Mail.SMTP.Dto.SentLog;
import Bulk.Mail.SMTP.Entity.CustomerDetails;
import Bulk.Mail.SMTP.Repo.CustomerDbRepo;
import Bulk.Mail.SMTP.Service.MessageService;
import Bulk.Mail.SMTP.ServiceImpl.MessageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class MailController {

    MessageService messageService;
    CustomerDbRepo customerDbRepo;

    @GetMapping("/")
    public String progbar() {
        return "<h1>paper</h1>"; // Thymeleaf template name without .html
    }

    @PostMapping("/")
    public SseEmitter sendMail(@RequestBody MailStructure mailStructure){

        return  messageService.sendMessage(mailStructure.getSubject(), mailStructure.getBody());
    }

    @PostMapping("/send")
    public String sendOne(@RequestBody MailStructure mailStructure){
        List<String> mails = new ArrayList<>();
        return messageService.sendMessageOne(new String[]{"soulpubg79@gmail.com","pubgnewstate977@gmail.com","prithivipappian97@gmail.com"}, mailStructure.getSubject(),
                mailStructure.getBody());
    }
    @GetMapping("/load")
    public String insertDummy(){
        for(int i=0;i<=280;i++){
            customerDbRepo.save(
                    new CustomerDetails(
                            Long.valueOf(i) ,"customer"+i,"customer"+i+"@gmail.com"
                    )
            );
        }
        return "OK";
    }
}
