package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.entity.ListRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Lists;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ProjectList;

@RestController
@CrossOrigin
public class ListRestController {

    @Autowired
    private ListRepository listRepository;

    @GetMapping(path = "api/lists/projects")
    public ResponseEntity<List<Lists>> getAllProjectLists() {
        List<Lists> result = listRepository.getAllProjectLists();
        if (!result.isEmpty()) {
        return new ResponseEntity<List<Lists>>(result, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    /*
     * @GetMapping(path = "/api/lists/actionlists")
     * public ResponseEntity<List<Lists>> getAllActionLists() {
     * List<Lists> result = listRepository.findAllActionList();
     * return new ResponseEntity<>(result, HttpStatus.OK);
     * }
     */

    @GetMapping(path = "/api/lists/{id}")
    public ResponseEntity<Lists> getListById(@PathVariable Long id) {
        Optional<Lists> result = listRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
