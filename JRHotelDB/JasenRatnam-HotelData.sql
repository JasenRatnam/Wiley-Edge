USE JasenRatnamHotelDB;

INSERT INTO `room` 
VALUES (201,'Double',0,2,4,199.99,10.00),(202,'Double',1,2,4,174.99,10.00),(203,'Double',0,2,4,199.99,10.00),(204,'Double',1,2,4,174.99,10.00),(205,'Single',0,2,2,174.99,0.00),(206,'Single',1,2,2,149.99,0.00),(207,'Single',0,2,2,174.99,0.00),(208,'Single',1,2,2,149.99,0.00),(301,'Double',0,2,4,199.99,10.00),(302,'Double',1,2,4,174.99,10.00),(303,'Double',0,2,4,199.99,10.00),(304,'Double',1,2,4,174.99,10.00),(305,'Single',0,2,2,174.99,0.00),(306,'Single',1,2,2,149.99,0.00),(307,'Single',0,2,2,174.99,0.00),(308,'Single',1,2,2,149.99,0.00),(401,'Suite',1,3,8,399.99,20.00),(402,'Suite',1,3,8,399.99,20.00);

SELECT *
FROM room;

INSERT INTO `guests` 
VALUES ('Aurore Lipton','762 Wild Rose Street','Saginaw','MI','48601','(377) 507-0974'),('Bettyann Seery','750 Wintergreen Dr.','Wasilla','AK','99654','(478) 277-9632'),('Duane Cullison','9662 Foxrun Lane','Harlingen','TX','78552','(308) 494-0198'),('Jasen Ratnam','135 Cat Tail Bay Dr','Conway','SC','29527','(843) 397-7718'),('Jeremiah Pendergrass','70 Oakwood St.','Zion','IL','60099','(279) 491-0960'),('Joleen Tison','87 Queen St.','Drexel Hill','PA','19026','(231) 893-2755'),('Karie Yang','9378 W. Augusta Ave.','West Deptford','NJ','8096','(214) 730-0298'),('Mack Simmer','379 Old Shore Street','Council Bluffs','IA','51501','(291) 553-0508'),('Maritza Tilton','939 Linda Rd.','Burke','VA','22015','(446) 351-6860'),('Walter Holaway','7556 Arrowhead St.','Cumberland','RI','2864','(446) 396-6785'),('Wilfred Vise','77 West Surrey Street','Oswego','NY','13126','(834) 727-1001'),('Zachery Luechtefeld','7 Poplar Dr.','Arvada','CO','80003','(814) 485-2615');

SELECT *
FROM guests;

INSERT INTO `amenities` 
VALUES (1,'Microwave',0.00),(2,'Jacuzzi',25.00),(3,'Refrigerator',0.00),(4,'Oven',0.00);

SELECT *
FROM amenities;

INSERT INTO `amenitieshotel` 
VALUES (1,201),(1,203),(1,205),(1,206),(1,207),(1,208),(1,301),(1,303),(1,305),(1,306),(1,307),(1,308),(1,401),(1,402),(2,201),(2,203),(2,205),(2,207),(2,301),(2,303),(2,305),(2,307),(3,202),(3,204),(3,205),(3,206),(3,207),(3,208),(3,302),(3,304),(3,305),(3,306),(3,307),(3,308),(3,401),(3,402),(4,401),(4,402);

SELECT *
FROM amenitieshotel;

INSERT INTO `reservations` 
VALUES (1,308,'Mack Simmer',1,0,'2023-02-02','2023-02-04',299.98),(2,203,'Bettyann Seery',2,1,'2023-02-05','2023-02-10',999.95),(3,305,'Duane Cullison',2,0,'2023-02-22','2023-02-24',349.98),(4,201,'Karie Yang',2,2,'2023-03-06','2023-03-07',199.99),(5,307,'Jasen Ratnam',1,1,'2023-03-17','2023-03-20',524.97),(6,302,'Aurore Lipton',3,0,'2023-03-18','2023-03-23',924.95),(7,202,'Zachery Luechtefeld',2,2,'2023-03-29','2023-03-31',349.98),(8,304,'Jeremiah Pendergrass',2,0,'2023-03-31','2023-04-05',874.95),(9,301,'Walter Holaway',1,0,'2023-04-09','2023-04-13',799.96),(10,207,'Wilfred Vise',1,1,'2023-04-23','2023-04-24',174.99),(11,401,'Maritza Tilton',2,4,'2023-05-30','2023-06-02',1199.97),(12,206,'Joleen Tison',2,0,'2023-06-10','2023-06-14',599.96),(13,208,'Joleen Tison',1,0,'2023-06-10','2023-06-14',599.96),(14,304,'Aurore Lipton',3,0,'2023-06-17','2023-06-18',184.99),(15,205,'Jasen Ratnam',2,0,'2023-06-28','2023-07-02',699.96),(16,204,'Walter Holaway',3,1,'2023-07-13','2023-07-14',184.99),(17,401,'Wilfred Vise',4,2,'2023-07-18','2023-07-21',1259.97),(18,303,'Bettyann Seery',2,1,'2023-07-28','2023-07-29',199.99),(19,305,'Bettyann Seery',1,0,'2023-08-30','2023-09-01',349.98),(20,208,'Mack Simmer',2,0,'2023-09-16','2023-09-17',149.99),(21,203,'Karie Yang',2,2,'2023-09-13','2023-09-15',399.98),(22,401,'Duane Cullison',2,2,'2023-11-22','2023-11-25',1199.97),(23,206,'Mack Simmer',2,0,'2023-11-22','2023-11-25',449.97),(24,301,'Mack Simmer',2,2,'2023-11-22','2023-11-25',599.97),(25,302,'Maritza Tilton',2,0,'2023-12-24','2023-12-28',699.96);

SELECT *
FROM reservations;

-- delete Jeremiah Pendergrass and his reservations from the database.
DELETE FROM guests
WHERE name = "Jeremiah Pendergrass";
--  ON DELETE CASCADE takes care of deleting any reservations connected to this user
