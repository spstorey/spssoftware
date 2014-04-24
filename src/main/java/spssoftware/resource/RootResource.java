package spssoftware.resource;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.core.InjectParam;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import spssoftware.dao.BlogsDao;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Singleton
@Path("/")
public class RootResource {

	public static int TIMEOUT = 5000;
	private final Client jerseyClient;
    private BlogsDao blogsDao;

	@Context
	ServletContext context;

	@Inject
	public RootResource(final Client jerseyClient, @Context ServletContext servletContext, BlogsDao blogsDao) throws Exception {
		this.context = servletContext;
        this.blogsDao = blogsDao;
		this.jerseyClient = jerseyClient;
		this.jerseyClient.setReadTimeout(TIMEOUT);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResourceInfo> get(@Context UriInfo uriInfo, @InjectParam ResourceNarrator resourceNarrator) {
		return resourceNarrator.narrateSubResourcesByAnnotation(uriInfo, this);
	}

    @GET
    @Path("/splash")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getSplashData() {

        List<String> blogs = blogsDao.getBlogs();
        JSONObject data = new JSONObject();
        data.put("message", "Welcome");
        data.put("date", (new DateTime()).getMillis());
        data.put("blog", blogs.get(0));

        return data;
    }
}