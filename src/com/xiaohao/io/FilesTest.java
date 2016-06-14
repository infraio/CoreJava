package com.xiaohao.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesTest {

  public static volatile int count = 0;
  
  public static void main(String[] args) throws IOException {
    String baseDir = System.getProperty("user.dir");
    System.out.println(baseDir);

    Path basePath = Paths.get(baseDir);

    Files.walkFileTree(basePath, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (path.getFileName().toString().endsWith("java")) {
          count++;
          System.out.println(path);
        }
        return FileVisitResult.CONTINUE;
      }
      @Override
      public FileVisitResult visitFileFailed(Path path, IOException ioe) throws IOException {
        return FileVisitResult.CONTINUE;
      }
    });
    
    System.out.println("total " + count + " java files under " + basePath);
  }

  
}
