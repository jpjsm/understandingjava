package com.service.mongodb;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class Resources {
    private String[] veggies = new String[] { "Artichoke", "Red_onion", "Spinach", "Sweet_potato", "Tomato", "Yam",
            "Asparagus", "Carrot", "Cauliflower", "Celery", "Chayote", "Bamboo_shoots", "Bean_sprouts", "Green_onion",
            "Leek", "Lettuce", "Mushroom", "Onion", "Parsnip", "Beans", "Beetroot", "Pepper", "Potato", "Pumpkin",
            "Radicchio", "Radish", "Bell_pepper", "Broccoli", "Brussels_sprouts", "Cabbage", "Cactus_pear",
            "Collard_greens", "Corn", "Cucumber", "Eggplant", "Endive", "Escarole", "Garlic", "Green_beans", "Pea",
            "Red_cabbage", "Red_chili_pepper", "Yellow_squash", "Zucchini" };
    private String[] animals = new String[] { "Aardvark", "African_Elephant", "African_Tree_Pangolin", "Albatross",
            "Alligator", "Alpaca", "Anaconda", "Angel_Fish", "Ant", "Anteater", "Antelope", "Archer_Fish", "Armadillo",
            "Asian_Elephant", "Atlantic_Puffin", "Aye-Aye", "Arab_horse", "Baboon", "Badger", "Bald_Eagle", "Bandicoot",
            "Bangle_Tiger", "Barnacle", "Barracuda", "Basilisk", "Bass", "Basset_Hound", "Bat", "Bearded_Dragon",
            "Beaver", "Bee", "Beetle", "Beluga_Whale", "Bird", "Big-horned_sheep", "Bird_of_paradise", "Bison",
            "Billy_goat", "Black_Bear", "Black_Fly", "Black_Rhino", "Black_Footed_Rhino", "Black_Widow_Spider",
            "Blackbird", "Blowfish", "Blue_Jay", "Blue_Whale", "Boa", "Boar", "Bob-Cat", "Bonobo",
            "Bottle-Nose_dolphin", "Bornean_Orang-utan", "Boxer_dog", "Brown_Bear", "Buck", "Budgie", "Buffalo", "Bull",
            "Bull_frog", "Bull_Mastiff", "Butterfly", "Buzzard", "Border_Collie", "Caribou", "Cheetah",
            "Common_Dolphin", "Common_seal", "Caiman_lizard", "Camel", "Canary", "Carp", "Cat", "Caterpillar", "Cattle",
            "Catfish", "Chameleon", "Centipede", "Chicken", "Chimpanzee", "Chihuahua", "Chinchilla", "Chipmunk", "Clam",
            "Chupacabra", "Clown_Fish", "Cobra", "Cocker_Spaniel", "Cockatiel", "Cockatoo", "Cockroach", "Cod", "Coho",
            "Corn_Snake", "Cougar", "Cow", "Coyote", "Crab", "Crane", "Crawfish", "Cray_fish", "Cricket", "Crocodile",
            "Crow", "Cuckoo_bird", "Cuttle_fish", "Deer", "Dog", "Du-gong", "Dalmation", "Dacshund", "Damsel_fly",
            "Dart_Frog", "Devi_Fish_(Giant_Sting_ray)", "Diamond_back_rattler", "Dik-dik", "Dingo", "Dinosaur",
            "Doberman_Pinscher", "Dodo_bird", "Dolphin", "Dolly_Varden", "Donkey", "Door_mouse", "Dormouse",
            "Draft_horse", "Dove", "Dragonfly", "Drake", "Duck", "Duckbill_Platypus", "Dung_beetle", "Eurasian_Lynx",
            "Eagle", "Earthworm", "Earwig", "Echidna", "Eclectus", "Eel", "Egret", "Elephant", "Elephant_Seal", "Elk",
            "Emu", "Erne", "Falcon", "Finch", "Fish", "Firefly", "Flamingo", "Flatworm", "Fly", "Ferret", "Fox",
            "Fresh_Water_Crocodile", "Frog", "Galapagos_Land_Iguana", "Galapagos_Tortoise", "Gazelle", "Giant_Anteater",
            "Giant_panda", "Giraffe", "Gnat", "Goat", "Goose", "Gopher", "Gorilla", "Grasshopper", "Green_fly",
            "Grey_Whale", "Great_White_Shark", "Green_poison_dart_frog", "Green_Sea_Turtle", "Groundhog",
            "Hammerhead_shark", "Hare", "Hawk", "Hedgehog", "Heron", "Herring", "Hippopotamus", "Horse", "Hyena",
            "Hyrax", "Irrawaddy_Dolphin", "Iguana", "Iguanodon", "Impala", "Inchworm", "Insect", "Jellyfish", "Jackal",
            "Jackrabbit", "Jaguar", "June_bug", "Kangaroo", "Killer_Whale", "King_Cobra", "Kingfisher", "Koala",
            "Komodo_Dragon", "Kookaburra", "Krill", "Lama", "Lamb", "Lancelet", "Leatherback_sea_turtle", "Leech",
            "Lemming", "Lemur", "Leopard", "Lice", "Lion", "Lionfish", "Llama", "Lobster", "Lynx", "Manatee",
            "Man-Of-War", "Mantis", "Marmot", "Marsupials", "Meerkat", "Mink", "Mole", "Mollusks", "Monarch_Butterfly",
            "Mongoose", "Monkey", "Moose", "Mountain_Lion", "Mouse", "Mule", "Muskox", "Muskrat", "Naked_Mole_Rat",
            "Narwhal", "Nautilus", "Newt", "Ocelot", "Octopus", "Opossum", "Orangutan", "Orca", "Osprey", "Ostrich",
            "Otter", "Owl", "Ox", "Panda", "Panther", "Peacock", "Pelican", "Penguin", "Pig", "Pigeon", "Platypus",
            "Polar_Bear", "Porcupine", "Praying_Mantis", "Prawn", "Puma", "Quail", "Quetzal", "Rabbit", "Raccoon",
            "Rat", "Ray", "Reindeer", "Rhino", "Ringworm", "Rhinoceros", "Robin", "Rooster", "Roundworm", "Salmon",
            "Salt_water_alligator", "Sandpiper", "Seahorse", "Seal", "Sea_anemone", "Sea_Lion", "Sea_urchin", "Scallop",
            "Scorpion", "Shark", "Sheep", "Shrimp", "Siberian_Husky", "Siberian_Tiger", "Skunks", "Slender_Loris",
            "Sloth", "Sloth_bear", "Slugs", "Snails", "Snake", "Snow_Hare", "Snow_Fox", "Snow_Leopard",
            "Somali_Wild_Ass", "Sponge", "Spectacled_Bear", "Squid", "Squirrel", "Starfish", "Stork", "Swan",
            "Swordfish", "Tapir", "Tasmanian_Devil", "Tiger", "Tadpole", "Tamarin", "Tapeworm", "Tarantula", "Tarpan",
            "Tazmanian_devil", "Tazmanian_tiger", "Terrapin", "Tick", "Tiger_shark", "Tortoise", "Trout", "Turkey",
            "Turtle", "Uakari", "Umbrella_bird", "Urchin", "Urutu", "Vicuna", "Viper", "Vulture", "Vampire_bat",
            "Velociraptor", "Vole", "Viper_Fish", "Velvet_worm", "Vervet", "Walrus", "Warbler", "Warthog", "Wasp",
            "Water_Buffalo", "Water_Dragons", "Wallaby", "Whale", "Whale_Shark", "White_Rhino", "Whippet",
            "White_tailed_dear", "Whooper", "Whooping_Crane", "Weasel", "Weevil", "Wolf", "Wolf_Spider", "Wolverine",
            "Woodchuck", "Woodpecker", "Widow_Spider", "Wren", "Wombat", "Wildcat", "Wildebeest", "X-ray_fish", "Yak",
            "Yellow_Bellied_Marmot", "Yellow_belly_sapsucker", "Yellow_finned_tuna", "Yeti", "Yorkshire_terrier",
            "Zander", "Zebra", "Zebra_Finch", "Zebra_Dove", "Zorilla", "Zebu" };
    private String[] services = new String[] { "Accounting_and_tax_advice", "Consulting", "Legal", "Marketing",
            "Web_and_app_design", "Recruiting", "Writing_and_translating" };
    private Random rnd;

    public Resources() {
        this.rnd = new Random();
    }

    public String getVeggie() {
        return veggies[rnd.nextInt(veggies.length)];
    }

    public String getAnimal() {
        return animals[rnd.nextInt(animals.length)];
    }

    public String getService() {
        return services[rnd.nextInt(services.length)];
    }

    public String[] getSomeResource() {
        int veggiesCount = this.veggies.length;
        int animalsCount = this.animals.length;
        return rnd.nextInt(veggiesCount + animalsCount) < veggiesCount ? new String[] { getService(), getVeggie() }
                : new String[] { getService(), getAnimal() };
    }

    public HashSet<UUID> getUuids() {
        HashSet<UUID> uuids = new HashSet<>();
        for (int index = 0; index < rnd.nextInt(5) + 1; index++) {
            uuids.add(UUID.randomUUID());
        }

        return uuids;
    }
}
