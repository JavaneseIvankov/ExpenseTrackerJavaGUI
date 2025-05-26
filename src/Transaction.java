public abstract class Transaction {
    String txTitle;
    String txCategory;
    Double txNominal;
    LocalDateTime txTime;

    public Transaction(String txTitle, String txCategory, Double txNominal, LocalDateTime txTime){
        this.txTitle = txTitle;
        this.txCategory = txCategory;
        this.txNominal = txNominal;
        this.txTime = txTime;
    }

    public String getTitle(){
        return txTitle;
    }

    public String getCategory(){
        return txCategory;
    }

    public Double getNominal(){
        return txNominal;
    }

    public LocalDateTime getTime(){
        return txTime;
    }

    public void setTitle(String txTitle){
        this.txTitle = txTitle;
    }

    public void setCategory(String txCategory){
        this.txCategory = txCategory;
    }

    public void setNominal(Double txNominal){
        this.txNominal = txNominal;
    }

    public void setTime(LocalDateTime txTime){
        this.txTime = txTime;
    }
}
