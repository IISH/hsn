# HSN controle Docker setup with the CLI

Run the Docker container with:

- A mapped volume on `/temp`: this is where all I/O takes place
- A `persistence.xml` file with the database connection configuration mapped on `/app/META-INF/persistence.xml`

`personalCardsConversion <workItem>`
Check and standardize the personal cards. Load the personal cards from the database with the given `workItem` or place
the DBF files (or ACCDB file) in the mapped volume.

`personalCardsToIDS`
Convert the standardized personal cards to IDS.

`civilCertsConversion <workItem>`
Check and standardize the civil certificates. Load the civil certificates from the database with the given `workItem` or
place the DBF files in the mapped volume.

`civilCertsToIDS`
Convert the standardized civil certificates to IDS.

`popRegTestErrors <workItem>`
Check and standardize the population registries. Load the population registries from the database with the
given `workItem` or place the DBF files in the mapped volume.

`popRegToIDS`
Convert the standardized population registries to IDS.

`popRegDeleteFromDefDB <fileName>`
Delete the entries from the population registries from the definitive database using the given DBF file with the
name `fileName`.

`popRegAppendToDefDB`
Move the checked population registries from the temporary database to the definitive database.

`popRegTestIDnrDoubles`
Test for duplicates between the population registries in the temporary and the definitive database.

`popRegReplaceDefWithTemp`
Replace population registries from the definitive database with the same population registries from the temporary
database.

`buildNewHSN <version>`
Integrate all IDS persons to a new IDS with the given `version`.

`initialiseHSN <version>`
Initialize the HSN IDS with the given `version` with context data.

`addLinksData`
Combine the HSN IDS with the LINKS IDS.

`miscPrintHSNPopRegAll`
Print all population registries in the temporary database. Places a Word file in the mapped volume.

`miscPrintHSNPopRegErrors`
Print population registries with validation problems in the temporary database. Places a Word file in the mapped volume.

`miscPrintHSNPopRegSelected <errorLevel>`
Print population registries with the given error level in the temporary database. Places a Word file in the mapped
volume.

Error levels:

- 3 = Printing HSN files Population Registers Controle A melding 1000-serie Ronde 2 finished.
- 4 = Printing HSN files Population Registers Controle B melding 1000-serie Ronde 2 finished.
- 5 = Printing HSN files Population Registers Controle A/B melding 1000-serie Ronde 3 finished.
- 6 = Printing HSN files Population Registers Controle A melding 4000-serie Ronde 4 finished.
- 7 = Printing HSN files Population Registers Controle B melding 4000-serie Ronde 4 finished.
- 8 = Printing HSN files Population Registers Controle A/B melding 4000-serie Ronde 5 finished.
