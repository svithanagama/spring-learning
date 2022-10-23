package dev.sanjayav.sbgrafanaprometheus.mvc;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Random;
import java.util.function.Supplier;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyRestController {

  final static Logger logger = LoggerFactory.getLogger(DummyRestController.class);

  @Autowired
  private MeterRegistry meterRegistry;

  @PostConstruct
  public void postConstruct() {
    Gauge.builder("dummyrestcontroller.random.number", randomNumberSupplier())
            .tag("version","v1")
            .description("Dummy Controller Random Number")
            .register(meterRegistry);
  }

  private Supplier<Number> randomNumberSupplier() {
    return ()-> new Random().nextInt(100 - 10) + 10;
  }

  @GetMapping("/v1/dummy")
  public ResponseEntity<String> dummyGetEndpoint() {
    logger.info("An info log statement");
    return ResponseEntity.ok("Success GET response");
  }

  @PostMapping("/v1/dummy")
  public ResponseEntity<String> dummyPostEndpoint() {
    logger.warn("A test warn log statement.");
    return ResponseEntity.ok("Success POST response");
  }
}
