package guru.springframework.sfgpetclinic.model;

import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 7/13/18.
 */
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    public Pet() {
        super(null);
    }

    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;

        if (visits == null || visits.size() > 0 ) {
            this.visits = visits;
        }
    }
    
    public static PetBuilder builder() {
        return new PetBuilder();
    }
    
    public static class PetBuilder {
        private Long id;
        private String name;
        private PetType petType;
        private Owner owner;
        private LocalDate birthDate;
        private Set<Visit> visits;
        
        public PetBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public PetBuilder petType(PetType petType) {
            this.petType = petType;
            return this;
        }
        
        public PetBuilder owner(Owner owner) {
            this.owner = owner;
            return this;
        }
        
        public PetBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }
        
        public PetBuilder visits(Set<Visit> visits) {
            this.visits = visits;
            return this;
        }
        
        public Pet build() {
            return new Pet(id, name, petType, owner, birthDate, visits);
        }
    }

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
