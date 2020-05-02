package in.shadowfacsimile.yact.scheduler;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.shadowfacsimile.yact.services.YactDataService;

@Service
public class YactScheduler {

	private static final Logger LOGGER = Logger.getLogger(YactScheduler.class.getName());

	@Autowired
	private YactDataService yactDataService;

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void syncCOVIDData() {
		LOGGER.info("***** Inside YactScheduler.syncCOVIDData() / Fetching Data From Sources *****");

		yactDataService.fetchAndStoreCOVIDDataFromSources();

		LOGGER.info("***** Inside YactScheduler.syncCOVIDData() / Data synch completed *****");
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void archiveCOVIDData() {
		LOGGER.info("***** Inside YactScheduler.archiveCOVIDData() *****");

		yactDataService.archiveCOVIDData();

		LOGGER.info("***** Inside YactScheduler.archiveCOVIDData() / Archival completed *****");
	}

}
