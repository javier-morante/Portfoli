/*-----USERS AND ROLES-----*/

-- users and roles
-- Super admin manage all databases and all tables
CREATE USER 'SuperAdmin' IDENTIFIED BY '1234';
GRANT ALL ON *.* TO 'SuperAdmin';
-- admin to manage all tabes of pokemon database
CREATE USER 'PokeAdmin' IDENTIFIED BY '1234';
GRANT ALL ON pokemondb.* TO 'PokeAdmin';
-- Roles for database
CREATE ROLE ash;
GRANT ALL ON pokemondb.* TO 'ash';
CREATE ROLE Pokeselect;
GRANT SELECT , EXECUTE ON pokemondb.* TO 'Pokeselect';
CREATE ROLE Pokedata;
GRANT INSERT , DELETE, UPDATE, SELECT ON pokemondb.* TO 'Pokedata';
CREATE ROLE Poketable;
GRANT CREATE , ALTER ,SELECT ,DELETE,DROP ,INDEX ,UPDATE , INSERT ON pokemondb.* TO 'Poketable';
CREATE ROLE PokeFuncProce;
GRANT ALTER ROUTINE , EXECUTE , CREATE ROUTINE  ON pokemondb.* TO PokeFuncProce;
-- set role to diferents users
CREATE USER 'javier' IDENTIFIED BY '1234';
GRANT Pokedata TO 'javier';
CREATE USER 'adrian' IDENTIFIED BY '1234';
GRANT Poketable TO 'adrian';
CREATE USER 'pol' IDENTIFIED BY '1234';
GRANT PokeFuncProce TO 'pol';
CREATE USER 'gabriel' IDENTIFIED BY '1234';
GRANT Pokeselect TO 'gabriel';
CREATE USER 'juan' IDENTIFIED BY '1234';
GRANT ash TO 'juan';
-- users and and permissions
CREATE USER 'miguiel' IDENTIFIED BY '1234';
GRANT SELECT , INSERT ,DELETE ON pokemondb.object TO 'miguiel' ;
CREATE USER 'pepe' IDENTIFIED BY '1234';
GRANT SELECT , UPDATE ,CREATE ROUTINE , DROP ON pokemondb.* TO 'pepe';
CREATE USER 'guillermo' IDENTIFIED BY '1234';
GRANT SELECT , CREATE , DROP ON pokemondb.* TO 'guillermo';
CREATE USER 'samuel' IDENTIFIED BY '1234';
GRANT SELECT , CREATE , DROP ON pokemondb.* TO 'guillermo';
GRANT SELECT  ON *.* TO 'guillermo';
CREATE USER 'karen' IDENTIFIED BY '1234';
GRANT SELECT , CREATE ROUTINE , EXECUTE , ALTER ROUTINE ON pokemondb.* TO 'karen';
GRANT SELECT ON *.* TO 'karen';
FLUSH PRIVILEGES ;
-- revoke and edit permisions
REVOKE ALL PRIVILEGES ON *.* FROM 'karen';
REVOKE DROP ON pokemondb.* FROM 'guillermo';
REVOKE DELETE , UPDATE ON pokemondb.object FROM  'miguiel';


/*-----PROCEDURES-----*/

/* -Procedure 1
The next procedure returns the name of the pokemon that belongs to the type given as a parameter in the procedure
*/
DROP PROCEDURE IF EXISTS get_pokemons_by_type;

DELIMITER //
CREATE PROCEDURE get_pokemons_by_type(IN inp_type VARCHAR(40))
BEGIN

	SELECT pok.name FROM pokemon AS pok 
		INNER JOIN pokemon_type AS pok_typ ON pok_typ.pokemon_id = pok.id_pokemon
        INNER JOIN type AS typ ON typ.id_type = pok_typ.type_id
        WHERE typ.name = inp_type;
        
END //
DELIMITER ;

CALL get_pokemons_by_type("planta");
CALL get_pokemons_by_type("fuego");
CALL get_pokemons_by_type("volador");


/* -Procedure 2
The next procedure returns all the games that have been launched between a given range of dates
*/
DROP PROCEDURE IF EXISTS get_game_in_date_range;

DELIMITER //
CREATE PROCEDURE get_game_in_date_range(IN start_date DATE, IN end_date DATE)
BEGIN

	SELECT gen.release_date AS launch_year, gen.number, gen.region FROM generation AS gen 
		WHERE gen.release_date BETWEEN start_date AND end_date
        ORDER BY gen.release_date;
        
END //
DELIMITER ;

CALL get_game_in_date_range("1990-01-01", "2030-01-01");
CALL get_game_in_date_range("1998-05-01", "2007-02-03"); 
CALL get_game_in_date_range("2040-01-01", "2050-01-01"); 


/* -Procedure 3
The following procedure asks a char value as input (p, o, g), then verifyes if the value is valid, 
if its valid, then returns the table that references to that value if its not, they select a "Not valid value" message
*/
DROP PROCEDURE IF EXISTS get_data;

