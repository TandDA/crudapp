package com.elaniaauto.crudapp.controller;

import com.elaniaauto.crudapp.model.Skill;
import com.elaniaauto.crudapp.repository.SkillRepository;
import com.elaniaauto.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepository skillRepository = new GsonSkillRepositoryImpl();

    public Skill create(Skill skill){
        skillRepository.save(skill);
        return skill;
    }
    public Skill read(Integer id){
        return skillRepository.getById(id);
    }

    public List<Skill> readAll(){
        return skillRepository.getAll();
    }

    public void update(Skill skill){
        skillRepository.update(skill);
    }

    public void delete(Integer id){
        skillRepository.deleteById(id);
    }
}
