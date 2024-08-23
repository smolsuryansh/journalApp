package com.hammy.journalApp.cache;

import com.hammy.journalApp.entity.ConfigJournalApp;
import com.hammy.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public Map<String, String> APP_CACHE;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;


    @PostConstruct // this method will be called the moment this bean is made
    public void init() {
        // reinitialize APP_CACHE to avoid creating two keys after making a change in api
        APP_CACHE = new HashMap<>();
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();

        for (ConfigJournalApp configJournalApp : all) {
            APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }

}
