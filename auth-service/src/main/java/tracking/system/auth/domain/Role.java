package tracking.system.auth.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(exclude = {"users"})
public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ERole name;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
