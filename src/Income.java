public class Income extends Transaction {
    String txType;

    public Income(String txTitle, String txCategory, Double txNominal, LocalDateTime txTime){
        txType = "Income";
        this.txTitle = txTitle;
        this.txCategory = txCategory;
        this.txNominal = txNominal;
        this.txTime = txTime;
    }

    public String getType(){
        return txType;
    }
}
