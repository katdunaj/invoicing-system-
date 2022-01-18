package pl.futurecollars.katarzyna.dunaj.db

class InMemoryDatabaseTest extends DatabaseTest {

    @Override
    Database getDatabaseInstance() {

        return new InMemoryDatabase()
    }
}