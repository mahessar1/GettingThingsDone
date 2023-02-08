package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListRepository extends JpaRepository<Lists, Long> {

    @Query("SELECT l from List as l WHERE l.listtype = Projectlist")
    public List<Lists> findAllProjectList();

    @Query("SELECT l from List as l WHERE l.listtype = Actionlist")
    public List<Lists> findAllActionList();

}
