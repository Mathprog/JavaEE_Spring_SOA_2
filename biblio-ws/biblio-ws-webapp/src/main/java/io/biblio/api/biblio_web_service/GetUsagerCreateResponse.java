//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.13 à 11:15:06 AM CET 
//


package io.biblio.api.biblio_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usagerWS" type="{http://biblio.io/api/biblio-web-service}usagerWS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usagerWS"
})
@XmlRootElement(name = "getUsagerCreateResponse")
public class GetUsagerCreateResponse {

    @XmlElement(required = true, nillable = true)
    protected UsagerWS usagerWS;

    /**
     * Obtient la valeur de la propriété usagerWS.
     * 
     * @return
     *     possible object is
     *     {@link UsagerWS }
     *     
     */
    public UsagerWS getUsagerWS() {
        return usagerWS;
    }

    /**
     * Définit la valeur de la propriété usagerWS.
     * 
     * @param value
     *     allowed object is
     *     {@link UsagerWS }
     *     
     */
    public void setUsagerWS(UsagerWS value) {
        this.usagerWS = value;
    }

}
