package tk.thesuperlab.freenomcalendar.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import tk.thesuperlab.freenom4j.Freenom;

import javax.annotation.PostConstruct;

import static tk.thesuperlab.freenomcalendar.FreenomCalendarApplication.freenom;

@Component
public class InitConfig {
	@Autowired
	private Environment environment;

	@PostConstruct
	public void init() {
		freenom = new Freenom(environment.getProperty("freenom.email"), environment.getProperty("freenom.password"));
	}
}
