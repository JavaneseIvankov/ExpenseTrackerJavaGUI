package org.example;

import org.example.repositories.IAppRepository;
import org.example.repositories.TemporaryRepository;

public class Context {
   public static IAppRepository appRepo;

   public static void initilize() {
      appRepo = new TemporaryRepository();
   }
}
