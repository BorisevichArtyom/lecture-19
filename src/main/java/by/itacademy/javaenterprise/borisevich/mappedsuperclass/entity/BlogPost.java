package by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "blogpost")
public class BlogPost extends Publication {
    @Column
    private String url;
}
