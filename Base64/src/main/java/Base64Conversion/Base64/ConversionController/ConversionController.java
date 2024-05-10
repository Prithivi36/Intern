package Base64Conversion.Base64.ConversionController;

import Base64Conversion.Base64.ConversionService.ConversionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConversionController {

    ConversionService conversionService;

    @GetMapping("/convert")
    public String Convert(@RequestParam String awbno){
    return conversionService.convert(awbno);
    }
}
