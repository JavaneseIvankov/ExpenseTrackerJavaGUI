package org.example;

import org.example.repositories.IAppRepository;

public class App {
   static IAppRepository appRepo;


   public static void main(String[] args) {
      Context.initilize();
      Demo.run();
   }
}
