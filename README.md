# Avaliable Routes

## Exercise controller

### Get
1. Get all exercises by scheme. We can get Upper body or Lower body exercises.
2. Get  all exercises by equipment 
3. Get exercise by ID 
4. Get all exercises by body part
5. Get all exercises by equipment

### Post
Add Exercie require to put id and name of exercise in Json. Misspell in exercise name leads to 
Error 24909 and status 500. Wont crash server

### Put
TODO remove max reps and max weight

put equipment - ok

## Equipment controller
This should be available for admins only

### Get
1. get list of all equipment
2. get by id

### Post
1. add new piece of equipment

## Body Part controller
This should be available for admins only (or deleted)

### Get
1. get list of all body parts
2. get by id

### Post
1. add new body part (?!)

## Exercise Scheme controller

### Get
1. get list of all schemes
2. get by id

### Post
1. add new training scheme

## Gender controller

### Get
1. get list of all gender

## Login Data controller

this will be refactored due to login mechanism change in spring security

## Set scheme controller

### Get
1. get list of exercises and sets by training
2. get heaviest weight in given exercise by user login

### Post
1. add new 

