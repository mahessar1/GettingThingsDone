package ch.zhaw.sml.iwi.meng.leantodo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
