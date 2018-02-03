package ecs.com.app.model;

import android.media.Image;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ECS-27 on 02-02-2018.
 */

@Root(name = "channel")
public class Channel {

    @Element(name = "title")
    private String title;

    @ElementArray(name = "link")
    private List<String> link = null;

    @Element(name = "description")
    private String description;

    @Element(name = "language")
    private String language;

    @Element(name = "lastBuildDate")
    private String lastBuildDate;

    @Element(name = "copyright")
    private String copyright;

    @Element(name = "docs")
    private String docs;

    @Element(name = "image")
    private Image image;

    @ElementArray(name = "item")
    private List<Item> item = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLink() {
        return link;
    }

    public void setLink(List<String> link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

}

