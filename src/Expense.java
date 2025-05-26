public class Expense extends Transaction {
    String txType;

    public Expense(String txTitle, String txCategory, Double txNominal, LocalDateTime txTime){
        txType = "Expense";
        this.txTitle = txTitle;
        this.txCategory = txCategory;
        this.txNominal = txNominal;
        this.txTime = txTime;
    }

    public String getType(){
        return txType;
    }
}
