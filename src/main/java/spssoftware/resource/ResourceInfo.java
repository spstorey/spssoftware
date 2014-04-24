package spssoftware.resource;

public class ResourceInfo {

    private String name;

    private String link;

    public ResourceInfo(final String name, final String link) {
        this.name = name;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
}
