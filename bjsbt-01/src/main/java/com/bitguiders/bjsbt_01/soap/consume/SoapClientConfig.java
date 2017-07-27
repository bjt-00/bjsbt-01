package com.bitguiders.bjsbt_01.soap.consume;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

	@Bean
	public Jaxb2Marshaller getMarshaller() {
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.bitguiders.bjsbt_01.soap.service");//package to scan
        
		 /*Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		  jaxb2Marshaller.setPackagesToScan("com.bitguiders.bjsbt_01");
		  Map<String,Object> map = new HashMap<String,Object>();
		  map.put("jaxb.formatted.output", true);
		  jaxb2Marshaller.setMarshallerProperties(map);
	          return jaxb2Marshaller;*/
        return marshaller;
	}

	@Bean
	public UserSoapClient getHandler() {
		UserSoapClient processor = new UserSoapClient();
		processor.setDefaultUri("http://localhost:8080/ws/users");
		processor.setMarshaller(getMarshaller());
		processor.setUnmarshaller(getMarshaller());
		return processor;
	}

}