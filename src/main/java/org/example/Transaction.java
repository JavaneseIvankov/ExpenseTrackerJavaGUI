package org.example;

import java.time.LocalDate;

public abstract class Transaction {
   User user;
   String txTitle;
   String txCategory;
   String txType;
   Double txNominal;
   LocalDate txTime;

   public Transaction(String txTitle, String txCategory, Double txNominal, LocalDate txTime) {
      this.txTitle = txTitle;
      this.txCategory = txCategory;
      this.txNominal = txNominal;
      this.txTime = txTime;
      this.txType = "-";
   }

   public Transaction(String txTitle, String txCategory, Double txNominal, LocalDate txTime,
         User user) {
      this(txTitle, txCategory, txNominal, txTime);
      this.user = user;
   }

   public String getTitle() {
      return txTitle;
   }

   public String getCategory() {
      return txCategory;
   }

   public String getType() {
      return txType;
   }

   public Double getNominal() {
      return txNominal;
   }

   public LocalDate getTime() {
      return txTime;
   }

   public User getUser() {
      return this.user;
   }

   public void setTitle(String txTitle) {
      this.txTitle = txTitle;
   }

   public void setCategory(String txCategory) {
      this.txCategory = txCategory;
   }

   public void setType(String txType) {
      this.txType = txType;
   }

   public void setNominal(Double txNominal) {
      this.txNominal = txNominal;
   }

   public void setTime(LocalDate txTime) {
      this.txTime = txTime;
   }

   public void setUser(User user) {
      this.user = user;
   }
}
