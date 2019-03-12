package com.soap.app;

import com.soap.app.schema.webservice.TestApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return args -> {
            String name = "Sajal";//Default Name
            if(args.length>0){
                name = args[0];
            }
            TestApp request = new TestApp() {
                @Override
                public void setParams(Map<String, Object> value) {
                    super.setParams(value);
                }
            };
            Map<String, Object> params= new HashMap<>();
            params.put("code_projet","SUL0627-3");
            params.put("mois","02");
            params.put("annee","2019");
            request.setParams(params);
            System.out.println(soapConnector.callWebService("https://sqli.steering-project.com/sdp/administration/imputation_cra.php", request).toString());
//			StudentDetailsResponse response =(StudentDetailsResponse) soapConnector.callWebService("https://sqli.steering-project.com/sdp/administration/imputation_cra.php", request);
//			System.out.println("Got Response As below ========= : ");
//			System.out.println("Name : "+response.getStudent().getName());
//			System.out.println("Standard : "+response.getStudent().getStandard());
//			System.out.println("Address : "+response.getStudent().getAddress());
        };
    }
}
