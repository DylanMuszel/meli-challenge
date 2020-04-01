# Dylan Muszel - MercadoLibre challenge

This project uses MVP architecture is organized with a modularized clean architecture:
- entities: contains the entities of the project (e.g.: Product).
- data: contains the repositories to allow access to entities (e.g.: ProductRepository).
- usecases: contains the use cases of the application; business logic occurs here.
- app:
   - framework: contains the android implementation of the sources that the data module needs.
   - presentation: contains the android views and presenters.
   
JUnit and Mockito are used for unit testing and Espresso for instrumentation testing.