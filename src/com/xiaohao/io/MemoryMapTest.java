package com.xiaohao.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * 166M文件测试
 * Input Stream:3611947068
 * 94842 milliseconds
 * Random Access File:3611947068
 * 97596 milliseconds
 * Buffered Input Stream:3611947068
 * 4667 milliseconds
 * Mapped File:3611947068
 * 2304 milliseconds
 */
public class MemoryMapTest {

  public static void main(String[] args) throws IOException {
    if (args.length <= 0) {
      return;
    }
    for (String arg : args) {
      System.out.println(arg);
    }

    System.out.print("Input Stream:");
    long start = System.currentTimeMillis();
    Path filename = Paths.get(args[0]);
    long crcValue = checksumInputStream(filename);
    long end = System.currentTimeMillis();
    System.out.println(crcValue);
    System.out.println((end - start) + " milliseconds");
    
    System.out.print("Random Access File:");
    start = System.currentTimeMillis();
    filename = Paths.get(args[0]);
    crcValue = checksumRandomAccessFile(filename);
    end = System.currentTimeMillis();
    System.out.println(crcValue);
    System.out.println((end - start) + " milliseconds");    
  
    System.out.print("Buffered Input Stream:");
    start = System.currentTimeMillis();
    filename = Paths.get(args[0]);
    crcValue = checksumBufferedInputStream(filename);
    end = System.currentTimeMillis();
    System.out.println(crcValue);
    System.out.println((end - start) + " milliseconds");
  
    System.out.print("Mapped File:");
    start = System.currentTimeMillis();
    filename = Paths.get(args[0]);
    crcValue = checksumMappedFile(filename);
    end = System.currentTimeMillis();
    System.out.println(crcValue);
    System.out.println((end - start) + " milliseconds");
  }
  
  public static long checksumInputStream(Path filename) throws IOException {
    try (InputStream in = Files.newInputStream(filename)){
      CRC32 crc = new CRC32();
      int c;
      while ((c = in.read()) != -1) {
        crc.update(c);
      }
      return crc.getValue();
    }
  }

  public static long checksumRandomAccessFile(Path filename) throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
      CRC32 crc = new CRC32();
      long length = file.length();
      for (long p = 0; p < length; p++) {
        file.seek(p);
        int c = file.readByte();
        crc.update(c);
      }
      return crc.getValue();
    }
  }
  
  public static long checksumBufferedInputStream(Path filename) throws IOException {
    try (InputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
      CRC32 crc = new CRC32();
      int c;
      while ((c = in.read()) != -1) {
        crc.update(c);
      }
      return crc.getValue();
    }
  }
  
  public static long checksumMappedFile(Path filename) throws IOException {
    try (FileChannel channel = FileChannel.open(filename)) {
      CRC32 crc = new CRC32();

      long length = channel.size();
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

      for (long p = 0; p < length; p++) {
        int c = buffer.get((int) p);
        crc.update(c);
      }

      return crc.getValue();
    }
  }
}
