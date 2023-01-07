package com.elaniaauto.crudapp.controller;

import com.elaniaauto.crudapp.model.Specialty;
import com.elaniaauto.crudapp.repository.SpecialtyRepository;
import com.elaniaauto.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {
    private SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();

    public Specialty create(Specialty specialty){
        specialtyRepository.save(specialty);
        return specialty;
    }

    public Specialty read(Integer id){
        return specialtyRepository.getById(id);
    }
    public List<Specialty> readAll(){
        return specialtyRepository.getAll();
    }

    public void update(Specialty specialty){
        specialtyRepository.update(specialty);
    }

    public void delete(Integer id){
        specialtyRepository.deleteById(id);
    }
}
