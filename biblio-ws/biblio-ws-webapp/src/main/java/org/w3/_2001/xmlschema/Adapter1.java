//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.13 à 11:15:06 AM CET 
//


package org.w3._2001.xmlschema;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, LocalDateTime>
{


    public LocalDateTime unmarshal(String value) {
        return (oc.projet.biblio.webapp.controller.serviceWeb.utils.LocalDateTimeAdapter.unmarshal(value));
    }

    public String marshal(LocalDateTime value) {
        return (oc.projet.biblio.webapp.controller.serviceWeb.utils.LocalDateTimeAdapter.marshal(value));
    }

}
