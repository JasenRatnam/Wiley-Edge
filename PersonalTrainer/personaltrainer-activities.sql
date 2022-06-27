USE PersonalTrainer;

-- Select all rows and columns from the Exercise table. (64 rows)
SELECT *
FROM exercise;

-- Select all rows and columns from the Client table (500 rows)
SELECT *
FROM client;

-- Select all columns from Client where the City is Metairie. (29 rows)
SELECT *
FROM client
WHERE City = "Metairie";

-- Is there a Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'? (0 rows)
SELECT *
FROM client
WHERE ClientId = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';


-- How many rows are in the Goal table? (17 rows)
SELECT *
FROM goal;

-- Select Name and LevelId from the Workout table. (26 rows)
SELECT Name, LevelId
FROM workout;

-- Select Name, LevelId, and Notes from Workout where LevelId is 2. (11 rows)
SELECT Name, LevelId, Notes
FROM workout
WHERE LevelId = 2;

-- Select FirstName, LastName, and City from Client where City is Metairie, Kenner, or Gretna. (77 rows)
SELECT FirstName, LastName, City
FROM Client
WHERE City = "Metairie" OR City = "Kenner" OR City = "Gretna";

-- Select FirstName, LastName, and BirthDate from Client for Clients born in the 1980s. (72 rows)
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate BETWEEN '1980-01-01' AND '1989-12-31';

-- Write the query above in a different way.
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate >= '1980-01-01' AND BirthDate <= '1989-12-31';

-- How many rows in the Login table have a .gov EmailAddress? (17 rows)
SELECT *
FROM login
WHERE EmailAddress LIKE '%.gov';

-- How many Logins do NOT have a .com EmailAddress? (122 rows)
SELECT *
FROM login
WHERE EmailAddress NOT LIKE '%.com';

-- Select first and last name of Clients without a BirthDate. (37 rows)
SELECT firstName, LastName
FROM client
WHERE BirthDate IS NULL;

-- Select the Name of each ExerciseCategory that has a parent (ParentCategoryId value is not null). (12 rows)
SELECT Name
FROM exercisecategory
WHERE ParentCategoryId IS NOT NULL;

-- Select Name and Notes of each level 3 Workout that contains the word 'you' in its Notes. (4 rows)
SELECT Name, Notes
FROM workout
WHERE LevelId = 3 AND Notes LIKE '%you%';

-- Select FirstName, LastName, City from Client whose LastName starts with L,M, or N and who live in LaPlace. (5 rows)
SELECT FirstName, LastName, City
FROM client
WHERE (LastName LIKE 'L%' OR LastName LIKE 'M%' OR LastName LIKE 'N%') AND City = "LaPlace";

-- Select InvoiceId, Description, Price, Quantity, ServiceDate and the line item total, a calculated value, from InvoiceLineItem, where the line item total is between 15 and 25 dollars. (667 rows)
SELECT InvoiceId, Description, Price, Quantity, ServiceDate, (Price*Quantity) AS line_item_total 
FROM invoicelineitem
HAVING line_item_total >= 15 AND line_item_total <= 25; 

-- Does the database include an email address for the Client, Estrella Bazely?
-- Select a Client record for Estrella Bazely. Does it exist?
-- If it does, select a Login record that matches its ClientId.

SELECT login.EmailAddress
FROM client, login
WHERE client.FirstName = "Estrella" AND client.LastName = "Bazely"
AND client.ClientId = login.ClientId;


-- What are the Goals of the Workout with the Name 'This Is Parkour'?

-- Select the WorkoutId from Workout where Name equals 'This Is Parkour'. (1 row)
-- Select GoalId from WorkoutGoal where the WorkoutId matches the WorkoutId from your first query. (3 rows)
-- Select the goal name from Goal where the GoalId is one of the GoalIds from your second query. (3 rows)

SELECT goal.Name
FROM workout, workoutgoal, goal
WHERE workout.Name = "This Is Parkour"
AND workout.WorkoutId = workoutgoal.WorkoutId
AND workoutgoal.GoalId = goal.GoalId;

-- Select all columns from ExerciseCategory and Exercise.
SELECT *
FROM exercisecategory
INNER JOIN exercise ON exercisecategory.ExerciseCategoryId = exercise.ExerciseCategoryId;

-- Select ExerciseCategory.Name and Exercise.Name where the ExerciseCategory does not have a ParentCategoryId (it is null).
SELECT exercisecategory.Name, exercise.Name
FROM exercisecategory
INNER JOIN exercise ON exercisecategory.ExerciseCategoryId = exercise.ExerciseCategoryId
WHERE exercisecategory.parentCategoryId IS NULL;

-- Rewrite the query using aliases:
SELECT exercisecategory.Name CategoryName, exercise.Name ExerciseName
FROM exercisecategory
INNER JOIN exercise ON exercisecategory.ExerciseCategoryId = exercise.ExerciseCategoryId
WHERE exercisecategory.parentCategoryId IS NULL;

-- Select FirstName, LastName, and BirthDate from Client and EmailAddress from Login where Client.BirthDate is in the 1990s.
SELECT client.FirstName, client.LastName, client.BirthDate, login.EmailAddress
FROM client
INNER JOIN login ON client.ClientId = login.ClientId
WHERE client.BirthDate BETWEEN "1990-01-01" AND "1999-12-31";










