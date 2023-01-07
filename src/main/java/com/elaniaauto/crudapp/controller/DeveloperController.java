package com.elaniaauto.crudapp.controller;

import com.elaniaauto.crudapp.model.Developer;
import com.elaniaauto.crudapp.repository.DeveloperRepository;
import com.elaniaauto.crudapp.repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController{
    private DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    public Developer create(Developer developer) {
        developerRepository.save(developer);
        return developer;
    }

    public Developer read(Integer id) {
        return developerRepository.getById(id);
    }

    public List<Developer> readAll() {
        return developerRepository.getAll();
    }

    public void update(Developer developer){
        developerRepository.update(developer);
    }

    public void delete(Integer id) {
        developerRepository.deleteById(id);
    }
}
