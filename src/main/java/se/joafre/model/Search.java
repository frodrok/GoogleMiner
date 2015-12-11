package se.joafre.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Search {

    @Id
    @GeneratedValue
    private Long search_id;

    @Column(nullable = false)
    private String searchQuery;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_id")
    private Collection<SearchResult> results = new HashSet();




    protected Search() {}

    public Search(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Long getSearch_id() {
        return search_id;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public Collection<SearchResult> getResults() {
        return new HashSet<SearchResult>(results);
    }



    public void addResult(SearchResult result) {
        this.results.add(result);
    }
}
