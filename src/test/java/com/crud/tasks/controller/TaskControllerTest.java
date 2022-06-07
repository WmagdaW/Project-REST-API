package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


@SpringJUnitWebConfig
    @WebMvcTest(TaskController.class)
    class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService service;

        @Test
        public void shouldGetAllTasks() throws Exception{
            // Given
            TaskDto task1 = new TaskDto(1L, "Task1", "TaskOne");
            List<TaskDto> taskList = new ArrayList<>();
            taskList.add(task1);

            when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskList);

            //When & Then
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/v1/task/getTasks")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Task1")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("TaskOne")));
        }

    @Test
    public void shouldGetTaskById() throws Exception{
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task1", "TaskOne");
        Task task = new Task(1L, "Task1", "TaskOne");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.getTask(1L)).thenReturn(java.util.Optional.of(task));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("TaskOne")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        // Given
        Task task = new Task(1L, "Task1", "TaskOne");

        when(service.getTask(1L)).thenReturn(java.util.Optional.of(task));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        verify(service, times(1)).deleteTask(1L);

    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task1", "TaskOne");
        Task task = new Task(1L, "Task1", "TaskOne");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("TaskOne")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task1", "TaskOne");
        Task task = new Task(1L, "Task1", "TaskOne");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));// or isOk()

    }

}


