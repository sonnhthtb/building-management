package myteam.project4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "food_service")
@PrimaryKeyJoinColumn(name = "service_id")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class FoodService extends Service{

    @Column(name = "time")
    private String time;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food_service")
//    private List<UsedService> usedServiceList;
}
