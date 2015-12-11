package se.joafre;

import com.google.gson.Gson;
import se.joafre.model.SearchResult;
import se.joafre.model.Search;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public final class Runner {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceUnit");
    private static EntityManager em;

    public static void main(String[] args) throws Exception {

        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = "trampolin";
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        /*System.out.println(results.getResponseData().getResults().get(0).getTitle());
        System.out.println(results.getResponseData().getResults().get(0).getUrl());*/



        Search s = new Search(search);

        List<GoogleResults.Result> resultList = results.getResponseData().getResults();
        for (int i = 0; i < resultList.size(); i++) {
            SearchResult sr = new SearchResult(resultList.get(i).getTitle(),
                                                resultList.get(i).getUrl(),
                    (i+1L));

            s.addResult(sr);
        }

        for (SearchResult searchResult : s.getResults()) {
            System.out.println(searchResult);
        }

        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(s);

        em.getTransaction().commit();
        em.close();


    }
}
