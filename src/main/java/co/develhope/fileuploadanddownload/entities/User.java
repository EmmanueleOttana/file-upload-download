package co.develhope.fileuploadanddownload.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String cognome;
    private String immagineProfile;

}
