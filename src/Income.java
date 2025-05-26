import java.time.LocalDate;

public class Income extends Transaction {
    public Income(String txTitle, String txCategory, Double txNominal, LocalDate txTime){
        super(txTitle, txCategory, txNominal, txTime);
        txType = "Income";
    }
}
