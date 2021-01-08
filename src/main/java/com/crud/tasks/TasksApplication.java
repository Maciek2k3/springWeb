package com.crud.tasks;

import com.crud.tasks.controller.TaskContoller;
import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		TaskContoller taskContoller=new TaskContoller();

		SpringApplication.run(TasksApplication.class, args);
	}

}
