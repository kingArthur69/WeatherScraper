package md.buiucani.entities;

public class SearchEntity {
    private String link;
    private String text;

    public SearchEntity() {
    }

    public SearchEntity(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public Integer getLinkLength(){
        return link.length();
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SearchEntity{");
        sb.append("link='").append(link).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
