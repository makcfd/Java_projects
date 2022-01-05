import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HttpClient {


    public final static String DATA_SOURCE_URL = "https://api.nasa.gov/planetary/apod?api_key=";
    public final static String API_KEY = "3tjRU1WBqxprOm7WftpOxdobtya7VBSxRFg84jAc";

    public static ObjectMapper mapper = new ObjectMapper();

    static InputStream is = null;

    public static void main(String[] args) throws IOException {

        String URL_TO_ACCESS = DATA_SOURCE_URL + API_KEY;

        // create http client
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        // create request object
        HttpGet request = new HttpGet(URL_TO_ACCESS);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        // send request
        CloseableHttpResponse response = httpClient.execute(request);

        /**
         * commented out lines below related to failed attempt to store NASA JSON to JAVA object using object mapper.
         * mapper itself returns data correctly.
         * I couldn't find a way to store data into JAVA object.
         * So I stored it without http request.
         * I directly passed URL and object class to mapper in line 67
         */

        //System.out.println("====================================== \n");
        // line below works fine
        //System.out.println(mapper.readValue(response.getEntity().getContent(), new TypeReference<NasaApiResponse>() {}));

        // i tried to store data into JAVA object like this, but it fails
        //NasaApiResponse nasaResponse = mapper.readValue(response.getEntity().getContent(), NasaApiResponse.class);

        // i also tried this option unfortunately without any success
        //NasaApiResponse nasaResponse = mapper.readValue(response.getEntity().getContent(), new TypeReference<NasaApiResponse>() {});
        //System.out.println("====================================== \n");

        // map url and java object
        NasaApiResponse nasaObject = mapper.readValue(new URL(URL_TO_ACCESS), NasaApiResponse.class);

        // ============================================
        //  the second step of the task
        // ============================================

        String urlDataToStore = nasaObject.getUrl();
        HttpGet requestToStoreData = new HttpGet(urlDataToStore);
        requestToStoreData.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        // send request
        CloseableHttpResponse httpResponse = httpClient.execute(requestToStoreData);

        // from response retrieving inputStream
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();

        // preparing file name to store picture
        String[] result = urlDataToStore.split("/");
        String fileName = result[result.length-1];
        System.out.println("file name is: " + fileName);

        // calling method to store data and passing inputStream and file name
        saveImage(is, fileName);

        // closing limited resources
        response.close();
        httpClient.close();

    }

    public static void saveImage(InputStream is, String fileName) throws IOException {

        OutputStream os = new FileOutputStream(fileName);

        byte[] b = new byte[2048];
        int length;
        // reading inputStream in loop and writing into a outputStream
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        // closing streams
        is.close();
        os.close();
    }

}
