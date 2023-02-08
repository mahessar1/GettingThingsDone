package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<Lists, Long> {

    @Query("SELECT l from Lists l WHERE TYPE(l) = Projectlist")
    public List<Lists> getAllProjectLists();

    /*
     * @Query("SELECT l from Lists l WHERE l.listtype = 'Actionlist'")
     * public List<Lists> findAllActionList();
     */

}
