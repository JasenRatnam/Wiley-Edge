USE TrackIt;

-- views
CREATE VIEW ProjectNameWithMinTaskId
AS
SELECT
    p.Name ProjectName,
    MIN(t.TaskId) MinTaskId
FROM Project p
INNER JOIN Task t ON p.ProjectId = t.ProjectId
GROUP BY p.ProjectId, p.Name;

SELECT * FROM ProjectNameWithMinTaskId;

SELECT
    pt.ProjectName,
    pt.MinTaskId TaskId,
    t.Title
FROM Task t
INNER JOIN ProjectNameWithMinTaskId pt -- Aliased just like a table.
    ON t.TaskId = pt.MinTaskId;