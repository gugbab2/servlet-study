package org.example.todolist.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.todolist.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.todolist.repository.TaskRepositoryV1;

import java.io.IOException;
import java.util.List;

import static jakarta.servlet.http.HttpServletResponse.*;

@WebServlet("v1/tasks")
public class TaskControllerV1 extends HttpServlet {
    private final TaskRepositoryV1 taskRepository = new TaskRepositoryV1();
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Task task = taskRepository.getTaskById(id);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (task != null) {
                response.getWriter().write(objectMapper.writeValueAsString(task));
            } else {
                response.setStatus(SC_NOT_FOUND);
            }
        } else {
            List<Task> tasks = taskRepository.getAllTasks();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = objectMapper.writeValueAsString(tasks);
            response.getWriter().write(jsonResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String owner = request.getParameter("owner");
        String description = request.getParameter("description");

        Task task = new Task(taskRepository.getAllTasks().size() + 1, owner, description);
        taskRepository.addTask(task);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(task));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String owner = request.getParameter("owner");
        String description = request.getParameter("description");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Task task = taskRepository.getTaskById(id);
            if (task != null) {
                task.setOwner(owner);
                task.setDescription(description);
                taskRepository.addTask(task);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(task));
            } else {
                response.setStatus(SC_NOT_FOUND);
            }
        } else {
            response.setStatus(SC_BAD_REQUEST); // 잘못된 요청
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            taskRepository.deleteTask(id);

            response.setStatus(SC_NO_CONTENT);
        } else {
            response.setStatus(SC_BAD_REQUEST);
        }
    }
}
