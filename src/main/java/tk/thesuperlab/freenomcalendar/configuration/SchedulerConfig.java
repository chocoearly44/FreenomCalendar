package tk.thesuperlab.freenomcalendar.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tk.thesuperlab.freenom4j.Freenom;

import static tk.thesuperlab.freenomcalendar.FreenomCalendarApplication.freenom;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	@Value("${freenom.email}")
	private String email;
	@Value("${freenom.password}")
	private String password;

	@Scheduled(cron = "00 00 00 * * *")
	public void scheduleTaskUsingCronExpression() {
		freenom = new Freenom(email, password);
	}
}
