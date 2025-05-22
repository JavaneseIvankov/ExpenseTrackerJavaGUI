package org.example;

import org.example.gui.GUIExpenseTracker;

public class App {

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
