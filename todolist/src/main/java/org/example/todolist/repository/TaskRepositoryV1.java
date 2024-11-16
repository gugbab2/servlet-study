package org.example.todolist.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.todolist.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class TaskRepositoryV1 {
    private static final String FILE_PATH = Paths.get("temp", "tasks.json").toAbsolutePath().toString();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Task> tasks;

    public TaskRepositoryV1() {
        tasks = loadTasksFromFile();
    }

    private List<Task> loadTasksFromFile() {
        try {
            Path filePath = Paths.get(FILE_PATH);

            // temp 디렉토리 확인 및 생성
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
                System.out.println("temp 디렉토리 생성");
            }

            // tasks.json 파일 확인 및 생성
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.writeString(filePath, "[]", CREATE);
                System.out.println("tasks.json 파일 생성");
            }

            // 파일에서 JSON 데이터 읽기
            String jsonData = Files.readString(filePath);
            return objectMapper.readValue(jsonData, new TypeReference<List<Task>>() {});

        } catch (IOException e) {
            e.printStackTrace(); // 예외 처리 개선 필요
        }
        return new ArrayList<>();
    }

    private void saveTasksToFile() {
        try {
            String jsonData = objectMapper.writeValueAsString(tasks);
            Files.writeString(Paths.get(FILE_PATH), jsonData, CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // 예외 처리 개선 필요
        }
    }

    public synchronized void addTask(Task task) {
        // ID가 주어지지 않으면 새로 추가
        if (task.getId() == null || task.getId() <= 0) {
            task.setId(tasks.size() + 1); // 새로운 ID 설정
        } else {
            // ID가 주어진 경우 기존 작업을 제거하여 수정할 준비
            deleteTask(task.getId());
        }
        tasks.add(task);
        saveTasksToFile();
    }

    public synchronized List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public synchronized Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null; // 해당 ID의 작업이 없는 경우 null 반환
    }

    public synchronized void deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                break; // 작업을 찾으면 루프 종료
            }
        }
        saveTasksToFile();
    }
}