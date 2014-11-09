


Introduction
============

Executable Documentation
------------------------

Everything in this documentation is executed as part of the build process, so all the examples are guaranteed to run with the latest master branch of Dollar. 

Yep Dollar can actually run Markdown files, in fact the source file that this page was built from starts with:


```
#!/usr/bin/env dollar
```
So it can be executed directly from a Unix command line.

The source for this page (minus that header) is [here](scripting.md)

Getting Started
---------------

NOTE: At present only Mac OS X is officially supported, however since Dollar is entirely based in Java it's trivial to port to other systems.

First download the Dollar scripting runtime from [ ![Download](https://api.bintray.com/packages/neilellis/dollar/dollar-runtime-osx/images/download.svg) ](https://bintray.com/neilellis/dollar/dollar-runtime-osx/_latestVersion) 
 
Make sure `dollar/bin` is on your PATH.

Run `dollar <filename>` to execute a Dollar script. 
 
Here is an example of what DollarScript looks like

```dollar  

testParams := ($2 + " " + $1)

=> testParams ("Hello", "World") == "World Hello"

```

Understanding the Basics
========================

DollarScript has it's own peculiarities, mostly these exists to help with it's major function - data/API centric Internet applications. So it's important to understand the basic concepts before getting started.

Reactive Programming
--------------------

DollarScript expressions are by default *lazy*, this is really important to understand otherwise you may get some surprises. This lazy evaluation makes DollarScript a [reactive programming language](http://en.wikipedia.org/wiki/Reactive_programming) by default.


Let's see some of that behaviour in action:

```dollar

variableA := 1
variableB := $variableA
variableA := 2

=> $variableB == 2 
```

In the above example we are declaring (using the declarative operator `:=`) that variableA is current the value 1, we then declare that variableB is the *same as* variableA. So when we change variableA to 2 we also change variableB to 2.

The assertion operator `=>` will throw an assertion error if the value following is either non boolean or not true.


Now let's throw in the -> or causes operator :

```dollar
 
a=1
$a -> { >> $a }
a=2
a=3
a=4
 
```

~~~
2
3
4
~~~

That remarkable piece of code will simply output each change made to the variable a, but wait a minute what about ...

```dollar

b=1
a=1
$a + $b + 1 -> { >> "a=" + $a + ", b=" + $b}
a=2
a=3
a=4
b=2 
```

~~~
a=2, b=1 
a=3, b=1 
a=4, b=1 
a=4, b=2 
~~~

Yep, you can write reactive expressions based on collections or arbitrary expressions !! When any component changes the right hand side is re-evaluated (the actual value that changed is passed in as $1).


###Assignment

Obviously the declarative/reactive behavior is fantastic for templating and creating lambda style expressions, however a lot of the time we want to simply assign a value.

```dollar

variableA = 1
variableB = $variableA
variableA = 2

=> $variableB == 1 
```

So as you can see when we use the `=` assignment operator we assign the *value* of the right hand side to the variable. Watch what happens when we use expressions.


```dollar

variableA = 1
variableB = $variableA
variableC = ($variableA +1 )
variableD := ($variableA + 1)
variableA = 2

=> $variableB == 1 
=> $variableC == 2 
=> $variableD == 3
 
```

So `:=` allows the default behaviour of Dollar, which is to make everything declarative, and `=` is used to nail down a particular value. Later we'll come across the value anchor operator or diamond `<>` which instructs DollarScript to fix a value at the time of declaration. More on that later.

###Blocks

DollarScript supports several block types, the first is the 'line block' a line block lies between `{` and `}` and is separated by either newlines or `;` characters.

```dollar

block := {
    "Hello "
    "World"
}

=> $block == "World"

block2 := {1;2;}

=> $block2 == 2

```
When a line block is evaluated the result is the value of the last entry. For advanced users note that all lines will be evaluated, the value is just ignored. A line block behaves a lot like a function in an imperative language.
 
Next we have the array block, the array block preserves all the values each part is seperated by either a `,` or a newline but is delimited by `[` and `]`.

```dollar

array := [
    "Hello "
    "World"
]

=> $array == ["Hello ","World"]

array2 := [1,2]

=> $array2 == [1,2]

```

Finally we have the appending block, when an appending (or map) block is evaluated the result is the concatenation (using $plus() in the Dollar API) of the parts from top to bottom. The appending block starts and finishes with the `{` `}` braces, however each part is seperated by a `,` not a `;` or *newline*

```dollar

appending := {
    "Hello ",
    "World"
}

=> $appending == "Hello World"

appending2 := { 1, 2}

=> $appending2 == 3

```

Appending blocks can be combined with the pair `:` operator to create maps/JSON like this:


```dollar

appending := {
    first:"Hello ",
    second:"World"
}

>> $appending

=> $appending.second == "World"

```

The stdout operator `>>` is used to send a value to stdout in it's serialized (JSON) format, so the result of the above would be to output `{"first":"Hello ","second":"World"}` a JSON object created using JSON like syntax. This works because of how appending works with pairs, i.e. they are joined together to form a map.

```dollar
 
pair1 := first : "Hello ";
pair2 := second : "World";
  
=> $pair1 + $pair2 == {"first":"Hello ","second":"World"}
 
```

###Reactive Control Flow

DollarScript as previously mentioned is a reactive programming language, that means that changes to one part of your program can automatically affect another. Consider this a 'push' model instead of the usual 'pull' model.

Let's start with the simplest reactive control flow operator, the '->' or 'causes' operator. 

```dollar
 
$a -> { >> $a } //alternatively for clarity '$a causes {>> $a} '

a=1
a=2
a=3
a=4
a=2
 
```

When the code is executed we'll see the values 1,2,3,4,2 printed out, this is because whenever `$a` changes the block { >> $a } is evaluated, resulting in the variable $a being printed to stdout. Imagine how useful that is for debugging changes to a variable!

Next we have the 'when' operator, there is no shorthand for this operator, to help keep you code readable:


```dollar

a=1
 
when $a == 2 { >> $a } 

a=2
a=3
a=4
a=2
 
```

This time we'll just see the number 2 twice, this is because the `when` operator triggers the evaluation of the supplied block ONLY when the supplied expression (`$a == 2`) becomes true. 



Imperative Control Flow
-----------------------

Parameters &amp; Functions
----------------------

In most programming languages you have the concept of functions and parameters, i.e. you can parametrized blocks of code. In DollarScript you can parameterize *anything*. For example let's just take a simple expression that adds two strings together, in reverse order, and pass in two parameters.

```dollar
($2 + " " + $1)("Hello", "World") <=> "World Hello"

```

The naming of positional parameters is the same as in shell scripts.

Now if we take this further we can use the declaration operator `:=` to say that a variable is equal to the expression we wish to parameterise, like so:

```dollar

testParams := ($2 + " " + $1)
($testParams) ("Hello", "World") <=> "World Hello"

```

Yep we built a function just by naming an expression. You can name anything and parameterise it - including maps, lists, blocks and plain old expressions. 

Obviously this syntax would be a bit clunky so DollarScript let's you use some nice syntatic sugar by letting you drop the $ operator and refer to the variable directly in this context.


```dollar
testParams := ($2 + " " + $1)
testParams("Hello", "World") <=> "World Hello"
```

What about named parameters, that would be nice.

```dollar
testParams := ($last + " " + $first)
testParams(first:"Hello", last:"World") <=> "World Hello"
```

Yep you can use named parameters, then refer to the values by the names passed in.


Resources &amp; URIs
--------------------

URIs are first class citizen's in DollarScript. They refer to a an arbitrary resource, usually remote, that can be accessed using the specified protocol and location. Static URIs can be referred to directly without quotation marks, dynamic URIs can be built using the `uri` operator

```dollar


search="Unikitty"

dynamicURI= uri "camel:http://google.com?q="+$search

marinaVideos = <+ camel:https://itunes.apple.com/search?term=Marina+And+The+Diamonds&entity=musicVideo
>> $marinaVideos.results each { $1.trackViewUrl }

```

In this example we've requested a single value (using `<+`) from a uri and assigned the value to `$marinaVideos` then we simply iterate over the results  using `each` and each value (passed in to the scope as `$1`) we extract the `trackViewUrl`. The each operator returns a list of the results and that is what is passed to standard out.


Using Java
----------

Hopefully you'll find DollarScript a useful and productive language, but there will be many times when you just want to quickly nip out to a bit of Java. To do so, just surround the Java in backticks.

```dollar

variableA="Hello World"

java = `out=scope.get("variableA");`

$java <=> "Hello World"

```

A whole bunch of imports are done for you automatically (java.util.*, java.math.*) but you will have to fully qualify any thirdparty libs. The return type will be of type `var` and is stored in the variable `out`. The Java snippet also has access to the scope (me.neilellis.dollar.script.Scope) object on which you can get and set DollarScript variables.

Reactive behaviour is supported on the Scope object with the listen and notify methods on variables. You'll need to then built your reactivity around those variables or on the `out` object directly (that's a pretty advanced topic).


Iterative Operators
-------------------

Numerical Operators
-------------------

DollarScript support the basic numerical operators +,-,/,*,%

Logical Operators
-----------------

DollarScript support the basic logical operators &&,||,!

Arrays
------

DollarScript's arrays are pretty similar to JavaScript. They are defined using the `[1,2,3]` style syntax and accessed using the `x[y]` subscript syntax.

```dollar
=> [1,2,3] + 4 == [1,2,3,4];
=> [1,2,3,4] - 4 == [1,2,3];
=> [] + 1 == [1] ;
=> [1] + [1] == [1,[1]];
=> [1] + 1 == [1,1];

[1,2,3][1] <=> 2
```

*Note we're introducing the assert equals or `<=>` operator here, this is a combination of `=>` and `==` that will cause an error if the two values are not equal.*
 
DollarScript maps are also associative arrays (like JavaScript) allowing you to request members from them using the array subscript syntax
 
```dollar
{"key1":1,"key2":2} ["key"+1] <=> 1
{"key1":1,"key2":2} [1] <=> {"key2":2}
{"key1":1,"key2":2} [1]["key2"] <=> 2
```

As you can see from the example you can request a key/value pair (or Tuple if you like) by it's position using a numeric subscript. Or you can treat it as an associative array and request an entry by specifying the key name. Any expression can be used as a subscript, numerical values will be used as indexes, otherwise the string value will be used as a key.
 
Pipe Operators
--------------

Modules & Module Locators
-------------------------

Remaining Operators
-------------------

Concurrency & Threads
---------------------



