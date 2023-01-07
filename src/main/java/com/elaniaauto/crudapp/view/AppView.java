package com.elaniaauto.crudapp.view;

import java.util.Scanner;

public class AppView {

    SkillView skillView = new SkillView();
    SpecialtyView specialtyView = new SpecialtyView();
    DeveloperView developerView = new DeveloperView();
    Scanner scanner = new Scanner(System.in);
    public void runApp(){
        while(true)
        {
            mainMenu();
        }
       // skillView.workProgram();
        // specialtyView.workProgram();
       // developerView.workProgram();
    }
    private void mainMenu(){
        System.out.println("Введите номер действия");
        System.out.println("1) Управлять Skill");
        System.out.println("2) Управлять Developer");
        System.out.println("3) Управлять Specialty");

        int num = scanner.nextInt();
        switch (num){
            case 1:
                skillMenu();
                break;
            case 2:
                developerMenu();
                break;
            case 3:
                specialtyMenu();
                break;
            default:
                mainMenu();
                break;
        }
    }
    private  void skillMenu(){
        System.out.println("Введите номер действия");
        System.out.println("1) новый Skill");
        System.out.println("2) обновить Skill");
        System.out.println("3) удалить Skill");

        int num = scanner.nextInt();
        switch (num){
            case 1:
                skillView.writeNewSkill();
                break;
            case 2:
                skillView.updateSkill();
                break;
            case 3:
                skillView.deleteSkill();
                break;
            default:
                mainMenu();
                break;
        }
        mainMenu();
    }
    private  void developerMenu(){
        System.out.println("Введите номер действия");
        System.out.println("1) новый Developer");
        System.out.println("2) обновить Developer");
        System.out.println("3) удалить Developer");

        int num = scanner.nextInt();
        switch (num){
            case 1:
                developerView.writeNewDeveloper();
                break;
            case 2:
                developerView.updateDeveloper();
                break;
            case 3:
                developerView.deleteDeveloper();
                break;
            default:
                mainMenu();
                break;
        }
        mainMenu();
    }
    private  void specialtyMenu(){
        System.out.println("Введите номер действия");
        System.out.println("1) новый Specialty");
        System.out.println("2) обновить Specialty");
        System.out.println("3) удалить Specialty");

        int num = scanner.nextInt();
        switch (num){
            case 1:
                specialtyView.writeNewSpecialty();
                break;
            case 2:
                specialtyView.updateSpecialty();
                break;
            case 3:
                specialtyView.deleteSpecialty();
                break;
            default:
                mainMenu();
                break;
        }
        mainMenu();
    }
}
