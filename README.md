# System Zarządzania Studentami

## Opis projektu
System Zarządzania Studentami (SMS) to aplikacja napisana w języku Java, która umożliwia zarządzanie danymi studentów przy użyciu graficznego interfejsu użytkownika (GUI). Dane studentów są przechowywane w bazie danych SQLite, a system umożliwia podstawowe operacje CRUD (Create, Read, Update, Delete). 

## Funkcjonalności systemu
System wspiera następujące funkcje:
1. **Dodawanie studenta**: Możliwość wprowadzenia nowego studenta do bazy danych.
2. **Usuwanie studenta**: Usuwanie studenta na podstawie unikalnego identyfikatora (`studentID`).
3. **Aktualizacja danych studenta**: Edycja istniejących informacji o studencie.
4. **Wyświetlanie wszystkich studentów**: Wyświetlanie listy wszystkich studentów zapisanych w bazie.
5. **Obliczanie średniej ocen**: Obliczanie średniej oceny wszystkich studentów zapisanych w bazie.
6. **Walidacja danych wejściowych**: Sprawdzanie poprawności danych, takich jak wiek (18–30 lat) i oceny (2.0–5.0).

## Wymagania techniczne
- Java Development Kit (JDK) 8 lub nowszy.
- Sterownik SQLite JDBC (załączony w projekcie).
- Biblioteka `Gson` do obsługi danych w formacie JSON (załączona w projekcie).

## Instrukcja instalacji i uruchamiania

### Instalacja
1. Pobierz i zainstaluj JDK (Java Development Kit) w wersji 8 lub nowszej.
2. Skonfiguruj sterownik SQLite JDBC:
   - Pobierz go z katalogu 'dependencies' w repozytorium i dodaj do projektu jako bibliotekę.

3. Dodaj bibliotekę `Gson`:
   - Pobierz ką z katalogu 'dependencies' w repozytorium i dodaj do projektu jako bibliotekę.

### Kompilacja i uruchamianie
1. Skompiluj wszystkie pliki Java:
   javac -d out src/**/*.java
2. Uruchom aplikację:
   java -cp out Main
   lub uruchom plik Main.java przy pomocy dowolnego IDE

### Konfiguracja bazy danych
1. Baza danych SQLite jest zapisana w pliku students.db znajdującym się w katalogu src/db/.
2. Aplikacja automatycznie tworzy tabelę students, jeśli nie istnieje.
3. Tabela students zawiera następujące kolumny:
   - name (TEXT): Imię studenta.
   - age (INTEGER): Wiek studenta (18–30 lat).
   - grade (REAL): Ocena studenta (2.0–5.0).
   - studentID (TEXT): Unikalny identyfikator studenta (klucz główny).
4. Wypełnienie tabeli przykładowymi danymi
   Plik students.json zawiera przykładowe dane dla tabeli students. Podczas pierwszego uruchomienia dane te są automatycznie wczytywane do bazy.
