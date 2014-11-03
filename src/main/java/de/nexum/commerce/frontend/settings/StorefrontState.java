package de.nexum.commerce.frontend.settings;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Component("storefrontSettings")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StorefrontState implements Serializable {

	private static final long serialVersionUID = -5017571350391557300L;

	private StorefrontViewState viewSetting;

	public StorefrontState() {
		
		super();

		this.viewSetting = StorefrontViewState.COMPACT;
	}

	public StorefrontViewState getViewSetting() {
		return viewSetting;
	}

	public void setViewSetting(StorefrontViewState viewSetting) {
		this.viewSetting = viewSetting;
	}
	
}
