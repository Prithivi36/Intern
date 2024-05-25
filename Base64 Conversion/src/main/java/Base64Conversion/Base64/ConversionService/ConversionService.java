package Base64Conversion.Base64.ConversionService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

        try {
            ResponseEntity<byte []> responseEntity = restTemplate.getForEntity(baseUrl+AwbNo+".jpg", byte[].class);

            if(responseEntity.getStatusCode().is2xxSuccessful()){

                byte[] imageData = responseEntity.getBody();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                return base64Image;
            }else{
                return "Failed to retrive Image , Excited with Statscode : " + responseEntity.getStatusCode();
            }
        }catch (Exception e){
//            return "Failed to retrive Image , Excited with Statscode : " + HttpStatus.NOT_FOUND;
        return "<h1>Not found</h1><p>Requested Image Not Found</p>";
        }


    }
}
