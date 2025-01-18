package software.ulpgc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainAnimals {
    public interface Pet {
        String name();
    }

    public interface Animal {
        int legs();
        String species();
    }

    // Clase Spider implementa Animal
    public static class Spider implements Animal {
        @Override
        public int legs() {
            return 8; // Las arañas tienen 8 patas
        }

        @Override
        public String species() {
            return "Spider";
        }

        @Override
        public String toString() {
            return "Spider";
        }
    }

    // Clase Cat implementa Animal y Pet
    public static class Cat implements Animal, Pet {
        private final String name;

        public Cat(String name) {
            this.name = name;
        }

        @Override
        public int legs() {
            return 4; // Los gatos tienen 4 patas
        }

        @Override
        public String species() {
            return "Cat";
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String toString() {
            return "Cat(" + name + ")";
        }
    }

    // Clase Dog implementa Animal y Pet
    public static class Dog implements Animal, Pet {
        private final String name;

        public Dog(String name) {
            this.name = name;
        }

        @Override
        public int legs() {
            return 4; // Los perros tienen 4 patas
        }

        @Override
        public String species() {
            return "Dog";
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String toString() {
            return "Dog(" + name + ")";
        }
    }

    // Clase Bird implementa Animal
    public static class Bird implements Animal {
        @Override
        public int legs() {
            return 2; // Las aves tienen 2 patas
        }

        @Override
        public String species() {
            return "Bird";
        }

        @Override
        public String toString() {
            return "Bird";
        }
    }

    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(
                new Spider(),
                new Cat("Whiskers"),
                new Dog("Buddy"),
                new Bird(),
                new Cat("Tom"),
                new Spider(),
                new Bird(),
                new Dog("Rex")
        );
        List<Animal> wildAnimals = animals.stream()
                .filter(a -> !(a instanceof Pet))
                .toList();
        System.out.println(wildAnimals);
        List<Animal> petAnimals = animals.stream()
                .filter(a -> a instanceof Pet)
                .toList();
        System.out.println(petAnimals);
        Animal moreLegs = animals.stream()
                .max(Comparator.comparingInt(Animal::legs))
                .orElse(null);
        System.out.println(moreLegs);
        long countLegs = animals.stream()
                .mapToInt(Animal::legs)
                .sum();
        System.out.println(countLegs);
        Map<Integer, List<Animal>> groupedByLegs = animals.stream()
                .collect(Collectors.groupingBy(Animal::legs));
        System.out.println(groupedByLegs);
        Map<String, Long> animalsPerSpecies = animals.stream()
                .collect(Collectors.groupingBy(Animal::species, Collectors.counting()));
        System.out.println(animalsPerSpecies);
        long numberOfSpecies = animals.stream()
                .map(Animal::species)
                .distinct()
                .count();
        System.out.println(numberOfSpecies);
    }
}
