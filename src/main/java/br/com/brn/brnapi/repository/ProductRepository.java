package br.com.brn.brnapi.repository;
/*
 * @author BRUN
 */

import br.com.brn.brnapi.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByIdAndActive(Long id, Boolean active);

    Iterable<Product> findByActive(Boolean active);
}
