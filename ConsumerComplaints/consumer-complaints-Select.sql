USE ConsumerComplaints;
DESCRIBE Complaint;

SELECT DateReceived, Product, Company, State
FROM Complaint;

SELECT *
FROM Complaint;

SELECT 
    DateReceived, 
    Product, 
    Issue, 
    Company
FROM Complaint
WHERE State = 'LA';

SELECT *
FROM Complaint
WHERE State = 'LA'
AND (Product = 'Mortgage' OR Product = 'Debt collection');

SELECT *
FROM Complaint
WHERE State = 'LA'
AND Product = 'Mortgage' OR Product = 'Debt collection';

SELECT 
    Product, 
    Issue, 
    Company, 
    ResponseToConsumer
FROM Complaint
WHERE ConsumerDisputed = 1
AND ConsumerConsent = 1
AND Product NOT IN ('Mortgage', 'Debt collection');

SELECT *
FROM Complaint
WHERE SubProduct IS NULL;

SELECT * 
FROM Complaint 
WHERE SubProduct IS NOT NULL;

SELECT *
FROM Complaint
WHERE ComplaintId > 15000 OR ComplaintId IS NULL;

SELECT *
FROM Complaint
WHERE SubIssue = 'Account status'
OR SubIssue IS NULL;

-- All Complaints with a value for ComplaintNarrative. 
-- Exclude null values.
SELECT *
FROM Complaint
WHERE ComplaintNarrative IS NOT NULL;

SELECT 
	ComplaintId, 
	DateReceived, 
	DateSentToCompany, 
	DateDiff(DateSentToCompany, DateReceived) AS DateDifference
FROM Complaint;
