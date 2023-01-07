package com.elaniaauto.crudapp.view;

import com.elaniaauto.crudapp.controller.SkillController;
import com.elaniaauto.crudapp.model.Skill;
import com.elaniaauto.crudapp.model.Status;

import java.util.Scanner;

public class  SkillView {
    Scanner scanner;
    private SkillController skillController = new SkillController();
    SkillView(){
        scanner = new Scanner(System.in);
    }
    public void workProgram(){

        writeNewSkill();
        updateSkill();
        deleteSkill();
        getAllSkill();

    }
    public void writeNewSkill(){
        Skill skillToSave = new Skill();
        System.out.println("Введите название Skill");
        String nameSkill = scanner.nextLine();
        skillToSave.setName(nameSkill);

        skillController.create(skillToSave);
    }

    public void updateSkill(){
        System.out.println("Введите id Skill для обновления ");
        Integer id = scanner.nextInt();
        System.out.println("Введите новое название Skill");
        String nameSkill = scanner.next();
        Skill skillToSave = new Skill(id, nameSkill);

        skillController.update(skillToSave);
    }

    public void deleteSkill(){
        System.out.println("Введите id Skill для удаления");
        Integer id = scanner.nextInt();

        skillController.delete(id);
    }

    public void getAllSkill(){
        skillController.readAll();
    }
}
