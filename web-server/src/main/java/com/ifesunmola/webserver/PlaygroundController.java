package com.ifesunmola.webserver;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@CrossOrigin(origins = "*")
public class PlaygroundController {
	private static final Logger log = getLogger(PlaygroundController.class);

	record RunResponse(boolean isSuccess, String msg) {}

	private record Sample(String name, String code) {}

	@PostMapping("/run")
	ResponseEntity<RunResponse> run(@RequestBody(required = false) String code) {
		// Yeah, I could leave it up to the global exception handler
		if (!StringUtils.hasText(code)) {
			log.info("Nothing to compile!");
			return ResponseEntity.ok()
				.body(new RunResponse(false, "Nothing to compile!"));
		}
		try {
			Path toCompile = Files.createTempFile("code", ".earth");
			Files.write(toCompile, code.getBytes());
			log.info("Wrote code to temporary file: {}", toCompile);

			log.info("Executing ...");

			var process = new ProcessBuilder("./earth", toCompile.toString())
				.start();
			String successMsg = streamToString(process.getInputStream());
			String errorMsg = streamToString(process.getErrorStream());

			int exitCode = process.waitFor();
			process.destroy();

			log.info("Execution finished with exit code: {}", exitCode);

			boolean isSuccess = exitCode == 0;
			String msg = isSuccess ? successMsg : errorMsg;

			return ResponseEntity.ok(new RunResponse(isSuccess, msg));
		}
		catch (IOException | InterruptedException e) {
			log.info(
				"Error occurred while running the compiler:", e
			);
			return ResponseEntity
				.internalServerError().body(new RunResponse(false,
					"An error occurred on the server. Check the logs!"
				));
		}
	}

	@GetMapping("/samples")
	List<Sample> samples() {
		log.info("Fetching samples ...");

		return List.of(
			new Sample("Select One", Samples.empty()),
			new Sample("Hello World", Samples.helloWorld()),
			new Sample("FizzBuzz", Samples.fizzBuzz()),
			new Sample("When Else", Samples.whenElse())
		);
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
