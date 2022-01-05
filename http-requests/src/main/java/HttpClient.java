import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HttpClient {

    public final static String DATA_SOURCE_URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        // create http client
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        // create request object
        HttpGet request = new HttpGet(DATA_SOURCE_URL);

        // send request
        CloseableHttpResponse response = httpClient.execute(request);

        // map response to java object
        List<CatsFact> catsFacts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<CatsFact>>() {});

        response.close();

        // filter facts
        List<CatsFact> catFactsNoVotes = catsFacts.stream()
                .filter(person -> person.getUpvotes() == 0)
                .collect(Collectors.toList());

        // print facts into console
        for (CatsFact fact: catFactsNoVotes) {
            System.out.println(fact);
        }

    }
}
