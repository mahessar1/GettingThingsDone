package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.entity.ListRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Lists;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Task;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TaskRepository;
import ch.zhaw.sml.iwi.meng.leantodo.model.TaskModel;

@RestController
@CrossOrigin
public class TaskRestController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListRepository listRepository;

    @GetMapping("api/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (!tasks.isEmpty()) {
            return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/tasks/{id}")
    public ResponseEntity<Task> getTaskPerId(@PathVariable("id") Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity<Task>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/lists/{id}/tasks")
    public ResponseEntity<List<Task>> getTasksInList(@PathVariable("id") Long id) {
        List<Task> projectTasks = taskRepository.getTasksPerList(id);
        if (!projectTasks.isEmpty()) {
            return new ResponseEntity<List<Task>>(projectTasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("api/tasks")
    public ResponseEntity<Task> createTask(@RequestBody TaskModel taskModel) {

        try {
            Task result = new Task();
            result.setTitle(taskModel.getTitle());
            result.setDescription(taskModel.getDescription());
            result.setCreated(LocalDateTime.now());
            result.setStatus(taskModel.getStatus());
            result.setCategory(taskModel.getCategory());
            result.setDueDate(taskModel.getDueDate());

            if (!(taskModel.getListId() == null)) {
                Lists lists = listRepository.findById(taskModel.getListId()).get();
                result.setLists(lists);
                lists.addTask(result);
            } else {
                result.setLists(null);
            }


            taskRepository.save(result);
            return new ResponseEntity<Task>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("api/tasks/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable("id") Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("api/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody TaskModel taskModel) {
        Optional<Task> updateTask = taskRepository.findById(id);
        if (updateTask.isPresent()) {
            updateTask.get().setTitle(taskModel.getTitle());
            updateTask.get().setDescription(taskModel.getDescription());
            updateTask.get().setStatus(taskModel.getStatus());
            updateTask.get().setCategory(taskModel.getCategory());
            updateTask.get().setDueDate(taskModel.getDueDate());

            if (!(taskModel.getListId() == null)) {
                Lists lists = listRepository.findById(taskModel.getListId()).get();
                updateTask.get().setLists(lists);
                lists.addTask(updateTask.get());
            } else {
                updateTask.get().setLists(null);
            }

            taskRepository.save(updateTask.get());
            return new ResponseEntity<Task>(updateTask.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }
}
