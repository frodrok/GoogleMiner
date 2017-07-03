package se.joafre.model;

import javax.persistence.*;

@Entity
public class SearchResult {

    @Id
    @GeneratedValue
    private Long searchresult_id;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private Long orderInSearch;

    @ManyToOne
    private Search search;

    protected SearchResult() {}

    public SearchResult(String title, String url, Long orderInSearch) {
        this.title = title;
        this.url = url;
        this.orderInSearch = orderInSearch;

    }

    public Long getSearchresult_id() {
        return searchresult_id;
    }

    public String getTitle() {
        return title;
    }
}
