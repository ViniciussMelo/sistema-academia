package database.models.payment;

import database.models.Model;
import database.models.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity(name = "payment")
public class Payment extends Model<Payment> {

    @Column(name = "referenceMonth")
    private Date reference_month;

    @JoinColumn(name = "userId", foreignKey =  @ForeignKey(name = "payment_userId"))
    private User user;

    @Column(name = "payday")
    private Date payday;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "amountPaid")
    private Double amount_paid;

    public Date getReference_month() {
        return reference_month;
    }

    public void setReference_month(Date reference_month) {
        this.reference_month = reference_month;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(Double amount_paid) {
        this.amount_paid = amount_paid;
    }

    @Override
    public String[] getResult() {
        return new String[0];
    }
}
