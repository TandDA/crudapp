package com.elaniaauto.crudapp.view;

import com.elaniaauto.crudapp.controller.SkillController;
import com.elaniaauto.crudapp.controller.SpecialtyController;
import com.elaniaauto.crudapp.model.Skill;
import com.elaniaauto.crudapp.model.Specialty;

import java.util.Scanner;

public class SpecialtyView {
    Scanner scanner;
    private SpecialtyController specialtyController = new SpecialtyController();
    SpecialtyView(){
        scanner = new Scanner(System.in);
    }
    public void workProgram(){
        writeNewSpecialty();
        updateSpecialty();
        deleteSpecialty();
        getAllSpeciality();

    }
    public void writeNewSpecialty(){
        Specialty specialtyToSave = new Specialty();
        System.out.println("Введите название Specialty");
        String nameSpecialty = scanner.nextLine();
        specialtyToSave.setName(nameSpecialty);

        specialtyController.create(specialtyToSave);

    }

    public void updateSpecialty(){
        System.out.println("Введите id Specialty для обновления ");
        Integer id = scanner.nextInt();
        System.out.println("Введите новое название Specialty");
        String nameSpecialty = scanner.next();
        Specialty specialtyToSave = new Specialty(id, nameSpecialty);

        specialtyController.update(specialtyToSave);
    }

    public void deleteSpecialty(){
        System.out.println("Введите id Specialty для удаления");
        Integer id = scanner.nextInt();

        specialtyController.delete(id);
    }

    public void getAllSpeciality(){
        specialtyController.readAll();
    }
}
