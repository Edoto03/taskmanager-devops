package com.university.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.taskmanager.model.Task;
import com.university.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private Task testTask;

    @BeforeEach
    void setUp() {
        testTask = new Task();
        testTask.setId(1L);
        testTask.setTitle("Test Task");
        testTask.setDescription("Test Description");
        testTask.setStatus("PENDING");
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() throws Exception {
        // Arrange
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Second Task");
        task2.setStatus("COMPLETED");

        List<Task> tasks = Arrays.asList(testTask, task2);
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2)).andExpect(jsonPath("$[0].title").value("Test Task"))
            .andExpect(jsonPath("$[1].title").value("Second Task"));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getTaskById_WhenTaskExists_ShouldReturnTask() throws Exception {
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(testTask));

        mockMvc.perform(get("/api/tasks/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("Test Task"))
            .andExpect(jsonPath("$.description").value("Test Description"))
            .andExpect(jsonPath("$.status").value("PENDING"));

        verify(taskService, times(1)).getTaskById(1L);
    }

    @Test
    void getTaskById_WhenTaskDoesNotExist_ShouldReturn404() throws Exception {
        when(taskService.getTaskById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/999")).andExpect(status().isNotFound());

        verify(taskService, times(1)).getTaskById(999L);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() throws Exception {
        Task newTask = new Task();
        newTask.setTitle("New Task");
        newTask.setDescription("New Description");
        newTask.setStatus("PENDING");

        when(taskService.createTask(any(Task.class))).thenReturn(testTask);

        mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask))).andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.title").value("Test Task"));

        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    void updateTask_WhenTaskExists_ShouldReturnUpdatedTask() throws Exception {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setStatus("COMPLETED");

        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/api/tasks/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTask))).andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Updated Task")).andExpect(jsonPath("$.status").value("COMPLETED"));

        verify(taskService, times(1)).updateTask(eq(1L), any(Task.class));
    }

    @Test
    void updateTask_WhenTaskDoesNotExist_ShouldReturn404() throws Exception {
        Task updateData = new Task();
        updateData.setTitle("Updated Task");

        when(taskService.updateTask(eq(999L), any(Task.class))).thenThrow(new RuntimeException("Task not found"));

        mockMvc.perform(put("/api/tasks/999").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateData))).andExpect(status().isNotFound());

        verify(taskService, times(1)).updateTask(eq(999L), any(Task.class));
    }

    @Test
    void deleteTask_ShouldReturnNoContent() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1")).andExpect(status().isNoContent());

        verify(taskService, times(1)).deleteTask(1L);
    }
}