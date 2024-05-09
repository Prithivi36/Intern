package Sync.syncapplication;

import Sync.syncapplication.Entity.Booking;
import Sync.syncapplication.Entity.BookingDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BookingController {

    BookingRepository bookingRepository;
    ModelMapper mapper = new ModelMapper();

    @PostMapping("/post")
    public String addBooking(@RequestBody BookingDto booking) {
        Booking bookingEntity = mapper.map(booking, Booking.class);
        Booking booked = bookingRepository.save(bookingEntity);

        return booked.toString();
    }

    @PutMapping("/patch/{book}")
    public String changeStats(@PathVariable Long book){
        Booking booking=bookingRepository.findByBookCnno(book);
        booking.setAwbSync(true);
        bookingRepository.save(booking);
        return booking.toString();
    }

    @GetMapping("/get")
    public List<BookingDto> getBookings(){
        List<Booking> bookings=bookingRepository.findAll();
        return  bookings.stream().map((i)->mapper.map(i, BookingDto.class)).toList();
    }
}
