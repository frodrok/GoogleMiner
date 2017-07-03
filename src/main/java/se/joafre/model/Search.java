package se.joafre.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Search {

    @Id
    @GeneratedValue
    private Long search_id;

    @Column
    private String searchQuery;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "search_id")
    private Collection<SearchResult> results;

    protected Search() {}

    public Search(String searchQuery) {
        this.searchQuery = searchQuery;
        this.results = new HashSet<>();
    }

    public Long getSearch_id() {
        return search_id;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public Search addResult(SearchResult result) {
        this.results.add(result);
        return this;
    }

    public Collection<SearchResult> getResults() {
        return new HashSet<>(results);
    }
}
