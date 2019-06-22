//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 04:25:21 PM CEST 
//


package rs.ac.uns.ftn.xml.team17.reservationsservice.model.message;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageDirection.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageDirection">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TO_AGENT"/>
 *     &lt;enumeration value="TO_CUSOTOMER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageDirection")
@XmlEnum
public enum MessageDirection {

    TO_AGENT,
    TO_CUSTOMER;

    public String value() {
        return name();
    }

    public static MessageDirection fromValue(String v) {
        return valueOf(v);
    }

}
