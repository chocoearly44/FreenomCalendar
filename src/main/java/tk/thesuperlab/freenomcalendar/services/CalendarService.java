package tk.thesuperlab.freenomcalendar.services;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.thesuperlab.freenom4j.Freenom;

@Service
public class CalendarService {
	@Value("${freenom.email}")
	private String email;
	@Value("${freenom.password}")
	private String password;

	public ResponseEntity<String> getDomains() {
		Freenom freenom = new Freenom(email, password);

		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//FreenomCalendar//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);

		freenom.listDomains().forEach(domain -> {
			VEvent event = new VEvent(
					new Date(domain.getExpiryDate()),
					new Date(domain.getExpiryDate()),
					domain.getName()
			);

			UidGenerator ug = new RandomUidGenerator();
			Uid uid = ug.generateUid();
			event.getProperties().add(uid);

			calendar.getComponents().add(event);
		});

		return new ResponseEntity<>(calendar.toString(), constructHeaders(), HttpStatus.OK);
	}

	private HttpHeaders constructHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Disposition", "attachment;filename=cal.ics");

		return responseHeaders;
	}
}
