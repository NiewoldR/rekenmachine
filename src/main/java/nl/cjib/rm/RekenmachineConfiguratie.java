package nl.cjib.rm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan("nl.cjib.rm")
public class RekenmachineConfiguratie {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }


}
