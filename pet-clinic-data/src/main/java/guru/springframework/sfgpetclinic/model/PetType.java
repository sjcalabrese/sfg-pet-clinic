package guru.springframework.sfgpetclinic.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jt on 7/13/18.
 */

@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    public PetType() {
        super(null);
    }

    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }
    
    public static PetTypeBuilder builder() {
        return new PetTypeBuilder();
    }
    
    public static class PetTypeBuilder {
        private Long id;
        private String name;
        
        public PetTypeBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public PetTypeBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public PetType build() {
            return new PetType(id, name);
        }
    }

    @Column(name = "name")
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
