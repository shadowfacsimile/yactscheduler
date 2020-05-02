package in.shadowfacsimile.yact.services;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import in.shadowfacsimile.yact.models.Statistics;

@Service
public class YactDataService {

	private static final Logger LOGGER = Logger.getLogger(YactDataService.class.getName());

	public void fetchAndStoreCOVIDDataFromSources() {
		HttpClient httpClient = HttpClient.newHttpClient();

		for (Statistics statistics : Statistics.values()) {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(statistics.getUrl())).build();

			try {
				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				LOGGER.info(statistics.name() + " / Response code: " + response.statusCode());

				File file = new File(statistics.getJson());
				processResponse(statistics, response, file);
			} catch (IOException | InterruptedException e) {
				LOGGER.severe("Error in processing data : " + e.getMessage());
			}
		}
	}

	private void processResponse(Statistics statistics, HttpResponse<String> response, File file) throws IOException {
		if (statistics.getUrl().endsWith(".csv"))
			processCSV(file, response);
		else
			processJSON(file, response);
	}

	private void processCSV(File file, HttpResponse<String> response) throws IOException {
		List<Map<?, ?>> data = convertCSVToList(response.body());
		writeToJson(data, file);
	}

	private void processJSON(File file, HttpResponse<String> response) throws IOException {
		Files.writeString(file.toPath(), response.body());
	}

	private List<Map<?, ?>> convertCSVToList(final String csv) throws IOException {
		CsvSchema csvschema = CsvSchema.emptySchema().withHeader();
		CsvMapper csvMapper = new CsvMapper();
		MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader().forType(Map.class).with(csvschema)
				.readValues(csv);
		return mappingIterator.readAll();
	}

	private void writeToJson(List<Map<?, ?>> data, File file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, data);
	}
}
