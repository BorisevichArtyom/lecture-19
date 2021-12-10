package by.itacademy.javaenterprise.borisevich.singletable.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {

    @Column(name = "card_number")
    private long cardNumber;

    @Column(name = "exp_month")
    private String expMonth;

    @Column(name = "exp_year")
    private String expYear;

    public CreditCard() {
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", expMonth='" + expMonth + '\'' +
                ", expYear='" + expYear + '\'' +
                '}';
    }
}