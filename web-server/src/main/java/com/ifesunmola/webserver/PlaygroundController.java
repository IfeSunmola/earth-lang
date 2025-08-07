package com.ifesunmola.webserver;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
public class PlaygroundController {
	private static final Logger log = getLogger(PlaygroundController.class);

	@GetMapping("/run")
	public ResponseEntity<?> run(@RequestBody String code) {
		try {
			Path toCompile = Files.createTempFile("code", ".earth");
			Files.write(toCompile, code.getBytes());
			log.info("Wrote code to temporary file: {}", toCompile);

			log.info("Executing ...");

			var process = new ProcessBuilder("./earth", toCompile.toString())
				.start();
			String successResult = streamToString(process.getInputStream());
			String errorResult = streamToString(process.getErrorStream());

			int exitCode = process.waitFor();
			process.destroy();

			log.info("Execution finished with exit code: {}", exitCode);

			if (exitCode == 0) {
				return ResponseEntity.ok(Map.of("success", successResult));
			}
			else {
				return ResponseEntity
					.badRequest()
					.body(Map.of("error", errorResult));
			}
		}
		catch (IOException | InterruptedException e) {
			log.info(
				"Error occurred while running the compiler: {}", e.getMessage()
			);
			return ResponseEntity
				.internalServerError()
				.body(Map.of("error", "Error occurred while running the compiler"));
		}
	}

	private String streamToString(InputStream s) throws IOException {
		try (var reader = new BufferedReader(new InputStreamReader(s))) {
			var sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}

			// Remove the last line separator if it exists
			if (!sb.isEmpty() && sb.lastIndexOf(System.lineSeparator()) == sb.length() - 1) {
				sb.setLength(sb.length() - System.lineSeparator().length());
			}
			return sb.toString();
		}
	}
}
