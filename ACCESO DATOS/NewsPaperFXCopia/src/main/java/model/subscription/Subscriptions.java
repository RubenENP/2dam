package model.subscription;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Subscriptions {
    @XmlElement
    private List<Subscription> subscription;

    public void addSubscription(Subscription subscription) {
        this.subscription.add(subscription);
    }
}
