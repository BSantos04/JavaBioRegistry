# JavaBioDB

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
docker run --name {container name} -it {image name}
```
### Example
``` 
docker run --name javabd -it jbd
```

The usage of the rest of the software will be quite self-explanatory:
```
Oh, hi there.

What do you wanna do?
-Add a species to the database (1);
-Add an habitat to the database (2);
-Verify if a species exists in the database by scientific name (3);
-See the number of species from a specific category (4)
-Show the registered species (5);
-Show the total genetic diversity of the database (6);
-Remove species by scientifc name (7) ;
-Get the detailed informations of a species by scientific name (8);
-Show all the species associated with the same habitat (9);
-Show all registered habitats (10);
-Export CSV file with all registered species (11).
-Exit (12).
Write the number of the activity you want to do:
```

After using the software, in order to get the exported CSV files, run the following command:
```
docker cp {container name}:/javabiodb/JavaBioDBcsv/ .
```

### Example
```
docker cp javabd:/javabiodb/JavaBioDBcsv/ .
```
After extracting the files, you can remove the container safely:
```
docker rm javabd
```

## License
GNU General Public License V3.0