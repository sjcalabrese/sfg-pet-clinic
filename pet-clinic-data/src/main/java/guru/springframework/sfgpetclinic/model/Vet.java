package guru.springframework.sfgpetclinic.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 7/13/18.
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    public Vet() {
        super(null, null, null);
    }

    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities) {
        super(id, firstName, lastName);
        if(specialities != null) {
            this.specialities = specialities;
        }
    }
    
    public static VetBuilder builder() {
        return new VetBuilder();
    }
    
    public static class VetBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private Set<Speciality> specialities;
        
        public VetBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public VetBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public VetBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public VetBuilder specialities(Set<Speciality> specialities) {
            this.specialities = specialities;
            return this;
        }
        
        public Vet build() {
            return new Vet(id, firstName, lastName, specialities);
        }
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();
    
    public Set<Speciality> getSpecialities() {
        return specialities;
    }
    
    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
