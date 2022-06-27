USE trackit;

DELETE FROM Worker
WHERE WorkerId = 50;

SELECT * FROM Worker;

SET SQL_SAFE_UPDATES = 0;

-- Delete Tasks first because Task references ProjectWorker.
DELETE FROM Task
WHERE WorkerId = 5;

-- Delete ProjectWorker next. 
-- That removes Kingsly from all Projects.
DELETE FROM ProjectWorker
WHERE WorkerId = 5;

-- Finally, remove Panchito.
DELETE FROM Worker
WHERE WorkerId = 5;

SET SQL_SAFE_UPDATES = 1;


SELECT * 
FROM Task;

SELECT * 
FROM ProjectWorker;

SELECT * 
FROM Worker;