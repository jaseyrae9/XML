package rs.ac.uns.ftn.xml.team17.reservationsservice.config;

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
	    schemaResources.add(new ClassPathResource("soap/reservation.xsd"));
	    return schemaResources.toArray(new Resource[schemaResources.size()]);
	}
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	@Bean
	public XsdSchema reservationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap/reservation.xsd"));
	}

	@Bean(name = "reservation")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema reservationSchema) {

		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setSchema(reservationSchema);
		definition.setLocationUri("/ws/reservation");
		definition.setPortTypeName("ReservationPort");
		definition.setTargetNamespace("http://www.team17.xml.ftn.uns.ac.rs/Reservation");
		return definition;
	}
}
