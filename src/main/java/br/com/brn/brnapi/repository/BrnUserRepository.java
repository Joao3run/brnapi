package br.com.brn.brnapi.repository;
/*
 * @author BRUN
 */

import br.com.brn.brnapi.model.BrnUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrnUserRepository extends CrudRepository<BrnUser, Long> {

    public Optional<BrnUser> findBrnUserByUsernameAndPassword(String username, String password);

}
