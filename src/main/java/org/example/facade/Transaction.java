package org.example.facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.Context;
import org.example.models.CategoryModel;
import org.example.models.TransactionModel;
import org.example.repositories.IAppRepository;

public class Transaction {
   private int id;
   private String title;
   private double amount;
   private String categoryName;
   private int categoryId;
   private LocalDateTime createdAt;
   private Category category;

   // Ambil dari konteks global (DI)
   static private IAppRepository _repo = Context.appRepo;

   public Transaction(String title, double amount, Category category) {
      CategoryModel categoryModel = category.toModel();
      TransactionModel model = _repo.createTransaction(title, amount, categoryModel);
      this.id = model.id;
      this.title = model.title;
      this.amount = model.amount;
      this.categoryName = model.categoryName;
      this.categoryId = model.categoryId;
      this.createdAt = model.createdAt;
      this.category = category;
   }

   public Transaction(String title, double amount, String categoryName, LocalDateTime createdAt) {
      this(title, amount, Category.getByName(categoryName));
   }

   public Transaction(int id, String title, double amount, int categoryId) {
      this(title, amount, Category.getById(categoryId));
   }


   TransactionModel toModel() {
      return new TransactionModel(id, title, amount, categoryName, categoryId, createdAt);
   }

   private Transaction(TransactionModel transactionModel) {
      this.id = transactionModel.id;
      this.title = transactionModel.title;
      this.amount = transactionModel.amount;
      this.categoryName = transactionModel.categoryName;
      this.categoryId = transactionModel.categoryId;
      this.createdAt = transactionModel.createdAt;
      Category _category = Category.getById(categoryId);
      this.category = _category != null ? _category : null;
      // this.category = _category != null ? _category : new Category(categoryName);
   }

   static void setRepository(IAppRepository repo) {
      _repo = repo;
   }

   static List<Transaction> getHistory(LocalDateTime from, LocalDateTime until) {
      List<TransactionModel> models = _repo.getTransactions(from, until);
      List<Transaction> res = new ArrayList<Transaction>();

      for (TransactionModel model : models) {
         Transaction t = new Transaction(model);
         res.add(t);
      }

      return res;
   }

   public static List<Transaction> getHistory() {
      List<TransactionModel> models = _repo.getTransactions();
      List<Transaction> res = new ArrayList<Transaction>();

      for (TransactionModel model : models) {
         Transaction t = new Transaction(model);
         res.add(t);
      }

      return res;
   }

   public static Transaction getById(int transactionId) {
      TransactionModel model = _repo.getTransaction(transactionId);
      return new Transaction(model);
   }


   public void delete() {
      _repo.deleteTransaction(id);
   }

   public void setTitle(String title) {
      _repo.editTransaction(this.id, title);
      this.title = title;
   }

   public void setAmount(double amount) {
      _repo.editTransaction(this.id, amount);
      this.amount = amount;
   }

   public void setCategory(Category category) {
      _repo.editTransaction(id, category.toModel());
      this.category = category;
   }

   public void setCategory(String categoryName) {
      Category category = Category.getByName(categoryName);
      if (category == null) {
         // handle if category is not found
         return;
      }
      _repo.editTransaction(id, category.toModel());
      this.category = category;
   }

   public int getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public double getAmount() {
      return amount;
   }

   public int getCategoryId() {
      return categoryId;
   }

   public String getCategoryName() {
      return categoryName;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

}
