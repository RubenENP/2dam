package model;

import dao.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement (name = "faction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Faction {
    @XmlElement
    private String name;
    @XmlElement
    private String contact;
    @XmlElement
    private String planet;
    @XmlElement
    private int numberCS;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateLastPurchase;
    @XmlElement
    private Weapons weapons;
}
