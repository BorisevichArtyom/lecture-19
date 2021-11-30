package by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected String title;
    @Version
    @Column(name = "version")
    private int version;
    @Column(name = "publishing_date")
    @Temporal(TemporalType.DATE)
    private Date publishingDate;

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", version=" + version +
                ", publishingDate=" + publishingDate +
                '}';
    }

}
