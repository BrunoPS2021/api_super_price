package com.app.restApiAndroid.models.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Table(name = "datasusers")
@Entity(name = "datasusers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DadosUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(unique=true)
    private String email;
    private String nascimento;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usersId", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contactId", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Contatos> contatos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "addressId", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Endereco> enderecos;


    public DadosUsuario(String name, String email,String nascimento, Usuario user, List<Endereco> enderecos, List<Contatos>contatos){
        this.name = name;
        this.email = email;
        this.user = user;
        this.enderecos = enderecos;
        this.contatos = contatos;
        this.nascimento = nascimento;
    }

}
