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

<<<<<<< HEAD
=======
    
    //public Lists getListFromTask();

>>>>>>> 99ed1c615e3c0d5b06de7621608735ea54a232b5
}
