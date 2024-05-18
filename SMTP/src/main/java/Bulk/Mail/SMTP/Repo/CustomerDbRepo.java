package Bulk.Mail.SMTP.Repo;

import Bulk.Mail.SMTP.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDbRepo extends JpaRepository<CustomerDetails ,Long> {
}
