package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Projectlist")
public class ProjectList extends Lists{

    private String description;
    private int priority;
    private LocalDate due;
    private int status;
}
