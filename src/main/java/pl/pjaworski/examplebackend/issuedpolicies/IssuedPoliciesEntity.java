package pl.pjaworski.examplebackend.issuedpolicies;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pl.pjaworski.examplebackend.domain.PolicyCoverage;
import pl.pjaworski.examplebackend.domain.PolicyHolder;

@Entity
@Table(name = "issued_policies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssuedPoliciesEntity {

    @Id
    private Long aggregateId;

    private Long policyKey;
    private String policyNumber;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "policy_holder_name")),
        @AttributeOverride(name = "surname", column = @Column(name = "policy_holder_surname"))
    })
    private PolicyHolder policyHolder;
    @JdbcTypeCode(SqlTypes.JSON)
    private PolicyCoverage policyCoverage;

    public IssuedPolicies toReadModel() {
        return new IssuedPolicies(policyKey, policyNumber, policyHolder, policyCoverage);
    }
}
