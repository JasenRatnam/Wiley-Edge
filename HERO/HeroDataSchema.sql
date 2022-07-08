drop database if exists HeroDB;

create database HeroDB;

use HeroDB;

-- Superpower
create table Superpower(
	Id int primary key auto_increment,
    Name varchar(50) not null
);

INSERT INTO `Superpower` (`Id`,`Name`)
VALUES
  (1,"penatibus"),
  (2,"vitae"),
  (3,"nulla."),
  (4,"nec,"),
  (5,"iaculis"),
  (6,"fermentum"),
  (7,"eu"),
  (8,"enim"),
  (9,"dolor."),
  (10,"eget");

-- Hero
create table Hero(
    Id int primary key auto_increment,
    IsHero boolean not null,
    Name varchar(50) not null,
    Description varchar(255),
    SuperpowerId int not null,
    foreign key fk_HeroSuperpower_Superpower(SuperpowerId)
		references Superpower(Id)
);

INSERT INTO `Hero` (`Id`,`IsHero`,`Name`,`Description`,`SuperpowerId`)
VALUES
  (1,"1","Gloria","bibendum ullamcorper. Duis cursus, diam at pretium aliquet, metus urna",7),
  (2,"0","Natalie","fringilla purus mauris a nunc. In at pede. Cras vulputate",1),
  (3,"0","Keefe","libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis",6),
  (4,"1","Chelsea","ut ipsum ac mi eleifend egestas. Sed pharetra, felis eget",8),
  (5,"1","Wang","Proin dolor. Nulla semper tellus id nunc interdum feugiat. Sed",5);





-- Organization
create table Organization(
	Id int primary key auto_increment,
    Name varchar(50) not null,
    IsHero boolean not null,
    Description varchar(255),
    Address varchar(255),
    Contact varchar(255)
);

INSERT INTO `Organization` (`Id`,`Name`,`IsHero`,`Description`,`Address`,`Contact`)
VALUES
  (1,"Vulputate Company","0","iaculis odio. Nam interdum enim non","3367 Sollicitudin Av.","1-384-497-8225"),
  (2,"Eget Metus In PC","0","hendrerit neque. In ornare sagittis felis. Donec","404-4909 Hymenaeos. St.","1-344-556-2479"),
  (3,"Parturient Corporation","0","amet,","Ap #360-2635 Sodales Rd.","(662) 365-4310"),
  (4,"Non Bibendum LLP","1","sed, hendrerit a, arcu. Sed et libero. Proin mi.","503-3183 Ac Ave","(459) 529-7755"),
  (5,"Imperdiet Nec Leo Foundation","1","condimentum. Donec at arcu. Vestibulum","Ap #840-8205 Id Ave","1-686-944-6247");

-- HeroOrganization
create table HeroOrganization(
	HeroId int not null,
    OrganizationId int not null,
    PRIMARY KEY pk_HeroOrganization (HeroId, OrganizationId),
    foreign key fk_HeroOrganization_HeroId (HeroId)
		references Hero(Id),
	foreign key fk_HeroOrganization_OrganizationId (OrganizationId)
		references Organization(Id)
);

INSERT INTO `HeroOrganization` (`HeroId`,`OrganizationId`)
VALUES
  (5,5),
  (3,3),
  (4,3),
  (2,2),
  (4,1);

-- Location
create table Location(
	Id int primary key auto_increment,
    Name varchar(50) not null,
    Latitude DECIMAL(10,8) not null,
    Longitude DECIMAL(11,8) not null,
    Description varchar(255),
    Address varchar(255)
);

INSERT INTO `Location` (`Id`,`Name`,`Latitude`,`Longitude`,`Description`,`Address`)
VALUES
  (1,"Todd Benjamin","-58.0471588864","-53.6414263296","risus. In mi pede, nonummy ut, molestie in, tempus","Ap #505-529 Molestie Rd."),
  (2,"Violet Mercado","-65.8355854336","-9.5690484736","dolor. Fusce feugiat. Lorem ipsum dolor sit","P.O. Box 951, 7190 Arcu. St."),
  (3,"Slade Larsen","-37.6763234304","-77.781766144","Ut sagittis lobortis mauris.","Ap #377-8320 Dolor Av."),
  (4,"Bree Mckay","2.158309376","74.59319808","Praesent eu nulla at sem molestie sodales.","Ap #266-1897 Luctus Ave"),
  (5,"Duncan Vaughan","49.4104300544","-113.5294377984","facilisis facilisis, magna","Ap #657-2363 Cursus. Av.");

-- Sighting
create table Sighting(
	Id int primary key auto_increment,
	HeroId int not null,
    LocationId int not null,
    Date datetime not null,
    foreign key fk_Sighting_HeroId (HeroId)
		references Hero(Id),
	foreign key fk_Sighting_LocationId (LocationId)
		references Location(Id)
);

INSERT INTO `Sighting` (`Id`,`HeroId`,`LocationId`,`Date`)
VALUES
  (1,1,3,"2021-12-13"),
  (2,2,1,"2022-8-17"),
  (3,3,1,"2022-4-2"),
  (4,5,4,"2022-2-20"),
  (5,2,2,"2023-6-3");
