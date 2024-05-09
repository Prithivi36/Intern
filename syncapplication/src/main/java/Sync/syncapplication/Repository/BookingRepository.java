package Sync.syncapplication.Repository;

import Sync.syncapplication.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookCnno(Long bookCnno);
}
