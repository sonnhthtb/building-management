package myteam.project4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "maintenance_service")
@PrimaryKeyJoinColumn(name = "service_id")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class MaintenanceService extends Service{

    @Column(name = "period")
    private Long period;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maintenance_service")
//    private List<UsedService> usedServiceList;
}
