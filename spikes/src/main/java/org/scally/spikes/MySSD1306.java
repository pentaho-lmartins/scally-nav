package org.scally.spikes;

import org.scally.server.core.oled.Font;
import org.scally.server.core.oled.Glyph;
import org.scally.server.core.oled.SSD1306;
import org.scally.server.core.oled.fonts.Grand9K;

import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MySSD1306 {

  // create display object
// Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT,
  //  OLED_MOSI, OLED_CLK, OLED_DC, OLED_RESET, OLED_CS);


  //display.display() to show logo
  //sleep(2s)
  //  display.clear()

  // display.drawPixel(10, 10, SSD1306_WHITE); draw a single pixel
  // sleeps(2s)

  // draw a line
  // clear()

//  for(i=0; i<display.width(); i+=4) {
//    display.drawLine(0, 0, i, display.height()-1, SSD1306_WHITE);
//    display.display(); // Update screen with each newly-drawn line
//    delay(1);
//  }

  public static void main( String[] args ) throws Exception {
    SSD1306 display = new SSD1306();
    display.display();

    int[] image = new int[] {
      0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80,
      0xC0, 0xE0, 0x78, 0x3E, 0x80, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
      0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x7C, 0xFE, 0xC7, 0x03,
      0x7D, 0xFE, 0xE7, 0xC3, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
      0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0C, 0x0C, 0x6E, 0x6A, 0x6A, 0xC8, 0xD8, 0xC9, 0xC9,
      0xC8, 0xCF, 0x6B, 0x6D, 0x4C, 0x04, 0x00, 0x42, 0x62, 0x3E, 0x1E, 0x00, 0x00, 0x00, 0x00, 0x00,
      0x00, 0x00, 0x00, 0x00, 0x00, 0x18, 0x1C, 0x14, 0x34, 0x70, 0x73, 0x73, 0x76, 0x76, 0x76, 0x76,
      0x76, 0x76, 0x77, 0x72, 0x72, 0x50, 0x70, 0x78, 0x78, 0x3C, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00
    };

    byte[] buffer = new byte[ image.length ];

    for( int i = 0; i < image.length; i ++ ) {
      buffer[i] = (byte) image[i];
    }

    display.display( buffer, 48, 0, 32, 32 / 8 );
/*
    for( int i = 0; i < 5; i ++ ) {
      Thread.sleep( 1000 );
      display.invert();
      Thread.sleep( 1000 );
      display.normal();
    }

    for(int y = 0; y < display.getHeight(); y ++) {
      for(int x = 0; x < display.getWidth(); x ++) {
        display.point( x, y );
        display.display();

        System.out.format( "x: %d, y: %d\n", x, y );
        Thread.sleep( 10 );
      }
    }

    for(int y = 0; y < display.getHeight(); y ++) {
      for(int x = 0; x < display.getWidth(); x ++) {
        display.point( x, y, true );
        display.display();

        System.out.format( "x: %d, y: %d\n", x, y );
        Thread.sleep( 10 );
      }
    }

    display.clear();
*/
    Thread.sleep( 2000 );

    buffer = display.getBuffer();

    Font font = new Grand9K();
    String text = "Scally";
    int column = 0;

    // print Scally in the top left corner

    for( int i = 0; i < text.length(); i ++ ) {
      Glyph glyph = font.getGlyph( text.charAt( i ) );

      for( byte b : glyph.getData() ) {
        buffer[column ++] = b;
      }

      column += font.getSpaceWidth();
    }

    display.setBuffer( buffer );
    display.display();

    // ===========================================================

    // print IP address in bottom right corner
    String ip = getLocalIPAddress();

    if ( ip == null ) {
      throw new SocketException( "Unable to get local IP address." );
    }

    int width = font.getTextWidth( ip ); // width in pixels

    // print bytes to the buffer at the 4th page and aligh right
    int offset = display.getWidth() - width;
    offset += display.getWidth() * 3; // move to the third page

    for( int i = 0; i < ip.length(); i ++ ) {
      Glyph glyph = font.getGlyph( ip.charAt( i ) );

      for( byte b : glyph.getData() ) {
        buffer[offset ++] = b;
      }

      offset += font.getSpaceWidth();
    }

    display.setBuffer( buffer );
    display.display();
  }

  public static String getLocalIPAddress() throws SocketException {
    Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

    while( interfaces.hasMoreElements() ) {
      NetworkInterface network = (NetworkInterface) interfaces.nextElement();
      if( network.getInterfaceAddresses().stream().filter( x -> x.getAddress().isLoopbackAddress() ).count() != 0 ) {
        continue;
      }

      return network.getInterfaceAddresses().stream().filter( x -> x.getAddress().getAddress().length == 4 ).findFirst().get().getAddress().getHostAddress();
    }

    return null;
  }
}
