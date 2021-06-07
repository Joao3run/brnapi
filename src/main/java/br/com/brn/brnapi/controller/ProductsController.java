package br.com.brn.brnapi.controller;
/*
 * @author BRUN
 */

import br.com.brn.brnapi.model.FilterParams;
import br.com.brn.brnapi.model.Product;
import br.com.brn.brnapi.repository.ProductRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@GraphQLApi
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @GraphQLQuery(name = "listAllBrnProducts")
    public Iterable<Product> listAllBrnProducts() {
        return productRepository.findAll();
    }

    @GraphQLMutation(name = "productSave")
    public Product save(@GraphQLArgument(name = "product") Product product) {
        return productRepository.save(product);
    }

    @GraphQLMutation(name = "productRemove")
    public void remove(@GraphQLArgument(name = "id") Long id) {
        productRepository.deleteById(id);
    }

    @GraphQLQuery(name = "listBrnProductsByFilter")
    public Iterable<Product> listBrnProductsByFilter(@GraphQLArgument(name = "filter") FilterParams filterParams) {
        List<Product> list = new ArrayList<>();
        Optional<Product> optionalProduct;
        if (filterParams.getFilterByActive() && filterParams.getId() > 0) {
            optionalProduct = productRepository.findByIdAndActive(filterParams.getId(), filterParams.getActive());
            if (optionalProduct.isPresent()) {
                list.add(optionalProduct.get());
            }
            return list;
        }
        if (filterParams.getFilterByActive() ) {
            return productRepository.findByActive(filterParams.getActive());

        }else if (filterParams.getId() > 0) {
            optionalProduct = productRepository.findById(filterParams.getId());
            if (optionalProduct.isPresent()) {
                list.add(optionalProduct.get());
            }
            return list;

        }else {
            return productRepository.findAll();
        }
    }
}
