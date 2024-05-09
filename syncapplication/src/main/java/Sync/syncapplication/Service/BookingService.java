package Sync.syncapplication.Service;

import Sync.syncapplication.Entity.Booking;
import Sync.syncapplication.Entity.BookingDto;
import Sync.syncapplication.Repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    ModelMapper mapper = new ModelMapper();

    public String addBooking(@RequestBody BookingDto booking) {
        Booking bookingEntity = mapper.map(booking, Booking.class);
        Booking booked = bookingRepository.save(bookingEntity);

        return booked.toString();
    }

    public String changeStats(@PathVariable Long book){
        Booking booking=bookingRepository.findByBookCnno(book);
        booking.setAwbSync(true);
        bookingRepository.save(booking);
        return booking.toString();
    }

    public List<BookingDto> getBookings(){
        List<Booking> bookings=bookingRepository.findAll();
        return  bookings.stream().map((i)->mapper.map(i, BookingDto.class)).toList();
    }

    public BookingDto findByBookingCnno(Long bookingCnno){
        return mapper.map(bookingRepository.findByBookCnno(bookingCnno), BookingDto.class);
    }
}
