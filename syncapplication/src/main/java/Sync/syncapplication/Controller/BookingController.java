package Sync.syncapplication.Controller;

import Sync.syncapplication.Repository.BookingRepository;
import Sync.syncapplication.Entity.Booking;
import Sync.syncapplication.Entity.BookingDto;
import Sync.syncapplication.Service.BookingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BookingController {

    public BookingService bookingService;

    @PostMapping("/post")
    public String addBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.addBooking(bookingDto);
    }

    @GetMapping("/getall")
    public List<BookingDto> getBookings() {
        return bookingService.getBookings();
    }

    @PutMapping("/change/{booking}")
    public String changeBoooking(@PathVariable Long booking){
        return bookingService.changeStats(booking);
    }
}
