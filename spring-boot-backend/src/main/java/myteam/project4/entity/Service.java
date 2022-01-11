package myteam.project4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
@Inheritance(strategy=InheritanceType.JOINED)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Service extends BaseModel {

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "type", length = 250)
    private String type;

    @Column(name = "price")
    private Float price;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private List<UsedService> usedServiceList;

}