DELIMITER //
CREATE PROCEDURE get_data(IN id CHAR)
BEGIN
	SET @given_id := UPPER(id);
    
	IF (@given_id NOT IN ("P", "O", "G")) THEN
		SELECT "Not valid Value" AS ERROR;
        
	ELSEIF (id = "P") THEN
		SELECT * FROM pokemon;
        
	ELSEIF (@given_id = "O") THEN
		SELECT * FROM object;
        
    ELSEIF (@given_id = "G") THEN
		SELECT * FROM game;
        
	END IF;
END //
DELIMITER ;

CALL get_data("z");
CALL get_data("p");
CALL get_data("o");
CALL get_data("g");

/* -Procedure 4
The next procedure counts all the objects that belongs to a given generation
*/
DROP PROCEDURE IF EXISTS count_object;

DELIMITER //
CREATE PROCEDURE count_object(IN id_gen INT)
BEGIN
	DECLARE cout INTEGER DEFAULT 0;
    DECLARE id INTEGER;
	DECLARE end INTEGER DEFAULT 0;
	
	DECLARE data CURSOR FOR SELECT id_generation FROM generation_object;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET end = 1;
	OPEN data;

    _loop : LOOP
		FETCH data INTO id;
        
		IF (end = 1) THEN
			CLOSE data;
            SELECT cout;
			leave _loop;
		
        ELSEIF (id = id_gen) THEN
            SET cout = cout + 1;
		END IF;
        
    END LOOP _loop;
		
END //
DELIMITER ;

CALL count_object(101);
CALL count_object(106);
CALL count_object(108);


/* -Procedure 5
The following procedure generates a new medal, gym and leader set in the database
*/
DROP PROCEDURE IF EXISTS add_new_gym;

DELIMITER //
CREATE PROCEDURE add_new_gym(IN gym_name VARCHAR(40), IN gym_type VARCHAR(40), IN med_name VARCHAR(40), IN leader_name VARCHAR(40), IN age INTEGER(3), IN gen_id INT)
BEGIN

	INSERT INTO gym(name, gym_type) VALUES (gym_name, gym_type);
    SET @gym_id = LAST_INSERT_ID();
    
    INSERT INTO medal(name) VALUES (med_name);
	SET @medal_id = LAST_INSERT_ID();
    
    INSERT INTO gym_leader(name, age, generation_id) VALUES (leader_name, age, gen_id);
	SET @leader_id = LAST_INSERT_ID();

    INSERT INTO gym_leader_gym_medal values (@leader_id, @gym_id ,@medal_id);

END //
DELIMITER ;

CALL add_new_gym("Ciudad Lavanda", "Fantasma", "Fantasmal", "Paco", "36", "101");


/* -Procedure 6
The following procedure grab 2 input parameters (table name, id) and returns the data that belongs to the element in the 
table with that id, if data is not found returns a message that says “DATA NOT FOUND”, if the id is not given the procedure 
will show all the data from the table, if the given table didn’t exist will return a message “table not found” and if 
anything is given to the procedure will return a message “Table can’t be null”
*/
DELIMITER //
CREATE OR REPLACE PROCEDURE selAll(IN tTable VARCHAR(100),IN iID_data SMALLINT)
BEGIN
	
	DECLARE CONTINUE HANDLER FOR 1146 SELECT 'The table hasn\'t exist' ERROR;
    DECLARE CONTINUE HANDLER FOR 1064 SELECT 'Table can\'t be null' ERROR;
	
	IF(iID_data IS NULL) THEN 
	
	SET @instruc := CONCAT('SELECT * FROM ',tTable);

	ELSE
	
	 CASE tTable
	 
		 WHEN 'pokemon' THEN
		 
		 SET @instruc := CONCAT('SELECT * FROM ',tTable,' WHERE id_pokemon = ',iID_data);
         SET @isExist := CONCAT('SELECT 1 INTO @DONE FROM ',tTable,' WHERE id_pokemon = ',iID_data);
		 
		 WHEN 'type' THEN
		 
		 SET @instruc := CONCAT('SELECT * FROM ',tTable,' WHERE id_type = ',iID_data);
         SET @isExist := CONCAT('SELECT 1 INTO @DONE FROM ',tTable,' WHERE id_type = ',iID_data);
		 
		 ELSE 
		 
		 	SET @instruc := CONCAT('SELECT * FROM ',tTable,' WHERE id = ',iID_data);
            SET @isExist := CONCAT('SELECT 1 INTO @DONE FROM ',tTable,' WHERE id = ',iID_data);
	 
	 END CASE ;

	END IF;

	PREPARE seles FROM @instruc;
	PREPARE ifDExist FROM @isExist;
	EXECUTE ifDExist;
	
    
	IF(iID_data IS NULL) THEN 
		EXECUTE seles;
    
    ELSEIF (@DONE) THEN
		EXECUTE seles;
        
	ELSE 
    
    	SELECT 'DATA NOT FOUND' ERROR;
    
    END IF ;
    
    SET @DONE:= 0;
	DEALLOCATE PREPARE seles;
    DEALLOCATE PREPARE ifDExist;
	
