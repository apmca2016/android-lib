package ecs.com.app.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ECS-27 on 02-02-2018.
 */
@Root(name = "rss")
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    /*@Element(name = "xmlnsAtom")
    private String xmlnsAtom;

    @Element(name = "version")
    private String version;*/

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

   /* public String getXmlnsAtom() {
        return xmlnsAtom;
    }

    public void setXmlnsAtom(String xmlnsAtom) {
        this.xmlnsAtom = xmlnsAtom;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }*/
}
