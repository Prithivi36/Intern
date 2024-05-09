package Booking.Server2.server2;

import Booking.Server2.server2.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookCnno(Long bookCnno);
}
