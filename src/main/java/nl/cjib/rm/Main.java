package nl.cjib.rm;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("nl.cjib.rm");

        Input input = annotationConfigApplicationContext.getBean(Input.class);

        System.out.println(input.rekenmachine());
    }

}
