package ecs.com.app.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ECS-27 on 02-02-2018.
 */

@Root(name = "image")
public class Image {

    @Element(name = "title")
    private String title;

    @Element(name = "url")
    private String url;

    @Element(name = "link")
    private String link;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
