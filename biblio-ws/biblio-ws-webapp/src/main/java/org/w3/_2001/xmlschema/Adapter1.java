//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.08 à 04:06:05 PM CET 
//


package org.w3._2001.xmlschema;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, LocalDate>
{


    public LocalDate unmarshal(String value) {
        return (oc.projet.biblio.webapp.controller.serviceWeb.utils.LocalDateAdapter.unmarshal(value));
    }

    public String marshal(LocalDate value) {
        return (oc.projet.biblio.webapp.controller.serviceWeb.utils.LocalDateAdapter.marshal(value));
    }

}
