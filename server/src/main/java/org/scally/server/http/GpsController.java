package org.scally.server.http;

import org.scally.server.core.gps.GpsAntenna;
import org.scally.server.core.gps.GpsFix;
import org.scally.server.http.messages.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping( "/api/gps" )
public class GpsController {

  private GpsAntenna gps;

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping( path = "/status" )
  public GpsFix status() {
    return gps != null ? gps.getFixStatus() : null;
  }

  @RequestMapping( method = GET, path = "/greeting" )
  public Greeting greeting( @RequestParam( value = "name", defaultValue = "World" ) String name ) {
    return new Greeting( counter.incrementAndGet(), String.format( template, name ) );
  }

  public void setGpsAntenna( GpsAntenna gps ) {
    this.gps = gps;
  }
}