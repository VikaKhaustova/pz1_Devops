package org.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.io.IOException;
import java.util.Base64;

public class JenkinsJobR {

  ion e) {
                throw new RuntimeException(e);
            }
        }

    }

    // Метод для кодування авторизаційних даних
    private static String getAuthHeader() {
        String auth = USERNAME + ":" + API_TOKEN;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }
}
