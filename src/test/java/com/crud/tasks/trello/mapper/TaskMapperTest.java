package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @InjectMocks
    TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(2L, "TaskDto1", "TaskDtoOne");

        //When
        Task testTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(2L, testTask.getId());
        assertEquals("TaskDto1", testTask.getTitle());
        assertEquals("TaskDtoOne", testTask.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task (1L, "Task1", "TaskOne");

        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, testTaskDto.getId());
        assertEquals("Task1", testTaskDto.getTitle());
        assertEquals("TaskOne", testTaskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(1L, "Task1", "TaskOne");
        Task task2 = new Task(2L, "Task2", "TaskTwo");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        // When
        List<TaskDto> testTaskDtoList = taskMapper.mapToTaskDtoList(taskList);
        List<TaskDto> testEmptyTaskDtoList = taskMapper.mapToTaskDtoList(new ArrayList<>());

        //Then
        assertEquals(2 , testTaskDtoList.size());
        assertEquals(2L, testTaskDtoList.get(1).getId());
        assertEquals("Task1", testTaskDtoList.get(0).getTitle());
        assertEquals("TaskTwo", testTaskDtoList.get(1).getContent());
        assertEquals(0, testEmptyTaskDtoList.size());
    }
}

