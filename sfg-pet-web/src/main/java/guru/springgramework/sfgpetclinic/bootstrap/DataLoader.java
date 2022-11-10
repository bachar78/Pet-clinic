package guru.springgramework.sfgpetclinic.bootstrap;

import guru.springgramework.sfgpetclinic.model.Owner;
import guru.springgramework.sfgpetclinic.model.Pet;
import guru.springgramework.sfgpetclinic.model.PetType;
import guru.springgramework.sfgpetclinic.model.Vet;
import guru.springgramework.sfgpetclinic.services.OwnerService;
import guru.springgramework.sfgpetclinic.services.PetTypeService;
import guru.springgramework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Dussel");
        owner1.setCity("Limmen");
        owner1.setTelephone("123123123");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Max");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("235 KerkWeg");
        owner2.setCity("Castricum");
        owner2.setTelephone("086123123");
        Pet FionasCat = new Pet();
        FionasCat.setPetType(savedCatType);
        FionasCat.setOwner(owner2);
        FionasCat.setBirthDate(LocalDate.now());
        FionasCat.setName("Sweaty");
        owner2.getPets().add(FionasCat);
        ownerService.save(owner2);

        System.out.println("Loaded Owners......");

        Vet vet1 = new Vet();
        vet1.setFirstName("Samer");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bachar");
        vet2.setLastName("Azure");
        vetService.save(vet2);
        System.out.println("Loaded vets......");
    }
}
