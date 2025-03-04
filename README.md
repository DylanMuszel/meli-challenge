# Dylan Muszel - MercadoLibre challenge

This project uses MVP architecture and is organized with a modularized clean architecture:
- entities: contains the entities of the project (e.g.: Product).
- data: contains the repositories to allow access to entities (e.g.: ProductRepository).
- usecases: contains the use cases of the application; business logic occurs here (e.g.: SearchProductsUseCase).
- app:
   - framework: contains the android implementation of the sources that the data module needs (e.g.: ProductNetworkDataSource).
   - presentation: contains the android views and presenters (e.g.: ProductDetailFragment).
   
JUnit and Mockito are used for unit testing and Espresso for instrumentation testing.

![challenge](https://user-images.githubusercontent.com/14262610/78101581-4e213100-73be-11ea-926d-d72a8f0ac6b3.gif)
