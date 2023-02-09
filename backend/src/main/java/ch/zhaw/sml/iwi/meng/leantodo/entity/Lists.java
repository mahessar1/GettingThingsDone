package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Listtype", discriminatorType = DiscriminatorType.STRING)
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime created;

    @OneToMany
    @JsonIgnoreProperties("lists")
    private List<Task> taskList;

    public void addTask(Task task) {
        if (!this.taskList.contains(task)) {
            this.taskList.add(task);
            if (task.getLists() == null) {
                task.setLists(this);
            }
        }
    }

}
