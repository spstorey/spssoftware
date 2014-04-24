package spssoftware.guice;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import spssoftware.exception.DefaultExceptionMapper;
import spssoftware.resource.RootResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class GuiceModule extends ServletModule {

    public GuiceModule() {
        super();
    }

    @Override
    protected void configureServlets() {

        this.bind(DefaultExceptionMapper.class);

        bind(RootResource.class);

	    final Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");

        serve("/rest/*").with(GuiceContainer.class, parameters);
    }
}
