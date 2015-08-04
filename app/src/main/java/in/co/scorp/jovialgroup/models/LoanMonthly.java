package in.co.scorp.jovialgroup.models;

/**
 * Created by root on 7/28/15.
 */
public class LoanMonthly {

    private String interest;
    private String principleAmount;
    private String balance;
    private String month;
    private String year;

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(String principleAmount) {
        this.principleAmount = principleAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
