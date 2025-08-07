package guru.springframework.sfgpetclinic.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jt on 7/29/18.
 */
@Entity
@Table(name = "specialties")
public class Speciality extends BaseEntity {

    public Speciality() {
        super(null);
    }

    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }
    
    public static SpecialityBuilder builder() {
        return new SpecialityBuilder();
    }
    
    public static class SpecialityBuilder {
        private Long id;
        private String description;
        
        public SpecialityBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public SpecialityBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public Speciality build() {
            return new Speciality(id, description);
        }
    }

    @Column(name = "description")
    private String description;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
