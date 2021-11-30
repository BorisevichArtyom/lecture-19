CREATE TABLE `blogpost`(`id` BIGINT NOT NULL AUTO_INCREMENT,`publishing_date` date,`title` varchar(45),
`version` int(11),`url` varchar(45),PRIMARY KEY (`id`));

CREATE TABLE `book`(`id` BIGINT NOT NULL AUTO_INCREMENT,`publishing_date` date,`title` varchar(45),`version` int(11),
`pages` int(11),PRIMARY KEY (`id`));

INSERT INTO blogpost(id,publishing_date,title,version,url)
VALUES (1, '2008-11-11', 'How to be a man',1,'https://developer.mozilla.org'), (2, '2021-11-11', 'How to stay young',1,'https://developer.mozilla.org');


INSERT INTO book(id,publishing_date,title,version,pages)
VALUES (1, '2008-11-11', 'War',1,300), (2, '2021-11-11', 'Peace',1,400);