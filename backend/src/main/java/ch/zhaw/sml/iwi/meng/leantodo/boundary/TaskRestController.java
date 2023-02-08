package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Task;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TaskRepository;

@RestController
@CrossOrigin
public class TaskRestController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("api/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if(!tasks.isEmpty()) {
            return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/tasks/{id}")
    public ResponseEntity<Task> getTaskPerId(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            return new ResponseEntity<Task>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("api/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskRepository.save(task);
        return new ResponseEntity<Task>(newTask, HttpStatus.OK);
    }

    //@DeleteMapping("api/tasks/{id}")
}
