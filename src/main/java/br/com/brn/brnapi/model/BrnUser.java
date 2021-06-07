package br.com.brn.brnapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "GL13USUA",  schema = "GLOBAL")
@Entity
@SequenceGenerator(name = "co13usua_seq", sequenceName = "gl13usua_co13usua_seq", allocationSize = 1)
public class BrnUser {

    ////    @GraphQLQuery(name = "id", description = "Academic Calendar Id (long)")

    @Column(name = "CO13USUA", nullable = false)
    @Id
    @GeneratedValue(generator = "co13usua_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOMEUSUA")
    private String name;

    @Column(name = "LOGIUSUA")
    private String username;

    @Column(name = "MAILUSUA")
    private String email;

    @JsonIgnore
    @Column(name = "SENHUSUA")
    private String password;

    @Type(type = "numeric_boolean")
    @Column(name = "ATIVUSUA")
    private boolean active;

}


