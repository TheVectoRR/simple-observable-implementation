# simple-observable-implementation

This simple repo helps to demonstrate how observables and operators work under the hood of RXJava2.

A chain of Observable operators do not operate independently on the original Observable that originates the chain, but they operate in turn, each one operating on the Observable generated by the operator immediately previous in the chain.
