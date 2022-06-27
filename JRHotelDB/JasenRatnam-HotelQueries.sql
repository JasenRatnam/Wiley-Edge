USE JasenRatnamHotelDB;

-- 1.
-- Write a query that returns a list of reservations that end in July 2023, 
-- including the name of the guest, the room number(s), and the reservation dates.

SELECT reservationId, name, roomNumber, startDate, endDate
FROM reservations
WHERE endDate BETWEEN "2023-07-01" AND "2023-07-31";
/*
reservationId, name,             roomNumber, startDate,     endDate
'15',         'Jasen Ratnam',    '205',      '2023-06-28', '2023-07-02'
'16',          'Walter Holaway', '204',      '2023-07-13', '2023-07-14'
'17',          'Wilfred Vise',   '401',      '2023-07-18', '2023-07-21'
'18',          'Bettyann Seery', '303',      '2023-07-28', '2023-07-29'
*/

-- 2.
-- Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.
SELECT reservationId, name, reservations.roomNumber, startDate, endDate
FROM reservations
INNER JOIN amenitiesHotel ON amenitiesHotel.roomNumber = reservations.roomNumber
WHERE
(amenitiesHotel.amenitiesId = 
	(
	SELECT amenitiesId
	FROM amenities
	WHERE amenityType = "Jacuzzi"
	)
);
/*
# reservationId	name	roomNumber	startDate	endDate
4	Karie Yang	201	2023-03-06	2023-03-07
2	Bettyann Seery	203	2023-02-05	2023-02-10
21	Karie Yang	203	2023-09-13	2023-09-15
15	Jasen Ratnam	205	2023-06-28	2023-07-02
10	Wilfred Vise	207	2023-04-23	2023-04-24
9	Walter Holaway	301	2023-04-09	2023-04-13
24	Mack Simmer	301	2023-11-22	2023-11-25
18	Bettyann Seery	303	2023-07-28	2023-07-29
3	Duane Cullison	305	2023-02-22	2023-02-24
19	Bettyann Seery	305	2023-08-30	2023-09-01
5	Jasen Ratnam	307	2023-03-17	2023-03-20
*/
    
-- 3.
-- Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)

SELECT reservations.name, reservations.roomNumber, reservations.startDate, reservations.endDate,
SUM(reservations.numberOfAdults + reservations.numberOfChildrens) AS NumberOfPeople
FROM reservations
WHERE reservations.name = "Jasen Ratnam"
GROUP BY reservations.roomNumber;
/*
# name	       	roomNumber	startDate	endDate		NumberOfPeople
Jasen Ratnam	205			2023-06-28	2023-07-02	2
Jasen Ratnam	307			2023-03-17	2023-03-20	2
*/

-- 4.
-- Write a query that returns a list of rooms, reservation ID, and per-room cost 
-- for each reservation. The results should include all rooms, whether or not there is a 
-- reservation associated with the room.
SELECT room.roomNumber, if(reservationId IS NOT NULL, 'TRUE', 'FALSE') AS asReservation
FROM room
LEFT OUTER JOIN reservations ON room.roomNumber = reservations.roomNumber;
/*
# roomNumber	asReservation
201	TRUE
202	TRUE
203	TRUE
203	TRUE
204	TRUE
205	TRUE
206	TRUE
206	TRUE
207	TRUE
208	TRUE
208	TRUE
301	TRUE
301	TRUE
302	TRUE
302	TRUE
303	TRUE
304	TRUE
305	TRUE
305	TRUE
306	FALSE
307	TRUE
308	TRUE
401	TRUE
401	TRUE
401	TRUE
402	FALSE
*/

-- 5.
-- Write a query that returns all rooms with a capacity of three or more and that are 
-- reserved on any date in April 2023.
SELECT reservationId, reservations.roomNumber, startDate, endDate, 
room.standardOccupancy, room.maximumOccupancy
FROM reservations
INNER JOIN room ON room.roomNumber = reservations.roomNumber
WHERE (room.maximumOccupancy >= 3 AND room.standardOccupancy <= 3)
AND 
(
	("2023-04-01" BETWEEN startDate AND endDate)
    OR
    (startDate BETWEEN "2023-04-01" AND "2023-04-31")
)
ORDER BY reservations.reservationId;

/*
# reservationId	roomNumber	startDate	endDate	standardOccupancy	maximumOccupancy
9	301	2023-04-09	2023-04-13	2	4
*/

-- 6.
-- Write a query that returns a list of all guest names and the number of reservations 
-- per guest, sorted starting with the guest with the most reservations and then by the 
-- guest's last name.
SELECT guests.name, count(reservationId) AS numberOfReservations
FROM guests
LEFT OUTER JOIN reservations ON guests.name = reservations.name
GROUP BY guests.name
ORDER BY numberOfReservations DESC, guests.name;
/* ERROR in solution: should of split first name and last name
name	numberOfReservations
Mack Simmer	4
Bettyann Seery	3
Aurore Lipton	2
Duane Cullison	2
Jasen Ratnam	2
Joleen Tison	2
Karie Yang	2
Maritza Tilton	2
Walter Holaway	2
Wilfred Vise	2
Zachery Luechtefeld	1
*/

-- 7.
-- Write a query that displays the name, address, and phone number of a guest based on their 
-- phone number. (Choose a phone number from the existing data.)
SELECT *
FROM guests
WHERE phoneNumber = "(478) 277-9632";
/*
name	        address			city	state	zipCode	phoneNumber
Bettyann Seery	750 Wintergreen Dr.	Wasilla	AK		99654	(478) 277-9632
*/