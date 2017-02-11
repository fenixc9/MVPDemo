/**
  * Copyright 2017 aTool.org 
  */
package mrgood.com.mvpdemo.model.bean;
import java.util.List;
/**
 * Auto-generated: 2017-02-11 17:40:27
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Contentlist {

    private String pubDate;
    private String channelname;
    private String desc;
    private int sentimentDisplay;
    private String channelid;
    private String nid;
    private String link;
    private List<Alllist> alllist;
    private boolean havepic;
    private SentimentTag sentimentTag;
    private String title;
    private List<Imageurls> imageurls;
    private String source;
    public void setPubDate(String pubDate) {
         this.pubDate = pubDate;
     }
     public String getPubDate() {
         return pubDate;
     }

    public void setChannelname(String channelname) {
         this.channelname = channelname;
     }
     public String getChannelname() {
         return channelname;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setSentimentDisplay(int sentimentDisplay) {
         this.sentimentDisplay = sentimentDisplay;
     }
     public int getSentimentDisplay() {
         return sentimentDisplay;
     }

    public void setChannelid(String channelid) {
         this.channelid = channelid;
     }
     public String getChannelid() {
         return channelid;
     }

    public void setNid(String nid) {
         this.nid = nid;
     }
     public String getNid() {
         return nid;
     }

    public void setLink(String link) {
         this.link = link;
     }
     public String getLink() {
         return link;
     }

    public void setAlllist(List<Alllist> alllist) {
         this.alllist = alllist;
     }
     public List<Alllist> getAlllist() {
         return alllist;
     }

    public void setHavepic(boolean havepic) {
         this.havepic = havepic;
     }
     public boolean getHavepic() {
         return havepic;
     }

    public void setSentimentTag(SentimentTag sentimentTag) {
         this.sentimentTag = sentimentTag;
     }
     public SentimentTag getSentimentTag() {
         return sentimentTag;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setImageurls(List<Imageurls> imageurls) {
         this.imageurls = imageurls;
     }
     public List<Imageurls> getImageurls() {
         return imageurls;
     }

    public void setSource(String source) {
         this.source = source;
     }
     public String getSource() {
         return source;
     }

    @Override
    public String toString() {
        return "Contentlist{" +
                "pubDate='" + pubDate + '\'' +
                ", channelname='" + channelname + '\'' +
                ", desc='" + desc + '\'' +
                ", sentimentDisplay=" + sentimentDisplay +
                ", channelid='" + channelid + '\'' +
                ", nid='" + nid + '\'' +
                ", link='" + link + '\'' +
                ", alllist=" + alllist +
                ", havepic=" + havepic +
                ", sentimentTag=" + sentimentTag +
                ", title='" + title + '\'' +
                ", imageurls=" + imageurls +
                ", source='" + source + '\'' +
                '}';
    }
}