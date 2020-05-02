package in.shadowfacsimile.yact.rest;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shadowfacsimile.yact.services.YactDataService;

@RestController
public class YactRestService {

	private static final Logger LOGGER = Logger.getLogger(YactRestService.class.getName());

	@Autowired
	private YactDataService yactDataService;

	@GetMapping("/")
	public String test() {
		return "YACT!";
	}

	@GetMapping("/syncdata")
	public String syncCOVIDData() {
		LOGGER.info("***** Inside YactRestService.syncCOVIDData() / Fetching Data From Sources *****");

		yactDataService.fetchAndStoreCOVIDDataFromSources();

		LOGGER.info("***** Inside YactRestService.syncCOVIDData() / Data synch completed *****");
		return "Synchronization of COVID-19 data complete!";
	}

}
