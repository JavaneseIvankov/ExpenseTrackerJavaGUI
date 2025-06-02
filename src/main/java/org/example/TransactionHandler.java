package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionHandler {
   static String path = "data.txt";

   private static String formatStr(Transaction tx) {
      String txTitle = tx.getTitle();
      String txCategory = tx.getCategory();
      Double txAmount = tx.getNominal();
      String txTimeStr = tx.getTime().toString();
      String txType = tx.getType();
      User txUser = tx.getUser();
      String userName = txUser.getName();
      return String.format("%s, %s, %s, %s, %.2f, %s\n", txTimeStr, txTitle, txCategory, txType,
            txAmount, userName);
   }

   public static void saveTransaction(Transaction tx) {
      String str = formatStr(tx);

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
            if (strDetail[0].compareTo(from) >= 0 && strDetail[0].compareTo(to) <= 0) {
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
            String dataDate = strDetail[0];
            String dataUserName = strDetail[5].strip();

            boolean filter = true;
            filter = (from != null) ? filter && dataDate.compareTo(from) >= 0 : true;
            filter = (to != null) ? filter && dataDate.compareTo(to) <= 0 : true;
            filter = (userName != null && !userName.isEmpty() && !userName.isBlank())
                  ? filter && dataUserName.equals(userName)
                  : true;

            if (filter) {
               res.add(str);
            }
         }
      } catch (IOException e) {
         GUIExpenseTracker.showErrorMessageDialog(e.getMessage());
      }

      return res;
   }

}
