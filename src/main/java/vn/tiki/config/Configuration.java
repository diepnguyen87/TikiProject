package vn.tiki.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({	
	"classpath:general.properties",
	"classpath:${environment}.properties"})
public interface Configuration extends Config {

	@Key("url")
	String getURL();
	
	@Key("timeout.long")
	long getLongTimeout();
	
	@Key("timeout.short")
	long getShortTimeout();
}
