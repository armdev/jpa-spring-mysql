package io.project.repository;

import io.project.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public int updateEmail(Long userId, User user) {
        try {
            int executeUpdate = em.createQuery("UPDATE User o SET o.email=?1 WHERE o.id=?2")
                    .setParameter(1, user.getEmail()).setParameter(2, userId).executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
   
    public List<User> getList(Integer start, Integer max) {
        List<User> finalList = new ArrayList<>();
        try {          
            Query query = em.createQuery("SELECT c FROM User c WHERE c.id > 0 ORDER BY c.id DESC");
            if (start != null) {
                query.setFirstResult(start);
            }
            if (max != null) {
                query.setMaxResults(max);
            }
            if (!query.getResultList().isEmpty()) {
                finalList = query.getResultList();
            }                        
        } catch (Exception e) {
        } 
        return finalList;
    }

    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    public void delete(Long id) {
        User course = findById(id);
        em.remove(course);
    }

}
