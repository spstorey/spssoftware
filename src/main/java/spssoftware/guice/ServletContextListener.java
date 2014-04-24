package spssoftware.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletContextListener extends GuiceServletContextListener {

	Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

	Injector injector = null;

	public ServletContextListener() {
		super();

		injector = Guice.createInjector(new GuiceModule());
	}

	@Override
	protected Injector getInjector() {
		return injector;
	}
}
