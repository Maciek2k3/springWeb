package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        Task task = new Task(1L, "task1", "content1");
        TaskDto taskDto = new TaskDto(1L, "task1", "content1");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(task.getId(), mappedTask.getId());
        assertEquals(task.getTitle(), mappedTask.getTitle());
        assertEquals(task.getContent(), mappedTask.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "task1", "content1");
        TaskDto taskDto = new TaskDto(1L, "task1", "content1");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(mappedTaskDto.getId(),task.getId());
        assertEquals(mappedTaskDto.getTitle(),task.getTitle());
        assertEquals(mappedTaskDto.getContent(),task.getContent());
    }
}

