package fezzik.common.security.model;

/**
 * Represents the type of web application security to apply.  Since this app is a prototype and we are unsure which
 * security type will be necessary, this enum will drive the type.  
 */
public enum WebApplicationSecurityTypeEnum {
	basicAuth,
	x509,
	formLogin;
}