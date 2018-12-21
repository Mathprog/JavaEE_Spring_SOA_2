package oc.projet.biblio.client.consumer;

import oc.projet.biblio.client.consumer.ws.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

@Configuration
public class ClientWSConfiguration {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
        wss4jSecurityInterceptor.setSecurementActions("Timestamp UsernameToken");
        wss4jSecurityInterceptor.setSecurementUsername("admin");
        wss4jSecurityInterceptor.setSecurementPassword("secret");
        return wss4jSecurityInterceptor;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("oc.projet.biblio.client.consumer.generated");
        marshaller.setMtomEnabled(true); // IMPORTANT
        return marshaller;
    }


   @Bean
    public UsagerClient usagerClient(Jaxb2Marshaller marshaller) {
        UsagerClient usagerClient = new UsagerClient();
        usagerClient.setDefaultUri("http://localhost:8080/soapws");
        usagerClient.setMarshaller(marshaller);
        usagerClient.setUnmarshaller(marshaller);
       ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
       usagerClient.setInterceptors(interceptors);
        return usagerClient;
    }

    @Bean
    public OuvrageClient ouvrageClient(Jaxb2Marshaller marshaller){
        OuvrageClient ouvrageClient = new OuvrageClient();
        ouvrageClient.setDefaultUri("http://localhost:8080/soapws");
        ouvrageClient.setMarshaller(marshaller);
        ouvrageClient.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
        ouvrageClient.setInterceptors(interceptors);
        return ouvrageClient;
    }

    @Bean
    public ExemplaireClient exemplaireClient(Jaxb2Marshaller marshaller){
        ExemplaireClient exemplaireClient = new ExemplaireClient();
        exemplaireClient.setDefaultUri("http://localhost:8080/soapws");
        exemplaireClient.setMarshaller(marshaller);
        exemplaireClient.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
        exemplaireClient.setInterceptors(interceptors);
        return exemplaireClient;
    }

    @Bean
    public PretClient pretClient(Jaxb2Marshaller marshaller){
        PretClient pretClient = new PretClient();
        pretClient.setDefaultUri("http://localhost:8080/soapws");
        pretClient.setMarshaller(marshaller);
        pretClient.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
        pretClient.setInterceptors(interceptors);
        return pretClient;
    }

    @Bean
    public RelanceClient relanceClient(Jaxb2Marshaller marshaller){
        RelanceClient relanceClient = new RelanceClient();
        relanceClient.setDefaultUri("http://localhost:8080/soapws");
        relanceClient.setMarshaller(marshaller);
        relanceClient.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
        relanceClient.setInterceptors(interceptors);
        return relanceClient;
    }
}