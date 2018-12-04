# simple-observable-implementation

This simple repo helps to demonstrate how observables and operators work under the hood of RXJava2.

Most operators operate on an Observable and return an Observable.
This allows you to apply these operators one after the other, in a chain.
Each operator in the chain creates a new Observable based on the results from the operation of the previous operator.
