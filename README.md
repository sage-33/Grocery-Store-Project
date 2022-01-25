# Grocery Store Simulator 

## Backstory

After success with their latest simulation game “The Jims 3”, BA Games is at it again. You’ve been hired to develop a prototype simulator for this year's most anticipated Grocery Store Simulation game creatively named “Grocery Store Simulator 2018”. Will you rise to the challenge?

### Table of Contents
**[Files to complete](#files-to-complete)**<br>
**[Support Code API](#support-code-api)**<br>
**[Part One: Clone, Branch, and Import Project](#part-one-clone-branch-and-import-project)**<br>
**[Part Two: Implementing QueueInterface](#part-two-implementing-queueinterface)**<br>
**[Part Three: Implement NormalLine and ExpressLine](#part-three-implement-normalline-and-expressline)**<br>
**[Part Four: Create Groceries](#part-four-create-groceries)**<br>
**[Part Five: Implement Receipt](#part-five-implement-receipt)**<br>
**[Part Six: Submit Pull Request ](#part-six-submit-pull-request)**<br>
**[Part Seven: Implement a SimpleRegister Class ](#part-seven-implement-a-simpleregister-class)**<br>
**[Part Eight: Implement a SimpleStore Class ](#part-eight-implement-a-simplestore-class)**<br>
**[Part Nine: Implement a ProfitableStore Class ](#part-nine-implement-a-profitablestore-class)**<br>
**[Part Ten: Submit Pull Request ](#part-ten-submit-pull-request)**<br>

## Book
This assignment is based off Chapter 5 in the Java Software Structures book

## Files to complete
You are expected write an implementation for each of the interfaces listed in the classes presented in the **config** package provided. As with the last assignment, you must specify which implementation you would like us to grade in this file.

It will almost certainly be useful for you to write additional class files. Any class file you write that is used by your solution **MUST**  be in the provided **src** folder. When we test your assignment, all files not included in the **src** folder will be ignored.

**Note:** When you submit your solution, be sure to remove **ALL** compilation errors from your project. Any compilation errors in your project may cause the autograder to fail and you will receive a zero for your submission.

### Test files
In the test folder, you are provided with several JUnit test cases that will help you keep on track while completing this assignment. We recommend you run the tests often and use them as a checklist of things to do next. You are not allowed to remove anything from these files. If you have errors in these files, it means the structure of the files found in the src folder have been altered in a way that will cause your submission to lose points. We highly recommend that you add new @Test cases to these files. However, before submitting, make sure that your program compiles with the original test folder provided.

### Support Code API
The Support Code’s comments have been generated into a nicely formatted API that can be found here: 

https://jd12.github.io/grocery-simulator-student/

It is not important that you understand how the support code is implemented but it is important that you understand what the support code offers. It is highly recommended that you spend a day simply reading over the comments in each of the interfaces provided.


## Part One: Clone, Branch, and Import Project 
Begin by cloning the provided project, creating your dev branch, and importing it into your workspace. 

```
git clone <url for github repository>
```

After cloning this repository you want to run these commands

```
cp pre-commit .git/hooks
chmod +x .git/hooks/pre-commit
```

Then you want to create a development branch

```
git branch development
git checkout development
```

You should then import it to your IDE of choice. 

By default, your project should have no errors and contain the following root items:

**src** - The source folder where all code you are submitting must go. You can change anything you want in this folder, you can add new files, etc...<br>
**test** - The test folder where all of the public unit tests are available<br>
**support** - This folder contains support code that I encourage you to use (and must be used to pass certain tests). I would be very careful changing files in this folder.<br>
**JUnit 4** - A library that is used to run the test programs<br>
**JRE System Library** - This is what allows java to run<br>

If you are missing any of the above or errors are present in the project, seek help immediately so you can get started on the project right away. 

## Part Two: Implementing QueueInterface
Create a class that implements `structures.QueueInterface`. The `Queue` you are implementing should be unbounded and meet the big-O time complexities specified in the interface.

**Tip:** Don’t try to implement your entire Queue class all at once. Start by looking at the the tests provided in structures.QueueInterfaceTest. Do these tests look like they cover all the cases presented by the interface? If not, can you come up with additional tests? Add a method to the test file and add the @Test annotation before it. When you run your unit tests, this method will also be executed. Be careful not to change any of the provided unit tests! 

Once you feel like the tests accurately represent what you would like to test, choose the test that looks the least complex. Implement the parts that will allow that test to pass. Once those tests pass. Look for another test to implement. 

**Note**: For this portion of the assignment, you may not use any of the provided Collection classes provided by the java standard API. A list of them can be seen in the “All Known Subinterfaces” and “All Known Implementing Classes” of the following URL: http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html

## Part Three: Implement NormalLine and ExpressLine
The `simulator.checkout.CheckoutLineInterface` extends `QueueInterface<Shopper>`. That is, it is just a queue for shoppers to stand in while they wait to be processed. However, there are a few additional requirements for this interface.

First, it defines a `canEnterLine`. This method determines if a shopper is allowed to enter a particular line. This can be used to represent special lines such as “15 items or less”.

Second, the definition for enqueue, is slightly different in that if the `canEnterLine` method returns false on the input, this method should throw an exception.

For this part, you must implement two versions of `CheckoutLineInterface`. The `NormalLine` class should always return true with a call to `canEnterLine`. The `ExpressLine` should return false if the specified `Shopper` has more than 15 items.

**Tip:** You should be able to easily implement this class by extending the class you wrote in part two and @Override `enqueue`.

**Tip:** Write `NormalLine` first then extend it when you implement `ExpressLine` and @Override `canEnterLine`.


## Part Four: Create Groceries
In the `config.Groceries` class, return valid implementations for each of the static methods defined.

**Tip:** You should be able to accomplish this by implementing a single class called `Grocery`.

## Part Five: Implement Receipt  

For this part of the assignment, you will be extending the abstract class `simulator.AbstractReceipt`. If you look at the definition, you will see there are two abstract methods left for you to implement: `getSubTotal()` and `getSaleValue()`. 

**Tip:** Take advantage of the supplied methods. You shouldn’t need to have fields in your subclass to hold the list or discount. You can access these in your subclass by using `super.getDiscount()` and `super.getGroceries()`.

**Tip:** Take a look at the `List<T>` interface in the Java API to see how it works. You will need to use it to calculate the subtotal. Notice that `List<T>` implements `Iterable<T>`. This means that it can be used in the enhanced for loop! 

**List API:** http://docs.oracle.com/javase/7/docs/api/java/util/List.html<br>
**Iterable API:** http://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html<br>

**Using Enhanced For Loop w/ List:**
Assume there is some `List<String>` called strings. Then we can write:

```java
for (String s : strings){
  System.out.println(s);
}
```

This will print out all of the elements in strings.

**Another Example:**
Assume there is some `List<GroceryInterface>` called groceries. Then we can write:

```java
for (GroceryInterface g : groceries){
  System.out.println(g.getCost());
}
```

This will print out the cost of all `GroceryInterface` items in groceries.

## Part Six: Submit Pull Request
When you have finished and are ready to submit, submit a pull request on Github to merge your development branch with your master branch. Mark me as a reviewer and I’ll get notified.

## Part Seven: Implement a SimpleRegister Class

For this part of the assignment, you will familiarize yourself with the `simulator.checkout.AbstractRegister` class. 

This class facilitates creating Transactions for Shoppers who visit a Grocery Store. To implement this class, you can simply extend it and implement the abstract method `createTransaction(Shopper s)`. Later in the assignment, you will need to create a more sophisticated algorithm for checking out a customer. However, for this portion you should simply generate a Transaction with the following properties:

A Receipt with all of the shoppers groceries and no discount.
The Shopper passed to the `createTransaction` method.
If the number of grocery items being sold is 0, the checkout time should be 1. Otherwise, the amount of time to process a shopper should be 4 multiplied by the number of grocery items being sold.

## Part Eight: Implement a SimpleStore class

For this part of the assignment, you will familiarize yourself with the `simulator.store.AbstractGroceryStore` class. Your `SimpleStore` implementation must meet the following requirements:

- Utilizes 2 SimpleRegisters that are always turned on.
- Uses 1 Normal Line
- Uses 1 Express Line

The implementation details for this class might seem daunting at first. Below is a suggested order for implementing them and hints on how you might do it.

### SimpleStore Constructor
In the constructor for your SimpleStore, you should create 2 SimpleRegisters and call the `turnOn()` method for both of them. The `turnOn()` method ensures that the registers can process shoppers.

In your constructor, you should create a `List<CheckoutLineInterface>` and add a `NormalLine` and `ExpressLine` to it.

### getLines()
This method should return the List you created in your constructor. This line is handed off to the BigBrother object. BigBrother will use this to direct customers to your store.

### tick()
This method is called by BigBrother during the simulation to advance the simulation one time step. When this method is called, you will want to check to see if each of your registers `isBusy()`. If they are not, you will want to try and process a shopper. You can do this by checking if your lines are `isEmpty()` and then `dequeue()` the next Shopper to a register to be processed.

**Hint**: Use two ArrayLists whose indices associate each register to a single line.

### getTransactions()
The AbstractRegister tracks all of the Transactions it creates. You should utilize this to make your life easier. List has an `addAll()` method that might be useful!

### getAverageWaitingTime()
This should return the average waiting time per shopper. This is the sum of `getWaitingTime()` for each Shopper divided by the total number of Shoppers.

**Hint**: Use the Transactions created by your registers to get to the Shopper data.

### getTotalSales()
This should return the total amount of money your store receives from all Shoppers.

**Hint**: This can be calculated by using the Transactions created by your registers to get to the Receipt data.

### getTotalCost()
This should return the total amount of money your store spent to run. This is the sum of the cost of all groceries sold AND the `getRunningCost()` of each of your registers.

### getTotalProfit()
Returns the total profit generated by your store.

### getNumberOfShoppers()
This is the total number of Shoppers that visited your store. This should be equal to the number of transactions your store generates.

### getNumberOfIrateShoppers()
This is the total number of Shoppers that visited your store that `isIrate()`.

## Part Nine: Implement a ProfitableStore Class

Now that you’ve familiarized yourself with the `AbstractGroceryStore` class, you must implement a store that can turn a profit using the SimpleWorld. To do this, you will need to implement a more competitive Register class. In addition to this, you will almost certainly need a better strategy than the SimpleStore.

For this portion, it is recommended that you subclass your SimpleStore class and @Override the `tick()`, `getLines()`, and `getTransactions()` methods. If you have implemented the other methods in a way that utilizes `getTransactions()`, they should work for this subclass. Depending on your implementation, you may also want to @Override `getTotalCost()`.

It is also recommended that you write your own tests to double check that your get methods are returning the way you believe they should. The BigBrother object collects information that will be used by the autograder to ensure that your methods are not simply fooling the test simulations.

### Graded Simulations
There are two graded simulations in the test/ directory provided. These are in the simulator.simulations package. You will notice that these are not unit tests. Instead, these are runnable classes containing main methods. Each of these creates a SimpleWorld specifying the rate at which customers will enter your store. After this, your ProfitableStore (as specified in the Configuration class) is selected. BigBrother ticks 43200 times running the simulation. Finally, the `getTotalProfit()` method of your store will be called to check if your store turned a profit. You should NOT make your store simply return a value greater than 0, the BigBrother object collects information throughout the simulation that will be able to verify the results. 

Two tips for increasing profit

**How many irate shoppers did you have?** Irate shoppers empty their shopping cart before they are dequeued. Although it is possible to make a profit by only serving some shoppers, it is highly discouraged.<br>
**How much are your registers pushing up your running cost?**
If you process shoppers too fast, you will not be able to make a profit. The formula for calculating the cost to check out a shopper is in the AbstractRegister class.
In the SimulationRate1000.java test, your registers may be idle. Perhaps you can save money by turning them off when they are not being used.

## Part Ten: Submit Pull Request
When you have finished and are ready to submit, submit a pull request on Github to merge your development branch with your master branch. Mark me as a reviewer and I’ll get notified.



