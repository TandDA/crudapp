package com.elaniaauto.crudapp.repository.gson;

import com.elaniaauto.crudapp.model.Skill;
import com.elaniaauto.crudapp.model.Status;
import com.elaniaauto.crudapp.repository.SkillRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final String SKILL_FILE_PATH = "src/main/resources/skills.json";
    private final Gson GSON = new Gson();

    private List<Skill> readSkillsFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(SKILL_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Skill>>(){}.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String json = GSON.toJson(skills);
        try {
            Files.write(Paths.get(SKILL_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;

    }

    @Override
    public Skill getById(Integer id) {
        return readSkillsFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return readSkillsFromFile();
    }

    @Override
    public Skill save(Skill skillToSave) {
        List<Skill> currentSkills = readSkillsFromFile();

        Integer id = generateNewId(currentSkills);
        skillToSave.setId(id);
        currentSkills.add(skillToSave);
        writeSkillsToFile(currentSkills);
        return skillToSave;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> currentSkills = readSkillsFromFile();

        Integer id = skill.getId();
        Optional<Skill> sk = Optional.ofNullable(currentSkills.stream().filter(skl -> skl.getId() == id)
                .findAny()
                .orElse(null));
        if(sk!= null){
            sk.get().setName(skill.getName());
        }
        writeSkillsToFile(currentSkills);
        return sk.get();
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> currentSkills = readSkillsFromFile();

        Optional<Skill> sk = Optional.ofNullable(currentSkills.stream().filter(skl -> skl.getId() == id)
                .findAny()
                .orElse(null));
        if(sk!= null){
            sk.get().setStatus(Status.DELETED);
        }
        writeSkillsToFile(currentSkills);
    }
}
