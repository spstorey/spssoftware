package spssoftware.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ResourceNarrator {

    public List<ResourceInfo> narrateSubResourcesByAnnotation(final UriInfo uriInfo, final Object resource) {
        final List<ResourceInfo> resourceInformation = new ArrayList<ResourceInfo>();
        final Method[] methods = resource.getClass().getMethods();

        for (final Method method : methods) {
            if (method.isAnnotationPresent(Path.class)) {
                final ResourceInfo resourceInfo = new ResourceInfo(method.getAnnotation(Path.class).value(), uriInfo.getRequestUriBuilder().path(method).build().toString());
                resourceInformation.add(resourceInfo);
            }
        }

        return resourceInformation;
    }
}
