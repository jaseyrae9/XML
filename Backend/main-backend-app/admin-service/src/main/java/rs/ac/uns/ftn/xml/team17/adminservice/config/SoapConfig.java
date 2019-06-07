package rs.ac.uns.ftn.xml.team17.adminservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
	    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	    servlet.setApplicationContext(applicationContext);
	    servlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	@Bean(name = "AdditionalServices")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema additionalServicesSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("AdditionalServicesPort");
	    wsdl11Definition.setLocationUri("/ws/additionalService");
	    wsdl11Definition.setTargetNamespace("http://www.team17.xml.ftn.uns.ac.rs/AdditionalServices");
	    wsdl11Definition.setSchema(additionalServicesSchema);
	    return wsdl11Definition;
	}
	 
	@Bean
	public XsdSchema additionalServicesSchema() {
	    return new SimpleXsdSchema(new ClassPathResource("soap/additionalService.xsd"));
	}

	@Bean(name = "roomType")
	public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema roomTypeSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("RoomTypePort");
	    wsdl11Definition.setLocationUri("/ws/roomType");
	    wsdl11Definition.setTargetNamespace("http://www.team17.xml.ftn.uns.ac.rs/roomType");
	    wsdl11Definition.setSchema(roomTypeSchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema roomTypeSchema() {
	    return new SimpleXsdSchema(new ClassPathResource("soap/roomType.xsd"));
	}
	
	@Bean(name = "roomCategory")
	public DefaultWsdl11Definition defaultWsdl11Definition3(XsdSchema roomCategorySchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("RoomCategoryPort");
	    wsdl11Definition.setLocationUri("/ws/roomCategory");
	    wsdl11Definition.setTargetNamespace("http://www.team17.xml.ftn.uns.ac.rs/roomCategory");
	    wsdl11Definition.setSchema(roomCategorySchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema roomCategorySchema() {
	    return new SimpleXsdSchema(new ClassPathResource("soap/roomCategory.xsd"));
	}
}
