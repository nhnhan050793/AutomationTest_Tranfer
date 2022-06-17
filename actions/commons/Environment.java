package commons;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

// cách config và fix cứng
//@Sources({ "classpath:dev.properties"})

//cách config và fix động
@Sources({ "classpath:${environment}.properties"})

public interface Environment extends Config {
	String url();
	
	@Key("db.hostname")
	String databaseHostname();
	
	@Key("db.username")
	String databaseUsername();

	@Key("db.password")
	String databasePassword();
	
}
