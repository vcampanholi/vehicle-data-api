package br.com.vcampanholi.vehicledata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class VehicleDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleDataApplication.class, args);
	}

	/**
	 * Configura o Spring para usar o timezone -3, pois o Heroku usa UTC.
	 */
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

}
