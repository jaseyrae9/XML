package rs.ac.uns.ftn.xml.team17.roomservice.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.validation.XmlValidatorFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {

		PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
		validatingInterceptor.setValidateRequest(true);
		validatingInterceptor.setValidateResponse(true);
		validatingInterceptor.setXsdSchemaCollection(new XsdSchemaCollection() {

			@Override
			public XsdSchema[] getXsdSchemas() {
				return null;
			}

			@Override
			public XmlValidator createValidator() {
				try {
					XmlValidator xmlValidator = XmlValidatorFactory.createValidator(getSchemas(),
							"http://www.w3.org/2001/XMLSchema");

					return xmlValidator;
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
				}
				return null;
			}
		});
		interceptors.add(validatingInterceptor);
	}
	
	public Resource[] getSchemas() {
	    List<Resource> schemaResources = new ArrayList<>();
	    schemaResources.add(new ClassPathResource("soap/newRoom.xsd"));
	    schemaResources.add(new ClassPathResource("soap/hotel.xsd"));
	    return schemaResources.toArray(new Resource[schemaResources.size()]);
	}

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/soap/*");
	}

	@Bean
	public XsdSchema roomSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap/newRoom.xsd"));
	}

	@Bean(name = "newRoom")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema roomSchema) {

		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setSchema(roomSchema);
		definition.setLocationUri("/soap/room");
		definition.setPortTypeName("NewRoomPort");
		definition.setTargetNamespace("http://www.team17.xml.ftn.uns.ac.rs/NewRoom");
		return definition;
	}
	
	@Bean
    public XsdSchema hotelSchema() {
        return new SimpleXsdSchema(new ClassPathResource("soap/hotel.xsd"));
    }
	
	@Bean(name = "hotel")
    public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema hotelSchema) {

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(hotelSchema);
        definition.setLocationUri("/soap/hotel");
        definition.setPortTypeName("HotelPort");
        definition.setTargetNamespace("http://www.team17.xml.ftn.uns.ac.rs/Hotel");
        return definition;
    }

}
