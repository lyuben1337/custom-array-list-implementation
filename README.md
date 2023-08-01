# MyCoolArrayList - Learning Project

![Java Version](https://img.shields.io/badge/Java-8%2B-blue)

This repository contains the code for **MyCoolArrayList**, a Java learning project that implements a generic ArrayList-like data structure with additional functionality. The project aims to provide an understanding of generics and custom data structures in Java.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)

## Introduction

The **MyCoolArrayList** is an implementation of a dynamic array, similar to Java's built-in `ArrayList`, but with a few differences. It demonstrates the use of generics (`T extends Number`) to create a list capable of storing numeric elements. Additionally, it showcases the usage of iterators and functional programming concepts.

## Features

- Generic data structure with type constraint (`T extends Number`).
- Dynamic resizing of the internal array when the capacity is exceeded.
- Basic list operations: `add`, `get`, and `remove`.
- Mapping functionality using Java's functional interface (`Function<T, R>`).
- Implements the `MyCoolList` interface.
- Custom iterator for iterating over the list.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/learning-project.git
   ```

2. Open the project in your favorite Java IDE.

## Usage

The **MyCoolArrayList** class provides a simple API for managing a list of numeric elements. Here's how to use it:

```java
// Create a new MyCoolArrayList with the default capacity (8)
MyCoolList<Integer> myCoolList = new MyCoolArrayList<>();

// Add elements to the list
myCoolList.add(10);
myCoolList.add(20);
myCoolList.add(30);

// Access elements using index
int elementAtIndex1 = myCoolList.get(1); // Returns 20

// Remove an element from the list
myCoolList.remove(1); // Removes the element at index 1 (20)

// Mapping functionality using Function
MyCoolList<Double> mappedList = myCoolList.map(num -> num * 2.0);

// Use an iterator to traverse the list
Iterator<Integer> iterator = myCoolList.iterator();
while (iterator.hasNext()) {
    int element = iterator.next();
    // Process the element
}
```
