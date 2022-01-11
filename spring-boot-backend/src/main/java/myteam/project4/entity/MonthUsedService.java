package myteam.project4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "month_used_service")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class MonthUsedService extends BaseModel{
    @ManyToOne(targetEntity = UsedService.class)
    @JoinColumn(name = "used_service_id", referencedColumnName = "id")
    private UsedService usedService;
    private Timestamp fromDate;
    private Timestamp toDate;
}
