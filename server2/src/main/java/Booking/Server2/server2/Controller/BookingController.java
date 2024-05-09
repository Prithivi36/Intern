package Booking.Server2.server2.Controller;

import Booking.Server2.server2.Entity.Booking;
import Booking.Server2.server2.Entity.BookingDto;
import Booking.Server2.server2.Entity.OrderDto;
import Booking.Server2.server2.Entity.OrderEntity;
import Booking.Server2.server2.Repository.OrderRepository;
import Booking.Server2.server2.Repository.BookingRepository;
import Booking.Server2.server2.Service.OrderEntryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BookingController {

    OrderEntryService orderEntryService;

    @GetMapping("/sync")
    public List<BookingDto> addBookingNo() throws JsonProcessingException {
        return orderEntryService.addBookingNo();
    }

}
