# individual-project-Aishwaryasjsu
individual-project-Aishwaryasjsu created by GitHub Classroom

Answer1

The main challenge is to verify and extract valid credit card numbers from input data, and instantiate the corresponding credit card class if the number is valid. While we currently only support Visa, MasterCard, Amex, and Discover cards, our design should allow for easy integration of new card types in the future without disrupting the current implementation.

Answer2 

This git repository contains design and source code for Credit Card type detection Application. The application takes an input file with credit card information and generates the output file with type of credit card. Supported input and output files types are .csv, .xml and .json. Supported list of credit card types are MasterCard, Visa, AmericanExpress and Discover. If given credit card record does not belong to any of these supported types then output file would have credit card type as Invalid for the corresponding credit card record.


Answer3
------------------------------------------------------How to Run the application

#### Pre-requisites
* JDK v1.8.0_231
* Apache Maven v3.6.1 - Path for mvn binary should be set in PATH environment variable.
* Third-party libraries dependencies

    | Library  | Version | Purpose |
    | -------- | ------- | --------|
    | gson  | 2.8.6  | Used for JSON file parsing.|

#### Compiling source code
Run following command to compile the Java source code from project base directory:
```
mvn install 
```
Above command will compile the source code and generate jar files under *target* directory. Maven will attempt to download dependent jars from internet, hence internet connectivity is required to run this command. In this command test case runs has been skipped. To run Junit test cases refer [How to run JUnits](#how-to-run-junits) section.

#### Running the application
Once code has been compiled, execute following command to run the program from project base directory:
* *On Windows*
```
java -cp "target/IndividualProject-Aish-1.0.jar;target/gson-2.8.6.jar" com.cmpe202.aish.creditcard.CreditCardDetailsProcessor target/input-files/input_file-1.csv output.csv
```
* *On MacOS*
```
java -cp "target/IndividualProject-Aish-1.0.jar;target/gson-2.8.6.jar" com.cmpe202.aish.creditcard.CreditCardDetailsProcessor target/input-files/input_file-1.csv output.csv

```
------------------------------------To run testcases run below command -------------------------------------

```
mvn test
```

## Design Patterns Used
Following are three design patterns used while implementing the solution for this application:
* Chain of Responsibility
* Factory Method
* Strategy

#### Explanation for using Chain of Responsibility Design Pattern
As we cannot predict the specific type of a given credit card record, we need to sequentially check the record against different credit card types. As a client, we do not have prior knowledge of which credit card type check to perform first in order to determine the correct type for a given record. In this situation, the Chain of Responsibility design pattern is suitable, as it allows multiple objects to have the opportunity to make the decision regarding the correct credit card type.

To implement the Chain of Responsibility pattern, I will create an interface called CCTypeChain, which will be implemented by several classes such as MasterCCTypeChain, VisaCCTypeChain, AMEXCCTypeChain, and DiscoverCCTypeChain. Each of these classes will implement two methods:

identifyType(CreditCard): This method will either determine the credit card type or invoke the same method on the next class in the chain. The initial class in the chain will be set by the invoker of the identifyType(CreditCard) method, followed by the subsequent classes in the chain using their respective setNext() method.

setNext(): This method will set the next class in the chain.

The identifyType(CreditCard) method of each class will decide the credit card type or pass the responsibility to the next class in the chain. Once a class determines the credit card type, the execution will not proceed to the remaining classes in the chain.

If the last class in the chain is unable to identify the credit card type of a record, that record will be declared as an Unknown type.

#### Explanation for using Factory Method Design Pattern
After identifying a credit card type from a record, instead of using conditional logic to create the corresponding CreditCard object, we can utilize the Factory Method design pattern to abstract the object creation process. By implementing this pattern, we can delegate the responsibility of creating objects to a separate class, thus making the creation process more organized and maintainable.

To implement the Factory Method pattern, we can create an interface called CreditCardFactory, which will be implemented by the ConcreteCreditCardFactory class. The ConcreteCreditCardFactory class will have a method called create(CreditCard) which will contain the complete implementation logic for creating the appropriate CreditCard object type. By using this pattern, the client can simply invoke the method of the concrete factory class and not worry about the complex object creation logic, as it will be hidden from the client.

#### Explanation for using Strategy Design Pattern
In my Individual Project part 1, I have enhanced the existing design to support the handling of three distinct input file formats: CSV, XML, and JSON. To achieve this, I employed the Strategy Pattern, where the appropriate class instance (CSVHandlingStrategy, XMLHandlingStrategy, or JSONHandlingStrategy) is created based on the file extension. The parse() and write() methods are responsible for parsing and writing the respective file types accordingly.

Answer 4

consequences of design patterns used:
Increased flexibility: The Factory Method pattern allows for flexible object creation by abstracting the creation process. This enables the addition of new credit card types without modifying existing code. Similarly, the Chain of Responsibility pattern provides flexibility by allowing different objects to handle the decision-making process for credit card type identification. New handlers can be added or existing ones can be modified independently, enhancing the system's flexibility.

Simplified client code: With the Factory Method pattern, the client does not need to directly implement conditional logic for object creation. Instead, it can simply invoke the appropriate factory method, which abstracts the creation process. This simplifies the client code and makes it more readable and maintainable. In the case of the Chain of Responsibility pattern, the client does not need to explicitly know which handler to use. The chain of responsibility handles the decision-making internally, reducing complexity for the client.

Encapsulation and decoupling: The Factory Method pattern encapsulates the object creation logic within the factory classes. The client does not need to know about the specific implementation details of object creation, promoting encapsulation and reducing coupling. Similarly, the Chain of Responsibility pattern encapsulates the decision-making logic within the handler classes. Each handler only needs to know about the next handler in the chain, enhancing decoupling and modularity.

Code reusability: The Factory Method pattern promotes code reusability by providing a common interface for object creation. Different concrete factory classes can be created to handle specific credit card types, allowing code reuse across similar creation processes. Likewise, the Chain of Responsibility pattern enables reusability of handler classes. Handlers can be reused and combined in different chains to handle various decision-making scenarios.

Scalability and maintainability: Both patterns contribute to the scalability and maintainability of the system. The Factory Method pattern allows for easy addition of new credit card types by creating new concrete factory classes. The Chain of Responsibility pattern supports scalability by allowing the addition of new handlers without modifying existing ones. This makes the system more maintainable and adaptable to future changes.

Improved testing: The use of these patterns can enhance the testability of the system. With the Factory Method pattern, different factory implementations can be tested independently, ensuring that each credit card type is created correctly. The Chain of Responsibility pattern allows for targeted testing of individual handlers, verifying their decision-making logic and ensuring the correct handling of credit card types.

Overall, the Factory Method and Chain of Responsibility patterns bring benefits such as flexibility, simplification, encapsulation, reusability, scalability, maintainability, and improved testability to the credit card type identification system
