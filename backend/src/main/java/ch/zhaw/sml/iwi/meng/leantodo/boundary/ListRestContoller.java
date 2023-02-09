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

import ch.zhaw.sml.iwi.meng.leantodo.entity.ActionList;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ListRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Lists;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ProjectList;

@RestController
@CrossOrigin
public class ListRestContoller {

    @Autowired
    private ListRepository listRepository;

    @GetMapping(path = "api/lists")
    public ResponseEntity<List<Lists>> getAllLists() {
        List<Lists> result = listRepository.findAll();
        if (!result.isEmpty()) {
            return new ResponseEntity<List<Lists>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "api/lists/projectlists")
    public ResponseEntity<List<Lists>> getAllProjectLists() {
        List<Lists> result = listRepository.getAllProjectLists();
        if (!result.isEmpty()) {
            return new ResponseEntity<List<Lists>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "api/lists/actionlists")
    public ResponseEntity<List<Lists>> getAllActionLists() {
        List<Lists> result = listRepository.findAllActionList();
        return new ResponseEntity<List<Lists>>(result, HttpStatus.OK);
    }

    @GetMapping(path = "api/lists/{id}")
    public ResponseEntity<Lists> getListById(@PathVariable Long id) {
        Optional<Lists> result = listRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Lists>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Lists>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "api/lists/projectlists")
    public ResponseEntity<Lists> createProject(@RequestBody ProjectList projectList) {

        projectList.setCreated(LocalDateTime.now());
        Lists result = listRepository.save(projectList);
        return new ResponseEntity<Lists>(result, HttpStatus.CREATED);
    }

    @PostMapping(path = "api/lists/actionlists")
    public ResponseEntity<Lists> createActionList(@RequestBody ActionList actionList) {

        actionList.setCreated(LocalDateTime.now());
        Lists result = listRepository.save(actionList);
        return new ResponseEntity<Lists>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "api/lists/actionlists/{id}")
    public ResponseEntity<Lists> changeActionList(@PathVariable Long id, @RequestBody ActionList actionList) {
        Optional<Lists> optionalList = listRepository.findById(id);

        if (optionalList.isPresent()) {
            Lists result = optionalList.get();
            actionList.setId(result.getId());
            actionList.setCreated(result.getCreated());
            listRepository.save(actionList);
            return new ResponseEntity<Lists>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "api/lists/projectlists/{id}")
    public ResponseEntity<Lists> changeProjectList(@PathVariable Long id, @RequestBody ProjectList projectList) {
        Optional<Lists> optionalList = listRepository.findById(id);

        if (optionalList.isPresent()) {
            Lists result = optionalList.get();
            projectList.setId(result.getId());
            projectList.setCreated(result.getCreated());
            listRepository.save(projectList);
            return new ResponseEntity<Lists>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "api/lists/{id}")
    public ResponseEntity<Lists> deleteList(@PathVariable Long id) {
        Optional<Lists> optionalList = listRepository.findById(id);

        if (optionalList.isPresent()) {
            listRepository.delete(optionalList.get());
            return new ResponseEntity<Lists>(optionalList.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
