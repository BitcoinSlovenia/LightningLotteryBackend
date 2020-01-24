CREATE TABLE lightninglottery.raffle (
	raffleID BIGINT auto_increment NOT NULL COMMENT 'raffleID, each raffle has its own ID. ID is referenced in ticket',
	startDate TIMESTAMP NOT NULL COMMENT 'DateTime when raffle started',
	endDate TIMESTAMP NOT NULL COMMENT 'DateTime when raffle ended',
	winningNumbers TEXT NOT NULL COMMENT 'Winning numbers in Text form. Each number is split by ","',
	CONSTRAINT Raffle_PK PRIMARY KEY (raffleID)
)COMMENT='Table for Raffles in Lightning Lottery';


CREATE TABLE lightninglottery.tickets (
	TicketID BIGINT auto_increment NOT NULL,
	RaffleID BIGINT NOT NULL COMMENT 'in which raffle this ticket is used',
	openNodeID varchar(100) NOT NULL COMMENT 'OpenNodeID of generated invoice',
	customerName varchar(100) NULL,
	customerEmail varchar(100) NULL,
	customerDescription varchar(300) NULL,
	numbers varchar(100) NULL COMMENT 'Selected numbers as string split by ","',
	status varchar(100) NULL COMMENT 'PAID, PROCESSING; FAILED',
	amount DECIMAL NULL,
	fiatValue DECIMAL NULL,
	lnPaymentRequest varchar(500) NULL,
	CONSTRAINT Tickets_PK PRIMARY KEY (TicketID),
	CONSTRAINT Tickets_FK FOREIGN KEY (RaffleID) REFERENCES lightninglottery.Raffle(raffleID) ON DELETE RESTRICT ON UPDATE RESTRICT
)COMMENT='Table for Tickets in Lightning Lottery, every request ticket request counts as a ticket and gets saved here';

CREATE TABLE lightninglottery.winners (
	raffleID BIGINT NOT NULL,
	ticketID BIGINT NOT NULL,
	prizeWon DECIMAL NOT NULL COMMENT 'v satoshijih',
	prizeType varchar(100) NOT NULL COMMENT 'sedmica, šestica, petica itd...',
	status varchar(100) NOT NULL COMMENT 'CLAIMED / UNCLAIMED -> when user claims reward, his status changes to CLAIMED',
	CONSTRAINT Winners_FK FOREIGN KEY (raffleID) REFERENCES lightninglottery.Raffle(raffleID) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT Winners_FK_1 FOREIGN KEY (ticketID) REFERENCES lightninglottery.Tickets(TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT
)COMMENT='Every week when a raffle ends we pick random numbers and find winners.'
