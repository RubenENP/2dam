package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "weapon")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapon {
    @XmlElement
    private String name;
    @XmlElement
    private int price;
}
