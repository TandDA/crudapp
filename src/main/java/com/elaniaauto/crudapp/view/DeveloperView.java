package com.elaniaauto.crudapp.view;

import com.elaniaauto.crudapp.controller.DeveloperController;
import com.elaniaauto.crudapp.controller.SkillController;
import com.elaniaauto.crudapp.controller.SpecialtyController;
import com.elaniaauto.crudapp.model.Developer;
import com.elaniaauto.crudapp.model.Skill;
import com.elaniaauto.crudapp.model.Specialty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    Scanner scanner;
    private DeveloperController developerController = new DeveloperController();
    private SkillController skillController = new SkillController();
    private SpecialtyController specialtyController = new SpecialtyController();
    DeveloperView(){
        scanner = new Scanner(System.in);
    }
    public void workProgram(){
        writeNewDeveloper();
        updateDeveloper();
        deleteDeveloper();
        getAllDeveloper();

    }
    public void writeNewDeveloper(){

        Developer developerToSave = new Developer();

        System.out.println("Введите firstName для Developer");
        String firstNameDeveloper = scanner.nextLine();
        developerToSave.setFirstName(firstNameDeveloper);

        System.out.println("Введите lastName для Developer");
        String lastNameDeveloper = scanner.nextLine();
        developerToSave.setLastName(lastNameDeveloper);

        System.out.println("Введите Id Skill для Developer");
        List<Skill> skill = skillRead();
        developerToSave.setSkills(skill);

        System.out.println("Введите Id Specialty для Developer");
        Specialty specialty = specialtyRead();
        developerToSave.setSpecialty(specialty);

        developerController.create(developerToSave);
    }

    public void updateDeveloper(){

        System.out.println("Введите id Developer для обновления ");
        Integer id = scanner.nextInt();

        System.out.println("Введите новое firstName Developer");
        String firstNameDeveloper = scanner.next();

        System.out.println("Введите новое lastName Developer");
        String lastNameDeveloper = scanner.next();

        System.out.println("Введите новое Id Skill для Developer");
        List<Skill>  skillDeveloper = skillRead();

        System.out.println("Введите новое название Developer");
        Specialty specialtyDeveloper = specialtyRead();

        Developer developerToSave = new Developer(id, firstNameDeveloper, lastNameDeveloper, skillDeveloper, specialtyDeveloper);

        developerController.update(developerToSave);
    }

    public void deleteDeveloper(){
        System.out.println("Введите id Developer для удаления");
        Integer id = scanner.nextInt();

        developerController.delete(id);
    }

    public void getAllDeveloper(){
        developerController.readAll();
    }

    public List<Skill> skillRead(){

        List<Skill> currentDeveloperSkills = new ArrayList<>();


        System.out.println("Введите id Skill");
        System.out.println(skillController.readAll());
        final int skillId = scanner.nextInt();
        currentDeveloperSkills.add(skillController.read(skillId));

        return currentDeveloperSkills;
    }

    public Specialty specialtyRead(){


        System.out.println("Введите Id Specialty");
        System.out.println(specialtyController.readAll());
        final int specialtyId = scanner.nextInt();

        return specialtyController.read(specialtyId);
    }
}
