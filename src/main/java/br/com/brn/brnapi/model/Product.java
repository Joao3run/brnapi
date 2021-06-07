package br.com.brn.brnapi.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "GL13PROD",  schema = "GLOBAL")
@Entity
@SequenceGenerator(name = "co13prod_seq", sequenceName = "gl13prod_co13prod_seq", allocationSize = 1)
public class Product {

    @Column(name = "CO13PROD")
    @Id
    @GeneratedValue(generator = "co13prod_seq", strategy = GenerationType.SEQUENCE)
    @GraphQLQuery(name = "id", description = "Product Id (Long)")
    private Long id;

    @Column(name = "NOMEPROD")
    @GraphQLQuery(name = "name", description = "Product Name (String)")
    private String name;

    @Type(type = "numeric_boolean")
    @Column(name = "ATIVPROD")
    @GraphQLQuery(name = "active", description = "Product active (boolean)")
    private boolean active;

}


