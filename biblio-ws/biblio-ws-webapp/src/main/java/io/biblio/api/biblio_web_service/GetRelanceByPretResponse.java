//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.02.14 à 07:00:07 PM CET 
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
 *         &lt;element name="relance" type="{http://biblio.io/api/biblio-web-service}relanceWS"/>
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
    "relance"
})
@XmlRootElement(name = "getRelanceByPretResponse")
public class GetRelanceByPretResponse {

    @XmlElement(required = true, nillable = true)
    protected RelanceWS relance;

    /**
     * Obtient la valeur de la propriété relance.
     * 
     * @return
     *     possible object is
     *     {@link RelanceWS }
     *     
     */
    public RelanceWS getRelance() {
        return relance;
    }

    /**
     * Définit la valeur de la propriété relance.
     * 
     * @param value
     *     allowed object is
     *     {@link RelanceWS }
     *     
     */
    public void setRelance(RelanceWS value) {
        this.relance = value;
    }

}
