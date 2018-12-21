//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.14 à 04:45:04 PM CET 
//


package oc.projet.biblio.client.consumer.generated;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour pretWS complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="pretWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="exemplaire" type="{http://biblio.io/api/biblio-web-service}exemplaireWS"/&gt;
 *         &lt;element name="ouvrage" type="{http://biblio.io/api/biblio-web-service}ouvrageWS"/&gt;
 *         &lt;element name="relance" type="{http://biblio.io/api/biblio-web-service}relanceWS"/&gt;
 *         &lt;element name="datePret" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dateFin" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pretWS", propOrder = {
    "id",
    "exemplaire",
    "ouvrage",
    "relance",
    "datePret",
    "dateFin"
})
public class PretWS {

    protected int id;
    @XmlElement(required = true)
    protected ExemplaireWS exemplaire;
    @XmlElement(required = true)
    protected OuvrageWS ouvrage;
    @XmlElement(required = true)
    protected RelanceWS relance;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected LocalDate datePret;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected LocalDate dateFin;

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
     * Obtient la valeur de la propriété exemplaire.
     * 
     * @return
     *     possible object is
     *     {@link ExemplaireWS }
     *     
     */
    public ExemplaireWS getExemplaire() {
        return exemplaire;
    }

    /**
     * Définit la valeur de la propriété exemplaire.
     * 
     * @param value
     *     allowed object is
     *     {@link ExemplaireWS }
     *     
     */
    public void setExemplaire(ExemplaireWS value) {
        this.exemplaire = value;
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

    /**
     * Obtient la valeur de la propriété datePret.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDate getDatePret() {
        return datePret;
    }

    /**
     * Définit la valeur de la propriété datePret.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatePret(LocalDate value) {
        this.datePret = value;
    }

    /**
     * Obtient la valeur de la propriété dateFin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Définit la valeur de la propriété dateFin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFin(LocalDate value) {
        this.dateFin = value;
    }

}
