import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionHandler {
    static String path = "data.txt";

    private static String formatStr(Transaction tx){
        String txTitle = tx.getTitle();
        String txCategory = tx.getCategory();
        Double txAmount = tx.getNominal();
        String txTimeStr = tx.getTime().toString();
        String txType = tx.getType();
        return String.format("%s, %f, %s, %s, %s", txTitle, txAmount, txCategory, txType, txTimeStr);
    }

    public static void writeToFile(Transaction tx){
        String str = formatStr(tx);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            writer.write(str);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> readFromFile(){
        ArrayList<String> res = new ArrayList<>();
        String str;

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            while((str = reader.readLine()) != null){
                res.add(str);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static ArrayList<String> readWithDate(String from, String to){
        ArrayList<String> res = new ArrayList<>();
        String str;

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            while((str = reader.readLine()) != null){
                res.add(str);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return res;
    }
}
