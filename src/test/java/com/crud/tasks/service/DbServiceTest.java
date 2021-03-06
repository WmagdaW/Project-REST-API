package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasksTest() {
        //Given

        Task task1 = new Task(1L, "Task1", "TaskOne");
        Task task2 = new Task(2L, "Task2", "TaskTwo");
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);

        when(repository.findAll()).thenReturn(list);

        //When
        List<Task> testList = dbService.getAllTasks();

        //Then
        assertEquals(2, testList.size());
        assertEquals("Task1", testList.get(0).getTitle());
    }

    @Test
    public void getAllTasksWithEmptyListTest() {
        //Given
        List<Task> list = new ArrayList<>();

        when(repository.findAll()).thenReturn(list);

        //When
        List<Task> testEmptyList = dbService.getAllTasks();

        //Then
        assertEquals(0, testEmptyList.size());
    }

    @Test
    public void getTaskTest()  {
        //Given
        Task task1 = new Task(1L, "Task1", "TaskOne");

        when(repository.findById(1L)).thenReturn(Optional.of(task1));

        //When
        Optional <Task> testTask = dbService.getTask(1L);

        //Then
        assertEquals("Task1", testTask.get().getTitle());
    }

    @Test
    public void getTaskThatDoesNotExistTest() {
        //Given
        when(repository.findById(1L)).thenReturn(null);

        //When
        Optional<Task> testTask = dbService.getTask(1L);

        //Then
        assertNull(testTask);
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task1 = new Task(1L, "Task1", "TaskOne");
        when(repository.save(task1)).thenReturn(task1);

        //When
        Task testTask = dbService.saveTask(task1);

        //Then
        assertEquals(task1.getId(), testTask.getId());
        assertEquals(task1.getTitle(), testTask.getTitle());
        assertEquals(task1.getContent(), testTask.getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given

        //When
        dbService.deleteTask(1L);

        //Then
        then(repository).should().deleteById(1L);
    }
}