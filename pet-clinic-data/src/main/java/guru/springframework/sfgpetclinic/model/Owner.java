package guru.springframework.sfgpetclinic.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 7/13/18.
 */
@Entity
@Table(name = "owners")
public class Owner extends Person {

    public Owner() {
        super();
    }

    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
    }
    
    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }
    
    public static class OwnerBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String telephone;
        private Set<Pet> pets;
        
        public OwnerBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public OwnerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public OwnerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public OwnerBuilder address(String address) {
            this.address = address;
            return this;
        }
        
        public OwnerBuilder city(String city) {
            this.city = city;
            return this;
        }
        
        public OwnerBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }
        
        public OwnerBuilder pets(Set<Pet> pets) {
            this.pets = pets;
            return this;
        }
        
        public Owner build() {
            return new Owner(id, firstName, lastName, address, city, telephone, pets);
        }
    }

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

}
