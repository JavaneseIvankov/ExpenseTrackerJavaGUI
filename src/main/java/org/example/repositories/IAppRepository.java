package org.example.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.example.models.CategoryModel;
import org.example.models.TransactionModel;

public interface IAppRepository {

   public TransactionModel createTransaction(String name, double amount, CategoryModel category);

   public TransactionModel createTransaction(String name, double amount, CategoryModel category,
         LocalDateTime datetime);

   public CategoryModel createCategory(String name);

   public List<TransactionModel> getTransactions();

   public List<TransactionModel> getTransactions(LocalDateTime from, LocalDateTime until);

   public TransactionModel getTransaction(int transactionId);

   public List<CategoryModel> getCategories();

   public CategoryModel getCategory(int categoryId);

   public CategoryModel getCategory(String name);

   public CategoryModel renameCategory(int id, String newName);

   public void editTransaction(int transactionId, double amount, CategoryModel category);

   public void editTransaction(int transactionId, double amount, String title);

   public void editTransaction(int transactionId, String title, CategoryModel category);

   public void editTransaction(int transactionId, CategoryModel category);

   public void editTransaction(int transactionId, double amount);

   public void editTransaction(int transactionId, String title);

   public void deleteTransaction(int transactionId);

   public void deleteCategory(String name);

   public void deleteCategory(int categoryId);
}
