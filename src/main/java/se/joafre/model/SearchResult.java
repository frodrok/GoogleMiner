package se.joafre.model;

import javax.persistence.*;

@Entity
public class SearchResult {

    @Id
    @GeneratedValue
    private Long search_id;

    private String title;

    private String url;

    @ManyToOne()
    private Search search;

    private Long order;

    protected SearchResult() {}
    
    public SearchResult(String title, String url, Long order) {
        this.title = title;
        this.url = url;
        this.order = order;
    }

    public Long getSearch_id() {
        return search_id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Long getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id=" + search_id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", search=" + search +
                ", order=" + order +
                '}';
    }
}
