package main.java.org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransactionHandler {
   static String path = "data.txt";

   public static void deleteTransaction(String id) {
      ArrayList<String> txs = getTransactions();
      boolean found = false;

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
         for (String tx : txs) {
            String[] details = tx.split(", ");
            if (!details[0].equals(id)) {
               writer.write(tx + "\n");
            } else {
               found = true;
            }
         }
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }

      if (!found) {
         GUIExpenseTracker.showErrorMessageDialog("Transaction with ID " + id + " not found.");
      }
   }

   public static void saveTransaction(Transaction tx) {
      String str = tx.toString();

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
         writer.write(str);
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }
   }

   public static ArrayList<String> getTransactions() {
      ArrayList<String> res = new ArrayList<>();
      String str;

      try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
         while ((str = reader.readLine()) != null) {
            res.add(str);
         }
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }

      return res;
   }

   public static ArrayList<String> getTransactions(String from, String to) {
      ArrayList<String> res = new ArrayList<>();
      String str;

      try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
         while ((str = reader.readLine()) != null) {
            String[] strDetail = str.split(", ");
            if (strDetail[1].compareTo(from) >= 0 && strDetail[1].compareTo(to) <= 0) {
               res.add(str);
            }
         }
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }

      return res;
   }

   public static ArrayList<String> getTransactions(String from, String to, String userName) {
      ArrayList<String> res = new ArrayList<>();
      String str;

      try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
         while ((str = reader.readLine()) != null) {
            String[] strDetail = str.split(", ");
            String dataDate = strDetail[1];
            String dataUserName = strDetail[6].trim();

            boolean filter = true;
            filter = (from != null) ? filter && dataDate.compareTo(from) >= 0 : true;
            filter = (to != null) ? filter && dataDate.compareTo(to) <= 0 : true;
            filter = (userName != null && !userName.isEmpty()) ? filter && dataUserName.equals(userName) : true;

            if (filter) {
               res.add(str);
            }
         }
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }

      return res;
   }

   public static ArrayList<Transaction> getTransactionsObj(String from, String to, String userName) {
      ArrayList<Transaction> res = new ArrayList<>();
      String str;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
         while ((str = reader.readLine()) != null) {
            String[] strDetail = str.split(", ");
            // String trxId = strDetail[0];
            LocalDate trxDate = LocalDate.parse(strDetail[1], formatter);
            String trxTitle = strDetail[2];
            String trxCategory = strDetail[3];
            String trxType = strDetail[4];
            double trxNominal = Double.parseDouble(strDetail[5]);
            String trxUserName = strDetail[6].trim();
            User trxUser = new User(trxUserName);


            boolean filter = true;
            filter = (from != null) ? filter && trxTitle.compareTo(from) >= 0 : true;
            filter = (to != null) ? filter && trxTitle.compareTo(to) <= 0 : true;
            filter = (userName != null && !userName.isEmpty() && !userName.isEmpty())
                  ? filter && trxUserName.equals(userName)
                  : true;

            if (filter) {
               Transaction trx = (trxType.equals("Income"))
                     ? new Income(trxTitle, trxCategory, trxNominal, trxDate, trxUser)
                     : new Expense(trxTitle, trxCategory, trxNominal, trxDate, trxUser);

               res.add(trx);
            }
         }
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }
      return res;
   }

}
