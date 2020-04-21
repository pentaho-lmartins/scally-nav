package org.scally.spikes.ssd1306;

public class Java32x32 {
  private final int w = 32, h = 32;
  // Created with http://en.radzio.dxp.pl/bitmap_converter/
  private final int[] imgBuffer = new int[] { // 128
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x80,
    0xC0, 0xE0, 0x78, 0x3E, 0x80, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x7C, 0xFE, 0xC7, 0x03,
    0x7D, 0xFE, 0xE7, 0xC3, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0C, 0x0C, 0x6E, 0x6A, 0x6A, 0xC8, 0xD8, 0xC9, 0xC9,
    0xC8, 0xCF, 0x6B, 0x6D, 0x4C, 0x04, 0x00, 0x42, 0x62, 0x3E, 0x1E, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x18, 0x1C, 0x14, 0x34, 0x70, 0x73, 0x73, 0x76, 0x76, 0x76, 0x76,
    0x76, 0x76, 0x77, 0x72, 0x72, 0x50, 0x70, 0x78, 0x78, 0x3C, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00
  };

  public int getW()
  {
    return this.w;
  }

  public int getH()
  {
    return this.h;
  }

  public int[] getImgBuffer()
  {
    return this.imgBuffer;
  }
}