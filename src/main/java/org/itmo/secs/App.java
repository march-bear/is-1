package org.itmo.secs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static int f(Object obj) {
        return obj.hashCode();
    }
    public static void main(String[] args) {
        f(null);
        SpringApplication.run(App.class, args);
    }
}
