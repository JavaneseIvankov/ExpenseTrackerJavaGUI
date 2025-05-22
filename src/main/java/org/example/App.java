package org.example;

import org.example.gui.GUIExpenseTracker;
import org.example.repositories.IAppRepository;

public class App {
   static IAppRepository appRepo;


   public static void main(String[] args) {
      Context.initilize();
      // Demo.run();

      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            GUIExpenseTracker gui = new GUIExpenseTracker();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
         }
      });
   }
}
