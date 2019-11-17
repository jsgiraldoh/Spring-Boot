package com.test.principal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.test.principal.entities.Usuario;

@Repository
public interface IUserRepository extends CrudRepository<Usuario, Integer> {

}
