# Demo Graphql server project using graphql-spring-boot

## Description
This is multi-module project that consist of graphql server (gateway) and two data services. 
Demo project includes:
 - Query, Mutation, Subscription operations via resolvers;
 - Solving n+1 problem with DataLoader;
 - Error handling;
 - Resolver methods security using AOP;
 - Testing.

## Data api
Gateway application communicates with two services via REST API using feign client.