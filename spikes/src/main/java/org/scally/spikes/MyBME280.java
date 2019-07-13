package org.scally.spikes;

import org.scally.server.core.sensors.BME280;
import org.scally.server.core.sensors.BME280Data;

public class MyBME280 {

  public static void main( String args[] ) throws Exception {
    BME280 bme280 = new BME280();


    while( true ) {
      BME280Data data = bme280.read();

      System.out.printf( "Temperature in Celsius : %.2f C %n", data.getTemperature() );
      System.out.printf( "Pressure : %.2f hPa %n", data.getPressure() );
      System.out.printf( "Relative Humidity : %.2f %% RH %n", data.getHumidity() );
      System.out.println();

      Thread.sleep( 10000 );
    }
  }
}
