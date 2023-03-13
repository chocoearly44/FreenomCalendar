package tk.thesuperlab.freenomcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.thesuperlab.freenom4j.Freenom;

@SpringBootApplication
public class FreenomCalendarApplication {
	public static Freenom freenom;

	public static void main(String[] args) {
		SpringApplication.run(FreenomCalendarApplication.class, args);
	}
}
