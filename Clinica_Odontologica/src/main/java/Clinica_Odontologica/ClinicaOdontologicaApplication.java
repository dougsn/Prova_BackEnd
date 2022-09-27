package Clinica_Odontologica;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	private static final Logger logger = Logger.getLogger(ClinicaOdontologicaApplication.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
