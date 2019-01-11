//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.10 à 02:53:20 PM CET 
//


package oc.projet.biblio.client.consumer.generated;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, LocalDate>
{


    public LocalDate unmarshal(String value) {
        return (oc.projet.biblio.client.consumer.utils.LocalDateAdapter.unmarshal(value));
    }

    public String marshal(LocalDate value) {
        return (oc.projet.biblio.client.consumer.utils.LocalDateAdapter.marshal(value));
    }

}
