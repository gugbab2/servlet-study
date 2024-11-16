package org.example.todolist.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.todolist.model.Task;
import org.example.todolist.repository.TaskRepositoryV1;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.*;

@WebFilter("/tasks/*")
public class AuthorizationFilter implements Filter {
    private final TaskRepositoryV1 taskRepository = new TaskRepositoryV1();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//        String owner = req.getParameter("owner");
//        String taskId = req.getParameter("id");
//
//        if (taskId != null && owner != null) {
//            Task task = taskRepository.getTaskById(Integer.parseInt(taskId));
//            if (task != null && !task.getOwner().equals(owner)) {
//                ((HttpServletResponse) response).sendError(SC_FORBIDDEN, "권한이 없습니다.");
//                return;
//            }
//        }
        chain.doFilter(request, response);
    }
}
