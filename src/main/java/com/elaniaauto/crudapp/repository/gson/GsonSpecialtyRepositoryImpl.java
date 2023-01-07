package com.elaniaauto.crudapp.repository.gson;


import com.elaniaauto.crudapp.model.Skill;
import com.elaniaauto.crudapp.model.Specialty;
import com.elaniaauto.crudapp.model.Status;
import com.elaniaauto.crudapp.repository.SpecialtyRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final String Specialty_FILE_PATH = "src/main/resources/specialties.json";
    private final Gson GSON = new Gson();

    private List<Specialty> readSpecialtyFromFile() {
        String fileContent;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(Specialty_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Specialty>>(){}.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeSpecialtyToFile(List<Specialty> specialties) {
        String json = GSON.toJson(specialties);
        try {
            Files.write(Paths.get(Specialty_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Integer generateNewId(List<Specialty> specialties) {
        Specialty maxIdSpecialty = specialties.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(maxIdSpecialty) ? maxIdSpecialty.getId() + 1 : 1;

    }
    @Override
    public Specialty getById(Integer id) {
        return readSpecialtyFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return readSpecialtyFromFile();
    }

    @Override
    public Specialty save(Specialty specialtyToSave) {
        List<Specialty> currentSpecialty = readSpecialtyFromFile();

        Integer id = generateNewId(currentSpecialty);
        specialtyToSave.setId(id);
        currentSpecialty.add(specialtyToSave);
        writeSpecialtyToFile(currentSpecialty);
        return specialtyToSave;
    }

    @Override
    public Specialty update(Specialty specialty) {
        List<Specialty> currentSpecialty = readSpecialtyFromFile();

        Integer id = specialty.getId();
        Optional<Specialty> sk = Optional.ofNullable(currentSpecialty.stream().filter(skl -> skl.getId() == id)
                .findAny()
                .orElse(null));
        if(sk!= null){
            sk.get().setName(specialty.getName());
        }
        writeSpecialtyToFile(currentSpecialty);
        return specialty;
    }

    @Override
    public void deleteById(Integer id) {
        List<Specialty> currentSpecialty = readSpecialtyFromFile();

        currentSpecialty = currentSpecialty.stream()
                .peek(s -> {
                    if (Objects.equals(s.getId(), id)) {
                        s.setStatus(Status.DELETED);
                    }
                })
                .collect(Collectors.toList());
        this.writeSpecialtyToFile(currentSpecialty);
    }
}
