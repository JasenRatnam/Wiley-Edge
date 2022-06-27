USE trackit;

-- Provide a Project Summary and change the DueDate.
UPDATE Project SET
    Summary = 'All lessons and exercises for the relational database milestone.',
    DueDate = '2018-10-15'
WHERE ProjectId = 'db-milestone';

-- Change Kingsly's LastName to 'Oaks'.
UPDATE Worker SET
    LastName = 'Oaks'
WHERE WorkerId = 2;

UPDATE ProjectWorker SET
    WorkerID = '5'
WHERE WorkerId = 2;

-- Disable safe updates.
SET SQL_SAFE_UPDATES = 0;

-- Deactivate active Projects from 2017.
UPDATE Project SET
    IsActive = 0
WHERE DueDate BETWEEN '2017-01-01' AND '2017-12-31'
AND IsActive = 1;



-- Update all of Kingsly's Task estimates to include 25% more time.
UPDATE Task SET
    EstimatedHours = EstimatedHours * 1.25
WHERE WorkerId = 2;
-- Enable safe updates.
SET SQL_SAFE_UPDATES = 1;