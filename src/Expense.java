import java.time.LocalDateTime;

public class Expense extends Transaction {
    public Expense(String txTitle, String txCategory, Double txNominal, LocalDateTime txTime){
        super(txTitle, txCategory, txNominal, txTime);
        txType = "Expense";
    }
}
