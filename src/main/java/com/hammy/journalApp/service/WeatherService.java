package com.hammy.journalApp.service;

import com.hammy.journalApp.api.response.WeatherResponse;
import com.hammy.journalApp.cache.AppCache;
import com.hammy.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city) {
        WeatherResponse cacheResponse = redisService.get("weather_of_" + city, WeatherResponse.class);

        if (cacheResponse != null) {
            return cacheResponse;
        } else {
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
}