END//
DELIMITER ;


CALL selAll('game' , 1); --  RETURN ALL DATA FROM TABLE game id 1
CALL selAll('pokemon',null); -- RETURN ALL DATA OF pokemon TABLE 
/*CALL selAll(NULL,NULL); -- RETURN 'Table can't be null'*/
CALL selAll('generation',33); -- RETURN 'No data found'


/* -Procedure 7
This procedure grab a number of generation as an input, and then shows the objects of the generation and then 
add one to num_generation to show to the last, if the given input values are null or negative, will return an error
*/
DELIMITER //
CREATE OR REPLACE PROCEDURE objectGen(IN num_generation INTEGER)
BEGIN
	PREPARE maxgen FROM 'SELECT MAX(number) INTO @MAXGEN FROM generation';
    EXECUTE maxgen;
	
	IF (num_generation >= 0 AND num_generation <= @MAXGEN) THEN

		WHILE (num_generation <= @MAXGEN) DO
			-- EXECUTE obsel;
            SELECT ge.number AS GENERATION, ob.id , ob.name, ob.description FROM object ob JOIN generation_object go ON ob.id = go.id_object JOIN generation ge ON go.id_generation = ge.id WHERE ge.number = num_generation;
            SET num_generation = num_generation+1;
		END WHILE;
	ELSE 
		
        SELECT 'DATA IS NOT POSIBLE' ERROR;
    
	END IF;
	DEALLOCATE PREPARE  maxgen;
END //
DELIMITER ;

CALL objectGen(7); 
CALL objectGen(NULL);
CALL objectGen(-1);

/* -Procedure 8
The following procedure takes as input the name of type and show all from last insert id
*/
DELIMITER //
CREATE OR REPLACE PROCEDURE insselOnType(IN nameType VARCHAR(40))
BEGIN
SET @nameType := nameType;

PREPARE ints FROM 'INSERT INTO type (name) VALUE (?)';
PREPARE sel FROM 'SELECT * FROM type WHERE id_type = LAST_INSERT_ID()';

IF(nameType IS NOT NULL) THEN
	EXECUTE ints USING @nameType;
	EXECUTE sel;
ELSE
	SELECT 'Can\'t be NULL' ERROR;
END IF;
DEALLOCATE PREPARE  ints;
DEALLOCATE PREPARE  sel;

END //
DELIMITER ;

CALL insselOnType(NUll);
CALL insselOnType('DemiGod');
CALl insselOnType('Dendro');

/* -Procedure 9
The following procedure adds an object into the object table
*/
DROP PROCEDURE IF EXISTS afegir_objecte ;

DELIMITER //
CREATE PROCEDURE afegir_objecte(IN name VARCHAR(20), IN description VARCHAR(50))
BEGIN
    DECLARE type_name VARCHAR(20);
    IF description LIKE '%cura%' THEN
        SET type_name = 'healing-class-1A';
    ELSE
        SET type_name = 'generic-class-1A';
    END IF;

    INSERT INTO object (name, description) VALUES (name, description);
END //
DELIMITER ;

CALL afegir_objecte('Galleta lava', 'Cura cualquier problema de estado');
CALL afegir_objecte('Pokocho', 'Alimento famoso por su gran sabor');
CALL afegir_objecte('Piedra fuego', 'Piedra que hace evolucionar a algunos pokemons');

SELECT * FROM object;


/* -Procedure 10
The following procedure task is in charge of counting the quantity of pokemons that belong to each generation, 
returning as result all the generation numbers with the corresponding number of pokemons that belongs to that generation
*/
DROP PROCEDURE IF EXISTS contar;

DELIMITER //
CREATE PROCEDURE contar()
BEGIN

    DECLARE end INTEGER DEFAULT 0;
    DECLARE gen_id TINYINT(2);
	DECLARE data CURSOR FOR SELECT id FROM generation;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET end = 1;
    OPEN data;
    
    DROP TEMPORARY TABLE IF EXISTS temp_table;
    CREATE TEMPORARY TABLE temp_table(
		generation_id INTEGER,
        pokemon_quanity INTEGER
    );
    
    _loop : LOOP
		FETCH data into gen_id;
        
		IF (end = 1) THEN
			CLOSE data;
				
			SELECT DISTINCT * FROM temp_table ORDER BY generation_id asc;
            DROP TABLE temp_table;
            
			LEAVE _loop;
		ELSE
			
            SET @count := (SELECT COUNT(*) AS quantity FROM pokemon AS pok WHERE pok.generation_id = gen_id);
            SET @gen_numb := (SELECT number FROM generation AS gen WHERE gen.id = gen_id);
           INSERT INTO temp_table(generation_id, pokemon_quanity) VALUES (@gen_numb,@count);
            
        END IF;
    
    END LOOP _loop;
    
END //
DELIMITER ;

CALL contar();
