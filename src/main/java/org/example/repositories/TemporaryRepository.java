package org.example.repositories;

import java.time.LocalDateTime;
import java.util.*;
import org.example.models.CategoryModel;
import org.example.models.TransactionModel;

public class TemporaryRepository implements IAppRepository {
   private final Map<Integer, TransactionModel> transactions = new HashMap<>();
   private final Map<Integer, CategoryModel> categories = new HashMap<>();

   private int transactionIdCounter = 1;
   private int categoryIdCounter = 1;

   public TemporaryRepository() {}

   @Override
   public TransactionModel createTransaction(String name, double amount, CategoryModel category) {
      return createTransaction(name, amount, category, LocalDateTime.now());
   }

   @Override
   public TransactionModel createTransaction(String name, double amount, CategoryModel category,
         LocalDateTime datetime) {
      int id = transactionIdCounter++;
      TransactionModel t =
            new TransactionModel(id, name, amount, category.name, category.id, datetime);
      transactions.put(id, t);
      return t;
   }

   @Override
   public CategoryModel createCategory(String name) {
      int id = categoryIdCounter++;
      CategoryModel c = new CategoryModel(id, name);
      categories.put(id, c);
      return c;
   }

   @Override
   public List<TransactionModel> getTransactions() {
      return new ArrayList<>(transactions.values());
   }

   @Override
   public List<TransactionModel> getTransactions(LocalDateTime from, LocalDateTime until) {
      List<TransactionModel> filtered = new ArrayList<>();
      for (TransactionModel t : transactions.values()) {
         LocalDateTime created = t.createdAt;
         if ((created.isEqual(from) || created.isAfter(from))
               && (created.isEqual(until) || created.isBefore(until))) {
            filtered.add(t);
         }
      }
      return filtered;
   }

   @Override
   public void editTransaction(int transactionId, double amount, CategoryModel category) {
      TransactionModel t = transactions.get(transactionId);
      if (t != null) {
         t.amount = amount;
         t.categoryId = category.id;
         t.categoryName = category.name;
      }
   }

   @Override
   public void editTransaction(int transactionId, double amount, String title) {
      TransactionModel t = transactions.get(transactionId);
      if (t != null) {
         t.amount = amount;
         t.title = title;
      }
   }

   @Override
   public void editTransaction(int transactionId, String title, CategoryModel category) {
      TransactionModel t = transactions.get(transactionId);
      if (t != null) {
         t.title = title;
         t.categoryId = category.id;
         t.categoryName = category.name;
      }
   }

   @Override
   public void editTransaction(int transactionId, CategoryModel category) {
      TransactionModel t = transactions.get(transactionId);
      if (t != null) {
         t.categoryId = category.id;
         t.categoryName = category.name;
      }
   }

   @Override
   public void editTransaction(int transactionId, double amount) {
      TransactionModel t = transactions.get(transactionId);
      if (t != null) {
         t.amount = amount;
      }
   }

   @Override
   public void editTransaction(int transactionId, String title) {
      TransactionModel t = transactions.get(transactionId);
      if (t != null) {
         t.title = title;
      }
   }

   @Override
   public TransactionModel getTransaction(int transactionId) {
      return transactions.get(transactionId);
   }

   @Override
   public List<CategoryModel> getCategories() {
      return new ArrayList<>(categories.values());
   }

   @Override
   public CategoryModel getCategory(int categoryId) {
      return categories.get(categoryId);
   }

   @Override
   public CategoryModel getCategory(String name) {
      for (CategoryModel category : categories.values()) {
         if (category.name.equals(name)) {
            return category;
         }
      }
      return null;
   }

   @Override
   public CategoryModel renameCategory(int id, String newName) {
      CategoryModel c = categories.get(id);
      if (c != null) {
         c.name = newName;
         for (TransactionModel t : transactions.values()) {
            if (t.categoryId == c.id) {
               t.categoryName = newName;
            }
         }
      }
      return c;
   }

   @Override
   public void deleteTransaction(int transactionId) {
      transactions.remove(transactionId);
   }

   @Override
   public void deleteCategory(int categoryId) {
      categories.remove(categoryId);
   }

   @Override
   public void deleteCategory(String name) {
      for (CategoryModel category : categories.values()) {
         if (category.name.equals(name)) {
            categories.remove(category.id);
            return;
         }
      }
   }
}
