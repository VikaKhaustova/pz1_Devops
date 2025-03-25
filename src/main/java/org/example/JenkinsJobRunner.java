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

public class JenkinsJobRunner {

    private static final String JENKINS_URL = "http://localhost:8080";  // Адреса сервера Jenkins
    private static final String JOB_NAME = "testpipeline-build";         // Назва job, яку треба запустити
    private static final String USERNAME = "admin";                     // Логін Jenkins
    private static final String API_TOKEN = "your-api-token";           // API-токен Jenkins

    public static void main(String[] args) throws IOException {
        triggerJob();
        checkJobStatus();
    }

    // Метод для запуску Jenkins job
    private static void triggerJob() throws IOException {
        String triggerUrl = JENKINS_URL + "/job/" + JOB_NAME + "/build";
        sendPostRequest(triggerUrl);
        System.out.println("Запущено завдання: " + JOB_NAME);
    }

    // Метод для перевірки статусу job
    private static void checkJobStatus() throws IOException {
        String statusUrl = JENKINS_URL + "/job/" + JOB_NAME + "/lastBuild/api/json";
        String response = sendGetRequest(statusUrl);
        System.out.println("Статус останньої збірки: " + response);
    }

    // Виконання POST-запиту
    private static void sendPostRequest(String url) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Authorization", "Basic " + getAuthHeader());
            try (CloseableHttpResponse response = client.execute(post)) {
                System.out.println("HTTP Відповідь: " + response.getCode());
            }
        }
    }

    // Виконання GET-запиту
    private static String sendGetRequest(String url) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Authorization", "Basic " + getAuthHeader());
            try (CloseableHttpResponse response = client.execute(get)) {
                return EntityUtils.toString(response.getEntity());
            } catch (ParseException e) {
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
