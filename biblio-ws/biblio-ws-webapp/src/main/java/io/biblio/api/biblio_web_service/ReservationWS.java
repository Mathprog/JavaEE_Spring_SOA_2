//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.10 à 04:51:40 PM CET 
//


package io.biblio.api.biblio_web_service;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Classe Java pour reservationWS complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservationWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ouvrage" type="{http://biblio.io/api/biblio-web-service}ouvrageWS"/>
 *         &lt;element name="usager" type="{http://biblio.io/api/biblio-web-service}usagerWS"/>
 *         &lt;element name="dateReservation" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="dateLimite" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="usagerPlace" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationWS", propOrder = {
    "id",
    "ouvrage",
    "usager",
    "dateReservation",
    "dateLimite",
    "usagerPlace"
})
public class ReservationWS {

    protected int id;
    @XmlElement(required = true)
    protected OuvrageWS ouvrage;
    @XmlElement(required = true)
    protected UsagerWS usager;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected LocalDate dateReservation;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected LocalDate dateLimite;
    protected int usagerPlace;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété ouvrage.
     * 
     * @return
     *     possible object is
     *     {@link OuvrageWS }
     *     
     */
    public OuvrageWS getOuvrage() {
        return ouvrage;
    }

    /**
     * Définit la valeur de la propriété ouvrage.
     * 
     * @param value
     *     allowed object is
     *     {@link OuvrageWS }
     *     
     */
    public void setOuvrage(OuvrageWS value) {
        this.ouvrage = value;
    }

    /**
     * Obtient la valeur de la propriété usager.
     * 
     * @return
     *     possible object is
     *     {@link UsagerWS }
     *     
     */
    public UsagerWS getUsager() {
        return usager;
    }

    /**
     * Définit la valeur de la propriété usager.
     * 
     * @param value
     *     allowed object is
     *     {@link UsagerWS }
     *     
     */
    public void setUsager(UsagerWS value) {
        this.usager = value;
    }

    /**
     * Obtient la valeur de la propriété dateReservation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDate getDateReservation() {
        return dateReservation;
    }

    /**
     * Définit la valeur de la propriété dateReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateReservation(LocalDate value) {
        this.dateReservation = value;
    }

    /**
     * Obtient la valeur de la propriété dateLimite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDate getDateLimite() {
        return dateLimite;
    }

    /**
     * Définit la valeur de la propriété dateLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateLimite(LocalDate value) {
        this.dateLimite = value;
    }

    /**
     * Obtient la valeur de la propriété usagerPlace.
     * 
     */
    public int getUsagerPlace() {
        return usagerPlace;
    }

    /**
     * Définit la valeur de la propriété usagerPlace.
     * 
     */
    public void setUsagerPlace(int value) {
        this.usagerPlace = value;
    }

}
