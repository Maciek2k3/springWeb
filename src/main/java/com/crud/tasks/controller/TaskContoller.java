package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskContoller {

    private DbService service;
    private TaskMapper taskMapper;

    @Autowired
    public TaskContoller(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    public TaskContoller() {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }


   /* @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) {
        return new TaskDto(1L, "test title", "test_content");

    }*/

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    @ResponseBody
    public String getTask(
            @RequestParam("id") long id) {
        return "Get a specific task with id=" + id;
    }

    /*  @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
      public void deleteTask(@RequestParam Long taskId) {

      }*/
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam long id) {
        System.out.println("Deleted");
        service.deleteById(id);
    }

    // public void delete(@RequestParam Long id) {
    //   videoCasseteManager.deleteById(id);

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(@RequestBody TaskDto taskDto) {

    }
}

