# multibarcodereader

Projekto tikslas nuskaityti vaizdą su vienu arba keliais barkodais, gautus kodus surinkti į sąrašą, pasirinktus kodus perduoti REST PAI metodu į db.

Etapai

Sukurti vaizdo įkėlimo iš kameros arba galerijos langą.
Panaudojant Google ML kit barkodų skaitymo paketą nuskaityti kodus į sąršą
Sukurti SQLite db telefone
Saugoti nuskaitytus kodaus į db ( kodas, kodo tipas, vaido pavadimas ir vieta, kodo nuskaitymo data ir laikas)
Sukurti Web REST API duoemnų įkėlimui į mysqldb 
Exportuoti pasirinktus kodus į mysql db REST API būdu.
