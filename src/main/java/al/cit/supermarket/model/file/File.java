package al.cit.supermarket.model.file;

import al.cit.supermarket.model.AbstractAuditingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class File extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)

    private String URL;
    @Column(nullable = false)
    private String uploadContentType;

    private String uploadFileSize;
}
