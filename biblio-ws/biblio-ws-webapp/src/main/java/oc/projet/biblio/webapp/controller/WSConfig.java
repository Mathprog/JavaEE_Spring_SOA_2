package oc.projet.biblio.webapp.controller;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@EnableWs
@Configuration
public class WSConfig extends WsConfigurerAdapter {

    @Bean
    public SimplePasswordValidationCallbackHandler securityCallbackHandler(){
        SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
        Properties users = new Properties();
        users.setProperty("admin", "secret");
        callbackHandler.setUsers(users);
        return callbackHandler;
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setValidationActions("Timestamp UsernameToken");
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
        return securityInterceptor;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soapws/*");
    }


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.biblio.api.biblio_web_service");
        marshaller.setMtomEnabled(true); // IMPORTANT
        return marshaller;
    }

    @Bean
    public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter() {
        List<MethodArgumentResolver> argumentResolvers =
                new ArrayList<MethodArgumentResolver>();
        argumentResolvers.add(methodProcessor());

        List<MethodReturnValueHandler> returnValueHandlers =
                new ArrayList<MethodReturnValueHandler>();
        returnValueHandlers.add(methodProcessor());

        DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
        adapter.setMethodArgumentResolvers(argumentResolvers);
        adapter.setMethodReturnValueHandlers(returnValueHandlers);

        return adapter;
    }

    @Bean
    public MarshallingPayloadMethodProcessor methodProcessor() {
        return new MarshallingPayloadMethodProcessor(marshaller());
    }



    @Bean(name = "bibliosoap")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema biblioSOAPSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BibliosoapPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://biblio.io/api/biblio-web-service");
        wsdl11Definition.setSchema(biblioSOAPSchema);
        return wsdl11Definition;
    }


    @Bean
    public XsdSchema biblioSOAPSchema() {
        return new SimpleXsdSchema(new ClassPathResource("WS-XSD/biblio_soap.xsd"));
    }
}
