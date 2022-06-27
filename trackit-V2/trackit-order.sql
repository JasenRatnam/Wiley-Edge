USE TrackIt;

--  sorted in natural order.
-- by a Worker's primary key, WorkerId, ascending.
SELECT * FROM Worker;

-- default ordering in ASC
-- Sort ascending by LastName.
-- ASC isn't strictly required since it is the default sort direction.
SELECT * 
FROM Worker
ORDER BY LastName;

-- reverse order
-- Sort descending by LastName.
SELECT * 
FROM Worker
ORDER BY LastName DESC;

-- sorting for JOIN Queries
SELECT
    w.FirstName,
    w.LastName,
    p.Name ProjectName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY w.LastName ASC;

-- Sort by a Worker's last name, then we sort by the Project's name.
-- The results are grouped by Worker, 
-- and Projects are listed alphabetically within each Worker group.
SELECT
    w.FirstName,
    w.LastName,
    p.Name ProjectName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY w.LastName ASC, p.Name ASC;

-- Results are sorted non-null to null, then by TaskStatus.Name.
-- That puts NULL values last.
SELECT
    t.Title,
    s.Name StatusName
FROM Task t
LEFT OUTER JOIN TaskStatus s ON t.TaskStatusId = s.TaskStatusId
ORDER BY ISNULL(s.Name), s.Name ASC;

-- There are 100 Workers in the TrackIt database. 
-- If we want the first 10, ordered by last name descending, we write:
SELECT *
FROM Worker
ORDER BY LastName DESC
LIMIT 0, 10;

-- Row offset is optional and uses the default value 0. 
SELECT *
FROM Worker
ORDER BY LastName DESC
LIMIT 10;


-- Offset by 10 rows and grab 10.
SELECT *
FROM Worker
ORDER BY LastName DESC
LIMIT 10, 10;

-- Offset past available records
SELECT *
FROM Worker
ORDER BY LastName DESC
LIMIT 200, 10;

-- Skip the first 100 records and show the next 25.
SELECT
    w.FirstName,
    w.LastName,
    p.Name ProjectName
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Project p ON pw.ProjectId = p.ProjectId
ORDER BY w.LastName DESC, p.Name ASC
LIMIT 100, 25;

SELECT
    p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
ORDER BY p.Name;
-- The query returns 543 records. 
-- The ProjectId and ProjectName values are repeated,

-- To remove the duplicates, we add DISTINCT.
SELECT DISTINCT
    p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
ORDER BY p.Name;

-- Count TaskIds, 543 values
SELECT COUNT(TaskId)
FROM Task;

-- Count everything, 543 values
SELECT COUNT(*)
FROM Task;

-- 532
SELECT COUNT(TaskStatusId)
FROM Task;
-- That doesn't match the number of Tasks. This is because NULLs are omitted.


-- group the results by status, and then count the number of tasks associated with each status.
SELECT
    IFNULL(s.Name, '[None]') StatusName,
    COUNT(t.TaskId) TaskCount
FROM Task t
LEFT OUTER JOIN TaskStatus s ON t.TaskStatusId = s.TaskStatusId
GROUP BY s.Name
ORDER BY s.Name;
-- INNER JOIN would eliminate NULL TaskStatusIds.

-- Most uses of DISTINCT can be accomplished by grouping data with GROUP BY
SELECT DISTINCT
    p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
ORDER BY p.Name;

-- To get a list of unique project names, we could GROUP BY Project.Name instead.
SELECT
    p.Name ProjectName,
    p.ProjectId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
GROUP BY p.Name
ORDER BY p.Name;


-- HAVING
SELECT
    CONCAT(w.FirstName, ' ', w.LastName) WorkerName,
    SUM(t.EstimatedHours) TotalHours
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Task t ON pw.WorkerId = t.WorkerId
    AND pw.ProjectId = t.ProjectId
GROUP BY w.WorkerId, w.FirstName, w.LastName;

-- To throw out totals less than 100 hours, add a HAVING clause. The HAVING clause is placed a
-- after GROUP BY and before ORDER BY.
SELECT
    CONCAT(w.FirstName, ' ', w.LastName) WorkerName,
    SUM(t.EstimatedHours) TotalHours
FROM Worker w
INNER JOIN ProjectWorker pw ON w.WorkerId = pw.WorkerId
INNER JOIN Task t ON pw.WorkerId = t.WorkerId
    AND pw.ProjectId = t.ProjectId
GROUP BY w.WorkerId, w.FirstName, w.LastName
HAVING SUM(t.EstimatedHours) >= 100
ORDER BY SUM(t.EstimatedHours) DESC;

SELECT
    p.Name ProjectName,
    MIN(t.DueDate) MinTaskDueDate
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
WHERE p.ProjectId LIKE 'game-%'
    AND t.ParentTaskId IS NOT NULL
GROUP BY p.ProjectId, p.Name
ORDER BY p.Name;

SELECT
    p.Name ProjectName,
    MIN(t.DueDate) MinTaskDueDate,
    MAX(t.DueDate) MaxTaskDueDate,
    SUM(t.EstimatedHours) TotalHours,
    AVG(t.EstimatedHours) AverageTaskHours,
    COUNT(t.TaskId) TaskCount
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
WHERE t.ParentTaskId IS NOT NULL
GROUP BY p.ProjectId, p.Name
HAVING COUNT(t.TaskId) >= 10
ORDER BY COUNT(t.TaskId) DESC, p.Name;
