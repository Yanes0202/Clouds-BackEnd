Projekt "Clouds", aplikacja przypominająca Facebooka. Aplikacja z logowaniem/rejestrowaniem konta, komunikatorem, tworzeniem postów/relacji.

I. Done

Front-end:
- strona logowania/rejestrowania konta
- główna strona z postami
- formularz tworzący posty
- guzik wylogowywujący

Back-end:
- łączenie się z bazą
- logowanie i przypisanie stringa jako token
- poprawione logowanie które sprawdza w bazie czy istnieje taki login jeśli tak error massage
- towrzenie posta (db)

II. To Do
Front-end:
- prawy panel ze znajomymi
- "messenger"
- panel użytkownika
- dodanie ikonki
- przyjęcie danych użytkownika z backendu

Back-end:
- logowanie wielu osób
- bearer
- utworzyć endpoint zwracający dane użytkowinka

PostgreSQL:
- baza danych z wiadomościami
- polepszyć bazę danych użytkowników, więcej danych itd.



Aplikację trzeba uruchomić w przeglądarce bez zabezpieczeń. Nie będzie działać komunikacja z back-endem, front-endem z powodu seciuryty przeglądarki.
Aby otworzyć przeglądarkę bez zabezpieczenia należy:

1. Open the start menu

2. Type windows+R or open "Run"

3. Execute the following command:

 chrome.exe --user-data-dir="C://Chrome dev session" --disable-web-security
