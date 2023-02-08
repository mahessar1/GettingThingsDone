package ch.zhaw.sml.iwi.meng.leantodo.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TaskModel {

    private String title;
    private String description;
    private Integer status;
    private String category;
    private LocalDate dueDate;
    private Long listId;
    
}
