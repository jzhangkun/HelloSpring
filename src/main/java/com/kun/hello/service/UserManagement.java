package com.kun.hello.service;

import com.kun.hello.dao.UserRepository;
import com.kun.hello.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kun.hello.util.QueryUtil;

@Service
public class UserManagement {

    Logger logger = LoggerFactory.getLogger(UserManagement.class);

    @Autowired
    UserRepository userRepository;

    public User getUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User createDummyUser() {
        User foo = new User("john", "foo", "jf");
        userRepository.save(foo);
        return foo;
    }

    public List<User> queryUser() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> employee = criteriaQuery.from(User.class);
        Predicate condition = criteriaBuilder.like(employee.<String>get("userId"), "zhang%");
        criteriaQuery.where(condition);

        TypedQuery<User> tq = em.createQuery(criteriaQuery);

        List<User> users = tq.getResultList();
        logger.info(users.toString());
        return users;
    }


    public void convertQuery(Map<String, String[]> params) {
        QueryUtil.convertQueryWithParam(User.class, em, params);
    }
}
