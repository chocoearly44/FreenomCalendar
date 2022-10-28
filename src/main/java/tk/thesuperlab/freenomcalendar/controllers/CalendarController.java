package tk.thesuperlab.freenomcalendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.thesuperlab.freenomcalendar.services.CalendarService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
	@Autowired
	private CalendarService service;

	@GetMapping("/domains")
	public ResponseEntity<String> getDomains() {
		return service.getDomains();
	}
}
