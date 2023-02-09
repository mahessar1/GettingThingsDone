package ch.zhaw.sml.iwi.meng.leantodo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskModel {

    private String title;
    private String description;
    private Integer status;
    private String category;
    private LocalDateTime dueDate;
    private Long listId;
    
}
