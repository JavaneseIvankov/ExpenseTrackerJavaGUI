import java.time.LocalDateTime;

public class Income extends Transaction {
    public Income(String txTitle, String txCategory, Double txNominal, LocalDateTime txTime){
        super(txTitle, txCategory, txNominal, txTime);
        txType = "Income";
    }
}
