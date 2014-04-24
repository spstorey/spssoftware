package spssoftware.exception;

import com.google.inject.Singleton;
import com.sun.jersey.api.client.ClientResponse.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    public DefaultExceptionMapper() {
        super();
    }

    @Override
    public Response toResponse(final Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        }

        LOGGER.error("Exception caught while trying to slip through to the end user. ", exception);

        StringBuilder responseMessage = new StringBuilder();
        responseMessage.append("<p><h1>").append(Status.INTERNAL_SERVER_ERROR).append(" (");
        responseMessage.append(Status.INTERNAL_SERVER_ERROR.getStatusCode()).append(")</h1></p>");
        responseMessage.append("<p>").append(exception.toString()).append("</p>");
        return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML).entity(responseMessage.toString()).build();
    }
}
