## Problem statement
You run a paint shop, and there are a few different colors of paint you can prepare.  Each color can be either "gloss" or "matte".

You have a number of customers, and each have some colors they like, either gloss or matte.  No customer will like more than one color in matte.

You want to mix the colors, so that:
   * There is just one batch for each color, and it's either gloss or matte.
   * For each customer, there is at least one color they like.
   * You make as few mattes as possible (because they are more expensive).

Your program should accept an input file as a command line argument, and print a result to standard out.


## Solution

- First step is to map input file to PaintShop and Customer java objects.
- First fix positions for matter/gloss only customers in output.
- Find if any mix customer(customer who likes both matter and gloss color) has already preferred color at desired position.
- If mix customer did not find preferred color in existing list then add new one. First try to add gloss color then matte.
- Throw error with message 'No solution exists' if not able to find any solotion.

## Technologies used

- Java 8
- Maven
- Spring boot
- Junit

## How to run

- Compile and build project using command `mvn clean install`
- Run following command with path of input file:

```
java -jar target\paint-shop-application-0.0.1-SNAPSHOT.jar "<file_path>"
```

