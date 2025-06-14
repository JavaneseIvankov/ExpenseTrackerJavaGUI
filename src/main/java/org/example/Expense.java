package org.example;

import java.time.LocalDate;

public class Expense extends Transaction {
   public Expense(String txTitle, String txCategory, Double txNominal, LocalDate txTime,
         User user) {
      super(txTitle, txCategory, txNominal, txTime, user);
      txType = "Expense";
   }
}
