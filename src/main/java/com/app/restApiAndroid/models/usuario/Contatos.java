package com.app.restApiAndroid.models.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "contactusers")
@Entity(name = "contactusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contatos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String ddd;
    private String telefone;
    private boolean principal;

}
