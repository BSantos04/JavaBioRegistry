# JavaBioRegistry

## Description



## Requirements
- Docker >= v20.10 (tested with v29.0.2)

## Installation
```
git clone https://github.com/BSantos04/JavaBioDB.git
```

## Usage
First, make sure to give yourself permission to use Docker.

Try this:
```
sudo usermod -aG docker $USER
newgrp docker
```
Now we need to create a Docker image:
```
docker build -t {image name} .
```
### Example
``` 
docker build -t jbd .
```

Now that you have a Docker image, run the following command:
```
docker run -it -v $(pwd)/data:/javabioreg/data {image name}
```
### Example
``` 
docker run -it -v $(pwd)/data:/javabioreg/data jbd
```

The usage of the rest of the software will be quite self-explanatory:
```
Oh, hi there.

What do you wanna do?
-Add a species to the database (1);
-Add an habitat to the database (2);
-Verify if a species exists in the database (3);
-Show the number of species from a specific category (4)
-Show the registered species (5);
-Show the total genetic diversity of the database (6);
-Show the detailed informations of a species (7);
-Show all the species associated with the same habitat (8);
-Show all registered habitats (9);
-Remove species (10) ;
-Remove habitat (11) ;
-Exit (12).
Write the number of the activity you want to do:
```

## License
GNU General Public License V3.0
