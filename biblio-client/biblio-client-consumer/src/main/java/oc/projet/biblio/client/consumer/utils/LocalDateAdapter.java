package oc.projet.biblio.client.consumer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateAdapter.class);

    public static LocalDate unmarshal(String xmlGregorianCalendar) {
        try {
            LocalDate result = LocalDate.parse(xmlGregorianCalendar, DateTimeFormatter.ISO_DATE);
            return result;
        } catch (DateTimeParseException ex) {
            logger.error("Could not parse date" + xmlGregorianCalendar, ex);
            return null;
        }
    }

    public static String marshal(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE);
    }

}
