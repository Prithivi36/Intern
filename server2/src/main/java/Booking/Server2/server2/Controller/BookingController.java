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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@CrossOrigin("*")
public class BookingController {

    @Autowired
    OrderEntryService orderEntryService;

    @Value("${auth.key}")
    private String authKey;

    @GetMapping("/sync/{key}")
    public String addBookingNo(@PathVariable String key) throws JsonProcessingException {
        if(!authKey.equals(key)) {
            return "Wrong Auth Key";
        }
        return orderEntryService.addBookingNo();
    }

    @GetMapping("/piecessync/{key}")
    public String SyncBooking(@PathVariable String key) throws JsonProcessingException {
        if(!authKey.equals(key)) {
            return "Wrong auth key";
        }
        return orderEntryService.addBookingPieces();
    }
}
