//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.25 at 04:24:33 PM CEST 
//


package rs.ac.uns.ftn.xml.team17.authservice.dto.authentication;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xml.team17.authentification package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xml.team17.authentification
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthenticationRequest }
     * 
     */
    public AuthenticationRequest createAuthentificationRequest() {
        return new AuthenticationRequest();
    }

    /**
     * Create an instance of {@link AuthenticationResponse }
     * 
     */
    public AuthenticationResponse createAuthentificationResponse() {
        return new AuthenticationResponse();
    }

}
