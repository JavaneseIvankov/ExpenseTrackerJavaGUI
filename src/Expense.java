import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(String txTitle, String txCategory, Double txNominal, LocalDate txTime){
        super(txTitle, txCategory, txNominal, txTime);
        txType = "Expense";
    }
}
