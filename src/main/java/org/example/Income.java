package org.example;

import java.time.LocalDate;

public class Income extends Transaction {
   public Income(String txTitle, String txCategory, Double txNominal, LocalDate txTime, User user) {
      super(txTitle, txCategory, txNominal, txTime, user);
      txType = "Income";
   }
}
