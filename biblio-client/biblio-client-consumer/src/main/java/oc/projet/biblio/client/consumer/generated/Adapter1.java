//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.02.14 à 07:00:54 PM CET 
//


package oc.projet.biblio.client.consumer.generated;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, LocalDateTime>
{


    public LocalDateTime unmarshal(String value) {
        return (oc.projet.biblio.client.consumer.utils.LocalDateTimeAdapter.unmarshal(value));
    }

    public String marshal(LocalDateTime value) {
        return (oc.projet.biblio.client.consumer.utils.LocalDateTimeAdapter.marshal(value));
    }

}
