package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<Lists, Long> {

    @Query("from ProjectList")
    public List<Lists> getAllProjectLists();

    @Query("from ActionList")
    public List<Lists> findAllActionList();

    
    //public Lists getListFromTask();

}
