package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Projectlist")
public class ProjectList extends Lists{

    private String description;
    private int priority;
    private LocalDateTime due;
    private int status;
}
