import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientPost {

    public static final String REMOTE_SERVICE_URI = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws IOException {

        // create http client with default settings
        CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpPost httpPost = new HttpPost(REMOTE_SERVICE_URI);

        final List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("title", "foo"));
        params.add(new BasicNameValuePair("body", "bar"));
        params.add(new BasicNameValuePair("userId", "1"));
        // url encoding data for post request
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        try (
                // call server through httpClient and send post
                CloseableHttpResponse response2 = httpClient.execute(httpPost)) {
            final HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(entity2));
        }
        httpClient.close();
    }
}
