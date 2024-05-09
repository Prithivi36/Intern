package Booking.Server2.server2;

import Booking.Server2.server2.Entity.Booking;
import Booking.Server2.server2.Entity.BookingDto;
import Booking.Server2.server2.Entity.OrderDto;
import Booking.Server2.server2.Entity.OrderEntity;
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

    BookingRepository bookingRepository;
    RestTemplate restTemplate;
    ModelMapper mapper;
    OrderRepository orderRepository;

    @GetMapping("/sync")
    public List<BookingDto> addBooking() throws JsonProcessingException {
        String baseUrl="http://SpringServer1:8080";
        String exchangeValue=restTemplate.exchange(baseUrl+"/get", HttpMethod.GET,new ResponseEntity<>(HttpStatus.OK),String.class).getBody();

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());

        List<Booking>  exchangedDto=objMapper.readValue(exchangeValue,new TypeReference<List<Booking>>(){});

        for(Booking bookingDto:exchangedDto){
            if(!bookingDto.isAwbSync()){
                OrderDto orderDto=new OrderDto();
                orderDto.setAwbno(bookingDto.getBookCnno());
                orderRepository.save(mapper.map(orderDto, OrderEntity.class));
                bookingRepository.save(bookingDto);
                restTemplate.put(baseUrl+"/patch/"+bookingDto.getBookCnno(),bookingDto,HttpMethod.PUT);
            }
        }
        return exchangedDto.stream().map((i)->mapper.map(i, BookingDto.class)).toList();
    }

}
