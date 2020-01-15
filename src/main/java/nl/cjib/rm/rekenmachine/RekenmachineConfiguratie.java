package nl.cjib.rm.rekenmachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;
import java.util.Scanner;

@Configuration
@ComponentScan("nl.cjib.rm")
@PropertySource("classpath:som.properties")
public class RekenmachineConfiguratie {

    @Bean
    public Scanner scanner() {

        return new Scanner(System.in);
    }


    @Bean
    public PrintStream printStream() {

        return System.out;
    }

}
