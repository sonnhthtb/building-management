package myteam.project4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "company_employee")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class CompanyEmployee extends BaseModel {

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "identification", length = 250)
    private String identification;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name = "phone", length = 250)
    private String phone;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

}
