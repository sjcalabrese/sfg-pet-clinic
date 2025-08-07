package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by jt on 7/29/18.
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    public Visit() {
        super(null);
        // Empty constructor needed by JPA
    }

    public Visit(Long id, LocalDate date, String description, Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        this.pet = pet;
    }
    
    public static VisitBuilder builder() {
        return new VisitBuilder();
    }
    
    public static class VisitBuilder {
        private Long id;
        private LocalDate date;
        private String description;
        private Pet pet;
        
        public VisitBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public VisitBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }
        
        public VisitBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public VisitBuilder pet(Pet pet) {
            this.pet = pet;
            return this;
        }
        
        public Visit build() {
            return new Visit(id, date, description, pet);
        }
    }

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Pet getPet() {
        return pet;
    }
    
    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
