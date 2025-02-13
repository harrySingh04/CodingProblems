# Recursion Concepts and Coding

A function or a method that calls itself is known as Recursion. It should have a base case which is a terminating scenario that does not use recursion to produce an answer and then it have set of rules that will reduce all the cases to the base case

Let's go with an example to understand more,

**Example 1**: Factorial of a number n

```python
def factorial(n):
	if n <= 1: # base case , terminating scenario that does not use recursion
		return 1 # add those things in base case that we already know, using smaller
					# values such as n = 0 , n = 1 cases
	
	return n * factorial(n-1) # recursive call , as we know for any number 
	# factorial will be the number and then the factorial of number minus 1
	# example, n = 5  = 5! = 5 * 4! , 4 ! = 4 * 3! hence we use recursion here
	
	 
```

**Example 2:** Print Reverse of a string

```python
def print(strA):
	
	solve(strA, 0)

def solve(strA, idx):
	
	if idx >= len(strA):
		return # base case return when we reach the end of the string
	
	
	solve(strA, idx+1) # call for remaining part, that will take the 
	# remaining part and reverse it for example: strA = 'abc' , bc will 
	# be passed to solve and then bc will call with c and print c at the end
	print(strA[idx])

```

**Drawing Recursion Tree**

A recursion tree helps visualize how recursive functions work by showing each recursive call as a node with its parameters.

For the reverse string example with input "abc", the recursion tree would look like:

```mermaid
graph TD
    A["solve('abc', 0)"] --> B["solve('abc', 1)"]
    B --> C["solve('abc', 2)"]
    C --> D["solve('abc', 3)"]
    D --> E["return"]
    E --> F["print 'c'"]
    F --> G["print 'b'"]
    G --> H["print 'a'"]

```

For the factorial example with input n=4, the recursion tree would look like:

```mermaid
graph TD
    A["factorial(4)"] --> B["4 * factorial(3)"]
    B --> C["4 * (3 * factorial(2))"]
    C --> D["4 * (3 * (2 * factorial(1)))"]
    D --> E["4 * (3 * (2 * 1))"]
    E --> F["4 * (3 * 2)"]
    F --> G["4 * 6"]
    G --> H["24"]

```

The recursion tree shows how factorial(4) breaks down into smaller subproblems until it reaches the base case (n=1), then builds back up to compute the final result.