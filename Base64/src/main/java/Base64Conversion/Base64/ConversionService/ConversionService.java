package Base64Conversion.Base64.ConversionService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class ConversionService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${customer.url}")
    String baseUrl;


    public String convert(String AwbNo){

        ResponseEntity<byte []> responseEntity = restTemplate.getForEntity(baseUrl+AwbNo+".jpg", byte[].class);

        byte[] imageData = responseEntity.getBody();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        return base64Image;
    }
}
