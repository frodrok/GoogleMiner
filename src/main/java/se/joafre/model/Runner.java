package se.joafre.model;

import com.google.gson.Gson;
import se.joafre.model.mapper.GoogleResults;
import se.joafre.model.model.Search;
import se.joafre.model.model.SearchResult;
import se.joafre.model.repo.SearchRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public final class Runner {

    private static SearchRepository searchRepository = new SearchRepository();

    public static void main(String[] args) throws Exception {

        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = "trampolin";
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        Search s = new Search(search);

        List<GoogleResults.Result> resultList = results.getResponseData().getResults();
        for (int i = 0; i < resultList.size(); i++) {
            SearchResult sr = new SearchResult(resultList.get(i).getTitle(),
                    resultList.get(i).getUrl(),
                    (i+1L));

            s.addResult(sr);
        }

        for (SearchResult searchResult : s.getResults()) {
            System.out.println(searchResult.getTitle());
        }

        searchRepository.persist(s);
    }
}
