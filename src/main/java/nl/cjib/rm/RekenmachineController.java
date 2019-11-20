package nl.cjib.rm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RekenmachineController {

//    @Autowired
//    private RekenmachineRepository rekenmachineRepository;

//    @GetMapping("/historie")
//    public List<String> getHistorie(){
//        return rekenmachineRepository.getHistorie();
//    }

    @GetMapping("/historie")
    public String getHistorie(){
        return "Test";
    }
}