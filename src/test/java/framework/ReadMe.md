# Test Framework

## Testing The Framework

Run src/test/java/framework/FrameworkRunner

This should create files in /target/cucumber-html-reports. There will be at least one failure (to prove that a screen
grab is made when a step fails), and some passes.

The features of the framework (exercised by the runner) are listed in /src/test/java/framework/features

## Overview Of The Framework

### Actor

Actors are the thing that establish the connection with the driver (e.g. the Chrome Selenium driver).

## Context

1. Has a handle on the default actor (specified in the test configuration).
1. Provides a place where data can be shared between steps (it is refreshed for each scenario). So you will probably add
   data members to it.

## Steps

* This provides some useful basic steps (that can be used in your SUT tests)

## Objects

* This provides some useful basic objects
