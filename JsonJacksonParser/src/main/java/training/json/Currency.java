package training.json;

public class Currency {

    private String exchangedate;
    private String rate;
    private String r030;
    private String cc;
    private String txt;

    public Currency() {
    }

    public Currency(String exchangedate, String rate, String r030, String cc, String txt) {
        this.exchangedate = exchangedate;
        this.rate = rate;
        this.r030 = r030;
        this.cc = cc;
        this.txt = txt;
    }

    public String getExchangedate (){
        return exchangedate;
    }

    public void setExchangedate (String exchangedate){
        this.exchangedate = exchangedate;
    }

    public String getRate (){
        return rate;
    }

    public void setRate (String rate){
        this.rate = rate;
    }

    public String getR030 (){
        return r030;
    }

    public void setR030 (String r030){
        this.r030 = r030;
    }

    public String getCc (){
        return cc;
    }

    public void setCc (String cc){
        this.cc = cc;
    }

    public String getTxt (){
        return txt;
    }

    public void setTxt (String txt){
        this.txt = txt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [exchangedate = "+exchangedate+", rate = "+rate+", r030 = "+r030+", cc = "+cc+", txt = "+txt+"]";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Currency)) return false;

        Currency test = (Currency) obj;

        if (!this.exchangedate.equals(test.exchangedate)) return false;
        if (!this.rate.equals(test.rate)) return false;
        if (!this.cc.equals(test.cc)) return false;
        return this.txt.equals(test.txt);
    }

    @Override
    public int hashCode(){
        int code = 31;
        code *= (exchangedate == null ? 1 : exchangedate.hashCode());
        code *= (rate == null ? 1 : rate.hashCode());
        code *= (cc == null ? 1 : cc.hashCode());
        code *= (txt == null ? 1 : txt.hashCode());
        return code;
    }
}
