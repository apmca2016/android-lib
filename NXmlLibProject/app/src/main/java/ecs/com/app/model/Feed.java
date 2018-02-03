package ecs.com.app.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ECS-27 on 02-02-2018.
 */

@Root(name = "feed")
public class Feed {

    @Element(name = "rss")
    private Rss rss;

    public Rss getRss() {
        return rss;
    }

    public void setRss(Rss rss) {
        this.rss = rss;
    }

}
