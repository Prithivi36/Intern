package Bulk.Mail.SMTP.Repo;

import Bulk.Mail.SMTP.Entity.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<MessageLog,Long> {
}
