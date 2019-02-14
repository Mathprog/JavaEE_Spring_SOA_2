//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.02.14 à 07:00:54 PM CET 
//


package oc.projet.biblio.client.consumer.generated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour reservationWS complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservationWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ouvrage" type="{http://biblio.io/api/biblio-web-service}ouvrageWS"/&gt;
 *         &lt;element name="usager" type="{http://biblio.io/api/biblio-web-service}usagerWS"/&gt;
 *         &lt;element name="dateReservation" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="dateReservationString" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dateLimite" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="usagerPlace" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    "dateReservationString",
    "dateLimite",
    "usagerPlace"
})
public class ReservationWS {

    protected int id;
    @XmlElement(required = true)
    protected OuvrageWS ouvrage;
    @XmlElement(required = true)
    protected UsagerWS usager;
    @XmlElement(required = true, type = String.class, nillable = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected LocalDateTime dateReservation;
    @XmlElement(required = true, nillable = true)
    protected String dateReservationString;
    @XmlElement(required = true, type = String.class, nillable = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected LocalDate dateLimite;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer usagerPlace;

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
    public LocalDateTime getDateReservation() {
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
    public void setDateReservation(LocalDateTime value) {
        this.dateReservation = value;
    }

    /**
     * Obtient la valeur de la propriété dateReservationString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateReservationString() {
        return dateReservationString;
    }

    /**
     * Définit la valeur de la propriété dateReservationString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateReservationString(String value) {
        this.dateReservationString = value;
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
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUsagerPlace() {
        return usagerPlace;
    }

    /**
     * Définit la valeur de la propriété usagerPlace.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUsagerPlace(Integer value) {
        this.usagerPlace = value;
    }

}
