package org.example.models;

import java.time.LocalDateTime;

public class TransactionModel {
   public int id;
   public String title;
   public double amount;
   public String categoryName;
   public int categoryId;
   public LocalDateTime createdAt;

   public TransactionModel(int id, String title, double amount, String categoryName, int categoryId,
         LocalDateTime createdAt) {
      this.id = id;
      this.title = title;
      this.amount = amount;
      this.categoryName = categoryName;
      this.categoryId = categoryId;
      this.createdAt = createdAt;
   }
}
