USE TrackIt;

SELECT *
FROM TaskStatus
WHERE IsResolved = 1;

-- Grab Tasks.

-- TaskStatusIds are in order, so we can use BETWEEN.
-- If they were out of sequence, we might use an IN (id1, id2, idN).
SELECT *
FROM Task
WHERE TaskStatusId BETWEEN 5 AND 8;

-- using INNER JOIN
SELECT 
    Task.TaskId,
    Task.Title,
    TaskStatus.Name
FROM TaskStatus
INNER JOIN Task ON TaskStatus.TaskStatusId = Task.TaskStatusId
WHERE TaskStatus.IsResolved = 1;

-- Let's see who's working on the Who's a GOOD boy!? game Project.
SELECT
    Project.Name,
    Worker.FirstName,
    Worker.LastName
FROM Project
INNER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
INNER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE Project.ProjectId = 'game-goodboy';

-- grab all TrackIt Task
SELECT * FROM Task;

-- JOIN each Task to its status.
SELECT *
FROM Task
INNER JOIN TaskStatus ON Task.TaskStatusId = TaskStatus.TaskStatusId;

-- The asterisk effectively includes all fields from both tables (including the duplicated TaskStatusID), but the query only returns 532 rows! 


SELECT * 
FROM Task
WHERE TaskStatusId IS NULL;
-- The query returns 11 rows. There are our missing rows! 543 - 11 = 532

-- fix our Task query, we add a LEFT OUTER JOIN:

SELECT *
FROM Task
LEFT OUTER JOIN TaskStatus 
    ON Task.TaskStatusId = TaskStatus.TaskStatusId;

-- Replacing a NULL Value With IFNULL()
SELECT
    Task.TaskId,
    Task.Title,
    IFNULL(Task.TaskStatusId, 0) AS TaskStatusId,
    IFNULL(TaskStatus.Name, '[None]') AS StatusName
FROM Task
LEFT OUTER JOIN TaskStatus 
    ON Task.TaskStatusId = TaskStatus.TaskStatusId;
    
SELECT
    Project.Name ProjectName, -- An alias makes this more clear.
    Worker.FirstName,
    Worker.LastName
FROM Project
LEFT OUTER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
LEFT OUTER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.WorkerId IS NULL; -- Throws out projects with workers.

-- we can simplify our query by omitting the Worker table
-- Projects without workers, we only need the bridge table to confirm.
SELECT
    Project.Name ProjectName
FROM Project
LEFT OUTER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
WHERE ProjectWorker.WorkerId IS NULL;

-- Workers Without a Project
SELECT
    Project.Name ProjectName,
    Worker.FirstName,
    Worker.LastName
FROM Project
RIGHT OUTER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
RIGHT OUTER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.ProjectId IS NULL;
-- WHERE ProjectWorker.WorkerId IS NULL; // This works as well. Why?

-- simplify by omitting Project.
-- Workers without a project
SELECT
    Worker.FirstName,
    Worker.LastName
FROM ProjectWorker
RIGHT OUTER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.ProjectId IS NULL;

--  rewrite the query to put the important concept, Worker, first
SELECT
    Worker.FirstName,
    Worker.LastName
FROM Worker
LEFT OUTER JOIN ProjectWorker ON Worker.WorkerId = ProjectWorker.WorkerId
WHERE ProjectWorker.WorkerId IS NULL;

-- differentiate between parent and child.
SELECT
    parent.TaskId ParentTaskId,
    child.TaskId ChildTaskId,
    CONCAT(parent.Title, ': ', child.Title) Title
FROM Task parent
INNER JOIN Task child ON parent.TaskId = child.ParentTaskId;

SELECT
   CONCAT(w.FirstName, ' ', w.LastName) WorkerName,
   p.Name ProjectName
FROM Worker w
CROSS JOIN Project p
WHERE w.WorkerId = 1
AND p.ProjectId NOT LIKE 'game-%';