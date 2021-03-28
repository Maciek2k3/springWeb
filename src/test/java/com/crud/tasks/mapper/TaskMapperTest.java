package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    @Test
    public void mapTaskDtoList(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "task1", "content1");
        List<TaskDto> taskDtoList= Arrays.asList(taskDto);

        Task task = new Task(1L, "task1", "content1");
        List<Task> taskList=Arrays.asList(task);
        //When
        List<TaskDto> taskDtoList1=taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(taskDtoList1);
        assertEquals(1,taskDtoList1.size());

        taskDtoList1.forEach(t->{
            assertEquals(taskDto.getId(),t.getId());
            assertEquals(taskDto.getTitle(),t.getTitle());
            assertEquals(taskDto.getContent(),t.getContent());
        });

    }
}

