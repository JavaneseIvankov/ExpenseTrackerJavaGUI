import java.time.LocalDateTime;

public abstract class Transaction {
    String txTitle;
    String txCategory;
    String txType;
    Double txNominal;
    LocalDateTime txTime;

    public Transaction(String txTitle, String txCategory, Double txNominal, LocalDateTime txTime){
        this.txTitle = txTitle;
        this.txCategory = txCategory;
        this.txNominal = txNominal;
        this.txTime = txTime;
        this.txType = "-";
    }

    public String getTitle(){
        return txTitle;
    }

    public String getCategory(){
        return txCategory;
    }

    public String getType(){
        return txType;
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

    public void setType(String txType){
        this.txType = txType;
    }

    public void setNominal(Double txNominal){
        this.txNominal = txNominal;
    }

    public void setTime(LocalDateTime txTime){
        this.txTime = txTime;
    }
}
