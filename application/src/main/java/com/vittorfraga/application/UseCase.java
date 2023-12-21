package com.vittorfraga.application;

/**
 * Base class for use cases in the application layer.
 *
 * @param <Input>  The type of input for the use case.
 * @param <Output> The type of output for the use case.
 */
public abstract class UseCase<Input, Output> {

    /**
     * Executes the use case with the provided input.
     *
     * @param anInput The input for the use case.
     * @return The result of the use case execution.
     */
    public abstract Output execute(Input anInput);
}
