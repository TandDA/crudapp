package com.elaniaauto.crudapp.repository.gson;

import com.elaniaauto.crudapp.model.Developer;
import com.elaniaauto.crudapp.model.Specialty;
import com.elaniaauto.crudapp.model.Status;
import com.elaniaauto.crudapp.repository.DeveloperRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private final String DEVELOPER_FILE_PATH = "src/main/resources/developers.json";
    private final Gson GSON = new Gson();

    private List<Developer> readDeveloperFromFile() {
        String fileContent;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(DEVELOPER_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Developer>>(){}.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private void writeDeveloperToFile(List<Developer> developers) {
        String json = GSON.toJson(developers);
        try {
            Files.write(Paths.get(DEVELOPER_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Developer> developers) {
        Developer maxIdDeveloper = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdDeveloper) ? maxIdDeveloper.getId() + 1 : 1;

    }

    @Override
    public Developer getById(Integer id) {
        return readDeveloperFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Developer save(Developer developerToSave) {
        List<Developer> currentDeveloper = readDeveloperFromFile();

        Integer id = generateNewId(currentDeveloper);
        developerToSave.setId(id);
        currentDeveloper.add(developerToSave);
        writeDeveloperToFile(currentDeveloper);
        return developerToSave;
    }


    @Override
    public List<Developer> getAll() {
        return readDeveloperFromFile();
    }


    @Override
    public Developer update(Developer developer) {
        List<Developer> currentDeveloper = readDeveloperFromFile();

        Integer id = developer.getId();
        Optional<Developer> sk = Optional.ofNullable(currentDeveloper.stream().filter(skl -> skl.getId() == id)
                .findAny()
                .orElse(null));
        if(sk!= null){
            sk.get().setFirstName(developer.getFirstName());
            sk.get().setLastName(developer.getLastName());
        }
        writeDeveloperToFile(currentDeveloper);
        return developer;
    }

    @Override
    public void deleteById(Integer id) {
        List<Developer> currentDeveloper = readDeveloperFromFile();
        currentDeveloper = currentDeveloper.stream()
                .peek(s -> {
                    if (Objects.equals(s.getId(), id)) {
                        s.setStatus(Status.DELETED);
                    }
                })
                .collect(Collectors.toList());
        this.writeDeveloperToFile(currentDeveloper);
    }
}
