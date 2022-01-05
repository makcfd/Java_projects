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
import java.util.Arrays;
import java.util.List;

public class HttpClient {

    public static final String REMOTE_SERVICE_URI = "https://jsonplaceholder.typicode.com/posts";
    // mapper one instance per app as static variable
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        // initialize connection and defining connection settings
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My test Service")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false) // do not process 301 and 302 errors
                        .build())
                .build();

        // create request object
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        // send request
        CloseableHttpResponse response = httpClient.execute(request);

        // print received data
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

        List<Post> posts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Post>>() {});

        posts.forEach(System.out::println);

        response.close();

        httpClient.close();

    }
}
